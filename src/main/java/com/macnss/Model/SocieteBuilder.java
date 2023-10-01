package com.macnss.Model;

public class SocieteBuilder {
    private int id;
    private String matrecule;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    public SocieteBuilder id(int id) {
        this.id = id;
        return this;
    }
    public SocieteBuilder matrecule(String matrecule) {
        this.matrecule = matrecule;
        return this;
    }
    public SocieteBuilder nom(String nom) {
        this.nom = nom;
        return this;
    }
    public SocieteBuilder prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }
    public SocieteBuilder email(String email) {
        this.email = email;
        return this;
    }
    public SocieteBuilder password(String password) {
        this.password = password;
        return this;
    }
    public Societe build(){
        return new Societe(id, matrecule, nom, prenom, email, password);
    }
}
