package extensions;

import java.math.*;

public class Position {
	private double radius;
	private double degree;

	// Mutability:
	// the reps are changeable
	// AF:
	// radius + degree = position
	// RI:
	// radius >= 0 0 <= degree <= 360
	// Safety from rep exposure:
	// reps are set to private final

	public Position(double radius, double degree) {
		this.radius = radius;
		this.degree = degree % 360;
	}

	public void SetPosition(Position position) {
		this.radius = position.GetRadius();
		this.degree = position.GetDegree();
	}
	/**
	 * Rotate the obj by degree
	 * @param degree can be either positive or negative
	 */
	public void Rotate(double degree) {
		this.degree += degree;
		degree = degree % 360;
	}
	
	/**
	 * get the radius of a position
	 * 
	 * @return radius
	 */
	public double GetRadius() {
		return radius;
	}

	/**
	 * get the degree of a position
	 * 
	 * @return degree
	 */
	public double GetDegree() {
		return degree;
	}

	/**
	 * Judge if two positions are of the same radius
	 * 
	 * @param position
	 * @return true: same false: different
	 */
	public boolean isRadiusEqual(Position position) {
		if (position.GetRadius() == this.radius) {
			return true;
		}
		return false;
	}

	/**
	 * Judge if two positions are of the same
	 * 
	 * @param position
	 * @return true: same false: different
	 */
	public boolean isEqual(Position position) {
		if (position.GetRadius() == this.radius && position.GetDegree() == this.degree) {
			return true;
		}
		return false;
	}
	
	public double GetStandardX() {
		return radius * Math.cos(degree) + 300;
	}
	
	public double GetStandardY() {
		return radius * Math.sin(degree) + 300;
	}
	
}
