/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;

//import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

import java.util.ArrayList;
import java.util.HashSet;

public class TurtleSoup {

	/**
	 * Draw a square.
	 * 
	 * @param turtle     the turtle context
	 * @param sideLength length of each side
	 */
	public static void drawSquare(Turtle turtle, int sideLength) {
		// throw new RuntimeException("implement me!");
		turtle.forward(sideLength);
		turtle.turn(90);
		turtle.forward(sideLength);
		turtle.turn(90);
		turtle.forward(sideLength);
		turtle.turn(90);
		turtle.forward(sideLength);
		turtle.turn(90);
	}

	/**
	 * Determine inside angles of a regular polygon.
	 * 
	 * There is a simple formula for calculating the inside angles of a polygon; you
	 * should derive it and use it here.
	 * 
	 * @param sides number of sides, where sides must be > 2
	 * @return angle in degrees, where 0 <= angle < 360
	 */
	public static double calculateRegularPolygonAngle(int sides) {
		// throw new RuntimeException("implement me!");
		return 180 - (sides - 2) * 180 / sides;
	}

	/**
	 * Determine number of sides given the size of interior angles of a regular
	 * polygon.
	 * 
	 * There is a simple formula for this; you should derive it and use it here.
	 * Make sure you *properly round* the answer before you return it (see
	 * java.lang.Math). HINT: it is easier if you think about the exterior angles.
	 * 
	 * @param angle size of interior angles in degrees, where 0 < angle < 180
	 * @return the integer number of sides
	 */
	public static int calculatePolygonSidesFromAngle(double angle) {
		throw new RuntimeException("implement me!");
	}

	/**
	 * Given the number of sides, draw a regular polygon.
	 * 
	 * (0,0) is the lower-left corner of the polygon; use only right-hand turns to
	 * draw.
	 * 
	 * @param turtle     the turtle context
	 * @param sides      number of sides of the polygon to draw
	 * @param sideLength length of each side
	 */
	public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
		// throw new RuntimeException("implement me!");
		for (int i = 0; i < sides; i++) {
			turtle.forward(sideLength);
			turtle.turn(calculateRegularPolygonAngle(sides));
		}
	}

	/**
	 * Given the current direction, current location, and a target location,
	 * calculate the Bearing towards the target point.
	 * 
	 * The return value is the angle input to turn() that would point the turtle in
	 * the direction of the target point (targetX,targetY), given that the turtle is
	 * already at the point (currentX,currentY) and is facing at angle
	 * currentBearing. The angle must be expressed in degrees, where 0 <= angle <
	 * 360.
	 *
	 * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
	 * 
	 * @param currentBearing current direction as clockwise from north
	 * @param currentX       current location x-coordinate
	 * @param currentY       current location y-coordinate
	 * @param targetX        target point x-coordinate
	 * @param targetY        target point y-coordinate
	 * @return adjustment to Bearing (right turn amount) to get to target point,
	 *         must be 0 <= angle < 360
	 */
	public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY, int targetX,
			int targetY) {
		// throw new RuntimeException("implement me!");
		double rotOriginal = 0;
		rotOriginal = Math.atan2(targetX - currentX, targetY - currentY) * 180 / Math.PI - currentBearing; // 极坐标计算
		if (rotOriginal < 0)
			return 360 + rotOriginal;
		return rotOriginal;
	}

	/**
	 * Given a sequence of points, calculate the Bearing adjustments needed to get
	 * from each point to the next.
	 * 
	 * Assumes that the turtle starts at the first point given, facing up (i.e. 0
	 * degrees). For each subsequent point, assumes that the turtle is still facing
	 * in the direction it was facing when it moved to the previous point. You
	 * should use calculateBearingToPoint() to implement this function.
	 * 
	 * @param xCoords list of x-coordinates (must be same length as yCoords)
	 * @param yCoords list of y-coordinates (must be same length as xCoords)
	 * @return list of Bearing adjustments between points, of size 0 if (# of
	 *         points) == 0, otherwise of size (# of points) - 1
	 */
	public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
		// throw new RuntimeException("implement me!");
		List<Double> rotEachStep = new ArrayList<Double>();
		int xIni = xCoords.get(0);
		xCoords.remove(0);
		int yIni = yCoords.get(0);
		yCoords.remove(0);
		double currentBearing = 0;
		double angle;
		while (xCoords.size() != 0) {
			angle = calculateBearingToPoint(currentBearing, xIni, yIni, xCoords.get(0), yCoords.get(0));
			rotEachStep.add(angle);
			currentBearing = angle;
			xIni = xCoords.get(0);
			yIni = yCoords.get(0);
			xCoords.remove(0);
			yCoords.remove(0);
		}
		return rotEachStep;
	}

	/**
	 * Given a set of points, compute the convex hull, the smallest convex set that
	 * contains all the points in a set of input points. The gift-wrapping algorithm
	 * is one simple approach to this problem, and there are other algorithms too.
	 * 
	 * @param points a set of points with xCoords and yCoords. It might be empty,
	 *               contain only 1 point, two points or more.
	 * @return minimal subset of the input points that form the vertices of the
	 *         perimeter of the convex hull
	 */
	// Jarvis’s Algorithm
	public static Set<Point> convexHull(Set<Point> points) {

		if (points.size() <= 2) // 上文提及可能有 1 个或 2 个点的情况
			return points;

		Point[] pointsArray = new Point[points.size()]; // 将 set 转变为数组
		points.toArray(pointsArray);
		Set<Point> outcomePoints = new HashSet<Point>();
		// 找到最左点
		int l = 0;
		int n = points.size();

		for (int i = 1; i < n; i++)
			if (pointsArray[i].x() < pointsArray[l].x())
				l = i;

		int p = l, q;
		do {
			outcomePoints.add(pointsArray[p]);
			q = (p + 1) % n;
			for (int i = 0; i < n; i++) {
				if (orientation(pointsArray[p], pointsArray[i], pointsArray[q]) == 2)
					q = i;
			}
			p = q;
		} while (p != l);
		return outcomePoints;
	}

	// 顺时针或者逆时针
	public static int orientation(Point p, Point q, Point r) {
		double val = (q.y() - p.y()) * (r.x() - q.x()) - (q.x() - p.x()) * (r.y() - q.y());
		if (val == 0)
			return 0;
		return (val > 0) ? 1 : 2;
	}

	/**
	 * Draw your personal, custom art.
	 * 
	 * Many interesting images can be drawn using the simple implementation of a
	 * turtle. For this function, draw something interesting; the complexity can be
	 * as little or as much as you want.
	 * 
	 * @param turtle the turtle context
	 */
	public static void drawPersonalArt(Turtle turtle) {
		// throw new RuntimeException("implement me!");
		for (int i = 0; i < 400; i++) {
			turtle.forward(2 * i);
			turtle.color(paintColor(i % 4));
			turtle.turn(91);
		}
	}

	public static PenColor paintColor(int colorCode) {
		switch (colorCode) {
		case 0:
			return PenColor.RED;
		case 1:
			return PenColor.YELLOW;
		case 2:
			return PenColor.MAGENTA;
		case 3:
			return PenColor.BLUE;
		}
		return PenColor.BLACK;
	}

	/**
	 * Main method.
	 * 
	 * This is the method that runs when you run "java TurtleSoup".
	 * 
	 * @param args unused
	 */
	public static void main(String args[]) {
		DrawableTurtle turtle = new DrawableTurtle();

		// drawSquare(turtle, 40);
		// drawRegularPolygon(turtle, 8, 40);
		drawPersonalArt(turtle);
		System.out.println(calculateBearingToPoint(1.0, 4, 5, 4, 6));

		Point p11 = new Point(1, 1);
		Point p1010 = new Point(10, 10);
		Point p110 = new Point(1, 10);
		/*
		 * Point p1010 = new Point(10, 10); Point p110 = new Point(1, 10); Point p12 =
		 * new Point(1, 2); Point p23 = new Point(2, 3); Point p32 = new Point(3, 2);
		 */
		Set<Point> points = new HashSet<Point>();
		points.add(p11);
		points.add(p1010);
		points.add(p110);

		Set<Point> outcomePoints = TurtleSoup.convexHull(points);

		for (Point element : outcomePoints) {
			System.out.println(element.x());
		}

		// draw the window
		turtle.draw();
	}

}
