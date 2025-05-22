
import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.Before;
import io.cucumber.java.fr.*;
import java.util.*;

public class MesSteps {

    private Compagnie compagnie;
    private Voyage voyage;
    private String nomCompagnie;
    private String nomVoyage;

    @Before
    public void setUp() {
        compagnie = null;
        voyage = null;
    }

    @Étantdonné("une compagnie vide nommée {string}")
    public void une_compagnie_vide_nommée(String nomCompagnie) {
        this.nomCompagnie = nomCompagnie;
        compagnie = new Compagnie();
        compagnie.setName(nomCompagnie);
    }

    @Étantdonné("un voyage nommé {string} vers {string}")
    public void un_voyage_nommé_vers(String nomVoyage, String ville) {
        this.nomVoyage = nomVoyage;
        voyage = new Voyage(nomVoyage, ville);
    }

    @Quand("j’ajoute le voyage à la compagnie")
    public void j_ajoute_le_voyage_à_la_compagnie() {
        compagnie.ajouterVoyage(voyage);
    }

    @Alors("le voyage est enregistré dans la liste de la compagnie")
    public void le_voyage_est_enregistré_dans_la_liste() {
        assertTrue(compagnie.getVoyages().contains(voyage));
    }

    @Alors("la compagnie du voyage est définie automatiquement à {string}")
    public void la_compagnie_du_voyage_est_définie_automatiquement(String nomAttendu) {
        assertNotNull(voyage.getCompagnie());
        assertEquals(nomAttendu, voyage.getCompagnie().getName());
    }

    @Étantdonné("une compagnie nommée {string} ayant déjà un voyage nommé {string}")
    public void une_compagnie_ayant_déjà_un_voyage(String nomCompagnie, String nomVoyage) {
        this.nomCompagnie = nomCompagnie;
        this.nomVoyage = nomVoyage;
        compagnie = new Compagnie();
        compagnie.setName(nomCompagnie);
        voyage = new Voyage(nomVoyage, "Destination");
        compagnie.ajouterVoyage(voyage);
    }

    @Quand("j’ajoute à nouveau ce voyage à la compagnie")
    public void j_ajoute_a_nouveau_le_voyage() {
        compagnie.ajouterVoyage(voyage);
    }

    @Alors("le système refuse l’ajout en doublon")
    public void refus_ajout_doublon() {
        long count = compagnie.getVoyages().stream().filter(v -> v == voyage).count();
        assertEquals(1, count);
    }

    @Alors("la liste de voyages de la compagnie contient une seule occurrence du voyage")
    public void une_seule_occurrence_dans_la_liste() {
        int occurrences = Collections.frequency(compagnie.getVoyages(), voyage);
        assertEquals(1, occurrences);
    }
}
