package warcraftTD.monsters;

import warcraftTD.util.Position;

public class FlyingMonster extends Monster {
	//TODO : ajouter les monstres plus résistants : des niveaux différents pour plus de difficulté
	//avec notamment des images différentes
	
	
	/**
	 * Classe qui gère les monstres volants
	 * @param p
	 */
	public FlyingMonster(Position p) {
		super("images/FlyingMonster.png", p, 3, 0.01, 8);
	}
}
