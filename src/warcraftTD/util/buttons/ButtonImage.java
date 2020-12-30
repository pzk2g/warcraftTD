package warcraftTD.util.buttons;

import warcraftTD.util.Position;

public class ButtonImage extends Button{
	String chemin;
	
	public ButtonImage(String chemin, Position p, int width, int height) {
		super(p, width, height);
		this.chemin = chemin;
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