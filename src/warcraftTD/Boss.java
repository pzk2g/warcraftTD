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
   	private static int setReward(int level) {
		int reward = 0;
		switch(level) {
		case 1:
			reward = 60;
			break;
		case 2:
			reward = 110;
			break;
		case 3:
			reward = 160;
			break;
		}
		return reward;
	}

	private static double setSpeed(int level) {
		double speed = 0;
		switch(level) {
		case 1:
			speed = 0.02;
			break;
		case 2:
			speed = 0.05;
			break;
		case 3:
			speed = 0.08;
			break;
		}
		return speed;
	}
	
	public static int setLife(int level) {
		int life = 0;
		switch(level) {
		case 1:
			life = 15;
			break;
		case 2:
			life = 25;
			break;
		case 3:
			life = 50;
			break;
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
}
