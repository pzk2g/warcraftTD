package warcraftTD.monsters;

import warcraftTD.util.Position;

public class FlyingMonster extends Monster {
	public static final int LIFE = 3;
	public static final int REWARD = 8;
	public static final double SPEED = 0.01;
	public static final String IMAGE = "images/FlyingMonster.png";
	
	/**
	 * Classe qui gère les monstres volants
	 * @param p
	 */
	public FlyingMonster(Position p, int level) {
		super(IMAGE, p, LIFE, SPEED, REWARD, level);
	}

	@Override
	public void takeLifePoint(int damage){
		takeLifePoint(LIFE, damage);
	}
}
