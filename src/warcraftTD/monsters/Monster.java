package warcraftTD.monsters;

import warcraftTD.util.ImageMobile;
import warcraftTD.util.Position;

public abstract class Monster extends ImageMobile {
	// Boolean pour savoir si le monstre à atteint le chateau du joueur
	public boolean reached;
	//Recompense en or lorsque l'on tue le montre
	public int reward;
	//Nombre de vies du monstre
	public int life;
	//Niveau du monstre
	public int level;
	
	/**
	 * Classe abstraite qui gèrent les monstres
	 * @param image le chemin l'image dans les fichiers
	 * @param p la position de l'image
	 * @param life le nombre de points de vie du monstre
	 * @param speed la vitesse du monstre
	 * @param reward la récompense en or lorsque l'on tue le monstre
	 */
	public Monster(String image, Position p, int life, double speed, int reward, int level) {
		super(image, p, p.clone(), speed);
		this.life = life;
		this.reward = reward;
		this.level = level;
	}

	/**
	 * Met à jour le monstre à l'échelle vis-à-vis des autres images
	 * @param normalizedX l'échelle des x
	 * @param normalizedY l'échelle des y
	 */
	@Override
	public void update(double normalizedX, double normalizedY) {
		super.update(normalizedX, normalizedY);
	}

	public boolean hasReached(){
		return reached;
	}
	public boolean isDead(){
		return (level==1 && life==0);
	}

	public abstract void takeLifePoint(int damage);

	/**
	 * Enlève un point de vie au monstre
	 * @param life le nombre de point de vie au début de la vie du monstre
	 */
	protected void takeLifePoint(int life, int damage){
		this.life -= damage;
		if (this.life<0) this.life = 0;
		if (this.life==0){
			switch (level){
				case 2:
					level--;
					this.life = life;
					break;
				case 3:
					level--;
					this.life = life;
					break;
			}
		}
	}

	public void levelUp(){
		//TODO : augmente le niveau du monstre de 1 (doit être abstraite)
	}

}
