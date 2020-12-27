package warcraftTD.missiles;

import warcraftTD.monsters.Monster;
import warcraftTD.util.ImageMobile;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;

/*
 * Classe qui permettra de gérer les projectiles
 */
public abstract class Missile extends ImageMobile{
	//cible visée par le projectil
	public Monster target;
	//dommages créés par le projectile lorsqu'il atteint la cible
	public int damage;
	
	
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
	
	
	
	public void hit() {
		int life = this.target.life - this.damage;
		this.target.life = (life<0)?0:life;
	}
	
}
