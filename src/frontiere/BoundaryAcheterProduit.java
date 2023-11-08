package frontiere;

import java.util.Scanner;

import controleur.ControlAcheterProduit;
import controleur.ControlVerifierIdentite;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		System.out.println("Quel produit voulez-vous acheter ?");
		String produit = scan.next();
		StringBuilder chaine = new StringBuilder();
		chaine.append("Chez quel commercant voulez vous acheter des " + produit + "\n");
		Gaulois[] listeCommercant = controlAcheterProduit.controlrechercherVendeursProduit(produit);
		if(listeCommercant == null) {
			System.out.println("Desolee, personne ne vend ce produit au marche.\n");
		} else {
			for (int i=0; i < listeCommercant.length ; i++) {
				chaine.append(i+1 +" - " + listeCommercant[i].getNom() +  "\n");
			}
			int choixUtilisateur = Clavier.entrerEntier(chaine.toString());
			String[] infosMarche = controlAcheterProduit.donnerInfosMarche();
			Etal etal = controlAcheterProduit.trouverEtalVendeur(infosMarche[(choixUtilisateur-1)*3]);
			String nomVendeur = etal.getVendeur().getNom();
			StringBuilder chaineproduit = new StringBuilder();
			chaineproduit.append(nomAcheteur + " se deplace jusqu'à l'étal du vendeur " + nomVendeur + "\n");
			chaineproduit.append("Bonjour " + nomAcheteur + "\n");
			chaineproduit.append("Combien de " + etal.getProduit() + " voulez-vous acheter? \n");
			int nbquantite = Clavier.entrerEntier(chaineproduit.toString());
			controlAcheterProduit.acheterProduit(nomAcheteur, nomVendeur, nbquantite);
		}
	}
	
	
}
