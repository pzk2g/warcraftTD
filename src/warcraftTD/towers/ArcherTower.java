package warcraftTD.towers;

import warcraftTD.missiles.Arrow;
import warcraftTD.missiles.Missile;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class ArcherTower extends Tower {
	/**
	 * Classe qui gère les tours d'archers
	 * @param p la position de la tour
	 */
	public ArcherTower(Position p) {
		super(p, "images/ArcherTower.png", 15, 50, 0.2);
	}
	
	@Override
	public Missile attack(Monster m) {
		long tps = System.currentTimeMillis();
		if (tps-this.time>this.speed*100) {
			this.time = tps;
			if (m.p.dist(this.p)<=super.reach){
				return new Arrow(super.p.clone(), m);
			}
		}
		return null;
	}
}
