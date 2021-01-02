package warcraftTD.util.buttons;
import warcraftTD.util.Position;


public abstract class Button {
	protected Position p;
	protected long time;
	protected double width;
	protected double heigth;
	
	public Button(Position p, int width, int height) {
		this.p = p;
		this.width = width;
		this.heigth = height;
		this.time = System.currentTimeMillis();
	}
	
	public abstract boolean action();
	
	public boolean mouseIn(Position mouse) {
		return isBetween(mouse.x, p.x, width) && isBetween(mouse.y, p.y, heigth);
	}
	
	private boolean isBetween(double x, double a, double epsilon) {
		return a-epsilon<=x && x<=a+epsilon;
	}
	
	public abstract void draw();
}
