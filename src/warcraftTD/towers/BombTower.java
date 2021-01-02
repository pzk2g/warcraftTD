package warcraftTD.towers;

import warcraftTD.missiles.Bomb;
import warcraftTD.missiles.Missile;
import warcraftTD.monsters.BaseMonster;
import warcraftTD.monsters.Boss;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class BombTower extends Tower{

	public static final String IMAGE = "images/BombTowerLevel1.png";
	public static final int SPEED = 20;
	public static final int PRICE = 60;
	public static final double  REACH = 0.12;

	/**
	 * Classe des tours de Bombes
	 * @param p la position de la tour
	 */
	public BombTower(Position p) {
		super(p,IMAGE, SPEED, PRICE, REACH);
	}
	
	@Override
	public Missile attack(Monster m) {
		long tps = System.currentTimeMillis();
		if (tps-this.time>this.speed) {
			if (m instanceof BaseMonster || 
			((m instanceof Boss) && ((Boss)m).state=='w'))
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
		this.speed -= (SPEED/3)*20;
		this.reach += 0.02;
	}
}
