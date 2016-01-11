package com.vlstr.valentin.lecampusambiant;

/**
 * Created by ling on 16/12/15.
 */
public class ItemSolde {
    private String titre;
    private String soldeTexte;
    private String soldeMontant;
    private String repasRestant;
    private String repasTexte;

    public ItemSolde(){
        titre = "";
        soldeTexte = "";
        soldeMontant = "";
        repasRestant = "";
        repasTexte = "";
    }

    public String getRepasTexte() {
        return repasTexte;
    }

    public void setRepasTexte(String repasTexte) {
        this.repasTexte = repasTexte;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSoldeMontant() {
        return soldeMontant;
    }

    public void setSoldeMontant(String soldeMontant) {
        this.soldeMontant = soldeMontant;
    }

    public String getRepasRestant() {
        return repasRestant;
    }

    public void setRepasRestant(String repas) {
        this.repasRestant = repas;
    }

    public String getSoldeTexte() {
        return soldeTexte;
    }

    public void setSoldeTexte(String soldeText) {
        this.soldeTexte = soldeText;
    }
}
