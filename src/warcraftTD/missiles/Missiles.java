package warcraftTD.missiles;

import warcraftTD.monsters.Monster;
import warcraftTD.util.ImageMobile;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;

public abstract class Missiles extends ImageMobile{
	Monster target;
	int damage;
	
	public Missiles(Position p, Monster target, double speed, String image, int damage) {
		super(image, p, target.p, speed);
	}

	@Override
	public void draw(double normalizedX, double normalizedY){
		double angle = (double) Math.toDegrees(Math.atan2(this.p.x - this.nextP.x, this.p.y - this.nextP.y));
		StdDraw.picture(this.p.x, this.p.y, this.image, normalizedX, normalizedY, angle);
	}
	
}
