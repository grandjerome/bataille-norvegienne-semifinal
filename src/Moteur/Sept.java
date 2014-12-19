package Moteur;



public class Sept extends Moteur.CarteSpeciale {


	
private String couleur;
	private int valeur;
	public Sept(int valeurcarte, String couleurcarte){
		couleur=couleurcarte;
		valeur=valeurcarte;
		
	};
	
	public void jouerEffet() {

	}
	public void donnerTalon() {

	}
	public void jouerCarteInferieure() {

	}
	
	public String toString(){
		//System.out.println("valeur en chiffre : "+ this.valeur);
		return("Sept"+" de "+this.couleur);
	}

}
