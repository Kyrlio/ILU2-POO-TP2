package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	private Village village;
	private Chef abraracourcix;
	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlLibererEtal controlLibererEtal;
	private ControlAcheterProduit controlAcheterProduit;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 2);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		controlAcheterProduit = new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
	}

	@Test
	void testAcheterProduit() {
		Gaulois bonemine = new Gaulois("Bonemine", 10);
		village.ajouterHabitant(bonemine);
		village.installerVendeur(bonemine, "fleurs", 10);
		int quantite = controlAcheterProduit.acheterProduit("Bonemine", 5);
		assertEquals(quantite, 5);
		assertNotEquals(quantite, 4);
	}
	
	@Test
	void testControlRechercherVendeursProduit() {
		Gaulois bonemine = new Gaulois("Bonemine", 10);
		village.ajouterHabitant(bonemine);
		village.installerVendeur(bonemine, "fleurs", 10);
		Gaulois[] tab = new Gaulois[1];
		tab[0] = bonemine;
		assertNotEquals(controlAcheterProduit.controlrechercherVendeursProduit("menhir"), tab);
		assertEquals(controlAcheterProduit.controlrechercherVendeursProduit("fleurs")[0], tab[0]);
	}
	
	@Test
	void testVerifierIdentite() {
		Gaulois bonemine = new Gaulois("Bonemine", 10);
		village.ajouterHabitant(bonemine);
		assertTrue(controlAcheterProduit.verifierIdentite("Bonemine"));
		assertFalse(controlAcheterProduit.verifierIdentite("Obelix"));
	}

}
