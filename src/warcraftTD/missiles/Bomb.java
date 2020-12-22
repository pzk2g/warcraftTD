package warcraftTD.missiles;

import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class Bomb extends Missiles {
    public Bomb(Position p, Monster target, int damage){
        super(p, target, 0.02, "images/Bomb.png", damage);
    }

}
