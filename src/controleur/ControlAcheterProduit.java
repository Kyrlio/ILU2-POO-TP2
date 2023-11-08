package controleur;

import frontiere.Clavier;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}
	
	public int acheterProduit(String nomAcheteur,String nomVendeur, int quantite) {
		Etal etal = trouverEtalVendeur(nomVendeur);
		
		if(etal.getQuantite()==0) {
			System.out.println(nomAcheteur + " veut acheter " + quantite + " " + etal.getProduit() + ", malheuresement il n'y en a plus ! \n");
		} else if (quantite>etal.getQuantite()){
			System.out.println(nomAcheteur + " veut acheter " + quantite + ", malheureusement " + nomVendeur + " n'en a plus que " + etal.getQuantite() + ". " + nomAcheteur + " achète tout le stock de " + nomVendeur + "\n");
			etal.acheterProduit(etal.getQuantite());
		} else {
			etal.acheterProduit(quantite);
			System.out.println(nomAcheteur + " achète " + quantite + " " + etal.getProduit() + " a " + nomVendeur);
		}
		
		return quantite;
		
	}
	
	public Gaulois[] controlrechercherVendeursProduit(String produit) {
        return village.rechercherVendeursProduit(produit);
    }
    
    public Etal trouverEtalVendeur(String nomVendeur) {
        return controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
    }
    
    public String[] donnerInfosMarche() {
        return village.donnerEtatMarche();
    }
	
	
}
