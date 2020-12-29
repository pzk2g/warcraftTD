package warcraftTD.monsters;

import warcraftTD.util.Position;

//TODO : creer un boss
public class FlyingMonster extends Monster {
	public static final int LIFE = 3;
	public static final int REWARD = 8;
	public static final double SPEED = 0.01;
	public static final String IMAGE = "images/FlyingMonster.png";
	
	//TODO : ajouter les monstres plus résistants : des niveaux différents pour plus de difficulté
	//avec notamment des images différentes
	
	
	/**
	 * Classe qui gère les monstres volants
	 * @param p
	 */
	public FlyingMonster(Position p, int level) {
		super(IMAGE, p, LIFE, SPEED, REWARD);
		this.setLevel(level);
	}

	@Override
	public void takeLifePoint(){
		takeLifePoint(LIFE);
	}
}
