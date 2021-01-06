package warcraftTD.util.buttons;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;


public class ButtonText extends Button{
	String text;
	
	public ButtonText(String text, Position p, int width, int height) {
		super(p, width, height);
		this.text = text;
	}

	@Override
	public boolean action() {
		
		return false;
	}

	@Override
	public void draw() {
		StdDraw.text(p.getX(), p.getY(), text);
		
	}
}