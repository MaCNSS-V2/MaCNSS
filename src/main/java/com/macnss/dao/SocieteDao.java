package com.macnss.dao;

import com.macnss.Model.Patient;
import com.macnss.Model.Societe;

import java.util.List;

public interface SocieteDao {
    boolean inscrireSociete(Societe societe);
    boolean ajouterEmploye(Patient patient);
    boolean modifierEmploye(Patient patient);
    boolean supprimerEmploye(String matrecule);
    List<Patient> affichageEmploye(String matreculeEntreprise);
    Societe loginSociete(Societe societe);
    boolean ajouterJourTravailleEmploye(int nombreJourAbsance, int nombreJourMaladie, String matrecule);
}