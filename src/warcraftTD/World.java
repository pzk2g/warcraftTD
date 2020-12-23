package warcraftTD;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import warcraftTD.missiles.Missiles;
import warcraftTD.monsters.Monster;
import warcraftTD.towers.ArcherTower;
import warcraftTD.towers.BombTower;
import warcraftTD.towers.Tower;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;

public class World {
	// l'ensemble des monstres, pour gerer (notamment) l'affichage
	LinkedList<Monster> monsters = new LinkedList<Monster>(); //List --> Linked!list
	
	// l'ensemble des monstres, pour gerer (notamment) l'affichage
	List<Tower> towers = new LinkedList <Tower>();

	//l'ensemble des missiles, pour gerer (notamment) l'affichage
	List<Missiles> missiles = new LinkedList<Missiles>();
	
	// Position par laquelle les monstres vont venir
	Position spawn;
	
	// Information sur la taille du plateau de jeu
	int width;
	int height;
	int nbSquareX;
	int nbSquareY;
	double squareWidth;
	double squareHeight;
	
	// Nombre de points de vie du joueur
	int life = 20;
	
	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;
	
	// Condition pour terminer la partie
	boolean end = false;
	
	//Chemin du plateau
	Map<Position, Position> path; //position et la position suivante
	
	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de cases données
	 * @param width
	 * @param height
	 * @param nbSquareX
	 * @param nbSquareY
	 * @param startSquareX
	 * @param startSquareY
	 */
	public World(int width, int height, int nbSquareX, int nbSquareY, int startSquareX, int startSquareY) {
		this.width = width;
		this.height = height;
		this.nbSquareX = nbSquareX;
		this.nbSquareY = nbSquareY;
		squareWidth = (double) 1 / nbSquareX;
		squareHeight = (double) 1 / nbSquareY;
		spawn = new Position(startSquareX * squareWidth + squareWidth / 2, startSquareY * squareHeight + squareHeight / 2);
		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
		path = new TreeMap<Position, Position>(); 
		
		//initialise le chemin
		int[][] chemin = {{3, 19}, {3, 18}, {3, 17}, {3, 16}, {3, 15}, {4, 15}, {5, 15}, {6, 15}, {7, 15},
		{8, 15}, {9, 15}, {10, 15}, {11, 15}, {12, 15}, {13, 15}, {14, 15}, {15, 15}, {15, 16}, {15, 17}, 
		{15, 18}, {15, 19}, {16, 19}, {17, 19}, {18, 19}, {19, 19}};
//		int [][] chemin = {{3, 12}, {3, 11}, {3, 10}, {3, 9}, {3, 8}, {3, 7}, {4,7}, {5, 7}, 
//				{6, 7}, {7, 7}, {7, 8}, {7, 9}, {8, 9}, {9, 9}, {10, 9}, {11, 9}, {12, 9},{12, 10}, {12, 11}, {12, 12}};
		for (int i=0; i<chemin.length-1; i++){
			Position p = new Position(chemin[i][0] * squareWidth + squareWidth / 2, chemin[i][1] * squareHeight + squareHeight / 2);
			Position nextP = new Position(chemin[i+1][0] * squareWidth + squareWidth / 2, chemin[i+1][1] * squareHeight + squareHeight / 2);
			path.put(p, nextP);
		}
		
		int n = chemin.length-1;
		Position p = new Position(chemin[n][0] * squareWidth + squareWidth / 2, chemin[n][1] * squareHeight + squareHeight / 2);
		path.put(p, null);
		System.out.println(path);
	}
	
	/**
	 * Définit le décors du plateau de jeu.
	 */
	 public void drawBackground() {
		 for (int i = 0; i < nbSquareX; i++)
			 for (int j = 0; j < nbSquareY; j++)
				 StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2, "images/Grass.png", squareWidth, squareHeight);
		 
		 for (Position p: path.keySet()) {
			 StdDraw.picture(p.x, p.y, "images/Path.png", squareWidth, squareHeight);
		 }
	 }
	 
	 /**
	  * Initialise le chemin sur la position du point de départ des monstres. Cette fonction permet d'afficher une route qui sera différente du décor.
	  */
	 public void drawPath() {
		 Position p = new Position(spawn);
		 StdDraw.setPenColor(StdDraw.YELLOW);
		 StdDraw.filledRectangle(p.x, p.y, squareWidth / 2, squareHeight / 2);
	 }
	 
	 /**
	  * Affiche certaines informations sur l'écran telles que les points de vie du joueur ou son or
	  */
	 public void drawInfos() {
		 StdDraw.setPenColor(StdDraw.BLACK);
	 }
	 
	 /**
	  * Fonction qui récupère le positionnement de la souris et permet d'afficher une image de tour en temps réél
	  *	lorsque le joueur appuie sur une des touches permettant la construction d'une tour.
	  */
	 public void drawMouse() {
		double normalizedX = (int)(StdDraw.mouseX() / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int)(StdDraw.mouseY() / squareHeight) * squareHeight + squareHeight / 2;
		Position mouse = new Position(normalizedX, normalizedY);
		switch (key) {
		case 'a' :
			if (!path.containsKey(mouse)) StdDraw.picture(normalizedX, normalizedY,  "images/ArcherTower.png", squareWidth, squareHeight);
			break;
		case 'b' :
			if (!path.containsKey(mouse)) StdDraw.picture(normalizedX, normalizedY,  "images/BombTower.png", squareWidth, squareHeight);
			break;
		case 'z' :
			break;
		}
//		if (image != null)
//			StdDraw.picture(normalizedX, normalizedY,  image, squareWidth, squareHeight);
	 }
		 
	 /**
	  * Pour chaque monstre de la liste de monstres de la vague, utilise la fonction update() qui appelle les fonctions run() et draw() de Monster.
	  * Modifie la position du monstre au cours du temps à l'aide du paramètre nextP.
	  */
	 public void updateMonsters() {
		List<LinkedList<Monster>> v = vague();
		for(LinkedList<Monster> listMonsters : v ) {
		Iterator<Monster> i = listMonsters.iterator();
		Monster m;
		while (i.hasNext()) {
			 m = i.next();
			 Position nextP = path.get(m.p);
			 if (path.containsKey(m.p)) {
				 m.nextP = nextP;
				 m.checkpoint++;
				 if (m.nextP==null) {
					 m.reached=true;
				 }
			 }
			 if (!m.reached && m.life!=0) m.update(squareWidth, squareHeight);
			 else {
				 //suppression du monstre
				 monsters.remove(m);
				 life--;
			 }
		 }
		 //pas eu l'effet recherché
		 try {
            		Thread.sleep(5000);
        	}
        		catch (InterruptedException e) {
           		System.out.println("Error sleeping");
       		}
	 	}
	 }
	//s'est avéré pas concluant :-(
	 public List<LinkedList<Monster>> vague(){
		 List<LinkedList<Monster>> v = new LinkedList<LinkedList<Monster>>();
		 v.add(monsters);
		 return v;
	 } 
	 
	 
	 /**
	  * Pour chaque tour, on vérifie si la tour doit attaquer ou pas
	  */
	 public void updateTowers() {
		 Iterator<Tower> i = towers.iterator();
		 Tower t;
		 while (i.hasNext()) {
			 t = i.next();
			 //cherche un monstre qui est dans sa zone de tir
			 ArrayList<Monster> lmonsters = new ArrayList<Monster>(monsters);
			 int index = 0;
			 boolean find = false;
			 Missiles missile=null;
			 while (index!=lmonsters.size() && !find){
				 missile = t.attack(lmonsters.get(index));
				 if (missile!=null) find=true;
				 index++;
			 }
			 if (missile!=null) missiles.add(missile);
			 t.update(squareWidth, squareHeight);
		 }
	 }

	 public void updateMissiles(){

	 }
	 
	 /**
	  * Met à jour toutes les informations du plateau de jeu ainsi que les déplacements des monstres et les attaques des tours.
	  * @return les points de vie restants du joueur
	  */
	 public int update() {
		drawBackground();
		drawPath();
		drawInfos();
		updateMonsters();
		updateTowers();
		drawMouse();
		return life;
	 }
	 
	/**
	 * Récupère la touche appuyée par l'utilisateur et affiche les informations pour la touche séléctionnée
	 * @param key la touche utilisée par le joueur
	 */
	public void keyPress(char key) {
		key = Character.toLowerCase(key);
		this.key = key;
		switch (key) {
		case 'a':
			System.out.println("Arrow Tower selected (50g).");
			break;
		case 'b':
			System.out.println("Bomb Tower selected (60g).");
			break;
		case 'e':
			System.out.println("Evolution selected (40g).");
			break;
		case 's':
			System.out.println("Starting game!");
		case 'q':
			System.out.println("Exiting.");
		}
	}
	
	/**
	 * Vérifie lorsque l'utilisateur clique sur sa souris qu'il peut: 
	 * 		- Ajouter une tour à la position indiquée par la souris.
	 * 		- Améliorer une tour existante.
	 * Puis l'ajouter à la liste des tours
	 * @param x
	 * @param y
	 */
	public void mouseClick(double x, double y) {
		double normalizedX = (int)(x / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int)(y / squareHeight) * squareHeight + squareHeight / 2;
		Position mouse = new Position(normalizedX, normalizedY);
		switch (key) {
		case 'a':
			if (!path.containsKey(mouse)) towers.add(new ArcherTower(mouse));
			break;
		case 'b':
			if (!path.containsKey(mouse)) towers.add(new BombTower(mouse));
			break;
		case 'e':
			System.out.println("Ici il est possible de faire évolué une des tours");
			break;
		}
	}
	
	/**
	 * Comme son nom l'indique, cette fonction permet d'afficher dans le terminal les différentes possibilités 
	 * offertes au joueur pour intéragir avec le clavier
	 */
	public void printCommands() {
		System.out.println("Press A to select Arrow Tower (cost 50g).");
		System.out.println("Press B to select Cannon Tower (cost 60g).");
		System.out.println("Press E to update a tower (cost 40g).");
		System.out.println("Press Z to cancel a selection");
		System.out.println("Click on the grass to build it.");
		System.out.println("Press S to start.");
	}
	
	/**
	 * Récupère la touche entrée au clavier ainsi que la position de la souris et met à jour le plateau en fonction de ces interractions
	 */
	public void run() {
		printCommands();
		while(!end) {
			
			StdDraw.clear();
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}
			
			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}
			//TODO: generer des monstres
			
			update();
			StdDraw.show();
			StdDraw.pause(20);			
		}
	}
}
