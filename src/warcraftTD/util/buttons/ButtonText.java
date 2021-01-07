package warcraftTD.util.buttons;
import java.awt.Font;

import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;


public class ButtonText extends Button{
	private String text;
	private Font f;
	
	public ButtonText(Position p, String text, char action, Font f, double width, double height) {
		super(p, action, text.length()*f.getSize()/(2*width), f.getSize()/(2*height));
		this.text = text;
		this.f = f;
	}

	@Override
	public void draw() {
		StdDraw.setFont(f);
		if (mouseIn()) StdDraw.setPenColor(StdDraw.RED);
		else StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(p.getX(), p.getY(), text);
		
	}
}