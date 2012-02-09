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
import org.parallelj.designer.properties.zones.ExecutableZone;
import org.parallelj.designer.properties.zones.ExecutionModeZone;
import org.parallelj.designer.properties.zones.HelpZone;
import org.parallelj.designer.properties.zones.JoinZone;
import org.parallelj.designer.properties.zones.NameZone;
import org.parallelj.designer.properties.zones.OutputLinksZone;
import org.parallelj.designer.properties.zones.PredicateZone;
import org.parallelj.designer.properties.zones.SplitZone;
import org.parallelj.ixea.Section;
import org.parallelj.ixea.tools.FormDataBuilder;

public class WhileLoopBasePropertySection extends Section {

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
		getZones().put("executableZone",
				new ExecutableZone(getBackGround(), false));
		getZones().put(
				"helpExecutable",
				new HelpZone(getBackGround(), false,
						ParallelJPropertiesMessages.help_executable.message()));
		getZones().put("joinZone", new JoinZone(getBackGround(), false));
		getZones().put(
				"helpJoin",
				new HelpZone(getBackGround(), false,
						ParallelJPropertiesMessages.help_join.message()));
		getZones().put("splitZone", new SplitZone(getBackGround(), false));
		getZones().put(
				"helpSplit",
				new HelpZone(getBackGround(), false,
						ParallelJPropertiesMessages.help_split.message()));
		getZones().put("predicateZone",
				new PredicateZone(getBackGround(), false, true));
		getZones().put(
				"helpPredicate",
				new HelpZone(getBackGround(), false,
						ParallelJPropertiesMessages.help_predicate.message()));
		getZones().put("executionModeZone",
				new ExecutionModeZone(getBackGround(), false));
		getZones().put(
				"helpExecutionMode",
				new HelpZone(getBackGround(), false,
						ParallelJPropertiesMessages.help_execution_mode
								.message()));
		getZones().put("outputLinksZone",
				new OutputLinksZone(getBackGround(), false));
		getZones()
				.put("helpOutputLinks",
						new HelpZone(getBackGround(), false,
								ParallelJPropertiesMessages.help_outputlinks
										.message()));
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
				.apply(getZone("helpExecutable"));
		new FormDataBuilder().top(getZone("helpCapacity")).left()
				.right(getZone("helpExecutable"))
				.apply(getZone("executableZone"));

		new FormDataBuilder().top(getZone("executableZone")).right()
				.apply(getZone("helpJoin"));
		new FormDataBuilder().top(getZone("executableZone")).left()
				.right(getZone("helpJoin")).apply(getZone("joinZone"));

		new FormDataBuilder().top(getZone("helpJoin")).right()
				.apply(getZone("helpSplit"));
		new FormDataBuilder().top(getZone("helpJoin")).left()
				.right(getZone("helpSplit")).apply(getZone("splitZone"));

		new FormDataBuilder().top(getZone("helpSplit")).right()
				.apply(getZone("helpPredicate"));
		new FormDataBuilder().top(getZone("helpSplit")).left()
				.right(getZone("helpPredicate"))
				.apply(getZone("predicateZone"));

		new FormDataBuilder().top(getZone("helpPredicate")).right()
				.apply(getZone("helpExecutionMode"));
		new FormDataBuilder().top(getZone("helpPredicate")).left()
				.right(getZone("helpExecutionMode"))
				.apply(getZone("executionModeZone"));

		new FormDataBuilder().top(getZone("helpExecutionMode")).right()
				.apply(getZone("helpOutputLinks"));
		new FormDataBuilder().top(getZone("helpExecutionMode")).left()
				.right(getZone("helpOutputLinks"))
				.apply(getZone("outputLinksZone"));

		new FormDataBuilder().top(getZone("outputLinksZone")).right()
				.apply(getZone("helpDescription"));
		new FormDataBuilder().top(getZone("outputLinksZone")).left()
				.right(getZone("helpDescription")).bottom()
				.apply(getZone("descriptionZone"));
	}

}
