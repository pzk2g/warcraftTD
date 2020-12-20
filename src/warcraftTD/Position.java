package warcraftTD;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Position {
	double x;
	double y;
	
	/**
	 * Classe qui permet d'avoir la position sur l'axe des x et des y des monstres et des tours
	 * @param x
	 * @param y
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Position p) {
		x = p.x;
		y = p.y;
	}
	
	@Override
	public boolean equals(Object p) {
		if (p==null) return false;
		if (p instanceof Position) {
			final Position p1 = (Position) p;
			return this.equals(p1, 0.0001);
		}
		return false;
	}

	
	/**
	 * Mesure la distance euclidienne entre 2 positions.
	 * @param p
	 * @return
	 */
	public double dist(Position p) {
		return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
	}

	/**
	 * Retourne la position du point sur l'axe des x et des y
	 */
	@Override
	public String toString() {
		return "(" + Double.toString(x) + "," + Double.toString(y) + ")";
	}
	
	public boolean equals(Position p, double epsilon) {
		return this.x-epsilon<= p.x &&  p.x <=this.x+epsilon && this.y-epsilon<=p.y && p.y<=this.y+epsilon;
	}
	
//	public static void main(String[] args) {
//		Position p1 = new Position(1, 1);
//		Position p2 = new Position(10, 10);
//		Position p3 = new Position(100, 100);
//		List<Position> truc = new ArrayList<Position>();
//		truc.add(p1);
//		truc.add(p2);
//		truc.add(p3);
//		Position p4 = new Position(10, 100);
//		System.out.println(truc.indexOf(p4)); //rennvoe faux
//	}
}
