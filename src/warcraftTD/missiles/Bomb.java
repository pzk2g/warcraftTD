package warcraftTD.missiles;

import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class Bomb extends Missile {
	public static final String IMAGE = "images/Bomb.png";
	public static final double SPEED = 0.02;
	public static final int DAMAGE = 8;


	/**
	 * Classe qui gère les bombes
	 * @param p la position du missile
	 * @param target le monstre visé par la bombe
	 */
    public Bomb(Position p, Monster target){
        super(p, target, SPEED, "images/Bomb.png", DAMAGE);
    }
    

}
