package Moteur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Carte {

	
	
	

	private int valeur;
	

	private String couleur;
	
	
	private boolean carteJouable;
	
	
	
	
	
	
	
		
	
	public Carte(int valeur,String couleurcarte){
		this.couleur=couleurcarte;
		this.valeur=valeur;
		//System.out.println("je suis le "+this.valeur+" "+"de"+" "+this.couleur);
		
	}
	public Carte(){
		
	}
	public boolean determinerCarteJouable() {
		boolean cartejouable=true;
		
		//System.out.println(Partie.partie.getTasDeCarte().getTalon().size());
		//System.out.println(Partie.partie.getTasDeCarte().getTalon().isEmpty());
		if (!(Partie.partie.getTasDeCarte().getTalon().isEmpty())){
			if(Partie.partie.getTasDeCarte().getTalon().get(Partie.partie.getTasDeCarte().getTalon().size()-1).getValeur()==6){
				if(getValeur()>7){
					cartejouable=false;
					System.out.println("vous devez jouer une carte inférieure ou égale au 7 !");
				}
			}
			else if (getValeur() < Partie.partie.getTasDeCarte().getTalon().get(Partie.partie.getTasDeCarte().getTalon().size()-1).getValeur()){
				if (estCarteSpeciale()){
					if (!(estDeux())){
						cartejouable=false;
					}
					else {
						cartejouable=true;
					}}
				else {cartejouable=false;}
				
				
				
			}
			
		}
		//System.out.println("carte jouable : "+cartejouable);
		return(cartejouable);

	}

	

	public int getValeur() {
		return valeur;
	}

	
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	
	/**
	 * Getter de l'attribut couleur
	 * @return couleur
	 */
	public String getCouleur() {
		return couleur;
	}

	
	


	public boolean getCarteJouable() {
		return carteJouable;
	}


	public void setCarteJouable(boolean carteJouable) {
		this.carteJouable = carteJouable;
	}
	public boolean estCarteSpeciale(){
		boolean cartespeciale=false;
		if (this instanceof CarteSpeciale){
			cartespeciale=true;
		}
		return (cartespeciale);
	}
	public boolean estAs(){
		boolean cartespeciale=false;
		if (this.getClass() == As.class){
			cartespeciale=true;
			//System.out.println("cest un 1");
		}
		return (cartespeciale);
	}
	public boolean estDeux(){
		boolean cartespeciale=false;
		//System.out.println("2 en cours");
		if (this.getClass() == Deux.class){
			cartespeciale=true;
			//System.out.println("cest un 2");
		}
		return (cartespeciale);
	}
	public boolean estSept(){
		boolean cartespeciale=false;
		if (this.getClass() == Sept.class){
			cartespeciale=true;
		}
		return (cartespeciale);
	}
	public boolean estHuit(){
		boolean cartespeciale=false;
		if (this.getClass() == Huit.class){
			cartespeciale=true;
		}
		return (cartespeciale);
	}
	public boolean estDix(){
		boolean cartespeciale=false;
		if (this.getClass() == Dix.class){
			cartespeciale=true;
			System.out.println("cest un dix");
		}
		return (cartespeciale);
	}
	public String chiffreEnNom(int valeur){
		String nom=null;
		switch(valeur){
		case 1:
			nom="Deux";
			break;
		case 2:
			nom="Trois";
			break;
		case 3:
			nom="Quatre";
			break;
		case 4:
			nom="Cinq";
			break;
		case 5:
			nom="Six";
			break;
		case 6:
			nom="Sept";
			break;
		case 7:
			nom="Huit";
			break;
		case 8:
			nom="Neuf";
			break;
		case 9:
			nom="Dix";
			break;
		case 10:
			nom="Valet";
			break;
		case 11:
			nom="Dame";
			break;
		case 12:
			nom="Roi";
			break;
		case 13:
			nom="As";
			break;
		
		}
		return(nom);
	}
	public void resetEffet(){
		
	}
	
	public String toString(){
		//System.out.println("valeur en chiffre : "+ this.valeur);
		return(chiffreEnNom(this.valeur)+" de "+this.couleur);
	}
	

}

