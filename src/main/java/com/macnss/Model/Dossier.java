package com.macnss.Model;

import static com.macnss.helpers.helpers.*;

public class Dossier {
    private int id;
    private float prix_retour;
    private String status;
    private int id_m;
    private int id_cm;
    private int id_a;
    private String  matrecule;

    public Dossier() {
    }

    public Dossier(int id, float prix_retour, String status, int id_m, int id_cm, int id_a ,String matrecule) {
        this.id = id;
        this.prix_retour = prix_retour;
        this.status = status;
        this.id_m = id_m;
        this.id_cm = id_cm;
        this.id_a = id_a;
        this.matrecule = matrecule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix_retour() {
        return prix_retour;
    }

    public void setPrix_retour(float prix_retour) {
        this.prix_retour = prix_retour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_m() {
        return id_m;
    }

    public void setId_m(int id_m) {
        this.id_m = id_m;
    }

    public int getId_cm() {
        return id_cm;
    }

    public void setId_cm(int id_cm) {
        this.id_cm = id_cm;
    }

    public int getId_a() {
        return id_a;
    }

    public void setId_a(int id_a) {
        this.id_a = id_a;
    }

    public String  getMatrecule() {
        return matrecule;
    }

    public void setMatrecule(String  matrecule) {
        this.matrecule = matrecule;
    }

    public static DossierBuilder builder(){
        return new DossierBuilder();
    }

    @Override
    public String toString() {
        String tr = getJauneColor() + "|";

        tr += createTrTdDossier(String.valueOf(id));
        tr += createTrTdDossier(String.valueOf(prix_retour));
        tr += createTrTdDossier(status);
        tr += createTrTdDossier(String.valueOf(id_a));
        tr += createTrTdDossier(String.valueOf(id_m));
        tr += createTrTdDossier(String.valueOf(id_cm));
        tr += createTrTdDossier(matrecule);

        return tr;
    }
}
