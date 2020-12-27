package warcraftTD.missiles;

import warcraftTD.monsters.Monster;
import warcraftTD.util.ImageMobile;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;

public abstract class Missile extends ImageMobile{
	public Monster target;
	public int damage;
	
	public Missile(Position p, Monster target, double speed, String image, int damage) {
		super(image, p, target.p, speed);
		this.target = target;
		this.damage = damage;
	}

	@Override
	public void draw(double normalizedX, double normalizedY){
		double angle = calculateAngle(this.p, this.nextP);
		StdDraw.picture(this.p.x, this.p.y, this.image, normalizedX, normalizedY, angle);
	}
	
	private double calculateAngle(Position p1, Position p2) {
		double dx = this.nextP.x - this.p.x;
		double dy = this.nextP.y - this.p.y;
		double angle;
		if (dx==0) angle=dy>0?90:270;
		else {
			angle = (double) Math.toDegrees(Math.atan(dy/Math.abs(dx)));
			angle = (dx<0)? -(angle+180):angle;
		}
		return angle;
	}
	
	public void hit() {
		int life = this.target.life - this.damage;
		this.target.life = (life<0)?0:life;
	}
	
}
