package Moteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;


public class Joueur {

	/*
	 * (non-javadoc)
	 */
	
	protected int nbCartes;


	protected String nomJoueur;
	
	
	protected ArrayList<Carte> main;
	protected HashSet<Carte> faceCachee;
	protected HashSet<Carte> faceVisible;
	private ArrayList<Carte> carteaposer;
	public boolean JoueurEnJeu=true;
	public Joueur(){
		
	}
	
	public Joueur(String nom){
		this.nomJoueur=nom;		
	}
	
	public void echangerCarte() {
		System.out.println("Voulez vous echanger des cartes? (oui ou non)");
		Scanner sc4 = new Scanner(System.in);
		String reponse = sc4.nextLine();
		if(reponse.equals("oui")){
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		Scanner sc3 = new Scanner(System.in);
		System.out.println("Combien de cartes voulez vous changer? (De 1 à 3)");
		int nbCartesAEchanger = sc.nextInt();
		for (int i=0;i<nbCartesAEchanger;i++){
			System.out.println("Carte a échanger n°"+(i+1));
			System.out.println("quelle carte voulez vous echanger parmi : ");
			System.out.println("main :");
			for (int j=0;j<main.size();j++){
				System.out.println("numero "+ (j+1) +" "+ main.get(j));
			}
			int numCarteAEchanger = sc2.nextInt()-1;
			Carte carteAEchanger = main.get(numCarteAEchanger);
			System.out.println("Avec quelle carte de la face visible parmi : ");
			System.out.println("face visible :");
			ArrayList<Carte> list = new ArrayList<Carte>( faceVisible );
			for (int j=0;j<main.size();j++){
				System.out.println("numero "+ (j+1) +" "+ list.get(j));
			}
			int numCarteAEchangerFaceVisible = sc3.nextInt()-1;
			Carte carteAEchangerFaceVisible = list.get(numCarteAEchangerFaceVisible);
			main.remove(numCarteAEchanger);
			main.add(carteAEchangerFaceVisible);
			list.remove(numCarteAEchangerFaceVisible);
			list.add(carteAEchanger);
			System.out.println("Vous avez échangé "+carteAEchanger+" et "+carteAEchangerFaceVisible);
			faceVisible = new HashSet<Carte>(list);
			}
		}
		else if (reponse.equals("non")){
			System.out.println("la partie commence");
		}
	}
	public void creerList(){
		main = new ArrayList<Carte>();
		faceCachee = new HashSet<Carte>();
		faceVisible = new HashSet<Carte>();
	}
	public void piocher(int nbCarte){
		int i=0;
		while(!(Partie.partie.getTasDeCarte().getpioche().isEmpty()) && i<nbCarte)
		{
		this.main.add(Partie.partie.getTasDeCarte().getpioche().remove());
		i++;
		}
	}
	public HashSet<Carte> getfaceCachee(){
		return(faceCachee);
	}
	public ArrayList<Carte> getmain(){
		return(main);
	}
	public HashSet<Carte> getfaceVisible(){
		return(faceVisible);
	}
	public void setfacevisible(ArrayList<Carte> list){
		faceVisible.removeAll(faceVisible);
		for (int i=0; i<3; i++){
			faceVisible.add(list.get(i));
		}
	}
	public boolean poserCarte(ArrayList<Carte> carteaposer, ArrayList<Carte> main) { 
		 boolean cartejouable=carteaposer.get(0).determinerCarteJouable();
		 
		if (cartejouable) {
			Carte element = null;
			if (carteaposer.get(0) instanceof CarteSpeciale) {
				ListIterator<Carte> it = carteaposer.listIterator();
				while (it.hasNext()) {
					element = it.next();
					System.out.println(element);
					Partie.partie.getTasDeCarte().getTalon().add(element);
					main.remove(element);
					((CarteSpeciale) element).jouerEffet();
					if (main.size() < 3) {
						piocher(1);
					}
				}

			}
			else {
				ListIterator<Carte> it = carteaposer.listIterator();
				while (it.hasNext()) {
					element = it.next();
					System.out.println(element + " posée");
					Partie.partie.getTasDeCarte().getTalon().add(element);
					main.remove(element);
					if (main.size() < 3) {
						piocher(1);
					}
				}

			}
			 element.resetEffet();
			 
		 }
		 else {System.out.println("vous ne pouvez pas poser cette carte");}
		 carteaposer.clear();
		 return (cartejouable);
	}

	

	public int getNbCartes() {
		return nbCartes;
	}

	public String getNom(Joueur j){
		return j.nomJoueur;
	}
	public void setNbCartes(int nbCartes) {
		this.nbCartes = nbCartes;
	}
	public void prendreCarteFaceVisible(){
		Iterator<Carte> it = faceVisible.iterator();
		
		while(it.hasNext()){
			Carte element = it.next();
			main.add(element);		
		}
		faceVisible.clear();
		System.out.println("vous avez récupéré les cartes visibles");
		
		
		
	}
	public void prendreCarteFaceCachee(){
		Iterator<Carte> it = faceCachee.iterator();
		Carte element=null;
		boolean entreeBoucle=true;
		while(it.hasNext() && entreeBoucle){
			element = it.next();
			main.add(element);
			entreeBoucle=false;
		}
		faceCachee.remove(element);
		System.out.println("vous avez récupéré une carte face cachée");
		
		
	}
	public void jouerCarte(){
		if (main.isEmpty() && faceVisible.isEmpty() && faceCachee.isEmpty()){
			SortieJoueur();
		}
		else {
		
			System.out.println("joueur : "+this);
			System.out.println("le talon est :" );
			Partie.partie.getTasDeCarte().afficherTalon();
			
		
		if (main.isEmpty() && !(Partie.partie.getTasDeCarte().getpioche().isEmpty()))
			{
			piocher(1);
			
			}
		else if(main.isEmpty() && Partie.partie.getTasDeCarte().getpioche().isEmpty()){
				if(faceVisible.isEmpty()){
					prendreCarteFaceCachee();
				}
				else {prendreCarteFaceVisible();}
			}
		else{
			
		
		
				boolean carteposee = false;
				while (!(carteposee)) {
					System.out
							.println("\n \n quelle carte voulez vous poser parmi : ");
					System.out.println("\n main :");
					int i;
					System.out.println(main);
					for (i = 0; i < main.size(); i++) {
						System.out.println("numero " + (i + 1) + " "
								+ main.get(i));
					}
					Scanner sc = new Scanner(System.in);
					System.out.println("\n combien de cartes à poser ?");
					int nombrecarteaposer = sc.nextInt();
					carteaposer = new ArrayList<Carte>();
					if (nombrecarteaposer == 0) {
						Partie.partie.getTasDeCarte().donnerTalon(this);
					} else {
						for (i = 0; i < nombrecarteaposer; i++) {
							System.out.println("numéro de la carte n° "
									+ (i + 1) + " :");

							int numerocarte = sc.nextInt();
							carteaposer.add(main.get(numerocarte - 1));
						}
						boolean identique = false;
						if (carteaposer.size() == 1) {
							identique = true;
						}
						if (carteaposer.size() > 1) {
							identique = verifierCarteIdentique(carteaposer);
						}
						if (!(identique)) {
							System.out
									.println("\n cartes non identiques, veuillez recommencer");
						}
						// ->exception identique=false
						if (identique) {
							carteposee = poserCarte(carteaposer, main);
						}

					}
				}
			}
		}

	}
		
		
		public void SortieJoueur(){
			Partie.partie.sortirJoueur();
		}
		
	public boolean verifierCarteIdentique(ArrayList<Carte> carteacomparer){
		boolean carteidentique=true;
		ListIterator<Carte> it = carteacomparer.listIterator();
		Carte elementreference=null;
		if (it.hasNext()){
			elementreference = it.next();
		}
		while(it.hasNext()){
			Carte elementacomparer = it.next();
			if (elementacomparer.getValeur()!=elementreference.getValeur()){
				carteidentique=false;
				
			}
			
		}
		return(carteidentique);
		
	}
	public ArrayList<Carte> getCarteAPoser(){
		return(carteaposer);
	}
	public String toString(){
		return("Joueur "+nomJoueur+" ");
	}
	
	


	

}

// /**
// * Ensures that this collection contains the specified element (optional
// * operation).
// *
// * @param element
// * whose presence in this collection is to be ensured.
// * @see java.util.Collection#add(Object)
// *
// */
// public boolean addCarte(Carte carte) {
// return this.carte.add(carte);
// }
// /**
// * Setter of the property <tt>carte</tt>
// *
// * @param carte
// * the carte to set.
// *
// */
// public void setCarte(Collection<Carte> carte) {
// this.carte = carte;
// }
// /**
// * Removes a single instance of the specified element from this
// * collection, if it is present (optional operation).
// *
// * @param element
// * to be removed from this collection, if present.
// * @see java.util.Collection#add(Object)
// *
// */
// public boolean removeCarte(Carte carte) {
// return this.carte.remove(carte);
// }
// /**
// * Getter of the property <tt>carte</tt>
// *
// * @return Returns the carte.
// *
// */
//
// public Collection<Carte> getCarte() {
// return carte;
// }
// /**
// * Returns the number of elements in this collection.
// *
// * @return the number of elements in this collection
// * @see java.util.Collection#size()
// *
// */
// public int carteSize() {
// return carte.size();
// }
// /**
// * Setter of the property <tt>partie</tt>
// *
// * @param partie
// * The partie to set.
// *
// */
// public void setPartie(Partie partie) {
// this.partie = partie;
// }
// /**
// * Getter of the property <tt>partie</tt>
// *
// * @return Returns the partie.
// *
// */
//
// public Partie getPartie() {
// return partie;
// }
