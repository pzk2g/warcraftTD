package warcraftTD;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import warcraftTD.missiles.Missile;
import warcraftTD.monsters.Monster;
import warcraftTD.towers.ArcherTower;
import warcraftTD.towers.BombTower;
import warcraftTD.towers.Tower;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.awt.Font; 
import java.util.Random;



public class World {
	// l'ensemble des monstres, pour gerer (notamment) l'affichage
	LinkedList<Monster> monsters = new LinkedList<Monster>();
	
	// l'ensemble des monstres, pour gerer (notamment) l'affichage
	ArrayList<Tower> towers = new ArrayList <Tower>();

	//l'ensemble des missiles, pour gerer (notamment) l'affichage
	List<Missile> missiles = new LinkedList<Missile>();
	
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
	
	//Nombre d'argent du joueur
	int money = 10000;
	
	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;
	
	// Condition pour terminer la partie
	boolean end = false;
	
	//Chemin des monstres
	ArrayList<Position> pathMonsters;
	//Positions des images du chemin
	Set<Position> path;
	
	//Liste des monstres
	Deque<Integer> waves = new LinkedList<Integer>();
	long timeWave; //temps en entre deux vagues;
	long timeMonster; //temps d'apparition entre chaque monstres;
	
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
		spawn = createPosition(startSquareX, startSquareY);
		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
		initPath(startSquareX, startSquareY);
		initWaves();
	}

	/**
	 * Initialise le chemin sur lequel passera les monstres
	 */
	public void initPath(int startSquareX, int startSquareY){
		//Prend des points au hasard dans le cadrillage
		int[][] chemin = new int[5][2]; //on prend 5 points sur le cadrillage
		chemin[0][0] = startSquareX; chemin[0][1] = startSquareY;
		Random rd = new Random();
		int index = 1;
		while (index!=4) {
			int x = rd.nextInt(nbSquareX-2);
			int y = rd.nextInt(nbSquareY);
			int j = 0;
			//vérfie certaines conditions sur les points
			//les points doivent être sur des lignes et des colonnes différentes
			while  (j!=index && !(equalsTo(chemin[j][0], x, 1) || equalsTo(chemin[j][1], y, 1))) j++;
			if (j==index) {
				chemin[index][0]=x; chemin[index][1]=y;
				index++;
			}
		}
		//ajout du point d'arrivé
		chemin[4][0] = nbSquareX-1;
		chemin[4][1] = nbSquareY-1;
		
		//on transforme les points en Position dans le canevas
		//on relie les points
		pathMonsters = new ArrayList<Position>();
		path = new TreeSet<Position>();
		//on parcourt la liste de point créer précédement
		for (int i=1; i<chemin.length; i++) {
			int x = chemin[i-1][0]; int y = chemin[i-1][1];
			int nextx = chemin[i][0]; int nexty = chemin[i][1];
			int dx = nextx - x;
			int dy = nexty - y;
			pathMonsters.add(createPosition(x,y));
			//on relie horizontalement
			for (int n=x; n!=nextx; n=n+signe(dx))
				path.add(createPosition(n, y));
			pathMonsters.add(createPosition(nextx, y));
			//on relie verticalement
			for (int n=y; n!=nexty; n=n+signe(dy))
				path.add(createPosition(nextx, n));
		}
		Position p = createPosition(chemin[chemin.length-1][0], chemin[chemin.length-1][1]);
		path.add(p);
		pathMonsters.add(p);
	}
	
	private int signe(int n) {
		return (n>0)?1:-1;
	}
	
	private boolean equalsTo(int a, int x, int epsilon) {
		return a-epsilon<=x && x<=a+epsilon;
	}

	private Position createPosition(int x, int y) {
		return new Position(x * squareWidth + squareWidth / 2, y * squareHeight + squareHeight / 2);
	}

	/**
	 * Initialise les vagues : le nombres de vagues de monstres et le nombre de monstres par vagues
	 */
	public void initWaves(){
		Random rd = new Random();
		int nbwaves = rd.nextInt(5+1) + 10;
		int nbmonstremin = 2;
		for (int i=0; i<nbwaves; i++){
			int nbmonsters = rd.nextInt(nbmonstremin+1) + nbmonstremin;
			if (i%5==0) nbmonsters = Integer.MAX_VALUE; //Integer.MAX_VALUE représente un boss
			waves.push(nbmonsters);
			nbmonstremin *= 2;
		}
	}
	
	/**
	 * Définit le décors du plateau de jeu.
	 */
	 public void drawBackground() {
		 for (int i = 0; i < nbSquareX; i++)
			 for (int j = 0; j < nbSquareY; j++)
				 StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2, "images/Grass.png", squareWidth, squareHeight);
	 }
	 
	 /**
	  * Initialise le chemin sur la position du point de départ des monstres. Cette fonction permet d'afficher une route qui sera différente du décor.
	  */
	 public void drawPath() {
		 for (Position p: path)
			 StdDraw.picture(p.x, p.y, "images/Path.png", squareWidth, squareHeight);
		 Position sp = spawn.clone();
		 //TODO : modifier l'image de départ
		 StdDraw.setPenColor(StdDraw.YELLOW);
		 StdDraw.filledRectangle(sp.x, sp.y, squareWidth / 2, squareHeight / 2);
		 StdDraw.picture((nbSquareX-1) * squareWidth + squareWidth / 2, (nbSquareY-1) * squareHeight + squareHeight / 2, "images/Castel.png", squareWidth, squareHeight);
		  
	 }
	 
	 /**
	  * Affiche certaines informations sur l'écran telles que les points de vie du joueur ou son or
	  */
	 public void drawInfos() {
		 //Configuration de la police
		 StdDraw.setFont(new Font("TimesNewRoman", Font.BOLD, 20));
		 StdDraw.setPenColor(StdDraw.BLACK);
		 //Affichage de l'argent :
		 StdDraw.setPenRadius(0.01);
		 StdDraw.picture(0.03, 0.97, "images/gold.png");
		 StdDraw.text(0.07, 0.97, Integer.toString(money));
		 //Affichage des vies
		 StdDraw.picture(0.03, 0.92, "images/heart.png");
		 StdDraw.text(0.07, 0.92, Integer.toString(life));
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
			if (!path.contains(mouse)) StdDraw.picture(normalizedX, normalizedY,  "images/ArcherTowerLevel1.png", squareWidth, squareHeight);
			break;
		case 'b' :
			if (!path.contains(mouse)) StdDraw.picture(normalizedX, normalizedY,  "images/BombTowerLevel1.png", squareWidth, squareHeight);
			break;
		case 'e': 
			//indique par une flèche les tours qui peuvent être évoluées
			for (Tower t: towers) {
				switch (t.level){
					case 1:
						StdDraw.picture(t.p.x, t.p.y, "images/up1.png");
						break;
					case 2:
						StdDraw.picture(t.p.x, t.p.y, "images/up2.png");
						break;
				}
			}
			break;
		case 'z' : //on désélectionne
			break;
		case 'r': //TODO : à finir pour avoir le rayon d'une tour
			break;
		}
	 }
		 
	 /**
	  * Pour chaque monstre de la liste de monstres de la vague, utilise la fonction update() qui appelle les fonctions run() et draw() de Monster.
	  * Modifie la position du monstre au cours du temps à l'aide du paramètre nextP.
	  */
	 public void updateMonsters() {
		Iterator<Monster> it = monsters.iterator();
		Monster m;
		while (it.hasNext()) {
			 m = it.next();
			 //on cherche la prochaine position du monstre dans le chemin
			 if (pathMonsters.contains(m.p)) {
				 int i = pathMonsters.indexOf(m.p) + 1;
				 if (i<pathMonsters.size())
					 m.nextP = pathMonsters.get(i);
				 else {
					 m.reached = true;
				 }
			 }
			 //TODO : faudrait plutot creer une méthode dans monster
			 //isDead() et une méthode hasReached()
			 if (!m.hasReached() && !m.isDead()) m.update(squareWidth, squareHeight);
			 else {
				 //suppression du monstre
				 if (m.isDead()) this.money += m.reward;
				 it.remove();
				 if (m.reached) life--;
			 }
		 }
	 }
	 
	 
	 
	 /**
	  * Pour chaque tour, on vérifie si la tour doit attaquer ou pas
	  */
	 public void updateTowers() {
		 Iterator<Tower> it = towers.iterator();
		 Tower t;
		 while (it.hasNext()) {
			 t = it.next();
			 //cherche un monstre qui est dans sa zone de tir
			 Iterator<Monster> itm = monsters.iterator();
			 boolean find = false;
			 Missile missile = null;
			 while (itm.hasNext() && !find){
				 Monster m = itm.next();
				 missile = t.attack(m);
				 find = missile!=null;
			 }
			 if (find) missiles.add(missile);
			 t.update(squareWidth, squareHeight);
		 }
	 }
	 
	 /*
	  * Met à jour les projectiles : les faits avancer et les détruits si besoin
	  */
	 public void updateMissiles(){
		 Iterator<Missile> it = missiles.iterator();
		 Missile msl;
		 while (it.hasNext()) {
			 msl = it.next();
			 //TODO : à modifier, ne rentre pas dans la condition
			 if (msl.p.equals(msl.target.p, 0.01)) {
				 msl.hit();
				 it.remove();
			 }
			 else msl.update(squareWidth, squareHeight);
		 }
	 }
	 
	 /**
	  * Met à jour toutes les informations du plateau de jeu ainsi que les déplacements des monstres et les attaques des tours.
	  * @return les points de vie restants du joueur
	  */
	 public int update() {
		drawBackground();
		drawPath();
		updateMonsters();
		updateTowers();
		updateMissiles();
		drawInfos();
		drawMouse();
		return life;
	 }

	/**
	 * Récupère la touche appuyée par l'utilisateur et affiche les informations pour la touche séléctionnée
	 * @param key la touche utilisée par le joueur
	 */
	public void keyPress(char key) {
		//TODO : si vraiment on est motivé : créer une classe Message qui affiche un message à l'écran
		//pour notamment supprimer les print dans la console
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
		case 'r':
			System.out.println("Reach selected");
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
		
		//Set des positions où l'on ne peut pas construire de tours
		TreeMap<Position, Tower> positions = new TreeMap<Position, Tower>();
		for (Tower t : towers) {
			positions.put(t.p, t);
		}
		//TODO : si vraiment on est motivé : créer une classe Message qui affiche un message à l'écran
		//pour notamment supprimer les print dans la console
		switch (key) {
		case 'a':
			if (!path.contains(mouse) && !positions.containsKey(mouse)) {
				if (this.money>=ArcherTower.PRICE) {
					towers.add(new ArcherTower(mouse));
					this.money-=ArcherTower.PRICE;
				}
				else System.out.println("Vous n'avez pas assez d'or !");
			}
			break;
		case 'b':
			if (!path.contains(mouse) && !positions.containsKey(mouse)) {
				if (this.money>=BombTower.PRICE) {
					towers.add(new BombTower(mouse));
					this.money-=BombTower.PRICE;
				}
				else System.out.println("Vous n'avez pas assez d'or !");
				
			}
			break;
		case 'e':
			if (positions.containsKey(mouse)) {
				Tower t = positions.get(mouse);
				if (this.money>=Tower.UPDATEPRICE && t.isUpdatable()){
				t.updating();
				this.money -= Tower.UPDATEPRICE;
				}
				else System.out.println("La tour ne peut pas être mise à jour !");
			}
			break;
		case 'r':
			System.out.println("Ici, on voit le rayon de visé d'une tour");
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
		System.out.println("Click on the grass to build it.");
		System.out.println("Press Z to cancel a selection");
		System.out.println("Press R to see the reach of a tower");
		System.out.println("Press S to start.");
	}
	
	/**
	 * Genère une vague de monstres
	 * @return true ssi une vague a été générée
	 */
	public boolean generateWave(){
		//TODO : completer
		return true;
	}
	
	/**
	 * Récupère la touche entrée au clavier ainsi que la position de la souris et met à jour le plateau en fonction de ces interractions
	 */
	public void run() {
		printCommands();
		while(!end) {	
			StdDraw.clear();
			if (StdDraw.hasNextKeyTyped()) {
				System.out.println();
				keyPress(StdDraw.nextKeyTyped());
			}
			
			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}

			end = update()==0 || key=='q';
			StdDraw.show();
			StdDraw.pause(20);
		}
	}
}
