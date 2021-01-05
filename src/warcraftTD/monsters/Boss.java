package warcraftTD.monsters;

import warcraftTD.util.Position;

public class Boss extends Monster {
    
    public  int life;
    public  int reward;
    public  double speed;
    public static final String IMAGE = "images/Boss.png";
    protected long time;
    public char state;

    public Boss(Position p, int level) {
        super(IMAGE, p, setLife(level), setSpeed(level), setReward(level), level);
    }
    //TODO : faire des switch
    private static int setReward(int level) {
        int reward = 0;
        for (int i=0; i<level; i++) reward +=60;
        return reward;
    }

    private static double setSpeed(int level) {
        double speed = 0.005;
        for (int i=1; i<level; i++) speed+=0.003;
        return speed;
    }
    
    public static int setLife(int level) {
        int life;
        switch (level) {
        case 1:
        	life = 15;
        	break;
        case 2:
        	life = 25;
        	break;
        case 3:
        	life = 50;
        	break;
        default:
        	throw new IllegalArgumentException("Level must be between 1 et 3");
        }
        return life;    
    }
    
    @Override
    public void takeLifePoint(int damage) {
        takeLifePoint(life, damage);
    }

    public char getState(){
        //TODO : à finir
        return ' ';
    }
    
//    @Override
//    public void draw(double normalisedX, double normalisedY) {
//    	//TODO : à faire draw
//    }
}