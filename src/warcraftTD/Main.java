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
		
		// Ajout d'un monstre "à la main" pour afficher comment un monstre se déplaçe. Vous ne devez pas faire pareil, mais ajouter une vague comportant plusieurs monstres 
		Monster monster1 = new FlyingMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2), 1);
		Monster monster7 = new FlyingMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2), 1);
		Monster monster6 = new FlyingMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2), 1);
		Monster monster5 = new FlyingMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2), 1);
		Monster monster2 = new BaseMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2), 1);
		Monster monster3 = new BaseMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2), 1);
		Monster monster4 = new BaseMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2), 1);
		Monster monster8 = new FlyingMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2), 1);
		w.monsters.add(monster2);
		w.monsters.add(monster1);
//		w.monsters.add(monster3);
//		w.monsters.add(monster4);
//		w.monsters.add(monster5);
//		w.monsters.add(monster6);
//		w.monsters.add(monster7);
//		w.monsters.add(monster8);
;		// Lancement de la boucle principale du jeu
		w.run();
	}
}

