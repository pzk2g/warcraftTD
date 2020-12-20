package warcraftTD;

public class BaseMonster extends Monster {
	int niveau; //niveau du montre
	public BaseMonster(Position p) {
		super(p, "images/BaseMonster.png", 5, 0.01, 5);
	}
}
