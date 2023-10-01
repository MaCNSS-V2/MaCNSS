package com.macnss.Model;

public class Societe {
    private int id;
    private String matrecule;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    public Societe() {
    }

    public Societe(int id, String matrecule, String nom, String prenom, String email, String password) {
        this.id = id;
        this.matrecule = matrecule;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public static SocieteBuilder builder(){
        return new SocieteBuilder();
    }

    @Override
    public String toString() {
        return "Societe{" +
                "id=" + id +
                ", matrecule='" + matrecule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
