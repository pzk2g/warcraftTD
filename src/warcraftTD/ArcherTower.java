package warcraftTD;

public class ArcherTower extends Tower {
	public ArcherTower(Position p) {
		super(p, "images/ArcherTower.png", 15, 50, 0.3);
	}
	
	protected boolean attackTo(Monster m) {
		if (m instanceof FlyingMonster) {
			return m.p.equals(this.p, 0.1);
		}
		else return false;
	}
}
