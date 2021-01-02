package warcraftTD.towers;

import warcraftTD.missiles.Arrow;
import warcraftTD.missiles.Missile;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;


public class ArcherTower extends Tower {
	public static final String IMAGE = "images/ArcherTowerLevel1.png";
	public static final int SPEED = 15;
	public static final int PRICE = 50;
	public static final double  REACH = 0.15;

	/**
	 * Classe qui gère les tours d'archers
	 * @param p la position de la tour
	 */
	public ArcherTower(Position p) {
		super(p, IMAGE, SPEED, PRICE, REACH);
	}
	
	@Override
	public Missile attack(Monster m) {
		long tps = System.currentTimeMillis();
		if (tps-this.time>this.speed) {
			if (m.p.dist(this.p)<=this.reach){
				this.time = tps;
				return new Arrow(this.p.clone(), m);
			}
		}
		return null;
	}
	
	@Override
	public void updating() {
		System.out.println("Mise à jour de la tour ");
		this.level++;
		this.image = String.format("images/ArcherTowerLevel%d.png", this.level);
		this.speed -= (SPEED/2)*20;
		this.reach += 0.05;
	}
}
