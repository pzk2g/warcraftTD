package warcraftTD.missiles;

import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class Bomb extends Missile {
	//TODO : changer d'image de la bombe : plutot une roquette
	/**
	 * Classe qui gère les bombes
	 * @param p la position du missile
	 * @param target le monstre visé par la bombe
	 */
    public Bomb(Position p, Monster target){
        super(p, target, 0.02, "images/Bomb.png", 8);
    }
    

}
