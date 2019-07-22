package physicalObject;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import extensions.Position;

public class Planet extends PhysicalObject {
	private String form;
	private String color;
	private double planetRadius;
	private double trackRadius;
	private double trackSpeed;
	private String trackOrientation;
	private double initDegree;
	private Position position;

	//AF
	//name + position + radius + speed + orientation + color + form -> planet
	
	
	public Planet(String name, String form, String color, double planetRadius, double trackRadius, double trackSpeed,
			String trackOrientation, double initDegree) {
		this.name = new String(name);
		this.form = new String(form);
		this.color = new String(color);
		this.planetRadius = planetRadius;
		this.trackRadius = trackRadius;
		this.trackSpeed = trackSpeed;
		this.trackOrientation = new String(trackOrientation);
		this.initDegree = initDegree;
		position = new Position(trackRadius, initDegree);
		// Position initPosition = new Position(trackRadius, initDegree);
		// SetPosition(initPosition);
	}

	public boolean isClockwise() {
		if (this.trackOrientation.equals("CW")) {
			return true;
		}
		return false;
	}

	@Override
	public Position GetPosition() {
		return position;
	}

	/**
	 * Get the angular velocity
	 * 
	 * @return positive angular velocity if it is CW, negative angular velocity if
	 *         it is not CW
	 */
	public double GetAngularV() {
		// Revised
		if (isClockwise()) {
			return trackSpeed * 360 * 1e4/ (2 * Math.PI * trackRadius);
		}
		return -trackSpeed * 360 * 1e4/ (2 * Math.PI * trackRadius);
	}

	public void UpdateAngular() {
		System.out.println("AngularV: " + GetAngularV());
		this.position.Rotate(GetAngularV());
		System.out.println("Degree: " + position.GetDegree());
	}

	public void Update(int radius, JPanel panel) {
		JLabel planet = new JLabel("New label");
		planet.setIcon(new ImageIcon(
				"lib/StellarSystem/planet.png"));
		// planet.setBounds(555, 70, 60, 60);
		planet.setBounds((int) (radius * Math.cos(GetPosition().GetDegree() * Math.PI / 180)) - 30 + 480,
				(int) (radius * Math.sin(GetPosition().GetDegree() * Math.PI / 180)) - 30 + 320, 60, 60);
		panel.add(planet);
	}
	
	public void Init(int radius, JPanel panel) {
		JLabel planet = new JLabel("New label");
		planet.setIcon(new ImageIcon(
				"lib/StellarSystem/planet.png"));
		// planet.setBounds(555, 70, 60, 60);
		planet.setBounds((int) (radius * Math.cos(GetPosition().GetDegree() * Math.PI / 180)) - 30 + 480,
				(int) (radius * Math.sin(GetPosition().GetDegree() * Math.PI / 180)) - 30 + 320, 60, 60);
		panel.add(planet);
	}
}
