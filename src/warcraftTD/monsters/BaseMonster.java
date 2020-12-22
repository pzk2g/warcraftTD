package warcraftTD.monsters;

import warcraftTD.util.Position;

public class BaseMonster extends Monster {
	int niveau; //niveau du montre
	public BaseMonster(Position p) {
		super("images/BaseMonster.png", p, 5, 0.005, 5);
	}
}
