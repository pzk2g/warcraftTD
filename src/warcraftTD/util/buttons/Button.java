package warcraftTD.util.buttons;
import warcraftTD.util.Position;
import warcraftTD.util.StdDraw;


public abstract class Button {
	protected Position p;
	protected double width;
	protected double heigth;
	
	public Button(Position p, double width, double height) {
		this.p = p;
		this.width = width;
		this.heigth = height;
	}
	
	public boolean action() {
		return StdDraw.isMousePressed() && mouseIn();
	}
	
	public boolean mouseIn() {
		return isBetween(StdDraw.mouseX(), p.getX(), width) && isBetween(StdDraw.mouseY(), p.getY(), heigth);
	}
	
	private boolean isBetween(double x, double a, double epsilon) {
		return a-epsilon<=x && x<=a+epsilon;
	}
	
	public abstract void draw();
}
