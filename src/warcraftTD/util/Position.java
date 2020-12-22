package warcraftTD.util;

public class Position implements Comparable<Position> {
	public double x;
	public double y;
	
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
	
	/**
	 * Mesure la distance euclidienne entre 2 positions.
	 * @param p
	 * @return
	 */
	public double dist(Position p) {
		return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
	}
	
	public boolean equals(Position p, double epsilon) {
		return this.x-epsilon<= p.x &&  p.x <=this.x+epsilon && this.y-epsilon<=p.y && p.y<=this.y+epsilon;
	}

	@Override
	public int compareTo(Position p) {
		//les deux positions sont égales
		if (p.equals(this)) return 0;
		//arbitrairement 
		else if (p.x<this.x) return -1;
		else if (p.x==this.x){
			if (p.y<this.y) return -1;
			else return 1;
		}
		else return 1;
	}

	@Override
	public boolean equals(Object p) {
		if (p==null) return false;
		if (p instanceof Position) {
			final Position p1 = (Position) p;
			return this.equals(p1, 0.0000001);
		}
		return false;
	}
	
	/**
	 * Retourne la position du point sur l'axe des x et des y
	 */
	@Override
	public String toString() {
		return "(" + Double.toString(x) + "," + Double.toString(y) + ")";
	}

}
