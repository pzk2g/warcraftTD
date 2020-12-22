package warcraftTD.monsters;

import warcraftTD.util.ImageMobile;
import warcraftTD.util.Position;

public abstract class Monster extends ImageMobile {
	// Boolean pour savoir si le monstre à atteint le chateau du joueur
	public boolean reached;
	// Compteur de déplacement pour savoir si le monstre à atteint le chateau du joueur
	public int checkpoint = 0;
	//Recompense en or lorsque l'on tue le montre
	public int reward;
	//Nombre de vies du monstre
	public int life;
	
	
	public Monster(String image, Position p, int life, double speed, int reward) {
		super(image, p, new Position(p), speed);
		this.life = life;
		this.reward = reward;
	}

	public void update(double normalizedX, double normalizedY) {
		super.update(normalizedX, normalizedY);
		checkpoint++;
	}
}
