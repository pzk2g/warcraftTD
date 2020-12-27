package warcraftTD.towers;

import warcraftTD.missiles.Missile;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;

public abstract class Tower {
	//position du tour à l'instant t
	public Position p;
	//vitesse d'attaque de la tour
	public int speed;
	//portée de l'attaque de la tour
	public double reach;
	//prix de contruction d'une tour
	public int price;
	//vitesse des projectiles
	public double speedMissile;
	//chemins des tours à afficher
	String image;
	//temps de rechargement des projectiles
	protected long time;
	//niveau de la tour
	int level;
	/**
	 * Classe abstraite qui permet de gérer les différentes types de tours 
	 * @param p la Position de la tour
	 * @param chemin l'url de l'image de la tour
	 * @param speed la vitesse de rechargement de la tour
	 * @param price le prix de production de la tour
	 * @param reach la distance de visée de la tour
	 */
	public Tower(Position p, String chemin, int speed, int price, double reach) {
		this.p = p;
		this.speed = speed*10;
		this.price = price;
		this.image = chemin;
		this.reach = reach;
		this.time = System.currentTimeMillis();
		this.level = 1;
	}
	
	/**
	 * Dessine une image de la tour dans le cadrillage
	 * @param normalizedX la taille en largeur d'un carré du cadrillage
	 * @param normalizedY la taille en longeur d'un carré du cadrillage
	 */
	public void update(double normalizedX, double normalizedY) {
		draw(normalizedX, normalizedY);
	}
	
	/**
	 * Lance un projectile en direction du monster m
	 * @param m le monstre pris en cible
	 */
	public abstract Missile attack(Monster m);
	
	public void draw(double normalizedX, double normalizedY) {
		StdDraw.picture(p.x, p.y, image, normalizedX, normalizedY);
	}
	
	/**
	 * Indique si la tour est améliorable ou non
	 * @return vrai si la tour est améliorable
	 */
	public boolean isUpdatable() {
		return level<3;
	}
	
	//TODO: mettre la méthode abstraite et la redéfinir pour les deux classes filles
	// et faire la javadoc
	public void updating() {
		
	}
}
