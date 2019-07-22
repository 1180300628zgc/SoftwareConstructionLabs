package track;

import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Log.LogFile;
import physicalObject.Atom;
import physicalObject.Friend;
import physicalObject.PhysicalObject;
import physicalObject.Planet;

import java.awt.Image;
import java.awt.List;
import java.io.IOException;
import java.math.*;

public class Track {

	private String name;
	private double radius;
	private Set<Planet> physicalObjects = new HashSet<Planet>();
	private Set<Atom> AtomObjects = new HashSet<Atom>();
	private int level;
	private Set<Friend> FriendObjects = new HashSet<Friend>();
	
	// Immutability:
	// the reps are unchangeable
	// AF:
	// name of the Track + radius = Track
	// RI:
	// radius > 0
	// Safety from rep exposure:
	// set all the rep to private and return the copy of the rep

	public Track(Planet physicalObject) {
		this.physicalObjects.add(physicalObject);
		this.radius = physicalObject.GetPosition().GetRadius();
		this.name = physicalObject.GetName();
	}

	/*
	 * public Track(String name, double radius) { this.name = new String(name);
	 * this.radius = radius; }
	 */

	public Track(Track track) {
		this.name = track.GetName();
		this.radius = track.GetRadius();
		this.physicalObjects = track.getPhysicalObjects();
	}

	public Track(int level) {
		this.level = level;
		this.name = String.valueOf(level);
	}

	public Track() {

	}

	/**
	 * Get the name of the track
	 * 
	 * @return name
	 */
	public String GetName() {
		return new String(name);
	}

	/**
	 * Get the radius of the track
	 * 
	 * @return radius
	 */
	public double GetRadius() {
		return radius;
	}

	/**
	 * Set level of the track
	 * 
	 * @param level
	 */
	public void SetLevel(int level) {
		this.level = level;
	}

	/**
	 * Get the level of the track
	 * 
	 * @return the current level
	 */
	public int GetLevel() {
		return this.level;
	}

	/**
	 * Change the radius to an appointed radius
	 * 
	 * @param radius
	 * @return true if the original radius has been successfully changed, false if
	 *         the original radius is the same as the appointed radius.
	 */
	public boolean SetRadius(double radius) {
		if (radius == this.radius) {
			return false;
		} else {
			radius = this.radius;
			return true;
		}
	}

	/**
	 * Get the perimeter of the track.
	 * 
	 * @return perimeter
	 */
	public double GetPerimeter() {
		return 2 * Math.PI * radius;
	}

	/**
	 * Add a new object to the track.
	 * 
	 * @param physicalObject
	 * @return false if the physical object is already included in the track, true
	 *         if the physical object is successfully added to the track
	 */
	public boolean AddObject(Planet physicalObject) {
		try {
			LogFile.WriteError("Planet " + physicalObject.GetName() + " has been added to a track");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return physicalObjects.add(physicalObject);
	}

	/**
	 * Add a new atom object to the track.
	 * 
	 * @param physicalObject
	 * @return false if the physical object is already included in the track, true
	 *         if the physical object is successfully added to the track
	 */
	public boolean AddAtomObject(Atom physicalObject) {
		try {
			LogFile.WriteError("Atom " + physicalObject.GetName() + " has been added to a track");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return AtomObjects.add(physicalObject);
	}

	/**
	 * Remove a atom object to the track.
	 * 
	 * @param physicalObject
	 * @return true if successfully removed
	 */
	public boolean RemoveAtomObject(Atom physicalObject) {
		return AtomObjects.remove(physicalObject);
	}

	/**
	 * Add a new friend object to the track.
	 * 
	 * @param physicalObject
	 * @return false if the physical object is already included in the track, true
	 *         if the physical object is successfully added to the track
	 */
	public boolean AddFriendObject(Friend physicalObject) {
		try {
			LogFile.WriteError("Friend " + physicalObject.GetName() + " has been added to a track");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return FriendObjects.add(physicalObject);
	}

	/**
	 * Get the friend set
	 * 
	 * @return friend set
	 */
	public Set<Friend> GetFriendObject() {
		return FriendObjects;
	}

	/**
	 * Remove a friend object to the track.
	 * 
	 * @param physicalObject
	 * @return true if successfully removed
	 */
	public void RemoveFriendObject(Friend friend) {
		FriendObjects.remove(friend);
	}

//	public boolean AddObject(PhysicalObject physicalObject) {
//		return physicalObjects.add(physicalObject);
//	}
	/**
	 * Remove a specific object to the track. WARNING: It's not safe since it
	 * requires an exposed param
	 * 
	 * @param physicalObject
	 * @return false if the physical object is not included in the track, true if
	 *         the physical object is successfully removed
	 */
	public boolean RemoveObject(PhysicalObject physicalObject) {
		return physicalObjects.remove(physicalObject);
	}

	/**
	 * Get the physicalobj set
	 * @return physicalobj set
	 */
	public Set<Planet> getPhysicalObjects() {
		return this.physicalObjects;
	}

	/**
	 * Get the atomobj set
	 * @return atomobj set
	 */
	public Set<Atom> getAtomObjects() {
		return this.AtomObjects;
	}

	/**
	 * Init is the GUI initiation part Including Track(a white circle) and Planet(a
	 * blue ball)
	 * 
	 * @param the real radius, which means the size shown on GUI canvas. And add to
	 *            which JPanel.
	 */
	public void Init(int paintRadius, JPanel panel) {
		JLabel originalTrack = new JLabel("New label");
		// originalTrack.setIcon(new ImageIcon("/Users/tony/Documents/Software
		// Construction/Handin/Lab3-1170300316/lib/StellarSystem/track.png"));
		originalTrack.setBounds(480 - paintRadius, 320 - paintRadius, paintRadius * 2, paintRadius * 2);

		ImageIcon trackImage = new ImageIcon(
				"lib/StellarSystem/track.png");
		trackImage.setImage(trackImage.getImage().getScaledInstance(originalTrack.getWidth(), originalTrack.getHeight(),
				Image.SCALE_DEFAULT));

		originalTrack.setIcon(trackImage);
		panel.add(originalTrack);

		// The planet on the track should also be initialized
		for (Planet physicalObject : physicalObjects) {
			physicalObject.Init(paintRadius, panel);
		}
	}

	/**
	 * Get the next frame of an obj
	 * @param paintRadius(location)
	 * @param panel to paint on
	 */
	public void Update(int paintRadius, JPanel panel) {
		for (Planet physicalObject : physicalObjects) {
			physicalObject.UpdateAngular();
			physicalObject.Init(paintRadius, panel);
		}
	}

	/**
	 * Initiate an atom
	 * @param paintRadius
	 * @param panel
	 */
	public void AtomInit(int paintRadius, JPanel panel) {
		int i = 0;
		for (Atom atom : AtomObjects) {
			atom.Init(i * 360 / AtomObjects.size(), paintRadius, panel);
			i++;
		}
		JLabel originalTrack = new JLabel("New label");
		// originalTrack.setIcon(new ImageIcon("/Users/tony/Documents/Software
		// Construction/Handin/Lab3-1170300316/lib/StellarSystem/track.png"));
		originalTrack.setBounds(480 - paintRadius, 320 - paintRadius, paintRadius * 2, paintRadius * 2);

		ImageIcon trackImage = new ImageIcon(
				"lib/AtomStructure/track.png");
		trackImage.setImage(trackImage.getImage().getScaledInstance(originalTrack.getWidth(), originalTrack.getHeight(),
				Image.SCALE_DEFAULT));

		originalTrack.setIcon(trackImage);
		panel.add(originalTrack);

		// System.out.println("SIZE" + AtomObjects.size());
	}

	/**
	 * Initiate an friend
	 * @param paintRadius
	 * @param panel
	 */
	public void FriendInit(int paintRadius, JPanel panel) {
		int i = 0;

		for (Friend friend : FriendObjects) {
			friend.Init(i * 360 / FriendObjects.size(), paintRadius, panel);
			i++;
		}

		JLabel originalTrack = new JLabel("New label");
		// originalTrack.setIcon(new ImageIcon("/Users/tony/Documents/Software
		// Construction/Handin/Lab3-1170300316/lib/StellarSystem/track.png"));
		originalTrack.setBounds(480 - paintRadius, 320 - paintRadius, paintRadius * 2, paintRadius * 2);

		ImageIcon trackImage = new ImageIcon(
				"lib/AtomStructure/track.png");
		trackImage.setImage(trackImage.getImage().getScaledInstance(originalTrack.getWidth(), originalTrack.getHeight(),
				Image.SCALE_DEFAULT));

		originalTrack.setIcon(trackImage);
		panel.add(originalTrack);
	}
}
