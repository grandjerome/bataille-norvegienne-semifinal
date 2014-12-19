package Moteur;



public class Deux extends Moteur.CarteSpeciale {


	
    private String couleur;
	private int valeur;
	public Deux(int valeurcarte, String couleurcarte){
		couleur=couleurcarte;
		valeur=valeurcarte;
		
	};
	
	public void jouerEffet() {
		

	}
	public void donnerTalon() {

	}
	public void repartirDuDeux() {

	}
	
	public String toString(){
		//System.out.println("valeur en chiffre : "+ this.valeur);
		return("Deux"+" de "+this.couleur);
	}

}
