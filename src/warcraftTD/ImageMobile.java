package warcraftTD.util;

abstract public class ImageMobile {
	//la position de l'image
	public Position p;
	//la position suivante (temporellement)  de l'image
	public Position nextP;
	//le chemin de l'image
	public String image;
	//vitesse de déplacement de l'image
	public double speed;
	
	
	/**
	 * Classe qui va permettre de gérer les images mobiles (comme les monstres, les projectiles)
	 * @param image url de l'image
	 * @param p la position de l'image
	 * @param nextP la position suivante (temporellement) de l'image 
	 * @param speed la vitesse de déplacement de l'image
	 */
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
		// Mesure sur quel axe l'image se dirige.
		double dx = nextP.x - p.x;
		double dy = nextP.y - p.y;
		if (dy + dx != 0){
			// Mesure la distance à laquelle l'image à pu se déplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			double px = ratioX * speed + p.x;
			double py = ratioY * speed + p.y;
			
			//conditions pour voir si on a dépassé nextP
			if (dx>0 && px>nextP.x) px = nextP.x;
			else if (dy>0 && py>nextP.y) py = nextP.y;
			else if (dx<0 && px<nextP.x) px = nextP.x;
			else if (dy<0 && py<nextP.y) py = nextP.y;
			p.x = px;
			p.y = py;
		}	
	}
	
	/**
	 * Dessine une image dans le canevas à échelle des autres images
	 * @param normalizedX l'échelle des x
	 * @param normalizedY l'échelle des y
	 */
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
	

	/**
	 * Calcul l'angle du mouvement de l'image en fonction de l'axe des abscisses
	 * @param pInit la position initiale
	 * @param pFinal la position finale
	 * @return l'angle entre la droite passant par pInit et pFinal et entre l'axe des
	 * abscisses.
	 */
	public static double calculateAngle(Position pInit, Position pFinal) {
		double dx = pFinal.x - pInit.x;
		double dy = pFinal.y - pInit.y;
		double angle;
		if (dx==0) angle=dy>0?90:270;
		else {
			angle = (double) Math.toDegrees(Math.atan(dy/Math.abs(dx)));
			angle = (dx<0)? -(angle+180):angle;
		}
		return angle;
	}
	
	/**
	 * Calcul l'angle du mouvement de l'image en fonction de l'axe des abscisses
	 * @return l'angle entre la droite passant par this.p et this.pNext et entre
	 * l'axe des abscisses.
	 */
	public double calculateAngle() {
		return calculateAngle(this.p, this.nextP);
	}
}
