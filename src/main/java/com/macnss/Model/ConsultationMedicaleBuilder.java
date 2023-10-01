package com.macnss.Model;

public class ConsultationMedicaleBuilder {
    private int id;
    private String nom_CM;
    private float prix_consultation;

    public ConsultationMedicaleBuilder id(int id) {
        this.id = id;
        return this;
    }

    public ConsultationMedicaleBuilder nom_CM(String nom_CM) {
        this.nom_CM = nom_CM;
        return this;
    }

    public ConsultationMedicaleBuilder prix_consultation(float prix_consultation) {
        this.prix_consultation = prix_consultation;
        return this;
    }
    public ConsultationMedicale build(){
        return new ConsultationMedicale(id, nom_CM, prix_consultation);
    }
}
