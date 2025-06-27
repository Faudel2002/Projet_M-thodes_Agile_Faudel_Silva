import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

public class MesSteps {

    private Compagnie compagnie;
    private Voyage voyage;



    @When("j'ajoute le voyage à la compagnie")
    public void j_ajoute_le_voyage_a_la_compagnie() {
        assertNotNull(compagnie, "Compagnie ne doit pas être null");
        assertNotNull(voyage, "Voyage ne doit pas être null");
        compagnie.ajouterVoyage(voyage);
    }

    @Then("le voyage est enregistré dans la liste de la compagnie")
    public void le_voyage_est_enregistre_dans_la_liste() {
        assertTrue(compagnie.getVoyages().contains(voyage), "Le voyage doit être dans la liste");
    }



    @When("j'ajoute à nouveau ce voyage à la compagnie")
    public void j_ajoute_a_nouveau_voyage() {
        assertNotNull(compagnie, "Compagnie ne doit pas être null");
        assertNotNull(voyage, "Voyage ne doit pas être null");
        compagnie.ajouterVoyage(voyage);
    }

    @Then("le système refuse l'ajout en doublon")
    public void refus_ajout_doublon() {
        long count = compagnie.getVoyages().stream().filter(v -> v == voyage).count();
        assertEquals(1, count, "Le voyage est en double");
    }

    @And("la liste de voyages de la compagnie contient une seule occurrence du voyage")
    public void une_seule_occurrence() {
        int occurrences = Collections.frequency(compagnie.getVoyages(), voyage);
        assertEquals(1, occurrences, "Il doit y avoir une seule occurrence du voyage");
    }

    @Given("une compagnie nommée {} ayant déjà un voyage nommé {}")
    public void uneCompagnieNommeeAyantDejaUnVoyageNomme(String nomCompagnie, String nomVoyage) {
        compagnie = new Compagnie();
        compagnie.setName(nomCompagnie);
        voyage = new Voyage(nomVoyage, "Destination");
        compagnie.ajouterVoyage(voyage);
    }

    @Given("une compagnie vide nommée {}")
    public void uneCompagnieVideNommee(String arg0) {
        compagnie = new Compagnie();
        compagnie.setName(arg0);
    }

    @And("un voyage nommé {} vers {}")
    public void unVoyageNommeVers(String nomVoyage, String ville) {
        voyage = new Voyage(nomVoyage, ville);
    }

    @And("la compagnie du voyage est définie automatiquement à {}")
    public void laCompagnieDuVoyageEstDefinieAutomatiquementA(String nomAttendu) {
        assertNotNull(voyage.getCompagnie(), "Compagnie du voyage ne doit pas être null");
        assertEquals(nomAttendu, voyage.getCompagnie().getName(), "Nom de la compagnie incorrect");
    }
}
