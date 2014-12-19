package Moteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Partie {

	/*
	 * (non-javadoc)
	 */
	private int nombreJoueurVirtuel=0;

	/*
	 * (non-javadoc)
	 */
	private String nomVainqueur;

	/**
*
*/
	private TasDeCarte tasdecarte;
	private int nbJoueurEnJeu=0;
	private boolean JoueurEnJeu=false;
	private ArrayList<Joueur> listJoueur;
	private ListIterator iterateurJoueur;
	static public Partie partie;
	
	
	
	static public void main(String[] args){
		
	partie = new Partie();
	partie.debutPartie();
	partie.deroulementPartie();
	}
	public void sortirJoueur(){
		JoueurEnJeu=false;
		nbJoueurEnJeu--;
	}
	public void sortirJoueurVirtuel(){
		nbJoueurEnJeu--;
	}
	public void determinerVainqueur() {

	}

	public void finPartie() {

	}
	public ArrayList<Joueur> getlistJoueur(){
		return (this.listJoueur);
	}
	public void setNbJoueur(){
		try{
		Scanner sc = new Scanner(System.in);
		System.out.println("Combien voulez vous de joueur virtuels (1 -> *)");
		int nbJoueur = sc.nextInt();
		this.nombreJoueurVirtuel=nbJoueur;
		}
		catch (InputMismatchException e){
			System.out.println("n'avez pas entrÃ© un nombre !");
			setNbJoueur();
		}
	}
	public String setNomJoueur(){
		String nomJoueur=null;
		try{
		Scanner sc = new Scanner(System.in);
		System.out.println("quel est votre nom ? \n \n");
		nomJoueur =sc.nextLine();}
		catch(InputMismatchException e){
			System.out.println("n'avez pas entrÃ© de texte !");
			setNomJoueur();
		}
		return(nomJoueur);
	}
	public void debutPartie() {
		
		setNbJoueur();
		
		String nomJoueur=setNomJoueur();
		listJoueur = new ArrayList<Joueur>();
		
		Joueur joueur = new Joueur(nomJoueur);
		
		listJoueur.add(joueur);
		this.JoueurEnJeu=true;
		this.nbJoueurEnJeu=this.nombreJoueurVirtuel+1;
		
		JoueurVirtuel[] joueurVirtuel = new JoueurVirtuel[this.nombreJoueurVirtuel];
		for (int i = 0;i<this.nombreJoueurVirtuel;i++){
			joueurVirtuel[i] = new JoueurVirtuel();
			listJoueur.add(joueurVirtuel[i]);
		}
		Collections.shuffle(listJoueur);
		ListIterator<Joueur> it = listJoueur.listIterator();
		while (it.hasNext()){
			Joueur element = it.next();
			System.out.println(element + " " );
		}
		
		tasdecarte = new TasDeCarte(nombreJoueurVirtuel);
		
		
		
		ListIterator<Joueur> it2 = listJoueur.listIterator();
		while (it2.hasNext()){
				
				Joueur element = it2.next();
				System.out.println("joueur : " + element );
				if(!(element instanceof JoueurVirtuel)){
					System.out.println("main :\n"+element.getmain()+"\n carte face cachÃ©e :\n"+element.getfaceCachee());
					
				}

				System.out.println("carte face visible :");
				System.out.println(element.getfaceVisible()+"\n \n");

				
		}
		ListIterator<Joueur> it3 = listJoueur.listIterator();
		
		while (it3.hasNext()){
			Joueur element = it3.next();
			element.echangerCarte();
		}
	}
	public void deroulementPartie(){
		while (this.nbJoueurEnJeu != 1 || JoueurEnJeu){
		while (!(tasdecarte.getpioche().isEmpty())){
			
			iterateurJoueur = listJoueur.listIterator();
			int i=0;
			while (iterateurJoueur.hasNext()){
				Joueur element=(Joueur)iterateurJoueur.next();
				if(element.JoueurEnJeu){
				System.out.println(element);
				element.jouerCarte();
				i++;
				}
			}	
		}
		{//partie avec tas de cartes visible et cachÃ©
		ListIterator<Joueur> it = listJoueur.listIterator();
		int i=0;
		System.out.println("cartes visibles debloquÃ©es");
		while (it.hasNext()){
			Joueur element=it.next();
			i++;
			System.out.println("\n \njoueur : "+i +" "+ element);
			System.out.println("le talon est :" );
			tasdecarte.afficherTalon();
			element.jouerCarte();
		}
		}
		
		}
		finDePartie();
	}
	public void finDePartie(){
	if (!JoueurEnJeu){
		System.out.println("\n \nVous Avez GagnÃ© !! fÃ©licitation");
	}
	else{
		System.out.println("\n \n Vous Avez Perdu!!");
	}
	}

	/**
	 * Getter of the property <tt>nombreJoueurVirtuel</tt>
	 * 
	 * @return Returns the nombreJoueurVirtuel.
	 * 
	 */

	public int getNombreJoueurVirtuel() {
		return nombreJoueurVirtuel;
	}

	/**
	 * Setter of the property <tt>nombreJoueurVirtuel</tt>
	 * 
	 * @param nombreJoueurVirtuel
	 *            The nombreJoueurVirtuel to set.
	 * 
	 */
	public void setNombreJoueurVirtuel(int nombreJoueurVirtuel) {
		this.nombreJoueurVirtuel = nombreJoueurVirtuel;
	}

	/**
	 * Getter of the property <tt>nomVainqueur</tt>
	 * 
	 * @return Returns the nomVainqueur.
	 * 
	 */

	public String getNomVainqueur() {
		return nomVainqueur;
	}

	/**
	 * Setter of the property <tt>nomVainqueur</tt>
	 * 
	 * @param nomVainqueur
	 *            The nomVainqueur to set.
	 * 
	 */
	public void setNomVainqueur(String nomVainqueur) {
		this.nomVainqueur = nomVainqueur;
	}
	public TasDeCarte getTasDeCarte(){
		return (tasdecarte);
	}
	public ListIterator getiIterateurJoueur(){
		return(iterateurJoueur);
	}

}

// /**
// * Setter of the property <tt>nombreJoueurVirtuel</tt>
// *
// * @param nombreJoueurVirtuel
// * The nombreJoueurVirtuel to set.
// *
// */
// public void setNombreJoueurVirtuel(String nombreJoueurVirtuel) {
// this.nombreJoueurVirtuel = nombreJoueurVirtuel;
// }
// /**
// * Getter of the property <tt>nombreJoueurVirtuel</tt>
// *
// * @return Returns the nombreJoueurVirtuel.
// *
// */
//
// public String getNombreJoueurVirtuel() {
// return nombreJoueurVirtuel;
// }
// /**
// * Setter of the property <tt>nombreJoueurVirtuel</tt>
// *
// * @param nombreJoueurVirtuel
// * The nombreJoueurVirtuel to set.
// *
// */
// public void setNombreJoueurVirtuel(int nombreJoueurVirtuel) {
// this.nombreJoueurVirtuel = nombreJoueurVirtuel;
// }
// /**
// * Getter of the property <tt>nombreJoueurVirtuel</tt>
// *
// * @return Returns the nombreJoueurVirtuel.
// *
// */
//
// public int getNombreJoueurVirtuel() {
// return nombreJoueurVirtuel;
// }
// /**
// * Returns an iterator over the elements in this collection.
// *
// * @return an <tt>Iterator</tt> over the elements in this collection
// * @see java.util.Collection#iterator()
// *
// */
// public Iterator<Joueur> joueurIterator() {
// return joueur.iterator();
// }
// /**
// * Returns <tt>true</tt> if this collection contains no elements.
// *
// * @return <tt>true</tt> if this collection contains no elements
// * @see java.util.Collection#isEmpty()
// *
// */
// public boolean isJoueurEmpty() {
// return joueur.isEmpty();
// }
// /**
// * Ensures that this collection contains the specified element (optional
// * operation).
// *
// * @param element
// * whose presence in this collection is to be ensured.
// * @see java.util.Collection#add(Object)
// *
// */
// public boolean addJoueur(Joueur joueur) {
// return this.joueur.add(joueur);
// }
// /**
// * Getter of the property <tt>joueur</tt>
// *
// * @return Returns the joueur.
// *
// */
//
// public Collection<Joueur> getJoueur() {
// return joueur;
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
// public boolean removeJoueur(Joueur joueur) {
// return this.joueur.remove(joueur);
// }
// /**
// * Returns all elements of this collection in an array.
// *
// * @return an array containing all of the elements in this collection
// * @see java.util.Collection#toArray()
// *
// */
// public Joueur[] joueurToArray() {
// return joueur.toArray(new Joueur[joueur.size()]);
// }
// /**
// * Returns <tt>true</tt> if this collection contains all of the elements
// * in the specified collection.
// *
// * @param elements
// * collection to be checked for containment in this
// * collection.
// * @see java.util.Collection#containsAll(Collection)
// *
// */
// public boolean containsAllJoueur(Collection<Joueur> joueur) {
// return this.joueur.containsAll(joueur);
// }
// /**
// * Setter of the property <tt>joueur</tt>
// *
// * @param joueur
// * the joueur to set.
// *
// */
// public void setJoueur(Collection<Joueur> joueur) {
// this.joueur = joueur;
// }
// /**
// * Returns <tt>true</tt> if this collection contains the specified
// * element.
// *
// * @param element
// * whose presence in this collection is to be tested.
// * @see java.util.Collection#contains(Object)
// *
// */
// public boolean containsJoueur(Joueur joueur) {
// return this.joueur.contains(joueur);
// }
// /**
// * Returns the number of elements in this collection.
// *
// * @return the number of elements in this collection
// * @see java.util.Collection#size()
// *
// */
// public int joueurSize() {
// return joueur.size();
// }
// /**
// * Removes all of the elements from this collection (optional
// * operation).
// *
// * @see java.util.Collection#clear()
// *
// */
// public void clearJoueur() {
// this.joueur.clear();
// }
