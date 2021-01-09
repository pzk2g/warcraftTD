package warcraftTD.util.buttons;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;


public abstract class Button {
	protected Position p;
	protected double width;
	protected double heigth;
	private char action;
	
	public Button(Position p, char action, double width, double height) {
		this.p = p;
		this.width = width;
		this.heigth = height;
		this.action = action;
	}
	
	public boolean isClicked() {
		return StdDraw.isMousePressed() && mouseIn();
	}
	
	public boolean mouseIn() {
		return isBetween(StdDraw.mouseX(), p.getX(), width/2) && isBetween(StdDraw.mouseY(), p.getY(), heigth/2);
	}
	
	private boolean isBetween(double x, double a, double epsilon) {
		return a-epsilon<=x && x<=a+epsilon;
	}
	
	public abstract void draw();
	
	public char getAction() {
		return action;
	}
}
