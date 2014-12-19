package Moteur;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import javax.swing.JSpinner.ListEditor;

import Moteur.Carte.couleurCarte;



public class As extends Moteur.CarteSpeciale {


public enum couleurCarte{coeur,carreau,pique,trefle};
	
    private String couleur;
	private int valeur;
	static boolean effetJoue;
	public As(int valeurcarte, String couleurcarte){
		valeur=valeurcarte;
		couleur=couleurcarte;
		effetJoue=false;
		
	}
	
	public void jouerEffet() {
		if(!(effetJoue)){
		Scanner sc = new Scanner(System.in);
		int i=1;
		System.out.println("à qui voulez vous donner le talon (numero)");
		ListIterator<Joueur> it = Partie.partie.getlistJoueur().listIterator();
		while (it.hasNext()){
			Joueur element = it.next();
			System.out.println(element+" "+(i));
			i++;
		}
		System.out.println("?");
		int joueur = sc.nextInt();
	
			Partie.partie.getTasDeCarte().donnerTalon(Partie.partie.getlistJoueur().get(joueur-1));
			effetJoue=true;
		
		
		}
	}
	
	public String getCouleur() {
		return couleur;
	}
	public int getValeur() {
		return valeur;
	}
	public void resetEffet(){
		effetJoue=false;
	}
	public boolean contreAs(Joueur joueur){
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		boolean contre=false;
		System.out.println("voulez vous contrer l'As ?");
		String reponse=sc.nextLine();
		System.out.println("reponse : "+reponse);
		if(reponse.compareTo("oui")==0){
			
			ListIterator<Carte> it = joueur.getmain().listIterator();
			ArrayList<Carte> carteContre = new ArrayList<Carte>();
			while (it.hasNext()){
				Carte element=it.next();
				if (element.estDeux() || element.estAs()){
					carteContre.add(element);	
				}
			}
			if (carteContre.size()>0){
				System.out.println("quelle carte voulez vous utiliser pour contrer ?");
				for (int i=0; i<carteContre.size();i++){
					System.out.println("carte n° "+(i+1)+" : "+carteContre.get(i));
				}
				int nocarteajouer = sc2.nextInt();
				Carte carteajouer = carteContre.get(nocarteajouer-1);
				carteContre.clear();
				carteContre.add(carteajouer);
				System.out.println("Vous avez choisi : "+carteajouer);
				joueur.poserCarte(carteContre, joueur.getmain());
				contre = true;
			}

			
		}
		
		return contre;
	}
	public String toString(){
		return("As"+" de "+this.couleur);
	}
	
}
