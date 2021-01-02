package warcraftTD;

import warcraftTD.monsters.BaseMonster;
import warcraftTD.monsters.FlyingMonster;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class Main {

	public static void main(String[] args) {
		int width = 1200;
		int height = 800;
		int nbSquareX = 15;
		int nbSquareY = 15;
		int startX = 3;
		int startY = 14;
		
		World w = new World(width, height, nbSquareX, nbSquareY, startX, startY);
		
;		// Lancement de la boucle principale du jeu
		w.run();
	}
}

