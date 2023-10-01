package com.macnss.Model;

import static com.macnss.helpers.helpers.*;
public class Agent {
    private int id;
    private String email;
    private String password;
    private String nom;
    private String prenom;


    public Agent() {
    }

    public Agent(int id, String email, String password, String nom, String prenom) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static AgentBuilder builder(){
        return new AgentBuilder();
    }


    @Override
    public String toString() {
        String tr = getJauneColor() + "|";

        tr += createTrTdAgents(String.valueOf(id));
        tr += createTrTdAgents(nom);
        tr += createTrTdAgents(prenom);
        tr += createTrTdAgents(email);

        return tr;
    }
}
