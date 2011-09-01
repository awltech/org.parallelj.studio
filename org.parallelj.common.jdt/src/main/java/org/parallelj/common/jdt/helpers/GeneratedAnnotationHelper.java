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
package org.parallelj.common.jdt.helpers;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.parallelj.common.jdt.Activator;

/**
 * Helper class to manage the hashcode computation, in Generated Annotations
 * (value used by the Java Code Merger).
 * 
 * @author mvanbesien/mhays
 * 
 */
@SuppressWarnings("unchecked")
public class GeneratedAnnotationHelper {

	/**
	 * Default hashCode value to overwrite
	 */
	private static final String VALUE_TO_OVERWRITE = "ToBeCalculated";

	/**
	 * Compiler options used inside this Java code Merger
	 */
	public static Map compilerOptions = null;

	static {
		// Set compiler options to 1.5 level
		GeneratedAnnotationHelper.compilerOptions = JavaCore.getOptions();
		GeneratedAnnotationHelper.compilerOptions
				.put("org.eclipse.jdt.core.compiler.source", "1.5");
	}

	/**
	 * Visitor which role is to detect methods from Compilation unit, where
	 * Generated annotation is missing
	 */
	private static class GeneratedAnnotationVisitor extends ASTVisitor {

		/**
		 * Methods to process list
		 */
		private List<NormalAnnotation> methodsToProcess = new ArrayList<NormalAnnotation>();

		/**
		 * Execute this visitor on Compilation Unit passed as parameter
		 * 
		 * @param cu
		 *            AST Compilation Unit
		 */
		public void process(CompilationUnit cu) {
			this.methodsToProcess.clear();
			cu.accept(this);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom
		 * .NormalAnnotation)
		 */
		@Override
		public boolean visit(NormalAnnotation node) {
			if (this.needToEnrichAnnotation(node))
				this.methodsToProcess.add(node);
			return super.visit(node);
		}

		/**
		 * Looks in declaration to find if the Generated annotation is present
		 * 
		 * @param methodDeclaration
		 *            MethodDeclaration
		 * @return true is annotation is present, false otherwise
		 */
		private boolean needToEnrichAnnotation(NormalAnnotation annotation) {
			MemberValuePair mvp = retrieveAnnotationMemberPairValue(annotation, "comments");
			if (mvp == null)
				return false;
			if (mvp.getValue() != null && mvp.getValue() instanceof StringLiteral) {
				StringLiteral literal = (StringLiteral) mvp.getValue();
				return VALUE_TO_OVERWRITE.equals(literal.getLiteralValue());
			} else
				return false;

		}

		/**
		 * @return List of method that should be processed by post process (i.e.
		 *         without Generated annotation)
		 */
		public List<NormalAnnotation> getAnnotationsToEnrich() {
			return this.methodsToProcess;
		}

	}

	/**
	 * Retrieves all member value pair of annotation (passed as parameter, which
	 * name fits with the string passed as parameter
	 * 
	 * @param annotation
	 *            : Annotation
	 * @param mpvName
	 *            : name to filter
	 * @return Matching MemberValuePair
	 */
	private static MemberValuePair retrieveAnnotationMemberPairValue(NormalAnnotation annotation,
			String mpvName) {
		for (Object o : annotation.values()) {
			if (o instanceof MemberValuePair) {
				MemberValuePair mvp = (MemberValuePair) o;
				String name = mvp.getName().toString();
				if (mpvName.equals(name))
					return mvp;
			}
		}
		return null;
	}

	/**
	 *Stores source code passed as parameter, in file, which path is passed as
	 * parameter.
	 * 
	 * @param source
	 *            : Source code
	 * @param path
	 *            : destination file path
	 */
	public static void writeSourceToFile(String source, String path) {
		IPath cuLocation = new Path(path);
		ICompilationUnit javaCU = null;

		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		IProject myProject = null;
		for (int i = 0; i < projects.length && myProject == null; i++) {
			if (projects[i].getLocation().isPrefixOf(cuLocation)) {
				myProject = projects[i];
			}
		}
		if (myProject != null) {
			IPath relativePath = cuLocation.removeFirstSegments(myProject.getLocation()
					.segmentCount());
			IFile file = myProject.getFile(relativePath);
			if (!file.exists())
				try {
					file.getParent().refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,
							"Exception thrown while refreshing package file parent of "
									+ cuLocation.toString(), e));
				}

			javaCU = (ICompilationUnit) JavaCore.create(file);
		}

		if (javaCU == null || !javaCU.exists()) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,
					"Java Compilation Unit couldn't be found from File at path: "
							+ cuLocation.toString(), new FileNotFoundException()));
			return;
		}
		try {
			// Writes file on disk
			javaCU.getBuffer().setContents(source);
			javaCU.getBuffer().save(null, true);
		} catch (Exception e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,
					"Error when writing file on disk " + javaCU, e));
		}
	}

	/**
	 * Updates the source code passed as parameter (updates the annotation
	 * value), and stores it in file, according to path passed as parameter.
	 * 
	 * @param source
	 *            : Source code
	 * @param path
	 *            : destination file path
	 */
	public static void updateAndWriteHashcodeInSource(String source, String path) {
		String updatedSource = updateHashcodeInSource(source);
		if (!source.equals(updatedSource)) {
			writeSourceToFile(updatedSource, path);
		}
	}

	/**
	 * Updates the source passed as parameter, by populating the hashCode
	 * values.
	 * 
	 * @param source
	 *            : Source
	 * @return updated Source
	 */
	public static String updateHashcodeInSource(String source) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(source.toCharArray());
		parser.setCompilerOptions(GeneratedAnnotationHelper.compilerOptions);
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		// Create and call the new Visitor, which role will be to
		// gather the methods on which the Generated annotation is yet not
		// defined.
		GeneratedAnnotationVisitor ev = new GeneratedAnnotationVisitor();
		ev.process(cu);

		// Get the annotations to modify, fetched by the Visitor
		List<NormalAnnotation> annotationsToEnrich = ev.getAnnotationsToEnrich();
		if (annotationsToEnrich.size() > 0) {
			AST ast = cu.getAST();
			cu.recordModifications();
			boolean modified = false;
			for (NormalAnnotation annotationToEnrich : annotationsToEnrich) {
				// For each annotation to modify, replace the null value of
				// the comments field by the method's hashcode
				ASTNode parent = annotationToEnrich.getParent();
				if (parent instanceof MethodDeclaration) {
					MethodDeclaration methodDeclaration = (MethodDeclaration) parent;
					if (methodDeclaration.getBody() != null) {
						MemberValuePair memberValuePair = retrieveAnnotationMemberPairValue(
								annotationToEnrich, "comments");
						if (memberValuePair != null) {
							int hashCode = ASTHelper.methodBodyHashCode(methodDeclaration.getBody()
									.toString());
							StringLiteral literal = ast.newStringLiteral();
							literal.setLiteralValue(String.valueOf(hashCode));
							memberValuePair.setValue(literal);
							modified = true;
						}
					}
				}

			}
			if (modified) {
				// Writes file on disk
				IDocument document = new Document(source);
				try {
					cu.rewrite(document, null).apply(document);
					return document.get();
				} catch (MalformedTreeException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,
							"The compilation unit could not be"
									+ " written on FileSystem because of thrown Exception.", e));
				} catch (BadLocationException e) {
					Activator.getDefault().getLog().log(new Status(Status.ERROR,Activator.PLUGIN_ID,
							"The compilation unit could not be"
									+ " written on FileSystem because of thrown Exception", e));
				}
			}
		}
		return source;
	}
}
