package Moteur;

import java.util.ArrayList;

public interface Strategie {

	public abstract void poserCarteStrategique(JoueurVirtuel j);
	public abstract void echangerCartes();
	public abstract void echangerCartes(JoueurVirtuel j);
	public abstract void piocher(int nbCartesPosees, Joueur j);

}
