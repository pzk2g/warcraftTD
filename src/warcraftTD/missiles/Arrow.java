package warcraftTD.missiles;

import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class Arrow extends Missiles {
    public Arrow(Position p, Monster target, int damage){
        super(p, target,  0.04, "images/Arrow.png", damage);
    }
}

