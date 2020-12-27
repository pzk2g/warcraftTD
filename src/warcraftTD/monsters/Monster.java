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
	
	//TODO : ajouter des niveaux pour les monstres
	
	/**
	 * Classe abstraite qui gèrent les monstres
	 * @param image le chemin l'image dans les fichiers
	 * @param p la position de l'image
	 * @param life le nombre de points de vie du monstre
	 * @param speed la vitesse du monstre
	 * @param reward la récompense en or lorsque l'on tue le monstre
	 */
	public Monster(String image, Position p, int life, double speed, int reward) {
		super(image, p, p.clone(), speed);
		this.life = life;
		this.reward = reward;
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
}
