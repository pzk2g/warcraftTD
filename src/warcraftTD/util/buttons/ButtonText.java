package warcraftTD.util.buttons;
import warcraftTD.util.Position;


public class ButtonText extends Button{
	String text;
	
	public ButtonText(String text, Position p, int width, int height) {
		super(p, width, height);
		this.text = text;
	}

	@Override
	public boolean action() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}