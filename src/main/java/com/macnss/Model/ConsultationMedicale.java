package com.macnss.Model;

import static com.macnss.helpers.helpers.createTrTdTrMedicamentConsultationMedicale;
import static com.macnss.helpers.helpers.getJauneColor;

public class ConsultationMedicale {
    private int id;
    private String nom_CM;
    private float prix_consultation;

    public ConsultationMedicale() {
    }

    public ConsultationMedicale(int id, String nom_CM, float prix_consultation) {
        this.id = id;
        this.nom_CM = nom_CM;
        this.prix_consultation = prix_consultation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_CM() {
        return nom_CM;
    }

    public void setNom_CM(String nom_CM) {
        this.nom_CM = nom_CM;
    }

    public float getPrix_consultation() {
        return prix_consultation;
    }

    public void setPrix_consultation(float prix_consultation) {
        this.prix_consultation = prix_consultation;
    }
    public static ConsultationMedicaleBuilder builder(){
        return new ConsultationMedicaleBuilder();
    }

    @Override
    public String toString() {
        String tr = getJauneColor() + "|";

        tr += createTrTdTrMedicamentConsultationMedicale(String.valueOf(id));
        tr += createTrTdTrMedicamentConsultationMedicale(nom_CM);
        tr += createTrTdTrMedicamentConsultationMedicale(String.valueOf(prix_consultation));

        return tr;
    }
}
