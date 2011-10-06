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
import org.parallelj.designer.properties.zones.DescriptionZone;
import org.parallelj.designer.properties.zones.HelpZone;
import org.parallelj.designer.properties.zones.NameZone;
import org.parallelj.designer.properties.zones.TypeZone;
import org.parallelj.ixea.Section;
import org.parallelj.ixea.tools.FormDataBuilder;

public class DataBasePropertySection extends Section {

	@Override
	protected void initParts() {
		getZones().put("nameZone", new NameZone(getBackGround(), false));
		getZones().put(
				"helpName",
				new HelpZone(getBackGround(), false,
						ParallelJPropertiesMessages.help_name.message()));
		getZones().put("typeZone", new TypeZone(getBackGround(), false));
		getZones().put(
				"helpType",
				new HelpZone(getBackGround(), false,
						ParallelJPropertiesMessages.help_type.message()));
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

		new FormDataBuilder().top(getZone("nameZone")).right()
				.apply(getZone("helpType"));
		new FormDataBuilder().top(getZone("nameZone")).left()
				.right(getZone("helpType")).apply(getZone("typeZone"));

		new FormDataBuilder().top(getZone("typeZone")).right()
				.apply(getZone("helpDescription"));
		new FormDataBuilder().top(getZone("typeZone")).left()
				.right(getZone("helpDescription")).bottom()
				.apply(getZone("descriptionZone"));
	}

}
