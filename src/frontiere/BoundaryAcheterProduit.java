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
		//TODO Regarder si l'acheteur fait partie du village
		if (controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Quel produit voulez-vous acheter ?");
			String produit = scan.next();
			StringBuilder chaine = new StringBuilder();
			chaine.append("Chez quel commercant voulez vous acheter des " + produit + "\n");
			Gaulois[] listeCommercant = controlAcheterProduit.controlrechercherVendeursProduit(produit);
			
			if(listeCommercant == null) {
				System.out.println("Desolé, personne ne vend ce produit au marche.\n");
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
				int quantite = Clavier.entrerEntier(chaineproduit.toString());
				
				if(etal.getQuantite()==0) {
					System.out.println(nomAcheteur + " veut acheter " + quantite + " " + etal.getProduit() + ", malheuresement il n'y en a plus ! \n");
				} else if (quantite>etal.getQuantite()){
					System.out.println(nomAcheteur + " veut acheter " + quantite + ", malheureusement " + nomVendeur + " n'en a plus que " + etal.getQuantite() + ". " + nomAcheteur + " achète tout le stock de " + nomVendeur + "\n");
					controlAcheterProduit.acheterProduit(nomVendeur, quantite);
				} else {
					controlAcheterProduit.acheterProduit(nomVendeur, quantite);
					System.out.println(nomAcheteur + " achète " + quantite + " " + etal.getProduit() + " a " + nomVendeur);
				}
			}
		} else {
			System.out.println("Désolé " + nomAcheteur + " mais vous devez faire partie du village pour acheter !");
		}
	}
	
	
}
