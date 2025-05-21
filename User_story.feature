Features : Ajout d'une compagnie 
En tant que Vendeur
Je veux ajouter un voyage à une compagnie
Afin de constituer une offre de voyages complète pour mes clients

Scenario Outline: ajout automatique du lien entre compagnie et voyage
  Given une compagnie vide nommée <nomCompagnie>
  And un voyage nommé <nomVoyage> vers <ville>
  When j’ajoute le voyage à la compagnie
  Then le voyage est enregistré dans la liste de la compagnie
  And la compagnie du voyage est définie automatiquement à <nomCompagnie>

Examples:
  | nomCompagnie | nomVoyage       | ville     |
  | Air Afrique  | Sénégal Tour    | Dakar     |
  | Air Ivoire   | Ivoire Express  | Abidjan   |

Scenario Outline: refus d’ajout en double
  Given une compagnie nommée <nomCompagnie> ayant déjà un voyage nommé <nomVoyage>
  When j’ajoute à nouveau ce voyage à la compagnie
  Then le système refuse l’ajout en doublon
  And la liste de voyages de la compagnie contient une seule occurrence du voyage

Examples:
  | nomCompagnie | nomVoyage      |
  | Air Afrique  | Sénégal Tour   |
