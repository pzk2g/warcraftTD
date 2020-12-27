package warcraftTD.missiles;

import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class Bomb extends Missile {
    public Bomb(Position p, Monster target){
        super(p, target, 0.02, "images/Bomb.png", 8);
    }
    

}
