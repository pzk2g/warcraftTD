package warcraftTD.monsters;
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
        if(level==1) reward = 60;
        else if(level==2) reward = 110;
        else if(level==3) reward = 160;
        return reward;
    }

    private static double setSpeed(int level) {
        double speed = 0;
        if(level==1) speed = 0.02;
        else if(level==2) speed = 0.05;
        else if(level==3) speed = 0.08;
        return speed;
    }
    
    public static int setLife(int level) {
        int life = 0;
        if(level==1) life = 15;
        else if(level==2) life = 25;
        else if(level==3) life = 50;;
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
}
