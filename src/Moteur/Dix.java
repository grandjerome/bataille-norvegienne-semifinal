package Moteur;


public class Dix extends Moteur.CarteSpeciale {


	
    private String couleur;
	private int valeur;
	public Dix(int valeurcarte, String couleurcarte){
		couleur=couleurcarte;
		valeur=valeurcarte;
		
	};
	
	public void jouerEffet() {
		Partie.partie.getTasDeCarte().getTalon().clear();

	}
	public void poserCarte() {

	}
	
	public String toString(){
		//System.out.println("valeur en chiffre : "+ this.valeur);
		return("Dix"+" de "+this.couleur);
	}

}
