package warcraftTD;

public class Main {

	public static void main(String[] args) {
		int width = 1200;
		int height = 800;
		int nbSquareX = 20;
		int nbSquareY = 20;
		int startX = 3;
		int startY = 19;
		
		World w = new World(width, height, nbSquareX, nbSquareY, startX, startY);
		
		// Ajout d'un monstre "à la main" pour afficher comment un monstre se déplaçe. Vous ne devez pas faire pareil, mais ajouter une vague comportant plusieurs monstres 
		Monster monster = new FlyingMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2));
		Monster monster2 = new BaseMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2));
		w.monsters.add(monster);
		w.monsters.add(monster2);
;		// Lancement de la boucle principale du jeu
		w.run();
	}
}

