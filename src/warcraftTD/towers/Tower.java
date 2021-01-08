package warcraftTD.towers;

import warcraftTD.missiles.Missile;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;

public abstract class Tower {
	//position du tour à l'instant t
	private final Position p;
	//vitesse d'attaque de la tour
	protected int speedReacharging;
	//portée de l'attaque de la tour
	protected double reach;
	//vitesse des projectiles
	protected double speedMissile;

	//chemins des tours à afficher
	private String image;
	//temps de rechargement des projectiles
	protected long time;
	//niveau de la tour
	private int level;
	//prix amélioration d'une tour
	public final static int UPDATEPRICE = 40;

	/**
	 * Classe abstraite qui permet de gérer les différentes types de tours 
	 * @param p la Position de la tour
	 * @param image l'url de l'image de la tour
	 * @param speed la vitesse de rechargement de la tour
	 * @param price le prix de production de la tour
	 * @param reach la distance de visée de la tour
	 */
	public Tower(Position p, String image, int speedReacharging, double reach) {
		this.p = p;
		this.speedReacharging = speedReacharging*25;
		this.setImage(image);
		this.reach = reach;
		this.time = System.currentTimeMillis();
		this.setLevel(1);
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
		StdDraw.picture(p.getX(), p.getY(), image, normalizedX, normalizedY);
	}
	
	/**
	 * Indique si la tour est améliorable ou non
	 * @return vrai si la tour est améliorable
	 */
	public boolean isUpdatable() {
		return level<3;
	}
	
	/**
	 * Augmente d'un niveau le niveau de la tour
	 */
	public abstract void updating();
	
	/**
	 * @return le chemin d'une image 
	 */	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Position getP() {
		return p;
	}
	
}
