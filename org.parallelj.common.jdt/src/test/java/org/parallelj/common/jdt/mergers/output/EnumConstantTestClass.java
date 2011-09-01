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
package org.parallelj.common.jdt.mergers.output;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("XA Designer")
public enum EnumConstantTestClass implements Serializable {
	@Generated("XA Designer")
	MERCURY(3.303e+23, 2.4397e6), @Generated("XA Designer")
	VENUS(4.869e+24, 6.0518e6), @Generated("XA Designer")
	EARTH(5.976e+24, 6.37814e6), @Generated("XA Designer")
	MARS(6.421e+23, 3.3972e6), @Generated("XA Designer")
	JUPITER(1.9e+27, 7.1492e7), @Generated("XA Designer")
	SATURN(5.688e+26, 6.0268e7), @Generated("XA Designer")
	URANUS(8.686e+25, 2.5559e7), @Generated("XA Designer")
	NEPTUNE(1.024e+26, 2.4746e7);

	@Generated("XA Designer")
	private final double mass; // in kilograms

	@Generated("XA Designer")
	private final double radius; // in meters

	@Generated("XA Designer")
	EnumConstantTestClass(double mass, double radius) {
		this.mass = mass;
		this.radius = radius;
	}

	@Generated("XA Designer")
	private double mass() {
		return mass;
	}

	@Generated("XA Designer")
	private double radius() {
		return radius;
	}

	@Generated("XA Designer")
	// universal gravitational constant (m3 kg-1 s-2)
	public static final double G = 6.67300E-11;

	@Generated("XA Designer")
	double surfaceGravity() {
		return G * mass / (radius * radius);
	}

	@Generated("XA Designer")
	double surfaceWeight(double otherMass) {
		return otherMass * surfaceGravity();
	}

	@Generated("XA Designer")
	public static void main(String[] args) {
		double earthWeight = Double.parseDouble(args[0]);
		double mass = earthWeight / EARTH.surfaceGravity();
		for (EnumConstantTestClass p : EnumConstantTestClass.values())
			System.out.printf("Your weight on %s is %f%n", p, p.surfaceWeight(mass));
	}
}
