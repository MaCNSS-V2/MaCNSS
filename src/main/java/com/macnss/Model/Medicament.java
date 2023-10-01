package com.macnss.Model;

import static com.macnss.helpers.helpers.*;

public class Medicament {
    private int id;
    private String nom_m;
    private float prix_medicament;

    public Medicament() {
    }

    public Medicament(int id, String nom_m, float prix_medicament) {
        this.id = id;
        this.nom_m = nom_m;
        this.prix_medicament = prix_medicament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_m() {
        return nom_m;
    }

    public void setNom_m(String nom_m) {
        this.nom_m = nom_m;
    }

    public float getPrix_medicament() {
        return prix_medicament;
    }

    public void setPrix_medicament(float prix_medicament) {
        this.prix_medicament = prix_medicament;
    }
    public static MedicamentBuilder builder(){
        return new MedicamentBuilder();
    }

    @Override
    public String toString() {
        String tr = getJauneColor() + "|";

        tr += createTrTdTrMedicamentConsultationMedicale(String.valueOf(id));
        tr += createTrTdTrMedicamentConsultationMedicale(nom_m);
        tr += createTrTdTrMedicamentConsultationMedicale(String.valueOf(prix_medicament));

        return tr;
    }
}
