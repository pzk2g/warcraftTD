package warcraftTD.towers;

import warcraftTD.missiles.Bomb;
import warcraftTD.missiles.Missile;
import warcraftTD.monsters.BaseMonster;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class BombTower extends Tower{
	/**
	 * Classe des tours de Bombes
	 * @param p la position de la tour
	 */
	public BombTower(Position p) {
		super(p, "images/BombTower.png", 20, 60, 0.15);
	}
	
	@Override
	public Missile attack(Monster m) {
		long tps = System.currentTimeMillis();
		if (tps-this.time>this.speed*100) {
			this.time = tps;
			if (m instanceof BaseMonster)
				if (m.p.dist(super.p) <= super.reach)
					return new Bomb(super.p.clone(), m);
		}
		return null;
	}
}
