package Moteur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Random;

public class StrategieLeMeilleurPourLaFin implements Strategie {

	public void poserCarteStrategique(JoueurVirtuel j) {
		ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		ArrayList<Carte> cartesNormalesJouables = new ArrayList<Carte>();
		ArrayList<Carte> cartesAPoser = new ArrayList<Carte>();
		Carte carteAPoser;
		cartesJouables = determinerCartesJouables(j.getmain());
		if (cartesJouables.size() > 0) {
			cartesNormalesJouables = determinerCartesNormalesJouables(cartesJouables);
			carteAPoser = determinerCarteAposer(cartesJouables,
					cartesNormalesJouables);
			cartesAPoser = determinerCartesAPoser(carteAPoser, cartesJouables);
			if (carteAPoser.estAs()) {
				ListIterator<Joueur> it = Partie.partie.getlistJoueur()
						.listIterator();
				Joueur joueurRecupereTalon = it.next();
				while (joueurRecupereTalon.getNom(joueurRecupereTalon) == j
						.getNom(j)) {
					joueurRecupereTalon = it.next();
				}
				int nbMinCartesEnMain = joueurRecupereTalon.getmain().size();
				while (it.hasNext()) {
					Joueur element = it.next();
					if (element.getmain().size() < nbMinCartesEnMain
							&& (element.getNom(element) != j.getNom(j))) {
						nbMinCartesEnMain = element.getmain().size();
						joueurRecupereTalon = element;
					}
				}

				ListIterator<Carte> it2 = cartesAPoser.listIterator();
				while (it2.hasNext()) {
					Carte element = it2.next();
					Partie.partie.getTasDeCarte().getTalon().add(element);// pose
																			// la
																			// carte
					j.getmain().remove(element);// retire de la main
				}
				if (peutContrerAs(joueurRecupereTalon)) {
					if (joueurRecupereTalon instanceof Joueur) {
						As carte = (As) carteAPoser;
						carte.contreAs(joueurRecupereTalon);
						Partie.partie.getTasDeCarte().donnerTalon(
								Partie.partie.getlistJoueur().get(
										Partie.partie.getlistJoueur().indexOf(
												joueurRecupereTalon)));
						System.out.println(joueurRecupereTalon
								+ " à contré "
								+ Partie.partie.getlistJoueur().get(
										(Partie.partie.getlistJoueur()
												.indexOf(j))));
					} else {// si c'est un joueur virtuel
						Carte cartePourcontrer = determinerCartePourContrer(joueurRecupereTalon
								.getmain());
						contrerAs(joueurRecupereTalon, j, cartePourcontrer);
					}
				} else {
					Partie.partie.getTasDeCarte().donnerTalon(
							Partie.partie.getlistJoueur().get(
									Partie.partie.getlistJoueur().indexOf(
											joueurRecupereTalon)));
					System.out.println(j
							+ " donne le talon à "
							+ Partie.partie.getlistJoueur().get(
									(Partie.partie.getlistJoueur()
											.indexOf(joueurRecupereTalon))));
					piocher(cartesAPoser.size(), j);
				}

			}

			else {
				j.poserCarte(cartesAPoser, j.getmain());
				piocher(cartesAPoser.size(), j);
			}
		}

		else {
			System.out.println("L'ordi ne peut pas jouer!");
			Partie.partie.getTasDeCarte().donnerTalon(j);
		}

	}

	public void echangerCarte(JoueurVirtuel j) {
		ArrayList<Carte> mainCartesSpeciales = new ArrayList<Carte>();
		ArrayList<Carte> faceVisibleCartesNormales = new ArrayList<Carte>();
		ListIterator<Carte> it = j.getmain().listIterator();
		while (it.hasNext()) {
			Carte element = it.next();
			if (element.estCarteSpeciale()) {
				mainCartesSpeciales.add(element);
			}
		}
		ArrayList<Carte> list = new ArrayList<Carte>(j.getfaceVisible());
		ListIterator<Carte> it2 = list.listIterator();
		while (it2.hasNext()) {
			Carte element = it2.next();
			if (element.estCarteSpeciale() == false) {
				faceVisibleCartesNormales.add(element);
			}
		}
		if (faceVisibleCartesNormales.size() <= mainCartesSpeciales.size()) {
			for (int i = 0; i < faceVisibleCartesNormales.size(); i++) {
				Carte carteAEchangerFacevisible = faceVisibleCartesNormales
						.get(i);
				Carte carteAEchangerMain = mainCartesSpeciales.get(i);
				j.getmain().remove(carteAEchangerMain);
				j.getmain().add(carteAEchangerFacevisible);
				j.getfaceVisible().remove(carteAEchangerFacevisible);
				j.getfaceVisible().add(carteAEchangerMain);
			}
		} else {
			for (int i = 0; i < mainCartesSpeciales.size(); i++) {
				Carte carteAEchangerFacevisible = faceVisibleCartesNormales
						.get(i);
				Carte carteAEchangerMain = mainCartesSpeciales.get(i);
				j.getmain().remove(carteAEchangerMain);
				j.getmain().add(carteAEchangerFacevisible);
				j.getfaceVisible().remove(carteAEchangerFacevisible);
				j.getfaceVisible().add(carteAEchangerMain);
			}

		}
		System.out.println("après échange, main : " + j.getmain().toString());
		System.out.println("après échange, face visible : "
				+ j.getfaceVisible().toString());
	}

	public void piocher(int nbCartesPosees, Joueur j) {
		for (int i = 0; i < nbCartesPosees; i++) {
			if (j.getmain().size() < 3) {
				j.piocher(1);
			}
		}
	}

	public ArrayList<Carte> determinerCartesJouables(ArrayList<Carte> main) {
		ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		ListIterator<Carte> it = main.listIterator();
		while (it.hasNext()) {
			Carte element = it.next();
			if (element.determinerCarteJouable()) {
				cartesJouables.add(element);
			}
		}
		return cartesJouables;
	}

	public Carte determinerCarteAposer(ArrayList<Carte> cartesJouables,
			ArrayList<Carte> cartesNormalesJouables) {
		Carte carteAPoser = null;
		if (cartesJouables.size() > 0) {
			if (cartesNormalesJouables.size() > 0) {
				ListIterator<Carte> it = cartesNormalesJouables.listIterator();
				Carte cartePlusPetite = it.next();
				while (it.hasNext()) {
					Carte element = it.next();
					if (element.getValeur() < cartePlusPetite.getValeur()) {
						cartePlusPetite = element;
					}
				}
				carteAPoser = cartePlusPetite;
			} else {
				ListIterator<Carte> it = cartesJouables.listIterator();
				Carte cartePlusPetite = it.next();
				while (it.hasNext()) {
					Carte element = it.next();
					if (element.getValeur() < cartePlusPetite.getValeur()) {
						cartePlusPetite = element;
					}
				}
				carteAPoser = cartePlusPetite;
			}

		}
		return carteAPoser;
	}

	public ArrayList<Carte> determinerCartesNormalesJouables(
			ArrayList<Carte> cartesJouables) {
		ArrayList<Carte> cartesNormalesJouables = new ArrayList<Carte>();
		ListIterator<Carte> it = cartesJouables.listIterator();
		while (it.hasNext()) {
			Carte element = it.next();
			if (element.estCarteSpeciale() == false) {
				cartesNormalesJouables.add(element);
			}
		}
		return cartesNormalesJouables;
	}

	public ArrayList<Carte> determinerCartesAPoser(Carte carteAPoser,
			ArrayList<Carte> cartesJouables) {
		ArrayList<Carte> cartesAPoser = new ArrayList<Carte>();
		ListIterator<Carte> it = cartesJouables.listIterator();
		while (it.hasNext()) {
			Carte element = it.next();
			if (element.getValeur() == carteAPoser.getValeur()) {
				cartesAPoser.add(element);
			}
		}
		return cartesAPoser;
	}

	public boolean peutContrerAs(Joueur joueur) {
		boolean peutContrer = false;
		ListIterator<Carte> it = joueur.getmain().listIterator();
		while (it.hasNext()) {
			Carte element = it.next();
			if (element.estDeux() || element.estAs()) {
				peutContrer = true;
			}
		}
		return peutContrer;
	}

	public Carte determinerCartePourContrer(ArrayList<Carte> main) {
		Carte cartePourContrer = null;
		ListIterator<Carte> it = main.listIterator();
		while (it.hasNext()) {
			Carte element = it.next();
			if (element.estDeux() || element.estAs()) {
				cartePourContrer = element;
			}
		}
		return cartePourContrer;
	}

	public boolean contrerAs(Joueur joueurQuiContre, Joueur joueurContre,
			Carte cartePourContrer) {
		boolean asContre = false;
		ArrayList<Carte> carteaposer = new ArrayList<Carte>();
		carteaposer.add(cartePourContrer);
		Partie.partie.getTasDeCarte().donnerTalon(
				Partie.partie.getlistJoueur().get(
						Partie.partie.getlistJoueur().indexOf(joueurContre)));
		System.out.println(joueurQuiContre
				+ " à contré "
				+ Partie.partie.getlistJoueur().get(
						(Partie.partie.getlistJoueur().indexOf(joueurContre))));
		joueurQuiContre.poserCarte(carteaposer, joueurQuiContre.getmain());
		piocher(1, joueurQuiContre);
		return asContre;
	}

}
