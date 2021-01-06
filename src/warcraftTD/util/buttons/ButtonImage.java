package warcraftTD.util.buttons;

import java.awt.Color;

import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;

public class ButtonImage extends Button{
	private String image;
	
	public ButtonImage(String image, Position p, double width, double height) {
		super(p, width, height);
		this.image = image;
	}

	@Override
	public boolean action() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw() {
		StdDraw.picture(p.getX(), p.getY(), image, width, heigth);
		if (mouseIn()) {
			StdDraw.setPenColor(new Color(255, 242, 200, 100));
			StdDraw.filledRectangle(p.getX(), p.getY(), width/2, heigth/2);
		}
		
	}
}
