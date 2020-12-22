package warcraftTD;

public abstract class Missiles extends ImageMobile{
	Monster target;
	
	public Missiles(Position p, Monster target, String image) {
		super(image, p, target.p, 0.1);
	}
}
