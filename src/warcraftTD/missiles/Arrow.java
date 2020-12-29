package warcraftTD.missiles;

import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class Arrow extends Missile {
	public static final String IMAGE = "images/Arrow.png";
	public static final double SPEED = 0.04;
	public static final int DAMAGE = 2;
	/**
	 * Classe qui gère les flèches
	 * @param p la position de la flèche
	 * @param target le monstre visé par la flèche
	 */
    public Arrow(Position p, Monster target){
        super(p, target, SPEED , IMAGE, DAMAGE);
    }
}

