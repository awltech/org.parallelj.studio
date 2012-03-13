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
	 * Temp. method will be removed once color picker issue solved
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
}
