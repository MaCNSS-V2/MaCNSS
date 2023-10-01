package com.macnss.Model;

public class MedicamentBuilder {
    private int id;
    private String nom_m;
    private float prix_medicament;

    public MedicamentBuilder id(int id) {
        this.id = id;
        return this;
    }

    public MedicamentBuilder nom_m(String nom_m) {
        this.nom_m = nom_m;
        return this;
    }

    public MedicamentBuilder prix_medicament(float prix_medicament) {
        this.prix_medicament = prix_medicament;
        return this;
    }
    public Medicament build(){
        return new Medicament(id, nom_m, prix_medicament);
    }
}
