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
package org.parallelj.designer.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;
import org.parallelj.designer.edit.parts.BlockBlockCompartmentEditPart;
import org.parallelj.designer.edit.parts.BlockEditPart;
import org.parallelj.designer.edit.parts.BlockProcedureEditPart;
import org.parallelj.designer.edit.parts.BusinessProcedureEditPart;
import org.parallelj.designer.edit.parts.ConditionEditPart;
import org.parallelj.designer.edit.parts.DataEditPart;
import org.parallelj.designer.edit.parts.ForEachLoopEditPart;
import org.parallelj.designer.edit.parts.HandlerEditPart;
import org.parallelj.designer.edit.parts.InputConditionEditPart;
import org.parallelj.designer.edit.parts.LinkEditPart;
import org.parallelj.designer.edit.parts.OutputConditionEditPart;
import org.parallelj.designer.edit.parts.PredicateEditPart;
import org.parallelj.designer.edit.parts.ProcedureEditPart;
import org.parallelj.designer.edit.parts.ProgramEditPart;
import org.parallelj.designer.edit.parts.ProgramProgramCompartmentEditPart;
import org.parallelj.designer.edit.parts.SpecificationEditPart;
import org.parallelj.designer.edit.parts.WhileLoopEditPart;
import org.parallelj.designer.part.Messages;
import org.parallelj.designer.part.ParallelJVisualIDRegistry;

/**
 * @generated
 */
public class ParallelJNavigatorContentProvider implements
		ICommonContentProvider {

	/**
	 * @generated
	 */
	private static final Object[] EMPTY_ARRAY = new Object[0];

	/**
	 * @generated
	 */
	private Viewer myViewer;

	/**
	 * @generated
	 */
	private AdapterFactoryEditingDomain myEditingDomain;

	/**
	 * @generated
	 */
	private WorkspaceSynchronizer myWorkspaceSynchronizer;

	/**
	 * @generated
	 */
	private Runnable myViewerRefreshRunnable;

	/**
	 * @generated
	 */
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public ParallelJNavigatorContentProvider() {
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE
				.createEditingDomain();
		myEditingDomain = (AdapterFactoryEditingDomain) editingDomain;
		myEditingDomain.setResourceToReadOnlyMap(new HashMap() {
			public Object get(Object key) {
				if (!containsKey(key)) {
					put(key, Boolean.TRUE);
				}
				return super.get(key);
			}
		});
		myViewerRefreshRunnable = new Runnable() {
			public void run() {
				if (myViewer != null) {
					myViewer.refresh();
				}
			}
		};
		myWorkspaceSynchronizer = new WorkspaceSynchronizer(editingDomain,
				new WorkspaceSynchronizer.Delegate() {
					public void dispose() {
					}

					public boolean handleResourceChanged(final Resource resource) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}

					public boolean handleResourceDeleted(Resource resource) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}

					public boolean handleResourceMoved(Resource resource,
							final URI newURI) {
						unloadAllResources();
						asyncRefresh();
						return true;
					}
				});
	}

	/**
	 * @generated
	 */
	public void dispose() {
		myWorkspaceSynchronizer.dispose();
		myWorkspaceSynchronizer = null;
		myViewerRefreshRunnable = null;
		myViewer = null;
		unloadAllResources();
		((TransactionalEditingDomain) myEditingDomain).dispose();
		myEditingDomain = null;
	}

	/**
	 * @generated
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		myViewer = viewer;
	}

	/**
	 * @generated
	 */
	void unloadAllResources() {
		for (Resource nextResource : myEditingDomain.getResourceSet()
				.getResources()) {
			nextResource.unload();
		}
	}

	/**
	 * @generated
	 */
	void asyncRefresh() {
		if (myViewer != null && !myViewer.getControl().isDisposed()) {
			myViewer.getControl().getDisplay()
					.asyncExec(myViewerRefreshRunnable);
		}
	}

	/**
	 * @generated
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IFile) {
			IFile file = (IFile) parentElement;
			URI fileURI = URI.createPlatformResourceURI(file.getFullPath()
					.toString(), true);
			Resource resource = myEditingDomain.getResourceSet().getResource(
					fileURI, true);
			ArrayList<ParallelJNavigatorItem> result = new ArrayList<ParallelJNavigatorItem>();
			ArrayList<View> topViews = new ArrayList<View>(resource
					.getContents().size());
			for (EObject o : resource.getContents()) {
				if (o instanceof View) {
					topViews.add((View) o);
				}
			}
			return result.toArray();
		}

		if (parentElement instanceof ParallelJNavigatorGroup) {
			ParallelJNavigatorGroup group = (ParallelJNavigatorGroup) parentElement;
			return group.getChildren();
		}

		if (parentElement instanceof ParallelJNavigatorItem) {
			ParallelJNavigatorItem navigatorItem = (ParallelJNavigatorItem) parentElement;
			if (navigatorItem.isLeaf() || !isOwnView(navigatorItem.getView())) {
				return EMPTY_ARRAY;
			}
			return getViewChildren(navigatorItem.getView(), parentElement);
		}

		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Object[] getViewChildren(View view, Object parentElement) {
		switch (ParallelJVisualIDRegistry.getVisualID(view)) {

		case HandlerEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Node sv = (Node) view;
			ParallelJNavigatorGroup incominglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Handler_3008_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ParallelJNavigatorGroup outgoinglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Handler_3008_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case InputConditionEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Node sv = (Node) view;
			ParallelJNavigatorGroup incominglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_InputCondition_3001_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ParallelJNavigatorGroup outgoinglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_InputCondition_3001_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case WhileLoopEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Node sv = (Node) view;
			ParallelJNavigatorGroup incominglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_WhileLoop_3007_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ParallelJNavigatorGroup outgoinglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_WhileLoop_3007_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ConditionEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Node sv = (Node) view;
			ParallelJNavigatorGroup incominglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Condition_3003_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ParallelJNavigatorGroup outgoinglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Condition_3003_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case BlockProcedureEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Node sv = (Node) view;
			ParallelJNavigatorGroup incominglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Procedure_3013_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ParallelJNavigatorGroup outgoinglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Procedure_3013_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case BusinessProcedureEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Node sv = (Node) view;
			ParallelJNavigatorGroup incominglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_BusinessProcedure_3014_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ParallelJNavigatorGroup outgoinglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_BusinessProcedure_3014_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ProcedureEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Node sv = (Node) view;
			ParallelJNavigatorGroup incominglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Procedure_3005_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ParallelJNavigatorGroup outgoinglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Procedure_3005_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case SpecificationEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Diagram sv = (Diagram) view;
			ParallelJNavigatorGroup links = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Specification_1000_links,
					"icons/linksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getDiagramLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			links.addChildren(createNavigatorItems(connectedViews, links, false));
			if (!links.isEmpty()) {
				result.add(links);
			}
			return result.toArray();
		}

		case LinkEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Edge sv = (Edge) view;
			ParallelJNavigatorGroup target = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Link_4001_target,
					"icons/linkTargetNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ParallelJNavigatorGroup source = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Link_4001_source,
					"icons/linkSourceNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(InputConditionEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(OutputConditionEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ConditionEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProcedureEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ForEachLoopEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(WhileLoopEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(HandlerEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(BlockEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(BlockProcedureEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksTargetByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(BusinessProcedureEditPart.VISUAL_ID));
			target.addChildren(createNavigatorItems(connectedViews, target,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(InputConditionEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(OutputConditionEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ConditionEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProcedureEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ForEachLoopEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(WhileLoopEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(HandlerEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(BlockEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(BlockProcedureEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			connectedViews = getLinksSourceByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(BusinessProcedureEditPart.VISUAL_ID));
			source.addChildren(createNavigatorItems(connectedViews, source,
					true));
			if (!target.isEmpty()) {
				result.add(target);
			}
			if (!source.isEmpty()) {
				result.add(source);
			}
			return result.toArray();
		}

		case OutputConditionEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Node sv = (Node) view;
			ParallelJNavigatorGroup incominglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_OutputCondition_3002_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ParallelJNavigatorGroup outgoinglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_OutputCondition_3002_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ProgramEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Node sv = (Node) view;
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramProgramCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry
							.getType(InputConditionEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramProgramCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry
							.getType(OutputConditionEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramProgramCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry
							.getType(ConditionEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramProgramCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry
							.getType(PredicateEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramProgramCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry
							.getType(ProcedureEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramProgramCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry
							.getType(ForEachLoopEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramProgramCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry
							.getType(WhileLoopEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramProgramCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry
							.getType(HandlerEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramProgramCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry.getType(BlockEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramProgramCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry.getType(DataEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getChildrenByType(
					Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(ProgramProgramCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry
							.getType(BusinessProcedureEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			return result.toArray();
		}

		case BlockEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Node sv = (Node) view;
			ParallelJNavigatorGroup incominglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Block_3012_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ParallelJNavigatorGroup outgoinglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_Block_3012_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getChildrenByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry
							.getType(BlockBlockCompartmentEditPart.VISUAL_ID));
			connectedViews = getChildrenByType(connectedViews,
					ParallelJVisualIDRegistry
							.getType(BlockProcedureEditPart.VISUAL_ID));
			result.addAll(createNavigatorItems(connectedViews, parentElement,
					false));
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}

		case ForEachLoopEditPart.VISUAL_ID: {
			LinkedList<ParallelJAbstractNavigatorItem> result = new LinkedList<ParallelJAbstractNavigatorItem>();
			Node sv = (Node) view;
			ParallelJNavigatorGroup incominglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_ForEachLoop_3006_incominglinks,
					"icons/incomingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			ParallelJNavigatorGroup outgoinglinks = new ParallelJNavigatorGroup(
					Messages.NavigatorGroupName_ForEachLoop_3006_outgoinglinks,
					"icons/outgoingLinksNavigatorGroup.gif", parentElement); //$NON-NLS-1$
			Collection<View> connectedViews;
			connectedViews = getIncomingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			incominglinks.addChildren(createNavigatorItems(connectedViews,
					incominglinks, true));
			connectedViews = getOutgoingLinksByType(Collections.singleton(sv),
					ParallelJVisualIDRegistry.getType(LinkEditPart.VISUAL_ID));
			outgoinglinks.addChildren(createNavigatorItems(connectedViews,
					outgoinglinks, true));
			if (!incominglinks.isEmpty()) {
				result.add(incominglinks);
			}
			if (!outgoinglinks.isEmpty()) {
				result.add(outgoinglinks);
			}
			return result.toArray();
		}
		}
		return EMPTY_ARRAY;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksSourceByType(Collection<Edge> edges,
			String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeSource = nextEdge.getSource();
			if (type.equals(nextEdgeSource.getType())
					&& isOwnView(nextEdgeSource)) {
				result.add(nextEdgeSource);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getLinksTargetByType(Collection<Edge> edges,
			String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (Edge nextEdge : edges) {
			View nextEdgeTarget = nextEdge.getTarget();
			if (type.equals(nextEdgeTarget.getType())
					&& isOwnView(nextEdgeTarget)) {
				result.add(nextEdgeTarget);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getOutgoingLinksByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getSourceEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getIncomingLinksByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getTargetEdges(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getChildrenByType(
			Collection<? extends View> nodes, String type) {
		LinkedList<View> result = new LinkedList<View>();
		for (View nextNode : nodes) {
			result.addAll(selectViewsByType(nextNode.getChildren(), type));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<View> getDiagramLinksByType(
			Collection<Diagram> diagrams, String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (Diagram nextDiagram : diagrams) {
			result.addAll(selectViewsByType(nextDiagram.getEdges(), type));
		}
		return result;
	}

	// TODO refactor as static method
	/**
	 * @generated
	 */
	private Collection<View> selectViewsByType(Collection<View> views,
			String type) {
		ArrayList<View> result = new ArrayList<View>();
		for (View nextView : views) {
			if (type.equals(nextView.getType()) && isOwnView(nextView)) {
				result.add(nextView);
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return SpecificationEditPart.MODEL_ID.equals(ParallelJVisualIDRegistry
				.getModelID(view));
	}

	/**
	 * @generated
	 */
	private Collection<ParallelJNavigatorItem> createNavigatorItems(
			Collection<View> views, Object parent, boolean isLeafs) {
		ArrayList<ParallelJNavigatorItem> result = new ArrayList<ParallelJNavigatorItem>(
				views.size());
		for (View nextView : views) {
			result.add(new ParallelJNavigatorItem(nextView, parent, isLeafs));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public Object getParent(Object element) {
		if (element instanceof ParallelJAbstractNavigatorItem) {
			ParallelJAbstractNavigatorItem abstractNavigatorItem = (ParallelJAbstractNavigatorItem) element;
			return abstractNavigatorItem.getParent();
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean hasChildren(Object element) {
		return element instanceof IFile || getChildren(element).length > 0;
	}

}
