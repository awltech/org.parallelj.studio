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

import java.util.List;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.parallelj.designer.properties.helpers.ParallelJPropertiesMessages;
import org.parallelj.ixea.Zone;
import org.parallelj.ixea.tools.Commands;
import org.parallelj.ixea.tools.FormDataBuilder;
import org.parallelj.model.ExecutionMode;
import org.parallelj.model.ParallelJPackage;
import org.parallelj.model.Procedure;

public class ExecutionModeZone extends Zone {
	
	private CLabel executionModeLabel;

	private CCombo executionModeCombo;
	
	public ExecutionModeZone(Composite parent, boolean isGroup) {
		super(parent, isGroup);
	}

	@Override
	public void addItemsToZone() {
		executionModeLabel = this.getWidgetFactory().createCLabel(getZone(),
				ParallelJPropertiesMessages.element_execution_mode.message());
		executionModeCombo = this.getWidgetFactory().createCCombo(getZone());
		List<ExecutionMode> executionModes = ExecutionMode.VALUES;
		for (ExecutionMode executionMode : executionModes)
			executionModeCombo.add(executionMode.getName());
	}

	@Override
	public void addLayoutsToItems() {
		new FormDataBuilder().left().top().mediumLabel().apply(executionModeLabel);
		new FormDataBuilder().left(executionModeLabel).top().right().apply(executionModeCombo);
	}

	@Override
	public void addListenersToItems() {
		SelectionListener comboListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				CCombo combo = (CCombo) event.getSource();
				Commands.doSetValue(getEditingDomain(), getEObject(),
						ParallelJPackage.eINSTANCE.getProcedure_ExecutionMode(),
						ExecutionMode.VALUES.get(combo.getSelectionIndex()),
						getEditPart());
				refreshZoneAndDiagram();
			}
		
		};
		executionModeCombo.addSelectionListener(comboListener);
	}

	@Override
	public void updateItemsValues() {
		String value = (getEObject() instanceof Procedure) ? ((Procedure) getEObject())
				.getExecutionMode().getName() : "";
		executionModeCombo.setText(value != null ? value.toUpperCase() : "");
		String tooltip = "";
		ExecutionMode executionMode = ExecutionMode.get(value);
		if (executionMode == ExecutionMode.PARALLEL)
			tooltip = ParallelJPropertiesMessages.tooltip_parallel_execution_mode.message();
		else if (executionMode == ExecutionMode.ITERATIVE)
			tooltip = ParallelJPropertiesMessages.tooltip_iterative_execution_mode.message();
		else if (executionMode == ExecutionMode.STREAM)
			tooltip = ParallelJPropertiesMessages.tooltip_stream_execution_mode.message();
		executionModeCombo.setToolTipText(tooltip);
		executionModeLabel.setToolTipText(tooltip);
	}

}
