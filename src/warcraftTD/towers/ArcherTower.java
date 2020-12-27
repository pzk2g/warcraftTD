package warcraftTD.towers;

import warcraftTD.missiles.Arrow;
import warcraftTD.missiles.Missile;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class ArcherTower extends Tower {
	public ArcherTower(Position p) {
		super(p, "images/ArcherTower.png", 15, 50, 0.2);
	}
	
	public Missile attack(Monster m) {
		long tps = System.currentTimeMillis();
		if (tps-this.temps>this.speed*100) {
			this.temps = tps;
			if (m.p.dist(this.p)<=super.reach){
				return new Arrow(super.p.clone(), m, 3);
			}
		}
		return null;
	}
}
