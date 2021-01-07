package warcraftTD.monsters;

import warcraftTD.util.Position;

public class Boss extends Monster {
    public static final String IMAGE = "images/Boss.png";
    protected long time;
    public char state;

    public Boss(Position p, int level) {
        super(IMAGE, p, level>3?3:level);
    }
    //TODO : faire des javadoc
    @Override
    protected void setReward(int level) {
        this.reward = 0;
        for (int i=1; i<=level; i++) this.reward +=60;
    }

    @Override
    protected double setSpeed(int level) {
        double speed = 0.005;
        for (int i=1; i<level; i++) speed+=0.003;
        return speed;
    }
    
    @Override
    protected void setLife(int level) {
        switch (level) {
        case 1:
        	life = 100;
        	break;
        case 2:
        	life = 150;
        	break;
        case 3:
        	life = 200;
        	break;
        default:
        	throw new IllegalArgumentException("Level must be between 1 et 3");
        }
    }
    
//    @Override
//    public void draw(double normalisedX, double normalisedY) {
//    	//TODO : à faire draw
//    }
}