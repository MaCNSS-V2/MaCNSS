package com.macnss.Model;

public class AdminBuilder {
    private int id;
    private String email;
    private String password;
    private String nom;
    private String prenom;

    public AdminBuilder id(int id){
        this.id = id;
        return this;
    }
    public AdminBuilder email(String email){
        this.email = email;
        return this;
    }
    public AdminBuilder password(String password){
        this.password = password;
        return this;
    }
    public AdminBuilder nom(String nom){
        this.nom = nom;
        return this;
    }
    public AdminBuilder prenom(String prenom){
        this.prenom = prenom;
        return this;
    }
    public Admin build(){
        return new Admin(id, email, password, nom, prenom);
    }
}
