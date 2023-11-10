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
	
	public int acheterProduit(String nomVendeur, int quantite) {
		Etal etal = trouverEtalVendeur(nomVendeur);
		return etal.acheterProduit(quantite);
		
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
    
    public boolean verifierIdentite(String nom) {
    	return controlVerifierIdentite.verifierIdentite(nom);
    }
	
	
}
