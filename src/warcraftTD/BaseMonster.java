package warcraftTD.monsters;

//TODO : creer un boss (une classe fille)
public class BaseMonster extends Monster {
	public static final int LIFE = 5;
	public static final int REWARD = 5;
	public static final double SPEED = 0.005;
	public static final String IMAGE = "images/BaseMonster.png";
	
	/**
	 * Classe qui gère les monstres qui marchent
	 * @param p une position
	 * @param level un entier compris entre 1 et 4
	 */
	public BaseMonster(Position p, int level) {
		super(IMAGE, p, LIFE, SPEED, REWARD, level);
	}

	@Override
	public void takeLifePoint(int damage){
		takeLifePoint(LIFE, damage);
	}
}
