package warcraftTD.monsters;

import warcraftTD.util.Position;

public class Boss extends Monster {
     public static final String IMAGEFLY = "images/Boss.png";
    public static final String IMAGEWALK = "images/BossInFire.png";
    public static final long TRANSFORMTIME = 20000; //Le monstre se transforme au bout de 20 secondes
    public static String image = IMAGEFLY;
    public char state;

    public Boss(Position p, int level) {
        super(IMAGE, p, level>3?3:level);
        this.time = System.currentTimeMillis();
    }
	
    /**
     * Augmente la recompense donnée par le boss éliminé en fonction de son niveau dans une partie
     * @param level 
     */    @Override
    protected void setReward(int level) {
        this.reward = 0;
        for (int i=1; i<=level; i++) this.reward +=60;
    }
	
    /**
     * Augmente la vitesse du boss en fonction de son niveau dans une partie
     * @param level 
     */
    @Override
    protected double setSpeed(int level) {
        double speed = 0.005;
        for (int i=1; i<level; i++) speed+=0.003;
        return speed;
    }
	
   /**
     * Augmente le nombre de point de vie du boss en fonction de son niveau dans une partie
     * @param level 
     */ 
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
    /**
     * Transformation du Boss de monstre volant à monstre de base au cours du jeu
     */
    protected String transform() {
    	long t = System.currentTimeMillis();
    	if(t-this.time > TRANSFORMTIME) {
    		this.state = 'w';
    		image = IMAGEWALK;
    		this.time = t;
    	}
    	return image;
    }
    
    @Override
    public void draw(double normalizedX, double normalizedY) {
		StdDraw.picture(getP().getX(), getP().getY(), transform(), normalizedX, normalizedY);
	}
}
