package warcraftTD.towers;

import warcraftTD.missiles.Bomb;
import warcraftTD.missiles.Missile;
import warcraftTD.monsters.BaseMonster;
import warcraftTD.monsters.Boss;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class BombTower extends Tower{
	public static final String IMAGE = "images/BombTowerLevel1.png";
	public static final int SPEEDREACHARGING = 20;
	public static final int PRICE = 60;
	public static final double  REACH = 0.10;

	/**
	 * Classe des tours de Bombes
	 * @param p la position de la tour
	 */
	public BombTower(Position p) {
		super(p,IMAGE, SPEEDREACHARGING, REACH);
	}
	
	@Override
	public Missile attack(Monster m) {
		long tps = System.currentTimeMillis();
		if (tps-this.time>this.speedReacharging) {
			if (m instanceof BaseMonster || 
			((m instanceof Boss) && ((Boss)m).state=='w'))
				if (m.getP().dist(this.getP()) <= this.reach) {
					this.time = tps;
					return new Bomb(this.getP().clone(), m);
				}
		}
		return null;
	}
	
	@Override
	public void updating() {
		this.setLevel(this.getLevel()+1);
		this.setImage(String.format("images/BombTowerLevel%d.png", this.getLevel()));
		this.speedReacharging -= (SPEEDREACHARGING/3)*5;
		this.reach += 0.02;
	}
}
