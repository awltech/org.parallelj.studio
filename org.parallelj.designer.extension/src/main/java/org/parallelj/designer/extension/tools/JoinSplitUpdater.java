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
package org.parallelj.designer.extension.tools;

import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.swt.graphics.Image;
import org.parallelj.model.ForEachLoop;
import org.parallelj.model.Handler;
import org.parallelj.model.Pipeline;
import org.parallelj.model.Procedure;
import org.parallelj.model.WhileLoop;
import org.parallelj.model.provider.ParallelJEditPlugin;

public class JoinSplitUpdater {

	private static final String IMAGE_FOLDER_PATH = "/icons/full/obj16/";

	private JoinSplitUpdater() {
	}

	/**
	 * Updates involved diagram for SPLIT and JOIN icon, based on value of the
	 * same. SPLIT and JOIN icon will appear, based on number of incoming and
	 * outgoing links.
	 * 
	 * @param procedure
	 * @param joinLabel
	 *            label containing join icon
	 * @param splitLabel
	 *            label containing split icon
	 */
	public static void updateSplitJoin(Procedure procedure,
			WrappingLabel joinLabel, WrappingLabel splitLabel) {
		if (procedure != null) {
			if (joinLabel != null) {
				// if incoming links are 2 or greater than that, add join icon
				// else no icon
				if (procedure.getInputLinks().size() >= 2) {
					setJoinIcon(procedure, procedure.getJoin().getName(),
							joinLabel);
				} else {
					clean(joinLabel);
				}
			}

			// if outgoing links are 2 or greater than that, add split icon else
			// no icon
			if (procedure.getOutputLinks().size() >= 2) {
				setSplitIcon(procedure, procedure.getSplit().getName(),
						splitLabel);
			} else {
				clean(splitLabel);
			}
		}
	}

	/**
	 * Updates JOIN icon based on passed mode. This will triggered when user
	 * change JOIN value from property view.
	 * 
	 * @param procedure
	 * @param mode
	 *            it can be AND, XOR or OR
	 * @param wrappingLabel
	 *            label containing JOIN icon
	 */

	public static void setJoinIcon(Procedure procedure, String mode,
			WrappingLabel wrappingLabel) {
		// if incoming links are 2 or greater than that, show icon
		if (procedure != null && procedure.getInputLinks().size() >= 2) {

			Image image = null;
			String path = null;
			String pathPostFix = ".png";

			// for whileLoop and ForEachLoop, tall icons
			if (procedure instanceof WhileLoop
					|| procedure instanceof ForEachLoop) {
				pathPostFix = "_tall" + pathPostFix;
			}
			// for Pipeline and Handler, tiny icons
			else if (procedure instanceof Pipeline
					|| procedure instanceof Handler) {
				pathPostFix = "_tiny" + pathPostFix;
			}

			if ("AND".equalsIgnoreCase(mode)) {
				path = IMAGE_FOLDER_PATH + "JoinAND" + pathPostFix;
			} else if ("XOR".equalsIgnoreCase(mode)) {
				path = IMAGE_FOLDER_PATH + "JoinXOR" + pathPostFix;
			} else {
				path = IMAGE_FOLDER_PATH + "JoinOR" + pathPostFix;
			}

			image = ImageLoader.getImage(
					ParallelJEditPlugin.INSTANCE.getSymbolicName(), path);

			wrappingLabel.setIcon(null, 1);
			wrappingLabel.setIcon(image, 1);
		}
	}

	/**
	 * Updates SPLIT icon based on passed mode. This will triggered when user
	 * change SPLIT value from property view.
	 * 
	 * @param procedure
	 * @param mode
	 *            it can be AND, XOR or OR
	 * @param wrappingLabel
	 *            label containing SPLIT icon
	 */
	public static void setSplitIcon(Procedure procedure, String mode,
			WrappingLabel wrappingLabel) {
		// if outgoing links are 2 or greater than that, show icon
		if (procedure != null && procedure.getOutputLinks().size() >= 2) {

			Image image = null;
			String path = null;
			String pathPostFix = ".png";

			// for whileLoop and ForEachLoop, tall icons
			if (procedure instanceof WhileLoop
					|| procedure instanceof ForEachLoop) {
				pathPostFix = "_tall" + pathPostFix;
			}
			// for Pipeline and Handler, tiny icons
			else if (procedure instanceof Pipeline
					|| procedure instanceof Handler) {
				pathPostFix = "_tiny" + pathPostFix;
			}

			if ("AND".equalsIgnoreCase(mode)) {
				path = IMAGE_FOLDER_PATH + "SplitAND" + pathPostFix;
			} else if ("XOR".equalsIgnoreCase(mode)) {
				path = IMAGE_FOLDER_PATH + "SplitXOR" + pathPostFix;
			} else {
				path = IMAGE_FOLDER_PATH + "SplitOR" + pathPostFix;
			}

			image = ImageLoader.getImage(
					ParallelJEditPlugin.INSTANCE.getSymbolicName(), path);

			wrappingLabel.setIcon(null, 1);
			wrappingLabel.setIcon(image, 1);
		}
	}

	/**
	 * Cleans the icon and text for passed label. This will triggered when any
	 * link is removed and count of incoming / outgoing links decrease to 1 or
	 * none then remove the respective icon of SPLIT or JOIN.
	 * 
	 * @param wrappingLabel
	 *            label containing SPLIT/JOIN icon
	 */
	private static void clean(WrappingLabel wrappingLabel) {
		wrappingLabel.setIcon(null, 1);
		wrappingLabel.setText("");
	}
}
