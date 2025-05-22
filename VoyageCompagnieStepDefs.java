import static org.junit.jupiter.api.Assertions.*;
import io.cucumber.java.fr.*;

public class VoyageCompagnieStepDefs {

    private Compagnie compagnie;
    private Voyage voyage;

    @Étantdonné("une compagnie appelée {string}")
    public void une_compagnie_appelee(String nom) {
        compagnie = new Compagnie();
        compagnie.setName(nom);
    }

    @Et("un voyage nommé {string} vers {string}")
    public void un_voyage_nomme_vers(String nom, String ville) {
        voyage = new Voyage(nom, ville);
    }

    @Quand("j’ajoute ce voyage à la compagnie")
    public void j_ajoute_ce_voyage_a_la_compagnie() {
        compagnie.ajouterVoyage(voyage);
    }

    @Alors("le voyage doit apparaître dans la liste de la compagnie")
    public void le_voyage_doit_apparaitre_dans_la_liste_de_la_compagnie() {
        assertTrue(compagnie.getVoyages().contains(voyage));
    }

    @Et("la compagnie du voyage doit être {string}")
    public void la_compagnie_du_voyage_doit_etre(String nomAttendu) {
        assertEquals(nomAttendu, voyage.getCompagnie().getName());
    }

    @Quand("j’ajoute deux fois ce voyage à la compagnie")
    public void j_ajoute_deux_fois_ce_voyage() {
        compagnie.ajouterVoyage(voyage);
        compagnie.ajouterVoyage(voyage);
    }

    @Alors("la compagnie ne doit contenir qu’un seul voyage")
    public void la_compagnie_ne_doit_contenir_qu_un_seul_voyage() {
        assertEquals(1, compagnie.getVoyages().size());
    }
}
