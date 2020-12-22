package warcraftTD;

abstract public class ImageMobile {
	Position p;
	Position nextP;
	String image;
	double speed;
	
	public ImageMobile(String image, Position p,  Position nextP, double speed) {
		this.p = p;
		this.image = image;
		this.nextP = nextP;
		this.speed = speed;
	}
	
	/**
	 * Déplace l'image en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		// Mesure sur quel axe le monstre se dirige.
		double dx = nextP.x - p.x;
		double dy = nextP.y - p.y;
		if (dy + dx != 0){
			// Mesure la distance à laquelle le monstre à pu se déplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			double px = ratioX * speed + p.x;
			double py = ratioY * speed + p.y;
			
			//condition pour voir si on a dépassé nextP
			if (dx>0 && px>nextP.x) px = nextP.x;
			if (dy>0 && py>nextP.y) py = nextP.y;
			if (dx<0 && px<nextP.x) px = nextP.x;
			if (dy<0 && py<nextP.y) py = nextP.y;
			p.x = px;
			p.y = py;
		}	
	}
	
	public void update(double normalizedX, double normalizedY) {
		move();
		draw(normalizedX, normalizedY);
	}
	
	/**
	 * Affiche une image normalisée en fonction de la taille de la fenetre
	 */
	public void draw(double normalizedX, double normalizedY) {
		StdDraw.picture(p.x, p.y, this.image, normalizedX, normalizedY);
	}
}
