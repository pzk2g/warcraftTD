package warcraftTD.towers;

import warcraftTD.missiles.Bomb;
import warcraftTD.missiles.Missile;
import warcraftTD.monsters.BaseMonster;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class BombTower extends Tower{
	public BombTower(Position p) {
		super(p, "images/BombTower.png", 20, 60, 0.15);
	}
	
	public Missile attack(Monster m) {
		long tps = System.currentTimeMillis();
		if (tps-this.temps>this.speed*100) {
			this.temps = tps;
			if (m instanceof BaseMonster)
				if (m.p.dist(super.p) <= super.reach)
					return new Bomb(super.p.clone(), m, 8);
		}
		return null;
	}
}
