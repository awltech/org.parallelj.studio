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
package org.parallelj.designer.extension.edit.parts;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.parallelj.designer.edit.parts.LinkEditPart;
import org.parallelj.model.Link;
import org.parallelj.model.Procedure;
import org.parallelj.model.SplitType;

public class LinkExtendedEditPart extends LinkEditPart {

	public LinkExtendedEditPart(View view) {
		super(view);
	}

	/**
	 * Refresh is override, to show default outgoing link distinct of Procedure
	 * objects from OR / XOR split.
	 * */
	@Override
	public void refresh() {
		Link link = (Link) ((View) this.getModel()).getElement();
		EObject linkContainer = link.eContainer();
		if (linkContainer != null) {
			if (linkContainer instanceof Procedure) {
				Procedure procedure = (Procedure) linkContainer;
				if (procedure.getOutputLinks().size() > 1
						&& (procedure.getSplit() == SplitType.XOR || procedure
								.getSplit() == SplitType.OR)) {
					int index = procedure.getOutputLinks().indexOf(link);

					// if it is last, show distinct by changing line style to
					// dash
					if (procedure.getOutputLinks().size() - 1 == index) {
						((LinkFigure) this.getFigure()).setLineStyle(2);
					} else {
						((LinkFigure) this.getFigure()).setLineStyle(1);
					}
				} else {
					((LinkFigure) this.getFigure()).setLineStyle(1);
				}
			}
		}
		super.refresh();
	}

	/**
	 * Making the Links rectilinear style routing as default.
	 */
	@Override
	protected void handleNotificationEvent(Notification notification) {
		super.handleNotificationEvent(notification);
		RoutingStyle routing = (RoutingStyle) ((View) this.getModel())
				.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
		if (routing == null) {
			routing = (RoutingStyle) ((View) this.getModel())
					.createStyle(NotationPackage.eINSTANCE.getRoutingStyle());
		}
		routing.setRouting(Routing.RECTILINEAR_LITERAL);
	}
}
