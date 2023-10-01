package com.macnss.Model;

public class PatientBuilder {
    private String matrecule;
    private String nom;
    private String prenom;
    private String email;
    private float salere;
    private String statusRetraite;
    private float prixRetraite;
    private int totaleJourTravail;
    private String matreculeSociete;

    public PatientBuilder matrecule(String matrecule) {
        this.matrecule = matrecule;
        return this;
    }

    public PatientBuilder nom(String nom) {
        this.nom = nom;
        return this;
    }

    public PatientBuilder prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public PatientBuilder email(String email) {
        this.email = email;
        return this;
    }
    public PatientBuilder salere(float salere) {
        this.salere = salere;
        return this;
    }

    public PatientBuilder statusRetraite(String statusRetraite) {
        this.statusRetraite = statusRetraite;
        return this;
    }

    public PatientBuilder prixRetraite(float prixRetraite) {
        this.prixRetraite = prixRetraite;
        return this;
    }

    public PatientBuilder totaleJourTravail(int totaleJourTravail) {
        this.totaleJourTravail = totaleJourTravail;
        return this;
    }
    public PatientBuilder matreculeSociete(String matreculeSociete) {
        this.matreculeSociete = matreculeSociete;
        return this;
    }

    public Patient build(){
        return new Patient(matrecule, nom, prenom, email, salere, statusRetraite, prixRetraite, totaleJourTravail, matreculeSociete);
    }
}
