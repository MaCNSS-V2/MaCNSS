package com.macnss.Model;

import java.util.Date;

import static com.macnss.helpers.helpers.*;

public class Patient {
    private String matrecule;
    private String nom;
    private String prenom;
    private String email;
    private float salere;
    private String statusRetraite;
    private float prixRetraite;
    private int totaleJourTravail;
    private String matreculeSociete;
    private String date_naissance;

    public Patient() {
    }

    public Patient(String matrecule, String nom, String prenom, String email, float salere, String statusRetraite, float prixRetraite, int totaleJourTravail, String matreculeSociete, String date_naissance) {
        this.matrecule = matrecule;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.salere = salere;
        this.statusRetraite = statusRetraite;
        this.prixRetraite = prixRetraite;
        this.totaleJourTravail = totaleJourTravail;
        this.matreculeSociete = matreculeSociete;
        this.date_naissance = date_naissance;
    }

    public String getMatrecule() {
        return matrecule;
    }

    public void setMatrecule(String matrecule) {
        this.matrecule = matrecule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public float getSalere() {
        return salere;
    }

    public void setSalere(float salere) {
        this.salere = salere;
    }

    public String getStatusRetraite() {
        return statusRetraite;
    }

    public void setStatusRetraite(String statusRetraite) {
        this.statusRetraite = statusRetraite;
    }

    public float getPrixRetraite() {
        return prixRetraite;
    }

    public void setPrixRetraite(float prixRetraite) {
        this.prixRetraite = prixRetraite;
    }

    public int getTotaleJourTravail() {
        return totaleJourTravail;
    }

    public void setTotaleJourTravail(int totaleJourTravail) {
        this.totaleJourTravail = totaleJourTravail;
    }

    public String getMatreculeSociete() {
        return matreculeSociete;
    }

    public void setMatreculeSociete(String matreculeSociete) {
        this.matreculeSociete = matreculeSociete;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public static PatientBuilder builder(){
        return new PatientBuilder();
    }

    @Override
    public String toString() {
        String tr = getJauneColor() + "|";

        tr += createTableEmployer(matrecule,"");
        tr += createTableEmployer(nom,"");
        tr += createTableEmployer(prenom,"");
        tr += createTableEmployer(String.valueOf(salere),"");
        tr += createTableEmployer(statusRetraite,"");
        tr += createTableEmployer(String.valueOf(totaleJourTravail),"");

        return tr;
    }
}
