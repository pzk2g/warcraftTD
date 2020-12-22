package warcraftTD.towers;

import warcraftTD.missiles.Bomb;
import warcraftTD.missiles.Missiles;
import warcraftTD.monsters.BaseMonster;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class BombTower extends Tower{
	public BombTower(Position p) {
		super(p, "images/BombTower.png", 20, 60, 0.15);
	}
	
	public Missiles attack(Monster m) {
		if (m instanceof BaseMonster)
			if (m.p.dist(super.p) <= super.reach)
				return new Bomb(super.p, m, 8);
		return null;
	}
}
