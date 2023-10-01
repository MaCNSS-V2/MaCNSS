package com.macnss.Model;

public class DossierBuilder {
    private int id;
    private float prix_retour;
    private String status;
    private int id_m;
    private int id_cm;
    private int id_a;
    private String  matrecule;

    public DossierBuilder id(int id) {
        this.id = id;
        return this;
    }

    public DossierBuilder prix_retour(float prix_retour) {
        this.prix_retour = prix_retour;
        return this;
    }

    public DossierBuilder status(String status) {
        this.status = status;
        return this;
    }
    public DossierBuilder id_m(int id_m) {
        this.id_m = id_m;
        return this;
    }

    public DossierBuilder id_a(int id_a) {
        this.id_a = id_a;
        return this;
    }
    public DossierBuilder id_cm(int id_cm) {
        this.id_cm = id_cm;
        return this;
    }

    public DossierBuilder matrecule(String matrecule) {
        this.matrecule = matrecule;
        return this;
    }

    public Dossier build(){
        return new Dossier(id, prix_retour, status, id_m, id_cm, id_a , matrecule);
    }
}