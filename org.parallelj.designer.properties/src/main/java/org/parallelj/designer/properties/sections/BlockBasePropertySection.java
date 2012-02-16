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
package org.parallelj.designer.properties.sections;

import org.parallelj.designer.properties.helpers.ParallelJPropertiesMessages;
import org.parallelj.designer.properties.zones.CapacityZone;
import org.parallelj.designer.properties.zones.DescriptionZone;
import org.parallelj.designer.properties.zones.HelpZone;
import org.parallelj.designer.properties.zones.NameZone;
import org.parallelj.designer.properties.zones.ProceduresZone;
import org.parallelj.ixea.Section;
import org.parallelj.ixea.tools.FormDataBuilder;

public class BlockBasePropertySection extends Section {

	@Override
	protected void initParts() {
		getZones().put("nameZone", new NameZone(getBackGround(), false));
		getZones().put(
				"helpName",
				new HelpZone(getBackGround(), false,
						ParallelJPropertiesMessages.help_name.message()));
		getZones().put("capacityZone",
				new CapacityZone(getBackGround(), false, false));
		getZones().put(
				"helpCapacity",
				new HelpZone(getBackGround(), false,
						ParallelJPropertiesMessages.help_capacity.message()));
		getZones().put("proceduresZone",
				new ProceduresZone(getBackGround(), false, true));
		getZones().put(
				"helpProcedures",
				new HelpZone(getBackGround(), false,
						ParallelJPropertiesMessages.help_procedures.message()));
		getZones().put("descriptionZone",
				new DescriptionZone(getBackGround(), false));
		getZones()
				.put("helpDescription",
						new HelpZone(getBackGround(), false,
								ParallelJPropertiesMessages.help_description
										.message()));
	}

	@Override
	protected void addLayoutsToParts() {
		new FormDataBuilder().top().right().apply(getZone("helpName"));
		new FormDataBuilder().top().left().right(getZone("helpName"))
				.apply(getZone("nameZone"));

		new FormDataBuilder().top(getZone("helpName")).right()
				.apply(getZone("helpCapacity"));
		new FormDataBuilder().top(getZone("helpName")).left()
				.right(getZone("helpCapacity")).apply(getZone("capacityZone"));

		new FormDataBuilder().top(getZone("helpCapacity")).right()
				.apply(getZone("helpProcedures"));
		new FormDataBuilder().top(getZone("helpCapacity")).left()
				.right(getZone("helpProcedures"))
				.apply(getZone("proceduresZone"));

		new FormDataBuilder().top(getZone("proceduresZone")).right()
				.apply(getZone("helpDescription"));
		new FormDataBuilder().top(getZone("proceduresZone")).left()
				.right(getZone("helpDescription")).bottom()
				.apply(getZone("descriptionZone"));
	}

}
