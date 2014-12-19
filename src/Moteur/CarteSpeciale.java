package Moteur;




public abstract class CarteSpeciale extends Moteur.Carte {
	
	
	private String couleur;
	private int valeur;
	public CarteSpeciale(int valeurcarte, String couleurcarte){
		this.couleur=couleurcarte;
		this.valeur=valeurcarte;
		
	}
	public CarteSpeciale(){
		
	}
	public String getCouleur() {
		return couleur;
	}
	public int getValeur() {
		return valeur;
	}
	
	public void poserCarteSpeciale() {

	}
	public abstract void jouerEffet();
	
	

}
