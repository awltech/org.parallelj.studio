package org.parallelj.code.generator.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.parallelj.model.Specification;

/**
 * ParallelJ Generation Job change listener. to execute specific action during
 * the job that triggers the generation
 * 
 * @author mvanbesien
 * 
 */
public class ParallelJCodeGeneratorJobChangeListener implements IJobChangeListener {

	/**
	 * currently processed project
	 */
	private IProject project;

	/**
	 * 
	 * @param project
	 *            currently processed project
	 */
	public void setProject(IProject project) {
		this.project = project;
	}

	/**
	 * 
	 * @return currently processed project
	 */
	public IProject getProject() {
		return project;
	}

	/**
	 * Currently processed specifications
	 */
	private Collection<Specification> specifications = new ArrayList<Specification>();

	/**
	 * 
	 * @return Currently processed specifications
	 */
	public Collection<Specification> getSpecifications() {
		return this.specifications;
	}

	/**
	 * 
	 * @param specifications
	 *            Currently processed specifications
	 */
	public void setSpecifications(Collection<Specification> specifications) {
		this.specifications = Collections.unmodifiableCollection(specifications);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.jobs.IJobChangeListener#aboutToRun(org.eclipse
	 * .core.runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void aboutToRun(IJobChangeEvent event) {
		// Does nothing. to be subclassed by client
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.jobs.IJobChangeListener#awake(org.eclipse.core
	 * .runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void awake(IJobChangeEvent event) {
		// Does nothing. to be subclassed by client
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.jobs.IJobChangeListener#done(org.eclipse.core
	 * .runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void done(IJobChangeEvent event) {
		// Does nothing. to be subclassed by client
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.jobs.IJobChangeListener#running(org.eclipse.
	 * core.runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void running(IJobChangeEvent event) {
		// Does nothing. to be subclassed by client
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.jobs.IJobChangeListener#scheduled(org.eclipse
	 * .core.runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void scheduled(IJobChangeEvent event) {
		// Does nothing. to be subclassed by client
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.jobs.IJobChangeListener#sleeping(org.eclipse
	 * .core.runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void sleeping(IJobChangeEvent event) {
		// Does nothing. to be subclassed by client
	}

}
