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
		super(p, "images/BombTowerLevel1.png", 20, 60, 0.15);
	}
	
	@Override
	public Missile attack(Monster m) {
		long tps = System.currentTimeMillis();
		if (tps-this.time>this.speed) {
			if (m instanceof BaseMonster)
				if (m.p.dist(this.p) <= this.reach) {
					this.time = tps;
					return new Bomb(this.p.clone(), m);
				}
		}
		return null;
	}
	
	@Override
	public void updating() {
		this.level++;
		this.image = String.format("images/BombTowerLevel%d.png", this.level);
		this.speed -=15;
		this.reach += 0.008;
	}
}
