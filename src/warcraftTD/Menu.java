package warcraftTD;

import java.awt.Font;
import java.util.ArrayList;

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
	private ArrayList<Button> buttons;
	
	//TODO : javadoc
	public Menu() {
		width = 1200;
		height = 800;
		nbSquareX = 15;
		nbSquareY = 15;
		startX = 13;
		startY = 14;

		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
		Font f = new Font("TimesNewRoman", Font.BOLD, 60);
		buttons = new ArrayList<Button>();
		buttons.add(new ButtonText("Jouer", new Position(0.5, 0.5), f, width, height));
		buttons.add(new ButtonText("Règles", new Position(0.5, 0.3), f, width, height));
		buttons.add(new ButtonText("Quitter", new Position(0.5, 0.1), f, width, height));
		
	}
	
	public void playGame() {
		World w = new World(width, height, nbSquareX, nbSquareY, startX, startY, 10);
		w.drawBackground();
		w.drawPath();
		StdDraw.show();
		System.out.println("Press S to start.");
		System.out.println("Press Q to exit");
		char c=' ';
		do {
			if (StdDraw.hasNextKeyTyped()) {
				c = StdDraw.nextKeyTyped();
			}
		} while (Character.toUpperCase(c)!='S' && Character.toUpperCase(c)!='Q');
		if (Character.toUpperCase(c)=='S') w.run();
	}
	
	public void règles() {
		
	}
	
	public void drawBackGround() {
		StdDraw.picture(0.5, 0.5, "images/menuBackground.jpg", 1, 1);
		StdDraw.picture(0.5, 0.8, "images/titre.png");
	}
	
	public void run() {
		boolean quitter = false;
		while (!quitter) {

			drawBackGround();
			for (Button b : buttons) b.draw();
			StdDraw.show();
		}
	}
	
	
	
}
