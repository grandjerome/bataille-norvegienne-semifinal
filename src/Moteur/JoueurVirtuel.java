package Moteur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class JoueurVirtuel extends Joueur{

	
	
	public String[] nom={"Roger","Patrick","Corentin","Emilie","Albert","Alberta","Alberte","Albertine","Albin","Alda","Aldo","Aldegonde","Al�the","Alex","Alexandra"};//,Alexandre,Alexandrine,Alexia,Alexiane,Alexis,Aleyde,Alfred,Alfreda,Alice,Alida,Aline,Alix,Alo�s,Aloysius,Alphonse,Alphonsine,Ama�l,Amance,Amand,Amandine,Amoury,Ambroise,Am�d�e,Am�lie,Amos,Ana�s,Anastase,Anastasie,Anatole,Andoche,Andr�,Andr�e,Ange,Ang�le,Ang�line,Ang�lique,Anicet,Anita,Anna,Annabelle,Anne,Annette,Annick,Annie,Annonciade,Anouchka,Anouck,Anselme,Anthelme,Anthony,Antoine,Antoinette,Antonin,Apollinaire,Apolline,Apollos,Arabelle,Arcadius,Arcady,Archibald,Ariane,Arielle,Aristide,Arlette,Armand,Armande,Armel,Armelle,Arnaud,Arnold,Arnould,Ars�ne,Arthur,Astrid,Athanase,Auberi,Aubert,Aubin,Aude,Audrey,Augusta,Auguste,Augustin,Augustine,Aure,Aur�le,Aur�lia,Aur�lie,Aur�lien,Aurore,Ava,Avit,Axel,Axelle,Aymar,Aymeric,Aymone,Babette,Babine,Babita,Balbine,Balthazar,Baptiste,Barbara,Barbe,Barberine,Barnab�,Barnard,Barth�l�my,Bartolom�,Basile,Bastien,Bastienne,Bathilde,Baudouin,B�atrice,Beatrix,B�n�dicte,Benjamin,Benjamine,Beno�t,Beno�te,B�renger};
	static private int indiceNom=0;
	
	
	private Strategie strategie;

	
	
	public JoueurVirtuel(){
		if (indiceNom == 15){
			indiceNom=0;
		}
		super.nomJoueur=nom[indiceNom];
		indiceNom++;
		this.strategie = new StrategieLeMeilleurPourLaFin();	
	}
	public void echangerCarte() {
		
	}
	
	
	
	
	
	
	public int determinernbCartesJouables(ArrayList<Carte> main){
		int nbCartesJouables=0;
		ListIterator<Carte> it = super.main.listIterator();
		while (it.hasNext()){
			Carte element = it.next();
			if (element.determinerCarteJouable()){
				nbCartesJouables++;
			}			
		}
		//System.out.println("l'ordi peut jouer "+nbCartesJouables+" cartes");
		return nbCartesJouables;
	}
	
	

	

	public void jouerCarte(){
		strategie.poserCarteStrategique(this);
		
	}
	public String toString(){
		return("Joueur Virtuel "+nomJoueur+" ");
	}
	

}
