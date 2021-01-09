package warcraftTD;

import java.awt.Font;
import java.util.Iterator;
import java.util.LinkedList;

import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;
import warcraftTD.util.buttons.Button;
import warcraftTD.util.buttons.ButtonText;

final public class Menu {
	private int width;
	private int height;
	private int nbSquareX;
	private int nbSquareY;
	private int startX;
	private int startY;
	private LinkedList<Button> buttons;
	private Font f;
	
	//TODO : javadoc
	public Menu() {
		width = 1500;
		height = 800;
		nbSquareX = 16;
		nbSquareY = 16;
		startX = 14;
		startY = 15;
		menu();

		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
	}
	
	public void menu() {
		StdDraw.setScale();
		f = new Font("TimesNewRoman", Font.BOLD, 60);
		buttons = new LinkedList<Button>();
		buttons.add(new ButtonText(new Position(0.5, 0.5), "Play", 'p' , f, width, height));
		buttons.add(new ButtonText(new Position(0.5, 0.3), "Rules", 'r', f, width, height));
		buttons.add(new ButtonText(new Position(0.5, 0.1), "Exit", 'q', f, width, height));
	}
	

	public char updateButton() {
		Iterator<Button> ib = buttons.iterator();
		char c = ' ';
		while (ib.hasNext()) {
			Button b = ib.next();
			if (b.isClicked() && c==' ') c = b.getAction();
			b.draw();
		}
		return c;
	}
	
	public void selected(char c) {
		switch (c){
		case 'p':
			playGame();
			break;
		case 'r':
			rules();
			break;
		case 'q':
			System.exit(0);
		}
	}
	
	public void playGame() {
		int nbWaves = 10;
		World w = new World(width, height, nbSquareX, nbSquareY, startX, startY, nbWaves);
		w.run();
		//une fois que joue est fini, on revient au menu
		menu();
	}
	
	public void rules() {
		
	}
	
	public void drawBackGround() {
		StdDraw.picture(0.5, 0.5, "images/menuBackground.jpg", 1, 1);
		StdDraw.picture(0.5, 0.8, "images/titre.png");
	}
	
	public void run() {
		boolean quitter = false;
		while (!quitter) {
			drawBackGround();
			selected(updateButton());
			StdDraw.show();
		}
	}
	
	
	
	
}