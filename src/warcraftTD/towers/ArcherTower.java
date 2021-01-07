package warcraftTD.towers;

import warcraftTD.missiles.Arrow;
import warcraftTD.missiles.Missile;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;


public class ArcherTower extends Tower {
	public static final String IMAGE = "images/ArcherTowerLevel1.png";
	public static final int SPEEDRECHARGING = 15;
	public static final int PRICE = 50;
	public static final double  REACH = 0.12;

	/**
	 * Classe qui gère les tours d'archers
	 * @param p la position de la tour
	 */
	public ArcherTower(Position p) {
		super(p, IMAGE, SPEEDRECHARGING, REACH);
	}
	
	@Override
	public Missile attack(Monster m) {
		long tps = System.currentTimeMillis();
		if (tps-this.time>this.speedReacharging) {
			if (m.getP().dist(this.getP())<=this.reach){
				this.time = tps;
				return new Arrow(this.getP().clone(), m);
			}
		}
		return null;
	}
	
	@Override
	public void updating() {
		this.setLevel(this.getLevel()+1);
		this.setImage(String.format("images/ArcherTowerLevel%d.png", this.getLevel()));
		this.speedReacharging -= (SPEEDRECHARGING/2)*10;
		this.reach += 0.05;
	}
}
