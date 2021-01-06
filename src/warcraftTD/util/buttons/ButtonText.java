package warcraftTD.util.buttons;
import java.awt.Font;

import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;


public class ButtonText extends Button{
	String text;
	
	public ButtonText(String text, Position p, Font f, double width, double height) {
		super(p, text.length()*f.getSize()/width, f.getSize()/height);
		System.out.println(text.length()*f.getSize()/width + " " + f.getSize()/height);
		StdDraw.setFont(f);
		this.text = text;
	}

	@Override
	public void draw() {
		if (mouseIn()) StdDraw.setPenColor(StdDraw.RED);
		else StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(p.getX(), p.getY(), text);
		
	}
}