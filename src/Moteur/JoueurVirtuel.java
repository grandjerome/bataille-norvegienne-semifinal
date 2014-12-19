package Moteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JoueurVirtuel extends Joueur {

	/*
	 * (non-javadoc)
	 */

	public String[] nom = { "Roger", "Patrick", "Corentin", "Emilie", "Albert",
			"Alberta", "Alberte", "Albertine", "Albin", "Alda", "Aldo",
			"Aldegonde", "Alèthe", "Alex", "Alexandra" };// ,Alexandre,Alexandrine,Alexia,Alexiane,Alexis,Aleyde,Alfred,Alfreda,Alice,Alida,Aline,Alix,Aloïs,Aloysius,Alphonse,Alphonsine,Amaël,Amance,Amand,Amandine,Amoury,Ambroise,Amédée,Amélie,Amos,Anaïs,Anastase,Anastasie,Anatole,Andoche,André,Andrée,Ange,Angèle,Angéline,Angélique,Anicet,Anita,Anna,Annabelle,Anne,Annette,Annick,Annie,Annonciade,Anouchka,Anouck,Anselme,Anthelme,Anthony,Antoine,Antoinette,Antonin,Apollinaire,Apolline,Apollos,Arabelle,Arcadius,Arcady,Archibald,Ariane,Arielle,Aristide,Arlette,Armand,Armande,Armel,Armelle,Arnaud,Arnold,Arnould,Arsène,Arthur,Astrid,Athanase,Auberi,Aubert,Aubin,Aude,Audrey,Augusta,Auguste,Augustin,Augustine,Aure,Aurèle,Aurélia,Aurélie,Aurélien,Aurore,Ava,Avit,Axel,Axelle,Aymar,Aymeric,Aymone,Babette,Babine,Babita,Balbine,Balthazar,Baptiste,Barbara,Barbe,Barberine,Barnabé,Barnard,Barthélémy,Bartolomé,Basile,Bastien,Bastienne,Bathilde,Baudouin,Béatrice,Beatrix,Bénédicte,Benjamin,Benjamine,Benoît,Benoîte,Bérenger};
	static private int indiceNom = 0;


	private Strategie strategie;

	public JoueurVirtuel() {
		if (indiceNom == 15) {
			indiceNom = 0;
		}
		super.nomJoueur = nom[indiceNom];
		indiceNom++;
		int random = (int) (Math.random() * 2) + 1;
		if (random == 1) {
			this.strategie = new StrategieAleatoire();
		} else {
			this.strategie = new StrategieLeMeilleurPourLaFin();
		}
	}

	public void echangerCarte() {
		strategie.echangerCarte(this);
	}

	public int determinernbCartesJouables(ArrayList<Carte> main) {
		int nbCartesJouables = 0;
		ListIterator<Carte> it = main.listIterator();
		while (it.hasNext()) {
			Carte element = it.next();
			if (element.determinerCarteJouable()) {
				nbCartesJouables++;
			}
		}
		return nbCartesJouables;
	}

	/**
	 * Getter of the property <tt>nbCartes</tt>
	 * 
	 * @return Returns the nbCartes.
	 * 
	 */

	/**
	 * Setter of the property <tt>nbCartes</tt>
	 * 
	 * @param nbCartes
	 *            The nbCartes to set.
	 * 
	 */

	/**
	 * Getter of the property <tt>main</tt>
	 * 
	 * @return Returns the main.
	 * 
	 */

	/**
	 * Setter of the property <tt>main</tt>
	 * 
	 * @param main
	 *            The main to set.
	 * 
	 */
	public void jouerCarte() {
		strategie.poserCarteStrategique(this);

	}

	public String toString() {
		return ("Joueur Virtuel " + nomJoueur + " ");
	}

}
