package warcraftTD;



public class Main {

	public static void main(String[] args) {
		int width = 1200;
		int height = 800;
		int nbSquareX = 15;
		int nbSquareY = 15;
		int startX = 13;
		int startY = 14;
		
		World w = new World(width, height, nbSquareX, nbSquareY, startX, startY);
		
		// Lancement de la boucle principale du jeu
		w.run();
	}
}

