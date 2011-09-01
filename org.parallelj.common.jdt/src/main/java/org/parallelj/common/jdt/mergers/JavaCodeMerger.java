/*
 *     ParallelJ, framework for parallel computing
 *     
 *     Copyright (C) 2010 Atos Worldline or third-party contributors as
 *     indicated by the @author tags or express copyright attribution
 *     statements applied by the authors.
 *     
 *     This library is free software; you can redistribute it and/or
 *     modify it under the terms of the GNU Lesser General Public
 *     License as published by the Free Software Foundation; either
 *     version 2.1 of the License.
 *     
 *     This library is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *     Lesser General Public License for more details.
 *     
 *     You should have received a copy of the GNU Lesser General Public
 *     License along with this library; if not, write to the Free Software
 *     Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package org.parallelj.common.jdt.mergers;

import static org.eclipse.jdt.core.dom.CompilationUnit.IMPORTS_PROPERTY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getMainType;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getTypeDeclaration;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getTypeName;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getType;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getName;
import static org.parallelj.common.jdt.mergers.MergerLogger.getDescription;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.Document;
import org.eclipse.text.edits.MalformedTreeException;

/**
 * Merge java sources.
 * 
 * @author Atos Worldline
 */
public class JavaCodeMerger {

	/**
	 * Generated annotation class name.
	 */
	public static final String GENERATED_CLASSNAME = javax.annotation.Generated.class.getName();

	/**
	 * Generated annotation simple class name.
	 */
	public static final String GENERATED_SIMPLECLASSNAME = javax.annotation.Generated.class
			.getSimpleName();

	/**
	 * Constant used for project prefix
	 */
	public static final String PROJECT_PREFIX = "//J";

	/**
	 * Merger logger
	 */
	private MergerLogger log = null;

	/**
	 * Compiler options used inside this Java code Merger
	 */
	public static Map compilerOptions = null;

	static {
		// Set compiler options to 1.5 level
		compilerOptions = JavaCore.getOptions();
		compilerOptions.put("org.eclipse.jdt.core.compiler.source", "1.5");
	}

	/**
	 * This set contains the list of predefined annotations. This set is used to
	 * perform specific treatments on each annotation (e.g. Remove an annotation
	 * when it's needed)
	 */
	private Set<String> preDefinedAnnotations = new HashSet<String>(5);

	/**
	 * Empty constructor
	 */
	public JavaCodeMerger() {
	}

	/**
	 * Constructor with a special annotations set as single argument.
	 */
	public JavaCodeMerger(Set<String> pda) {
		if (pda != null) {
			this.preDefinedAnnotations = pda;
		}
	}

	/**
	 * Merge 2 Java contents and return the result of the merge operation as
	 * String
	 * 
	 * @param existingContent
	 *            The initial content
	 * @param generatedContent
	 *            Result of a generation process
	 * @return The merge operation result as String
	 * @throws JavaModelException
	 *             If an error occurs during merge operation
	 */
	public String merge(String existingContent, String generatedContent) throws JavaModelException {
		if (existingContent == null || existingContent.length() == 0) {
			return generatedContent;
		}

		if (generatedContent == null || generatedContent.length() == 0) {
			return existingContent;
		}

		// Create a Document object for the existing content
		Document existingDocument = new Document(existingContent);

		// Create an AST parser for the existing content (use JLS3 to support
		// JDK 1.5)
		ASTParser existingContentParser = ASTParser.newParser(AST.JLS3);
		existingContentParser.setSource(existingDocument.get().toCharArray());
		existingContentParser.setCompilerOptions(compilerOptions);
		CompilationUnit targetCU = (CompilationUnit) existingContentParser.createAST(null);

		// Create an ASTRewrite object used to update the existing content
		ASTRewrite resultRewriter = ASTRewrite.create(targetCU.getAST());

		// Create a Document object for generated content
		Document generatedDocument = new Document(generatedContent);

		// Create an AST parser for generated content (use JSL3 to support JDK
		// 1.5)
		ASTParser generatedContentParser = ASTParser.newParser(AST.JLS3);
		generatedContentParser.setSource(generatedDocument.get().toCharArray());
		generatedContentParser.setCompilerOptions(compilerOptions);
		CompilationUnit generatedContentCU = (CompilationUnit) generatedContentParser
				.createAST(null);

		try {
			String pack = "";
			if(targetCU.getPackage() != null)
				pack = targetCU.getPackage().getName().getFullyQualifiedName();
			/* Create a merger logger instance */
			this.log = new MergerLogger(pack,
					getMainType(targetCU).getName().getFullyQualifiedName());

			/* Build a method renamer instance */
			MethodRenamer mr = new MethodRenamer(targetCU, generatedContentCU);

			/* If methods number is greater than 0, proceed renaming */
			if (mr.methodsNumberToBeRenamed() > 0) {
				ASTRewrite generatedRewriter = ASTRewrite.create(generatedContentCU.getAST());
				mr.proceed(resultRewriter, generatedRewriter);

				// Execute merge operations on existing content
				try {
					generatedRewriter.rewriteAST(generatedDocument, null).apply(generatedDocument);
				} catch (MalformedTreeException mte) {
					throw new JavaModelException(mte, 0);
				} catch (org.eclipse.jface.text.BadLocationException ble) {
					throw new JavaModelException(ble, 0);
				}

				/* Recreate a generatedContentCU after a renaming process */
				generatedContentParser.setSource(generatedDocument.get().toCharArray());
				generatedContentParser.setCompilerOptions(compilerOptions);
				generatedContentCU = (CompilationUnit) generatedContentParser.createAST(null);
			}

			// Merge imports
			mergeImports(targetCU, generatedContentCU, resultRewriter);

			// For each type defined in the generated source...
			for (AbstractTypeDeclaration generatedType : (List<AbstractTypeDeclaration>) generatedContentCU
					.types()) {

				/*
				 * ... call the merge process
				 */
				this.mergeTwoFragments(targetCU, getTypeDeclaration(targetCU, generatedType
						.getName().getFullyQualifiedName()), generatedType, resultRewriter);
			}

			// For each type defined in the existing source...
			for (AbstractTypeDeclaration td : (List<AbstractTypeDeclaration>) targetCU.types()) {
				/*
				 * ... call the merge process only if the type in the generated
				 * type doesn't exist
				 */

				/* The generatedType is a class or an interface */
				if (getType(generatedContentCU, getTypeName(td)) == null) {
					this.mergeTwoFragments(targetCU, td, null, resultRewriter);
				}
			}

			// Execute merge operations on existing content
			try {
				resultRewriter.rewriteAST(existingDocument, null).apply(existingDocument);
			} catch (MalformedTreeException mte) {
				throw new JavaModelException(mte, 0);
			} catch (org.eclipse.jface.text.BadLocationException ble) {
				throw new JavaModelException(ble, 0);
			}

			// Return result of the merging process as String object
			return existingDocument.get();
		} finally {
			if (this.log != null) {
				// Close logger
				this.log.close();
			}
		}
	}

	/**
	 * Merge a CompilationUnit in packageName from this name with generated
	 * content.
	 * 
	 * @param compilationUnitName
	 *            The name of the CompilationUnit without java extension
	 * @param newContent
	 *            The content to store in this CompilationUnit
	 * @param packageName
	 *            The source package where this CompilationUnit must be store
	 * @return The merge operation result as String
	 * @throws JavaModelException
	 *             If an error occurs during merge operation
	 */
	public String merge(String compilationUnitName, String newContent, IPackageFragment packageName)
			throws JavaModelException {

		// Create ICompilationUnit object associated with initial source
		ICompilationUnit initialIcp = packageName.getCompilationUnit(compilationUnitName + "."
				+ "java");

		if (initialIcp.exists()) {
			// Merge initial source and generated source
			return merge(initialIcp.getSource(), newContent);
		}

		return newContent;
	}

	/**
	 * Merge imports
	 */
	private void mergeImports(CompilationUnit initialContentCU, CompilationUnit generatedContentCU,
			ASTRewrite resultRewriter) {
		// One of these three parameters is null, cannot process imports.
		if (initialContentCU == null || generatedContentCU == null || resultRewriter == null) {
			return;
		}

		List<String> initialImportsName = null;

		// Retrieve the list of imports from initial source.
		List initialImports = initialContentCU.imports();

		// Create an imports list in String format.
		if (initialImports != null) {
			initialImportsName = new ArrayList<String>(5);

			for (Object o : initialImports) {
				ImportDeclaration id = (ImportDeclaration) o;

				initialImportsName.add(id.getName().getFullyQualifiedName());
			}
		}

		/*
		 * Get a ListRewrite object used to enhance existing imports with new
		 * generated imports
		 */
		ListRewrite lw = resultRewriter.getListRewrite(initialContentCU, IMPORTS_PROPERTY);

		// For each import of the generated source
		for (Object o : generatedContentCU.imports()) {
			ImportDeclaration id = (ImportDeclaration) o;

			if (!initialImportsName.contains(id.getName().getFullyQualifiedName())) {
				// This import is containing in the generated code but not in
				// the existing code => Add this import in the merge result.

				this.log.write("Add import : " + id.getName().getFullyQualifiedName());

				lw.insertLast(id, null);
			}
		}
	}

	/**
	 * Perform two fragments merging. To do this, take an {@code
	 * existingFragment}, take a {@code generatedFragment} and enhance {@code
	 * astr} ASTRewrite instance. If a fragment must be inserted, the complete
	 * fragment is inserted. If a fragment must be updated, only the generated
	 * fragment is updated (for exemple, with a type declaration fragment, only
	 * the declaration is updated, not fields and methods). If a fragment must
	 * be deleted, the complete fragment is deleted.
	 * 
	 * @param parent
	 *            The fragment parent
	 * @param existingFragment
	 *            An existing fragment
	 * @param generatedFragment
	 *            A generated fragment
	 * @param astr
	 *            An ASTRewrite instance used to enhance the merge result
	 */
	void mergeTwoFragments(ASTNode parent, BodyDeclaration existingFragment,
			BodyDeclaration generatedFragment, ASTRewrite astr) {

		if (existingFragment == null && generatedFragment == null) {
			/*
			 * Case 1 : Nothing in existing code, nothing in generated code =>
			 * Nothing to do.
			 */
			return;
		}

		/* Get the right merger instance according to the type to merge */
		FragmentMerger fm = this.getMerger(existingFragment, generatedFragment);
		fm.setAstRewrite(astr);

		if (existingFragment == null) {
			// The element doesn't exist in existing code.

			if (this.isGenerated(generatedFragment)) {
				/*
				 * Case 2 : Nothing in existing code, element marked as
				 * generated in generated code => Copy generated element in
				 * existing code.
				 */

				this.log.write("Merge " + getName(generatedFragment) + " "
						+ getDescription(generatedFragment) + " : empty existing / "
						+ "@Generated generated");

				fm.insert(parent, generatedFragment);
			} else {
				/*
				 * Case 3 : Nothing in existing code, element marked as not
				 * generated in generated code => Copy not generated element in
				 * existing code.
				 */

				this.log.write("Merge " + getName(generatedFragment) + " "
						+ getDescription(generatedFragment) + " : empty existing / "
						+ "@NotGenerated generated");

				fm.insert(parent, generatedFragment);
			}

			/*
			 * These two previous steps perform the same operation. But to
			 * understand the complete merging process, if and else has been
			 * kept.
			 */
		}

		if (generatedFragment == null) {
			// The element doesn't exist in generated code

			if (this.isGenerated(existingFragment)) {
				/*
				 * Case 4 : Element marked as generated in existing code,
				 * nothing in generated code => Remove element from existing
				 * code.
				 */

				this.log.write("Merge " + getName(existingFragment) + " "
						+ getDescription(existingFragment) + " : @Generated existing / "
						+ "empty generated");

				fm.remove(existingFragment);
			} else {
				/*
				 * Case 5 : Element marked as not generated in existing code,
				 * nothing in generated code => Nothing to do.
				 */

				this.log.write("Merge " + getName(existingFragment) + " "
						+ getDescription(existingFragment) + " : @NotGenerated existing / "
						+ "empty generated");

				this.log.write("\tNothing to do");
			}
		}

		// The element exist in existing code and generated code.
		if (existingFragment != null && generatedFragment != null) {
			if (this.isGenerated(existingFragment)) {
				// The exisiting element is marked as generated.

				if (this.isGenerated(generatedFragment)) {
					/*
					 * Case 6 : Element marked as generated in existing code,
					 * element marked as generated in generated code => Merge
					 * existing element with generated element.
					 */

					this.log.write("Merge " + getName(existingFragment) + " "
							+ getDescription(existingFragment) + " : @Generated existing / "
							+ "@Generated generated");

					fm.merge(existingFragment, generatedFragment, this.preDefinedAnnotations);
				} else {
					/*
					 * Case 7 : Element marked as generated in existing code,
					 * element marked as not generated in generated code =>
					 * Nothing to do. Remark : Very special case.
					 */

					this.log.write("Merge " + getName(existingFragment) + " "
							+ getDescription(existingFragment) + " : @Generated existing / "
							+ "@NotGenerated generated");

					this.log.write("\tNothing to do");
				}
			} else {
				// The exisiting element is marked as not generated.

				if (this.isGenerated(generatedFragment)) {
					/*
					 * Case 8 : Element marked as not generated in existing
					 * code, element marked as generated in generated code =>
					 * Nothing to do.
					 */

					this.log.write("Merge " + getName(existingFragment) + " "
							+ getDescription(existingFragment)
							+ " : @Generated existing / @Generated generated");

					this.log.write("\tNothing to do");
				} else {
					/*
					 * Case 9 : Element marked as not generated in existing
					 * code, element marked as not generated in generated code
					 * => Nothing to do.
					 */

					this.log.write("Merge " + getName(existingFragment) + " "
							+ getDescription(existingFragment)
							+ " : @NotGenerated existing / @NotGenerated generated");

					this.log.write("\tNothing to do");
				}
			}

			// Javadoc is merged
			this.mergeJavadoc(existingFragment, generatedFragment, astr);
		}

		/* Merge sub fragments if needed */
		fm.mergeSubFragments(existingFragment, generatedFragment);
	}

	/**
	 * Merge javadoc comment from existingFragment and generatedFragment to an
	 * AST Rewrite object instance. This method is call only when {@code
	 * existingFragment} is marked as generated and {@code generatedFragment}
	 * exist.
	 * 
	 * @param existingFragment
	 *            The existing fragment
	 * @param generatedFragment
	 *            The generated fragment
	 * @param astr
	 *            The ASTRewrite object instance containing the merge result
	 */
	protected void mergeJavadoc(BodyDeclaration existingFragment,
			BodyDeclaration generatedFragment, ASTRewrite astr) {
		boolean existingCommentIsGenerated = false;
		boolean generatedCommentIsGenerated = false;
		boolean existingCommentFound = true;
		boolean generatedCommentFound = true;

		try {
			existingCommentIsGenerated = isJavadocCommentGenerated(existingFragment.getJavadoc());
		} catch (IllegalArgumentException iae) {
			existingCommentFound = false;
		}

		try {
			generatedCommentIsGenerated = isJavadocCommentGenerated(generatedFragment.getJavadoc());
		} catch (IllegalArgumentException iae) {
			generatedCommentFound = false;
		}

		if (existingCommentIsGenerated) {
			if (!generatedCommentFound) {
				this.log.write("\tRemove javadoc from " + getName(existingFragment) + " "
						+ getDescription(existingFragment));

				astr.remove((ASTNode) existingFragment.getJavadoc(), null);
			} else if (generatedCommentIsGenerated) {
				this.log.write("\tReplace Javadoc comment from " + getName(existingFragment)
						+ "to " + getName(generatedFragment));

				astr.replace(existingFragment.getJavadoc(), generatedFragment.getJavadoc(), null);
			}
		} else if (!existingCommentFound && generatedCommentIsGenerated) {
			this.log.write("\tAdd Javadoc comment in " + getName(existingFragment));
			astr.set(existingFragment, existingFragment.getJavadocProperty(), generatedFragment
					.getJavadoc(), null);
		}
	}

	/**
	 * Return the good merge instance according to the type of the fragment to
	 * merge.
	 * 
	 * @param existing
	 *            The first fragment to merge. This parameter is used to know
	 *            wich type must be merged.
	 * @param generated
	 *            The second fragment to merge. This parameter is used only if
	 *            existing is null.
	 * 
	 */
	protected FragmentMerger getMerger(BodyDeclaration existing, BodyDeclaration generated) {
		BodyDeclaration bd = (existing == null) ? generated : existing;

		/*
		 * Check the bd type and return the right merger instance according to
		 * this type.
		 */
		if (bd instanceof FieldDeclaration) {
			// It's a field, return a FieldMerger
			return new FieldDeclarationMerger(this.log);
		} else if (bd instanceof MethodDeclaration) {
			// It's a method, return a MethodMerger
			return new MethodDeclarationMerger(this.log);
		} else if (bd instanceof TypeDeclaration) {
			// It's a type, return a TypeChecker taken this as parameter
			return new TypeMerger(this, this.log);
		} else if (bd instanceof EnumDeclaration) {
			// It's an enumeration, return a EnumMerger taken this as parameter
			return new EnumMerger(this, this.log);
		} else if (bd instanceof EnumConstantDeclaration) {
			// It's an enumeration, return a EnumMerger taken this as parameter
			return new EnumConstantMerger(this.log);
		}

		return null;
	}

	/**
	 * Return true if the BodyDeclaration object has been generated. Generated
	 * informations is annotated with.
	 * 
	 * @param bd
	 *            BodyDeclaration used as input test
	 * @return true if the BodyDeclaration object has been generated, false
	 *         otherwise
	 */
	protected boolean isGenerated(BodyDeclaration bd) {
		boolean isGenerated = false;
		List modifiers = bd.modifiers();

		// Test if this BodyDeclaration contains modifiers
		if (modifiers != null) {
			Iterator modifiersIterator = bd.modifiers().iterator();

			// For each modifier, search for @Generated(<GENERATOR_NAME>) marker
			// annotation
			while ((!isGenerated) && modifiersIterator.hasNext()) {
				IExtendedModifier modifier = (IExtendedModifier) modifiersIterator.next();

				if (modifier.isAnnotation()) {

					Annotation a = (Annotation) modifier;
					String annotationType = a.getTypeName().toString();

					if (annotationType.equals(GENERATED_CLASSNAME)
							|| annotationType.equals(GENERATED_SIMPLECLASSNAME)) {
						if (a.isSingleMemberAnnotation()) {
							isGenerated = ((SingleMemberAnnotation) a).getValue().toString()
									.contains(PROJECT_PREFIX);
						} else if (((Annotation) modifier).isNormalAnnotation()) {
							NormalAnnotation na = (NormalAnnotation) a;

							List<MemberValuePair> values = na.values();

							for (int inc = 0; inc < values.size() && !isGenerated; inc++) {
								MemberValuePair mvp = values.get(inc);

								if (mvp != null && mvp.getValue() != null) {
									isGenerated = mvp.getValue().toString()
											.contains(PROJECT_PREFIX);
								}
							}
						}
					}
				}
			}
		}

		return isGenerated;
	}

	/**
	 * Return if a javadoc comment is generated or not
	 */
	private static boolean isJavadocCommentGenerated(Javadoc jd) {
		if (jd == null) {
			throw new IllegalArgumentException();
		}

		boolean found = false;
		List<TagElement> tags = jd.tags();

		if (tags != null) {
			TagElement te = null;

			for (int inc = 0; inc < tags.size() && !found; inc++) {
				te = tags.get(inc);

				if ("@generated".equals(te.getTagName())) {
					List<ASTNode> fragments = te.fragments();

					if (fragments != null && fragments.size() == 1) {
						if (fragments.get(0) instanceof TextElement) {
							String commentText = ((TextElement) fragments.get(0)).getText().trim();
							found = commentText != null
									&& commentText.startsWith(JavaCodeMerger.PROJECT_PREFIX);
						}
					}
				}
			}
		} else {
			return false;
		}

		return found;
	}
}
