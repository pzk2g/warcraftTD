package warcraftTD;

public abstract class Monster {
	// Position du monstre à l'instant t
	Position p;
	// Vitesse du monstre
	double speed;
	// Position du monstre à l'instant t+1
	Position nextP;
	// Boolean pour savoir si le monstre à atteint le chateau du joueur
	boolean reached;
	// Compteur de déplacement pour savoir si le monstre à atteint le chateau du joueur
	int checkpoint = 0;
	//Recompense en or lorsque l'on tue le montre
	int reward;
	//Nombre de vies du monstre
	int life;
	//Chemain de l'image pour le monstre
	String image;
	
	
	public Monster(Position p,  String image, int life, double speed, int reward) {
		this.p = p;
		this.nextP = new Position(p);
		this.image = image;
		this.life = life;
		this.reward = reward;
		this.speed = speed;
	}
	
	/**
	 * Déplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		// Mesure sur quel axe le monstre se dirige.
		double dx = nextP.x - p.x;
		double dy = nextP.y - p.y;
		if (dy + dx != 0){
			// Mesure la distance à laquelle le monstre à pu se déplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			p.x += ratioX * speed;
			p.y += ratioY * speed;
		}
	}

	public void update(double normalizedX, double normalizedY) {
		move();
		draw(normalizedX, normalizedY);
		checkpoint++;
	}
	
	/**
	 * Affiche un monstre qui change de couleur au cours du temps
	 * Le monstre est représenté par un cercle de couleur bleue ou grise
	 */
	public void draw(double normalizedX, double normalizedY) {
		StdDraw.picture(p.x, p.y, this.image, normalizedX, normalizedY);
	}
}
