package warcraftTD;
import java.util.Random;

import warcraftTD.monsters.BaseMonster;
import warcraftTD.monsters.Boss;
import warcraftTD.monsters.FlyingMonster;
import warcraftTD.monsters.Monster;
import warcraftTD.util.Position;

public class Waves {
	private class Wave{
		// comteur de temps d'apparition entre chaque monstres;
		long timeMonster;
		//temps pour générer un monstre
		int timesGenerateMonster;
		//miveau des monstres
		int level;
		//nombre de monstres à créer;
		int nbMonster;
		//
		boolean premier;

		public Wave(int level, int nbMonster){
			this.level = level;
			this.nbMonster = nbMonster;
			this.timeMonster = System.currentTimeMillis();
			this.timesGenerateMonster = (new Random()).nextInt(1000)+500;
			this.premier = true;
		}
		
		public Monster createMonster(char type, Position beginMonster) {
			Monster m = null;
			long time = System.currentTimeMillis();
			if (time-timeMonster>timesGenerateMonster || premier) {
				if (premier) premier = false;
				switch (type) {
				case 'w':
					m = new BaseMonster(beginMonster, level);
					break;
				case 'f':
					m = new FlyingMonster(beginMonster, level);
					break;
				case 'b':
					m = new Boss(beginMonster, level);
					break;
				}
				this.timesGenerateMonster = (new Random()).nextInt(1000)+500;
				timeMonster = time;
				nbMonster--;
			}
			return m;
		}
		
		public boolean isEmpty() {
			return nbMonster==0;
		}
	}
	
	// nombre de vagues
	int nbWaves;
	// nombre de monstres
	int nbMonsters;
	int waveCounter;
	//vague courant
	Wave wave;
	//niveau des monstres
	int level;
	
	public Waves(int nbWaves) {
		this.nbWaves = nbWaves;
		this.nbMonsters = 2;
		this.waveCounter = 1;
		this.level = 1;
		this.wave = new Wave(level, nbMonsters);
		this.nbMonsters += 10;
	}

	public void newWave() {
		if (waveCounter%(nbWaves/3)==0) {
			wave = new Wave(level, 1);
			nbMonsters = 2;
			level++;
		}
		else {
			wave = new Wave(level, nbMonsters);
			nbMonsters += 10;
		}
		waveCounter++;
	}
	
	public Monster createMonster(Position beginMonster) {
		char c;
		if (waveCounter%(nbWaves/3)!=0){
			Random rd = new Random();
			c = "wf".charAt(rd.nextInt("wf".length()));
		}
		else c = 'b';
		return wave.isEmpty()?null:wave.createMonster(c, beginMonster);
	}
	
	public boolean end() {
		return waveCounter == nbWaves && wave.isEmpty();
	}
	
}
