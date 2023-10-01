package com.macnss.Model;

public class AgentBuilder {
    private int id;
    private String email;
    private String password;
    private String nom;
    private String prenom;

    public AgentBuilder id(int id){
        this.id = id;
        return this;
    }
    public AgentBuilder email(String email){
        this.email = email;
        return this;
    }
    public AgentBuilder password(String password){
        this.password = password;
        return this;
    }
    public AgentBuilder nom(String nom){
        this.nom = nom;
        return this;
    }
    public AgentBuilder prenom(String prenom){
        this.prenom = prenom;
        return this;
    }
    public Agent build(){
        return new Agent(id, email, password, nom, prenom);
    }
}
