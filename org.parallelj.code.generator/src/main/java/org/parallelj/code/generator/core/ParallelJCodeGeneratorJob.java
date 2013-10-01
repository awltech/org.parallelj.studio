package org.parallelj.code.generator.core;

import static org.parallelj.code.generator.core.ParallelJGeneratorConstants.SRC_MAIN_JAVA;
import static org.parallelj.code.generator.core.ParallelJGeneratorConstants.SRC_TEST_JAVA;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.atos.optimus.m2m.engine.core.OptimusM2MEngine;
import net.atos.optimus.m2m.engine.core.transformations.DefaultTransformationContext;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2t.java.core.JavaGenerator;
import net.atos.optimus.m2t.merger.java.core.JavaCodeMerger;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;
import org.jdom.JDOMException;
import org.parallelj.code.generator.Activator;
import org.parallelj.code.generator.helpers.ParallelJConfigHelper;
import org.parallelj.model.Specification;

/**
 * This {@link WorkspaceJob} allows to launch ParallelJ's source code generator.
 * You just need to provide it a {@link Collection} which contains all the
 * specifications to generate, and the {@link IProject} you want to generate the
 * source code in.
 * 
 * @author Antoine Neveux
 * @version 1.0
 * 
 */
public class ParallelJCodeGeneratorJob extends WorkspaceJob {

	private static Set<String> annot = new HashSet<String>();

	static {
		annot.add("AndJoin");
		annot.add("AndSplit");
		annot.add("Begin");
		annot.add("Capacity");
		annot.add("ExceptionHandlingPolicy");
		annot.add("ForEach");
		annot.add("Handler");
		annot.add("Link");
		annot.add("OrJoin");
		annot.add("OrSplit");
		annot.add("Procedure");
		annot.add("Process");
		annot.add("Program");
		annot.add("Programs");
		annot.add("While");
		annot.add("XorJoin");
		annot.add("XorSplit");
	}

	/**
	 * The specifications to use for the code generation
	 */
	private Collection<Specification> specifications;

	/**
	 * The project in which the source code will be generated
	 */
	private IProject project;

	/**
	 * Default constructor
	 * 
	 * @param specifications
	 *            the specifications to generate
	 * @param project
	 *            the project in which the source code will be generated
	 */
	public ParallelJCodeGeneratorJob(Collection<Specification> specifications, IProject project) {
		super(Messages.JOB_NAME.message());
		this.specifications = specifications;
		this.project = project;
		this.setPriority(BUILD);
		this.setUser(true);

		this.loadContributedChangeListeners();
	}

	/**
	 * Loads the contributed change listeners, from extension point.
	 */
	private void loadContributedChangeListeners() {
		final String extensionName = "ParallelJCodeGeneratorJobChangeListeners";
		final String configurationElementName = "listener";
		final String attributeName = "implementation";

		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
				extensionName);
		for (IExtension extension : extensionPoint.getExtensions()) {
			for (IConfigurationElement element : extension.getConfigurationElements()) {
				if (configurationElementName.equals(element.getName())) {
					try {
						Object object = element.createExecutableExtension(attributeName);
						if (object instanceof ParallelJCodeGeneratorJobChangeListener) {
							ParallelJCodeGeneratorJobChangeListener listener = (ParallelJCodeGeneratorJobChangeListener) object;
							listener.setProject(this.project);
							listener.setSpecifications(this.specifications);
							this.addJobChangeListener(listener);
						}
					} catch (Exception e) {
						Activator.sendErrorToErrorLog("An exception was caught when loading contributed listener.", e);
					}
				}
			}
		}
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {

		monitor.beginTask(Messages.TASK_NAME.message(), 6);

		// ----------------------------------------------
		// STEP 0 : Initialization of the context
		// ----------------------------------------------

		monitor.subTask(Messages.TASK_INIT_CONTEXT.message());

		ITransformationContext context = new DefaultTransformationContext();

		monitor.worked(1);

		// ----------------------------------------------
		// STEP 1 : Initializing the java model
		// ----------------------------------------------

		monitor.subTask(Messages.TASK_INIT_JAVA.message());

		Model javaModel = JavaFactory.eINSTANCE.createModel();
		Model testModel = JavaFactory.eINSTANCE.createModel();
		context.putRoot("java", javaModel);
		context.putRoot("test", testModel);

		monitor.worked(1);

		// ----------------------------------------------
		// STEP 2 : Invoking M2M Engine to transform the model
		// ----------------------------------------------

		monitor.subTask(Messages.TASK_M2M.message());

		Set<EObject> input = new HashSet<EObject>(specifications);

		OptimusM2MEngine optimusM2MEngine = new OptimusM2MEngine(context);
		optimusM2MEngine.setElements(input);
		optimusM2MEngine.execute();

		monitor.worked(1);

		// ----------------------------------------------
		// STEP 3 : Regenerate the source code
		// ----------------------------------------------

		monitor.subTask(Messages.TASK_JAVA_GENERATION.message());

		JavaGenerator javaGenerator = new JavaGenerator(new JavaCodeMerger(annot) {

			@Override
			protected String getGeneratorName() {
				return "//J";
			}
		});

		IFolder folder = project.getFolder(SRC_MAIN_JAVA);
		IPath outputPath = folder.getLocation();
		javaGenerator.generate(javaModel, outputPath);
		folder.refreshLocal(IResource.DEPTH_INFINITE, monitor);

		folder = project.getFolder(SRC_TEST_JAVA);
		outputPath = folder.getLocation();
		javaGenerator.generate(testModel, outputPath);
		folder.refreshLocal(IResource.DEPTH_INFINITE, monitor);

		monitor.worked(1);

		ResourceSet resourceSet = new ResourceSetImpl();

		// ----------------------------------------------
		// STEP 4 : Generating configuration file
		// ----------------------------------------------

		monitor.subTask(Messages.TASK_CONFIG_GENERATION.message());

		try {
			ParallelJConfigHelper.generateConfig(javaModel, resourceSet, project);
		} catch (IOException e) {
			return new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.TASK_CONFIG_GENERATION_ERROR.message(e
					.getMessage()));
		} catch (JDOMException e) {
			return new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.TASK_CONFIG_GENERATION_ERROR.message(e
					.getMessage()));
		}

		monitor.worked(1);

		// ----------------------------------------------
		// STEP 5 : For debug mode, write temporary java model
		// ----------------------------------------------

		if (Activator.getDefault().isDebugActivated()) {
			IPath filePath = project.getFullPath().append("optimus-" + System.currentTimeMillis() + ".javaxmi");
			// ResourceSet resourceSet = new ResourceSetImpl();
			Resource optimusResource = resourceSet.createResource(URI.createPlatformResourceURI(filePath.toString(),
					true));
			optimusResource.getContents().add(javaModel);
			try {
				Map<Object, Object> options = new HashMap<Object, Object>();
				options.put(XMIResource.OPTION_PROCESS_DANGLING_HREF_RECORD, true);
				optimusResource.save(options);
			} catch (IOException e) {
				e.printStackTrace();
			}
			optimusResource.unload();
		}

		// ----------------------------------------------
		// STEP 6 : Unloading resources
		// ----------------------------------------------

		monitor.subTask(Messages.TASK_UNLOAD.message());

		context.dispose();

		monitor.worked(1);

		return Status.OK_STATUS;
	}
}
