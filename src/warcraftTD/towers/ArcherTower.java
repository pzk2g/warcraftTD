package warcraftTD.towers;

import warcraftTD.missiles.Arrow;
import warcraftTD.missiles.Missiles;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class ArcherTower extends Tower {
	public ArcherTower(Position p) {
		super(p, "images/ArcherTower.png", 15, 50, 0.2);
	}
	
	public Missiles attack(Monster m) {
		if (m.p.dist(this.p)<=super.reach){
			return new Arrow(super.p, m, 3);
		}
		return null;
	}
}
