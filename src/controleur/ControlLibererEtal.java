package controleur;

import villagegaulois.Etal;

public class ControlLibererEtal {
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;

	public ControlLibererEtal(
			ControlTrouverEtalVendeur controlTrouverEtalVendeur) {
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}
	
	public boolean isVendeur(String nomVendeur) {
		Etal vendeurReconnu = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		return vendeurReconnu != null;
	}

	/**
	 * 
	 * @param produit
	 * @return donneesVente est un tableau de chaine contenant [0] : un boolean
	 *         indiquant si l'étal est occupé [1] : nom du vendeur [2] : produit
	 *         vendu [3] : quantité de produit à vendre au début du marché [4] :
	 *         quantité de produit vendu
	 */
	public String[] libererEtal(String nomVendeur) {
		String[] donneesEtal = null;
		if (isVendeur(nomVendeur)) {
			donneesEtal = new String[5];
			Etal donneesVente = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
			donneesEtal[0] = String.valueOf(donneesVente.isEtalOccupe());
			donneesEtal[1] = nomVendeur;
			donneesEtal[2] = donneesVente.getProduit();
			donneesEtal[3] = Integer.toString(donneesVente.getQuantite());
			donneesEtal[4] = String.valueOf(donneesVente.etatEtal()[4]);
		}		
		return donneesEtal;
	}

}
