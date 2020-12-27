package warcraftTD.missiles;

import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class Arrow extends Missile {
	
	/**
	 * Classe qui gère les flèches
	 * @param p la position de la flèche
	 * @param target le monstre visé par la flèche
	 */
    public Arrow(Position p, Monster target){
        super(p, target,  0.04, "images/Arrow.png", 2);
    }
}

