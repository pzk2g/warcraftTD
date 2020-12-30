package warcraftTD.missiles;

import warcraftTD.monsters.Monster;
import warcraftTD.util.ImageMobile;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;


public abstract class Missile extends ImageMobile{
	//cible visée par le projectil
	public Monster target;
	//dommages créés par le projectile lorsqu'il atteint la cible
	public int damage;
	
	
	/**
	 * Classe abstraite qui permet de gérer les projectiles
	 * @param p la position du projectile
	 * @param target le monstre visé par le projectile
	 * @param speed la vitesse du projectile
	 * @param image le chemin du projectile
	 * @param damage les dommages infligée par le projectile
	 */
	public Missile(Position p, Monster target, double speed, String image, int damage) {
		super(image, p, target.p, speed);
		this.target = target;
		this.damage = damage;
	}

	
	@Override
	public void draw(double normalizedX, double normalizedY){
		double angle = super.calculateAngle(this.p, this.nextP);
		StdDraw.picture(this.p.x, this.p.y, this.image, normalizedX, normalizedY, angle);
	}
	
	
	/**
	 * Enlève des points de vies au monstre touché
	 */
	public void hit() {
		this.target.takeLifePoint(damage);
	}
	
}
