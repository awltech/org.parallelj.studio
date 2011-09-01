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
package org.parallelj.common.jdt.checkers;

import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getImport;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getMainType;
import static org.parallelj.common.jdt.helpers.JavaCodeHelper.getType;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * Defines several methods used to perform Java code checking
 * 
 * @author Atos Worldline
 */
public class JavaCodeChecker {

	/**
	 * Default folder used to generate sources
	 */
	protected static final String DEFAULT_FOLDER = "src/main/java";

	private CompilationUnit cu = null;

	/**
	 * Create a new JavaCodeChecker using a source code as String.
	 * 
	 * @param javaCode
	 *            Source code as String
	 * 
	 */
	public JavaCodeChecker(String javaCode) {
		Map options = JavaCore.getOptions();
		options.put("org.eclipse.jdt.core.compiler.source", "1.5");
		ASTParser generatedContentParser = ASTParser.newParser(AST.JLS3);
		generatedContentParser.setSource(javaCode.toCharArray());
		generatedContentParser.setCompilerOptions(options);
		this.cu = (CompilationUnit) generatedContentParser.createAST(null);
	}

	/**
	 * Create a new JavaCodeChecker using a project path, a generation folder
	 * path, a package name and a class name.
	 * 
	 * @param projectPath
	 *            Absolute project path. If null, basedir system property is
	 *            used
	 * @param generationFolderPath
	 *            Generation folder used in this project. If null,
	 *            {@code DEFAULT_FOLDER} is used
	 * @param packageName
	 *            Package name of the checked class
	 * @param className
	 *            Name of the checked class
	 * @return A JavaCodeChecker instance
	 */
	public JavaCodeChecker(String projectPath, String generationFolderPath, String packageName,
			String className) throws IOException {
		RandomAccessFile raf = null;

		if (projectPath == null) {
			projectPath = System.getProperty("basedir").replace('\\', '/');
		}

		if (generationFolderPath == null) {
			generationFolderPath = DEFAULT_FOLDER;
		}

		Map options = JavaCore.getOptions();
		options.put("org.eclipse.jdt.core.compiler.source", "1.6");

		try {
			// Build an absolute path to the tested class and create a
			// RandomAccessFile to read the content of this class
			raf = new RandomAccessFile(projectPath + java.io.File.separator + generationFolderPath
					+ java.io.File.separator + packageName.replace('.', java.io.File.separatorChar)
					+ java.io.File.separator + className + ".java", "r");
			byte[] content = new byte[(int) raf.length()];
			raf.readFully(content);
			String javaCode = new String(content);

			// Use an AST parser to parse the String content of the tested class
			// and build Compilation Unit and Type Declaration objects
			ASTParser generatedContentParser = ASTParser.newParser(AST.JLS3);
			generatedContentParser.setSource(javaCode.toCharArray());
			generatedContentParser.setCompilerOptions(options);
			this.cu = (CompilationUnit) generatedContentParser.createAST(null);
		} finally {
			if (raf != null) {
				raf.close();
			}
		}
	}

	/**
	 * Create a new JavaCodeChecker using a package name and a class name. This
	 * method used DEFAULT_FOLDER as default generation folder and system
	 * property basedir as project absolute path.
	 * 
	 * @param packageName
	 *            Package name of the checked class
	 * @param className
	 *            Name of the checked class
	 * @return A JavaCodeChecker instance
	 */
	private JavaCodeChecker(String packageName, String className) throws IOException {
		this(null, null, packageName, className);
	}

	/* ---------------------------------------------------------------------------------------- */
	/* -----------------------------BEGIN_COMPILATION_UNIT_TESTS------------------------------- */
	/* ---------------------------------------------------------------------------------------- */

	/**
	 * Return the errors number of this compilation unit
	 * 
	 * @return the errors number of this compilation unit
	 */
	public int errorsNumber() {
		return this.cu.getProblems().length;
	}

	/**
	 * Return true if this compilation unit contains this import
	 * 
	 * @param importName
	 *            The name of the import
	 * @throws IllegalStateException
	 *             If this method is call in a wrong context (Inner type,...)
	 */
	public boolean containsImport(String importName) {
		return ((getImport(this.cu, importName)) != null);
	}

	/**
	 * Return imports number
	 * 
	 * @throws IllegalStateException
	 *             If this method is call in a wrong context (Inner type,...)
	 */
	public int importsNumber() {
		List imports = this.cu.imports();

		return (imports != null) ? imports.size() : 0;
	}

	/**
	 * Return true if this compilation unit is in the expected package
	 * {@code packageName}.
	 * 
	 * @param packageName
	 *            Expected package name
	 * @return true if this compilation unit is in the expected package
	 *         {@code packageName}, false otherwise
	 */
	public boolean isInPackage(String packageName) {
		PackageDeclaration pd = this.cu.getPackage();

		return (pd != null) ? pd.getName().getFullyQualifiedName().equalsIgnoreCase(packageName)
				: false;
	}

	/**
	 * Return an AbstractTypeChecker instance if this compilation unit contains
	 * the type named {@code typeName}.
	 * 
	 * @param typeName
	 *            Expected type name
	 * @return an AbstractTypeChecker instance if this compilation unit contains
	 *         the type named {@code typeName}
	 * @throws IllegalArgumentException
	 *             If type named {@code typeName} cannot be found
	 */
	public AbstractTypeChecker getTypeChecker(String typeName) {
		AbstractTypeDeclaration atd = getType(this.cu, typeName);

		if (atd instanceof TypeDeclaration) {
			return new TypeChecker((TypeDeclaration) atd);
		} else if (atd instanceof EnumDeclaration) {
			return new EnumTypeChecker((EnumDeclaration) atd);
		} else if (atd instanceof AnnotationTypeDeclaration) {
			// return new Anno((TypeDeclaration) atd);
		}

		return null;

	}

	/**
	 * Return a type checker from a package name and a class name.
	 * 
	 * @param packageName
	 *            Package name
	 * @param className
	 *            Class name
	 * @return A Type Checker instance associated with type {@code className},
	 *         null if an error occurs
	 */
	public static AbstractTypeChecker getTypeChecker(String packageName, String className) {
		try {
			return new JavaCodeChecker(packageName, className).getMainTypeChecker();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Return an AbstractTypeChecker instance if this compilation unit contains
	 * the public type.
	 * 
	 * @return an AbstractTypeChecker instance
	 */
	public AbstractTypeChecker getMainTypeChecker() {
		AbstractTypeDeclaration atd = getMainType(this.cu);

		if (atd instanceof TypeDeclaration) {
			return new TypeChecker((TypeDeclaration) atd);
		} else if (atd instanceof EnumDeclaration) {
			return new EnumTypeChecker((EnumDeclaration) atd);
		} else if (atd instanceof AnnotationTypeDeclaration) {
			// return new Anno((TypeDeclaration) atd);
		}

		return null;
	}

	/**
	 * Return true if this java code contains the type with {@code typeName} as
	 * name
	 * 
	 * @param typeName
	 *            The name of the type
	 * @return True if this java code contains this type, false otherwise
	 */
	public boolean containsType(String typeName) {
		return ((getType(this.cu, typeName)) != null);
	}

	/**
	 * Return a field checker from a package name, a class name and a field
	 * name.
	 * 
	 * @param packageName
	 *            Package name
	 * @param className
	 *            Class name
	 * @param fieldName
	 *            Field name
	 * @return A Field Checker instance associated with field {@code fieldName},
	 *         null if an error occurs
	 */
	public static FieldChecker getFieldChecker(String packageName, String className,
			String fieldName) {
		try {
			return new JavaCodeChecker(packageName, className).getMainTypeChecker()
					.getFieldChecker(fieldName);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Return a field checker from a package name, a class name and a field
	 * name.
	 * 
	 * @param prj
	 *            Fork project
	 * @param packageName
	 *            Package name
	 * @param className
	 *            Class name
	 * @param fieldName
	 *            Field name
	 * @return A Field Checker instance associated with field {@code fieldName},
	 *         null if an error occurs
	 */
	public static FieldChecker getFieldChecker(IProject prj, String packageName, String className,
			String fieldName) {
		try {
			return getJavaCodeChecker(prj, packageName, className).getMainTypeChecker()
					.getFieldChecker(fieldName);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Return an enum constant checker from a package name, a class name and a
	 * constant name.
	 * 
	 * @param packageName
	 *            Package name
	 * @param className
	 *            Class name
	 * @param constantName
	 *            Enum constant name
	 * @return A Enum Constant Checker instance associated with constant
	 *         {@code constantName}, null if an error occurs
	 */
	public static EnumConstantChecker getEnumConstantChecker(String packageName, String className,
			String constantName) {
		try {
			return ((EnumTypeChecker) new JavaCodeChecker(packageName, className)
					.getMainTypeChecker()).getConstantChecker(constantName);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Return an inner type checker from a package name, a class name and a
	 * inner type name.
	 * 
	 * @param packageName
	 *            Package name
	 * @param className
	 *            Class name
	 * @param innerTypeName
	 *            Inner type name
	 * @return An AbstractTypeChecker instance associated with inner type
	 *         {@code innerTypeName}, null if an error occurs
	 */
	public static AbstractTypeChecker getInnerTypeChecker(String packageName, String className,
			String innerTypeName) {
		try {
			return new JavaCodeChecker(packageName, className).getMainTypeChecker()
					.getInnerTypeChecker(innerTypeName);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Return a method checker from a package name, a class name, a method name
	 * and a arguments list.
	 * 
	 * @param prj
	 *            Fork project
	 * @param packageName
	 *            Package name
	 * @param className
	 *            Class name
	 * @param methodName
	 *            Method name
	 * @param argumentsList
	 *            Method arguments list as varargs argument
	 * @return A Method Checker instance associated with method
	 *         {@code methodName}, null if an error occurs
	 */
	public static MethodChecker getMethodChecker(IProject prj, String packageName, String className,
			String methodName, String... argumentsList) {
		try {
			return getJavaCodeChecker(prj, packageName, className).getMainTypeChecker()
					.getMethodChecker(methodName, argumentsList);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Return a method checker from a package name, a class name and a method
	 * name
	 * 
	 * @param packageName
	 *            Package name
	 * @param className
	 *            Class name
	 * @param methodName
	 *            Method name
	 * @return A Method Checker instance associated with method
	 *         {@code methodName}, null if an error occurs
	 */
	public static MethodChecker getMethodChecker(IProject prj, String packageName, String className,
			String methodName) {
		return getJavaCodeChecker(prj, packageName, className).getMainTypeChecker().getMethodChecker(methodName, (String[]) null);
	}

	/**
	 * Return a method checker from a package name, a class name, a method name
	 * and a arguments list.
	 * 
	 * @param packageName
	 *            Package name
	 * @param className
	 *            Class name
	 * @param methodName
	 *            Method name
	 * @param argumentsList
	 *            Method arguments list as varargs argument
	 * @return A Method Checker instance associated with method
	 *         {@code methodName}, null if an error occurs
	 */
	public static MethodChecker getMethodChecker(String packageName, String className,
			String methodName, String... argumentsList) {
		try {
			return new JavaCodeChecker(packageName, className).getMainTypeChecker()
					.getMethodChecker(methodName, argumentsList);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Return a type checker from a package name and a class name.
	 * 
	 * @param prj
	 * 				Fork project
	 * @param packageName
	 *            Package name
	 * @param className
	 *            Class name
	 * @return A Type Checker instance associated with type {@code className},
	 *         null if an error occurs
	 */
	public static AbstractTypeChecker getTypeChecker(IProject prj, String packageName, String className) {
		try {
			return getJavaCodeChecker(prj, packageName, className).getMainTypeChecker();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Return a type checker from a package name and a class name.
	 * 
	 * @param prj
	 * 				Fork project
	 * @param packageName
	 *            Package name
	 * @param className
	 *            Class name
	 * @return A Type Checker instance associated with type {@code className},
	 *         null if an error occurs
	 */
	public static AbstractTypeChecker getTypeChecker(IProject prj, String packageName, String className, String generatedFolder) {
		try {
			return getJavaCodeChecker(prj, packageName, className, generatedFolder).getMainTypeChecker();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Return a java code checker from a package name and a class name.
	 * 
	 * @param prj
	 * 				Fork project
	 * @param packageName
	 * 				Package name
	 * @param className
	 * 				Class name
	 * @return A Java Code Checker instance associated with package name and
	 *         class name, null if an error occurs
	 */
	public static JavaCodeChecker getJavaCodeChecker(IProject prj, String packageName, String className) {
		try {
			return new JavaCodeChecker(prj.getFile("fakeFile").getParent().getLocation().toOSString(), null, packageName, className);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Return a java code checker from a package name and a class name.
	 * 
	 * @param prj
	 * 				Fork project
	 * @param packageName
	 * 				Package name
	 * @param className
	 * 				Class name
	 * @return A Java Code Checker instance associated with package name and
	 *         class name, null if an error occurs
	 */
	public static JavaCodeChecker getJavaCodeChecker(IProject prj, String packageName, String className, String generatedFolder) {
		try {
			return new JavaCodeChecker(prj.getFile("fakeFile").getParent().getLocation().toOSString(), generatedFolder, packageName, className);
		} catch (Exception e) {
			return null;
		}
	}
	
}
