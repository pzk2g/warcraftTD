package warcraftTD.towers;

import warcraftTD.missiles.Missiles;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;

public abstract class Tower {
	//position du tour à l'instant t
	Position p;
	//vitesse d'attaque de la tour
	int speed;
	//portée de l'attaque de la tour
	double reach;
	//prix de contruction d'une tour
	int price;
	//vitesse des projectiles
	double speedMissile;
	//chemins des tours à afficher
	String image;
	
	public Tower(Position p, String chemin, int speed, int price, double reach) {
		this.p = p;
		this.speed = speed;
		this.price = price;
		this.image = chemin;
		this.reach = reach;
	}
	
	public void update(double normalizedX, double normalizedY) {
		draw(normalizedX, normalizedY);
	}
	
	/**
	 * Lance un projectile en direction du monster m
	 * @param m le monstre pris en cible
	 */
	public abstract Missiles attack(Monster m);
	
	public void draw(double normalizedX, double normalizedY) {
		StdDraw.picture(p.x, p.y, image, normalizedX, normalizedY);
	}
}
