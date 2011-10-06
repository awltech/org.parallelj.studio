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
package org.parallelj.designer.properties.zones;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.parallelj.designer.properties.Activator;
import org.parallelj.ixea.Zone;
import org.parallelj.ixea.tools.FormDataBuilder;

public class HelpZone extends Zone {

	private static final String ICONS_HELP_PNG = "/icons/help.png";

	private CLabel helpImage;

	private String tooltip;

	public HelpZone(Composite parent, boolean isGroup, String tooltip) {
		super(parent, isGroup);
		this.tooltip = tooltip;
	}

	@Override
	public void addItemsToZone() {
		helpImage = this.getWidgetFactory().createCLabel(getZone(), "");
		helpImage.setImage(Activator.getDefault().getImage(ICONS_HELP_PNG));
		helpImage.setToolTipText(this.tooltip);
	}

	@Override
	public void addLayoutsToItems() {
		new FormDataBuilder().left().top().right().bottom().apply(helpImage);
	}

	@Override
	public void addListenersToItems() {

	}

	@Override
	public void updateItemsValues() {

	}

}
