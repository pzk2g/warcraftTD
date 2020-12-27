package warcraftTD.monsters;

import warcraftTD.util.Position;

public class BaseMonster extends Monster {
	//TODO : ajouter les monstres plus résistants : des niveaux différents pour plus de difficulté
	//avec notamment des images différentes
	
	
	/**
	 * Classe qui gère les monstres qui marchent
	 * @param p
	 */
	public BaseMonster(Position p) {
		super("images/BaseMonster.png", p, 5, 0.005, 5);
	}
}
