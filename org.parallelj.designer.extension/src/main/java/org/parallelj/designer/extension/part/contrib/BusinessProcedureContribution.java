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
package org.parallelj.designer.extension.part.contrib;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;

public class BusinessProcedureContribution {

	/**
	 * The name shown on the ParallelJ Studio palette for Business Procedure
	 */
	private String name;

	/**
	 * The default colour to be used for Business Procedure in a control flow
	 */
	private String color;

	/**
	 * The Executable linked to Business Procedure (this is the executable in
	 * the business process api)
	 */
	private String executable;

	/**
	 * groupId of dependency jar of Business Procedure
	 */
	private String groupId;

	/**
	 * artifactId of dependency jar of Business Procedure
	 */
	private String artifactId;

	/**
	 * version of dependency jar of Business Procedure
	 */
	private String version;

	/**
	 * scope of dependency jar of Business Procedure
	 */
	private String scope;

	/**
	 * A text describing the inputs needs by the business executable
	 */
	private String inputs;

	/**
	 * A text describing the outputs of the business executable
	 */
	private String outputs;

	/**
	 * An overview of the business process
	 */
	private String description;

	/**
	 * The image to be used as the graphical representation of Business
	 * Procedure
	 */
	private Image image;

	/**
	 * The image path to be used as the graphical representation of Business
	 * Procedure
	 */
	private String imgPath;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getExecutable() {
		return executable;
	}

	public void setExecutable(String executable) {
		this.executable = executable;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getInputs() {
		return inputs;
	}

	public void setInputs(String inputs) {
		this.inputs = inputs;
	}

	public String getOutputs() {
		return outputs;
	}

	public void setOutputs(String outputs) {
		this.outputs = outputs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Temp. method will be removed once color picker is available
	 * 
	 * @return
	 */
	public RGB getRGBColor() {
		if (this.color != null) {
			String[] split = this.color.split(",");
			return new RGB(Integer.parseInt(split[0]),
					Integer.parseInt(split[1]), Integer.parseInt(split[2]));

		}
		return null;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
