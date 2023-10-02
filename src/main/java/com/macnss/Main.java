package com.macnss;

import com.macnss.Model.*;
import com.macnss.dao.*;
import com.macnss.helpers.*;

import java.util.Date;
import java.util.Scanner;
import java.util.prefs.Preferences;

import static com.macnss.helpers.helpers.*;

public class Main {

    public static void main(String[] args) {

        AdminDao adminDao = new AdminImpl();
        AgentDao agentDao = new AgentImpl();
        ConsultationMedicaleDao consultationMedicaleDao = new ConsultationMedicaleImpl();
        DossierDao dossierDao = new DossierImpl();
        MedicamentDao medicamentDao = new MedicamentImpl();
        PatientDao patientDao = new PatientImpl();
        SocieteDao societeDao = new SocieteImpl();

        Preferences preferences = Preferences.userNodeForPackage(Main.class);

        Scanner scanner = new Scanner(System.in);
        int choix;
        int id;
        String email;
        String password;
        String nom;
        String prenom;
        String matrecule;
        float salere;
        int status;
        int totaleJourTravail;
        int CM;
        int M;
        int nombreJourAbsance;
        int nombreJourMaladie;

        int randomCode;
        String body;
        String subject;

        String date_naissance;

        do {
            patientDao.checkRetraiteCalculerPrixRetraite();
            preferences.put("EMAIL", "null");
            preferences.put("NOM", "null");
            preferences.put("PRENOM", "null");
            preferences.put("MATRECULE", "null");
            preferences.putInt("ID", -1);

            System.out.println(createTable("", ""));
            System.out.println(createTable("Menu", getRougeClairColor()));
            System.out.println(createTable("", ""));
            System.out.println(createTable("1.Vous Etes Admin", getVertClairColor()));
            System.out.println(createTable("2.Vous Etes Agent", getVertClairColor()));
            System.out.println(createTable("3.Vous Etes Patient ou Employe", getVertClairColor()));
            System.out.println(createTable("4.Vous Etes Societe", getVertClairColor()));
            System.out.println(createTable("0.Quitter", getVertClairColor()));
            System.out.println(createTable("", ""));

            System.out.print("Enterez votre choix (0-4): ");
            choix = tryParse(scanner.nextLine());
            clearConsole(28);

            switch (choix) {
                case 1:
                    System.out.println("Entrer votre email");
                    email=scanner.nextLine();

                    System.out.println("Entrer votre password");
                    password=scanner.nextLine();

                        Admin admin = Admin.builder()
                                .email(email)
                                .password(password)
                                .build();
                        admin = adminDao.login(admin);
                        if (admin.getId() == -1){
                            System.out.println("votre email ou votre password est invalid");
                            break;
                        }
                        preferences.put("EMAIL", admin.getEmail());
                        preferences.put("NOM", admin.getNom());
                        preferences.put("PRENOM", admin.getPrenom());
                        preferences.putInt("ID", admin.getId());
                        preferences.put("MATRECULE", "null");

                        clearConsole(30);

                        System.out.println(createTable("", ""));
                        System.out.println(createTable("Menu", getRougeClairColor()));
                        System.out.println(createTable("", ""));
                        System.out.println(createTable("1.Afficher les Agents", getVertClairColor()));
                        System.out.println(createTable("2.Ajouter un Agent", getVertClairColor()));
                        System.out.println(createTable("3.Modifier un Agent", getVertClairColor()));
                        System.out.println(createTable("4.Supprimer un Agent", getVertClairColor()));
                        System.out.println(createTable("0.Quitter", getVertClairColor()));
                        System.out.println(createTable("", ""));

                        System.out.print("Enterez votre choix (0-4): ");
                        choix = tryParse(scanner.nextLine());
                        clearConsole(30);

                        switch (choix){
                            case 1:
                                System.out.println(createTableAgents("empty1"));
                                System.out.println(createTableAgents("List des Agents"));
                                System.out.println(createTableAgents("empty2"));
                                System.out.println(createTrAgents());
                                System.out.println(createTableAgents("empty2"));
                                adminDao.affichageAgent().forEach(System.out::println);
                                System.out.println(createTableAgents("empty2"));
                                break;
                            case 2:
                                System.out.println(createTableAgents("empty1"));
                                System.out.println(createTableAgents("Ajouter un Agent"));
                                System.out.println(createTableAgents("empty1"));
                                System.out.println("Entrer Email D'Agent");
                                email=scanner.nextLine();

                                System.out.println("Entrer Password D'Agent");
                                password=scanner.nextLine();

                                System.out.println("Entrer Nom D'Agent");
                                nom=scanner.nextLine();

                                System.out.println("Entrer Prenom D'Agent");
                                prenom=scanner.nextLine();

                                Agent agent = Agent.builder()
                                        .email(email)
                                        .password(password)
                                        .nom(nom)
                                        .prenom(prenom)
                                        .build();
                                boolean resultat = adminDao.ajoutAgent(agent);
                                if (resultat == true){
                                    System.out.println("Agent a ete ajoute");
                                }else {
                                    System.out.println("Cet agent se trouve dans la base donnee");
                                }
                                break;
                            case 3:
                                System.out.println(createTableAgents("empty1"));
                                System.out.println(createTableAgents("Modifier un Agent"));
                                System.out.println(createTableAgents("empty1"));

                                System.out.println("Entrer Email D'Agent");
                                email=scanner.nextLine();

                                System.out.println("Entrer Password D'Agent");
                                password=scanner.nextLine();

                                System.out.println("Entrer Nom D'Agent");
                                nom=scanner.nextLine();

                                System.out.println("Entrer Prenom D'Agent");
                                prenom=scanner.nextLine();

                                agent = Agent.builder()
                                        .email(email)
                                        .password(password)
                                        .nom(nom)
                                        .prenom(prenom)
                                        .build();
                                resultat = adminDao.modiferAgent(agent);
                                if (resultat == true){
                                    System.out.println("Agent est modife");
                                }else {
                                    System.out.println("Agent ne se trouve pas dans la base de donnee");
                                }
                                break;
                            case 4:
                                System.out.println(createTableAgents("empty1"));
                                System.out.println(createTableAgents("Supprimer un Agent"));
                                System.out.println(createTableAgents("empty1"));
                                System.out.println("Entrer Email D'Agent");
                                email=scanner.nextLine();

                                resultat = adminDao.supprissionAgent(email);
                                if (resultat == true){
                                    System.out.println("Agent Supprimer");
                                }else {
                                    System.out.println("Agent ne se trouve pas dans la base de donnee");
                                }
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Choix invalide. Selectionnez un choix valide.");
                                break;
                        }
                    break;
                case 2:
                    int codeConfirmation;
                    System.out.println("Entrer votre email");
                    email=scanner.nextLine();

                    System.out.println("Entrer votre password");
                    password=scanner.nextLine();

                    randomCode = helpers.generateCode();
                    body = "Code généré : " + randomCode;
                    subject = "Confirmer votre email";
                    helpers.sendMail(body,subject,email);

                    System.out.println("Le code de confirmation de confirmation est envoyer a votre adresse mail");
                    System.out.println("Entrer votre code de confirmation");
                    codeConfirmation=tryParse(scanner.nextLine());
                    if (codeConfirmation == randomCode){
                        Agent agent = Agent.builder()
                                .email(email)
                                .password(password)
                                .build();
                        agent = agentDao.login(agent);

                        if (agent.getId() == -1){
                            System.out.println("votre email ou votre password est invalid");
                            break;
                        }

                        preferences.put("EMAIL", agent.getEmail());
                        preferences.put("NOM", agent.getNom());
                        preferences.put("PRENOM", agent.getPrenom());
                        preferences.putInt("ID", agent.getId());
                        preferences.put("MATRECULE", "null");


                        clearConsole(30);

                        System.out.println(createTable("", ""));
                        System.out.println(createTable("Menu", getRougeClairColor()));
                        System.out.println(createTable("", ""));
                        System.out.println(createTable("1.Afficher les dossiers", getVertClairColor()));
                        System.out.println(createTable("2.Ajouter un Dossier", getVertClairColor()));
                        System.out.println(createTable("3.Modifier un Dossier", getVertClairColor()));
                        System.out.println(createTable("4.Supprimer un Dossier", getVertClairColor()));
                        System.out.println(createTable("5.comfirmer un Dossier", getVertClairColor()));
                        System.out.println(createTable("0.Quitter", getVertClairColor()));
                        System.out.println(createTable("", ""));

                        System.out.print("Enterez votre choix (0-5): ");
                        choix = tryParse(scanner.nextLine());
                        clearConsole(30);

                        switch (choix){
                            case 1:
                                System.out.println(createTrDossiers("sans1"));
                                System.out.println(createTrDossiers("List des Dossiers"));
                                System.out.println(createTrDossiers("sans2"));
                                System.out.println(createTrDossiers("sans3"));
                                System.out.println(createTrDossiers("sans2"));
                                agentDao.affichageDossier().forEach(System.out::println);
                                System.out.println(createTrDossiers("sans2"));
                                break;
                            case 2:
                                System.out.println(createTableAgents("empty1"));
                                System.out.println(createTableAgents("Ajouter un Dossier"));
                                System.out.println(createTableAgents("empty1"));

                                System.out.println("Entrer Matrecule de Patient");
                                matrecule=scanner.nextLine();

                                System.out.println(createTrMedicamentConsultationMedicale("sans1"));
                                System.out.println(createTrMedicamentConsultationMedicale("List des Consultations Medicales"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans3"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));
                                consultationMedicaleDao.affichageConsultationMedicales().forEach(System.out::println);
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));

                                System.out.println("Entrer Id de Consultation Medicale");
                                CM=tryParse(scanner.nextLine());


                                System.out.println(createTrMedicamentConsultationMedicale("sans1"));
                                System.out.println(createTrMedicamentConsultationMedicale("List des Medicaments"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans3"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));
                                medicamentDao.affichageMedicaments().forEach(System.out::println);
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));

                                System.out.println("Entrer Id de Medicament");
                                M=tryParse(scanner.nextLine());

                                float prix_retour = dossierDao.calculeDossier(M, CM);
                                Dossier dossier = Dossier.builder()
                                        .prix_retour(prix_retour)
                                        .matrecule(matrecule)
                                        .id_a(preferences.getInt("ID", -1))
                                        .id_m(M)
                                        .id_cm(CM)
                                        .build();
                                boolean resultat = agentDao.ajoutDossier(dossier);
                                if (resultat == true){
                                    System.out.println("Le dossier est ajoute");
                                }else {
                                    System.out.println("Le dossier n'est pas ajoute");
                                }
                                break;
                            case 3:
                                System.out.println(createTrDossiers("sans1"));
                                System.out.println(createTrDossiers("Modifier un Dossier"));
                                System.out.println(createTrDossiers("sans1"));

                                clearConsole(2);

                                System.out.println("Entrer Matrecule de Patient");
                                matrecule=scanner.nextLine();

                                System.out.println(createTrDossiers("sans1"));
                                System.out.println(createTrDossiers("List des Dossiers"));
                                System.out.println(createTrDossiers("sans2"));
                                System.out.println(createTrDossiers("sans3"));
                                System.out.println(createTrDossiers("sans2"));
                                patientDao.voirHistorique(matrecule,0).forEach(System.out::println);
                                System.out.println(createTrDossiers("sans2"));

                                System.out.println("Entrer Id de Dossier");
                                id=tryParse(scanner.nextLine());

                                System.out.println(createTrMedicamentConsultationMedicale("sans1"));
                                System.out.println(createTrMedicamentConsultationMedicale("List des Consultations Medicales"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans3"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));
                                consultationMedicaleDao.affichageConsultationMedicales().forEach(System.out::println);
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));

                                System.out.println("Entrer Id de Consultation Medicale");
                                CM=tryParse(scanner.nextLine());


                                System.out.println(createTrMedicamentConsultationMedicale("sans1"));
                                System.out.println(createTrMedicamentConsultationMedicale("List des Medicaments"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans3"));
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));
                                medicamentDao.affichageMedicaments().forEach(System.out::println);
                                System.out.println(createTrMedicamentConsultationMedicale("sans2"));

                                System.out.println("Entrer Id de Medicament");
                                M=tryParse(scanner.nextLine());

                                prix_retour = dossierDao.calculeDossier(M, CM);
                                dossier = Dossier.builder()
                                        .id(id)
                                        .prix_retour(prix_retour)
                                        .matrecule(matrecule)
                                        .id_a(preferences.getInt("ID", -1))
                                        .id_m(M)
                                        .id_cm(CM)
                                        .build();
                                resultat = agentDao.modifierDossier(dossier);
                                if (resultat == true){
                                    System.out.println("Le dossier est modifie");
                                }else {
                                    System.out.println("Le dossier n'est pas modifie");
                                }

                                break;
                            case 4:
                                System.out.println(createTrDossiers("sans1"));
                                System.out.println(createTrDossiers("Supprimer un Dossier"));
                                System.out.println(createTrDossiers("sans1"));

                                clearConsole(2);

                                System.out.println(createTrDossiers("sans1"));
                                System.out.println(createTrDossiers("List des Dossiers"));
                                System.out.println(createTrDossiers("sans2"));
                                System.out.println(createTrDossiers("sans3"));
                                System.out.println(createTrDossiers("sans2"));
                                agentDao.affichageDossier().forEach(System.out::println);
                                System.out.println(createTrDossiers("sans2"));

                                System.out.println("Entrer Id de Dossier");
                                id=tryParse(scanner.nextLine());

                                resultat = agentDao.supprisionDossier(id);
                                if (resultat == true){
                                    System.out.println("Le dossier est modifie");
                                }else {
                                    System.out.println("Le dossier n'est pas modifie");
                                }
                                break;
                            case 5:
                                System.out.println(createTrDossiers("sans1"));
                                System.out.println(createTrDossiers("List des Dossiers"));
                                System.out.println(createTrDossiers("sans2"));
                                System.out.println(createTrDossiers("sans3"));
                                System.out.println(createTrDossiers("sans2"));
                                agentDao.affichageDossier().forEach(System.out::println);
                                System.out.println(createTrDossiers("sans2"));

                                System.out.print("Enterez Id de Dossier");
                                id = tryParse(scanner.nextLine());

                                System.out.println(createTable("", ""));
                                System.out.println(createTable("comfirmer un Dossier", getRougeClairColor()));
                                System.out.println(createTable("", ""));
                                System.out.println(createTable("-1.Refusé", getVertClairColor()));
                                System.out.println(createTable("0.En attente", getVertClairColor()));
                                System.out.println(createTable("1.Validé", getVertClairColor()));
                                System.out.println(createTable("", ""));

                                System.out.print("Enterez votre choix (-1,0,1): ");
                                status = tryParse(scanner.nextLine());

                                resultat = agentDao.comfirmationDossier(id,status);
                                if (resultat == true){
                                    System.out.println("status est modifie");
                                }else {
                                    System.out.println("status n'est pas modifie");
                                }
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Choix invalide. Selectionnez un choix valide.");
                        }
                    }else {
                        System.out.println("Le code de confirmation est non valide");
                        break;
                    }
                    break;
                case 3:
                    System.out.println("Entrer votre matrecule");
                    matrecule=scanner.nextLine();

                    Patient patient = patientDao.login(matrecule);
                    if (patient.getemail() == "null"){
                        System.out.println("Matrecule est invalid");
                        break;
                    } else {

                            preferences.put("EMAIL", patient.getemail());
                            preferences.put("NOM", patient.getNom());
                            preferences.put("PRENOM", patient.getPrenom());
                            preferences.putInt("ID", -1);
                            preferences.put("MATRECULE", patient.getMatrecule());

                            clearConsole(30);

                            System.out.println(createTable("", ""));
                            System.out.println(createTable("Menu", getRougeClairColor()));
                            System.out.println(createTable("", ""));
                            System.out.println(createTable("1.Voir l'Historique Des dossiers", getVertClairColor()));
                            System.out.println(createTable("2.Voir l'Historique D'un' dossier", getVertClairColor()));
                            System.out.println(createTable("3.Voir êtes-vous retraité ?", getVertClairColor()));
                            System.out.println(createTable("0.Quitter", getVertClairColor()));
                            System.out.println(createTable("", ""));

                            System.out.print("Enterez votre choix (0-3): ");
                            choix = tryParse(scanner.nextLine());
                            clearConsole(30);

                            switch (choix){
                                case 1:
                                    System.out.println(createTrDossiers("sans1"));
                                    System.out.println(createTrDossiers("List des Dossiers"));
                                    System.out.println(createTrDossiers("sans2"));
                                    System.out.println(createTrDossiers("sans3"));
                                    System.out.println(createTrDossiers("sans2"));
                                    patientDao.voirHistorique(preferences.get("MATRECULE", "null"),0).forEach(System.out::println);
                                    System.out.println(createTrDossiers("sans2"));
                                    break;
                                case 2:
                                    System.out.println("Entrer Id de Dossier");
                                    id=tryParse(scanner.nextLine());

                                    System.out.println(createTrDossiers("sans1"));
                                    System.out.println(createTrDossiers("List des Dossiers"));
                                    System.out.println(createTrDossiers("sans2"));
                                    System.out.println(createTrDossiers("sans3"));
                                    System.out.println(createTrDossiers("sans2"));
                                    patientDao.voirHistorique(matrecule,id).forEach(System.out::println);
                                    System.out.println(createTrDossiers("sans2"));
                                    break;
                                case 3:
                                    String statusRetraite = patientDao.checkRetraite(preferences.get("MATRECULE", "null"));
                                    System.out.println(statusRetraite);
                                    break;
                                case 0:
                                    break;
                                default:
                                    System.out.println("Choix invalide. Selectionnez un choix valide.");
                            }

                    }
                    break;
                case 4:
                    System.out.println(createTable("", ""));
                    System.out.println(createTable("Menu", getRougeClairColor()));
                    System.out.println(createTable("", ""));
                    System.out.println(createTable("1.Login Societe", getVertClairColor()));
                    System.out.println(createTable("2.S'inscrire Societe", getVertClairColor()));
                    System.out.println(createTable("0.Quitter", getVertClairColor()));
                    System.out.println(createTable("", ""));

                    System.out.print("Enterez votre choix (0-2): ");
                    choix = tryParse(scanner.nextLine());
                    clearConsole(30);
                    switch (choix){
                        case 1:
                            System.out.println("Entrez votre email: ");
                            email = scanner.nextLine();
                            System.out.println("Entrez votre password");
                            password = scanner.nextLine();
                            Societe societe = Societe.builder()
                                    .email(email)
                                    .password(password)
                                    .build();
                            societe = societeDao.loginSociete(societe);
                            if (societe.getEmail() != "null") {
                                preferences.put("EMAIL", societe.getEmail());
                                preferences.put("NOM", societe.getNom());
                                preferences.put("PRENOM", societe.getPrenom());
                                preferences.putInt("ID", societe.getId());
                                preferences.put("MATRECULE", societe.getMatrecule());

                                clearConsole(30);
                                createNabarSociete(preferences.get("NOM", "Nom De Societe"));
                                clearConsole(5);

                                System.out.println(createTable("", ""));
                                System.out.println(createTable("Menu", getRougeClairColor()));
                                System.out.println(createTable("", ""));
                                System.out.println(createTable("1.Afficher les employes", getVertClairColor()));
                                System.out.println(createTable("2.Ajouter un employe", getVertClairColor()));
                                System.out.println(createTable("3.Modifier un employe", getVertClairColor()));
                                System.out.println(createTable("4.Supprimer un employe", getVertClairColor()));
                                System.out.println(createTable("5.Ajouter des jours de travail dans un mois a un employe", getVertClairColor()));
                                System.out.println(createTable("6.Ajouter un employe avec son matrecule", getVertClairColor()));
                                System.out.println(createTable("0.Quitter", getVertClairColor()));
                                System.out.println(createTable("", ""));

                                System.out.print("Enterez votre choix (0-6): ");
                                choix = tryParse(scanner.nextLine());
                                clearConsole(30);
                                switch (choix){
                                    case 1:
                                        createNabarSociete(preferences.get("NOM", "Nom De Societe"));
                                        System.out.println(createTableEmployer("Empty","Style1"));
                                        System.out.println(createTableEmployer("NoEmpty","Style1"));
                                        System.out.println(createTableEmployer("Empty","Style2"));
                                        System.out.println(createTableEmployer("NoEmpty","Style2"));
                                        System.out.println(createTableEmployer("Empty","Style2"));
                                        societeDao.affichageEmploye(preferences.get("MATRECULE", "Matrecule De Societe")).forEach(System.out::println);
                                        System.out.println(createTableEmployer("Empty","Style2"));
                                        break;
                                    case 2:
                                        createNabarSociete(preferences.get("NOM", "Nom De Societe"));
                                        System.out.println("Entrez email d'employe: ");
                                        email = scanner.nextLine();
                                        System.out.println("Entrez le nom d'employe");
                                        nom = scanner.nextLine();
                                        System.out.println("Entrez le prenom d'employe");
                                        prenom = scanner.nextLine();
                                        System.out.println("Entrez salere d'employe");
                                        salere = scanner.nextFloat();
                                        scanner.nextLine();
                                        System.out.println("Entrez totale des Jours de Travail d'employe");
                                        totaleJourTravail = tryParse(scanner.nextLine());
                                        System.out.println("Entrez date naissance d'employe sous la forme de " + getRougeClairColor()+ "yyyy-mm-jj" +getDefautColor());
                                        date_naissance = scanner.nextLine();
                                        patient = Patient.builder()
                                                .email(email)
                                                .nom(nom)
                                                .prenom(prenom)
                                                .salere(salere)
                                                .date_naissance(date_naissance)
                                                .matreculeSociete(preferences.get("MATRECULE", "Matrecule De Societe"))
                                                .totaleJourTravail(totaleJourTravail)
                                                .build();
                                        boolean resultat = societeDao.ajouterEmploye(patient);
                                        if (resultat == true){
                                            System.out.println("L'employe est ajoute");
                                        }else {
                                            System.out.println("L'employe dans la base de donnee");
                                        }
                                        break;
                                    case 3:
                                        createNabarSociete(preferences.get("NOM", "Nom De Societe"));
                                        System.out.println(createTableEmployer("Empty","Style1"));
                                        System.out.println(createTableEmployer("NoEmpty","Style1"));
                                        System.out.println(createTableEmployer("Empty","Style2"));
                                        System.out.println(createTableEmployer("NoEmpty","Style2"));
                                        System.out.println(createTableEmployer("Empty","Style2"));
                                        societeDao.affichageEmploye(preferences.get("MATRECULE", "Matrecule De Societe")).forEach(System.out::println);
                                        System.out.println(createTableEmployer("Empty","Style2"));

                                        System.out.println("Entrez matrecule d'employe: ");
                                        matrecule = scanner.nextLine();
                                        System.out.println("Entrez le nom d'employe");
                                        nom = scanner.nextLine();
                                        System.out.println("Entrez le prenom d'employe");
                                        prenom = scanner.nextLine();
                                        System.out.println("Entrez salere d'employe");
                                        salere = scanner.nextFloat();

                                        patient = Patient.builder()
                                                .nom(nom)
                                                .prenom(prenom)
                                                .salere(salere)
                                                .matrecule(matrecule)
                                                .build();
                                        resultat = societeDao.modifierEmploye(patient);
                                        if (resultat == true){
                                            System.out.println("L'employe est modifie");
                                        }else {
                                            System.out.println("L'employe ne se trouve pas de la base de donnee");
                                        }
                                        break;
                                    case 4:
                                        createNabarSociete(preferences.get("NOM", "Nom De Societe"));

                                        System.out.println(createTableEmployer("Empty","Style1"));
                                        System.out.println(createTableEmployer("NoEmpty","Style1"));
                                        System.out.println(createTableEmployer("Empty","Style2"));
                                        System.out.println(createTableEmployer("NoEmpty","Style2"));
                                        System.out.println(createTableEmployer("Empty","Style2"));
                                        societeDao.affichageEmploye(preferences.get("MATRECULE", "Matrecule De Societe")).forEach(System.out::println);
                                        System.out.println(createTableEmployer("Empty","Style2"));

                                        System.out.println("Entrez matrecule d'employe: ");
                                        matrecule = scanner.nextLine();

                                        resultat = societeDao.supprimerEmploye(matrecule);
                                        if (resultat == true){
                                            System.out.println("L'employe est supprimer");
                                        }else {
                                            System.out.println("L'employe ne se trouve pas dans la base de donnee");
                                        }
                                        break;
                                    case 5:
                                        createNabarSociete(preferences.get("NOM", "Nom De Societe"));

                                        System.out.println(createTableEmployer("Empty","Style1"));
                                        System.out.println(createTableEmployer("NoEmpty","Style1"));
                                        System.out.println(createTableEmployer("Empty","Style2"));
                                        System.out.println(createTableEmployer("NoEmpty","Style2"));
                                        System.out.println(createTableEmployer("Empty","Style2"));
                                        societeDao.affichageEmploye(preferences.get("MATRECULE", "Matrecule De Societe")).forEach(System.out::println);
                                        System.out.println(createTableEmployer("Empty","Style2"));

                                        System.out.println("Entrez matrecule d'employe: ");
                                        matrecule = scanner.nextLine();
                                        System.out.println("Entrez le nombre des jour ou employe est absant");
                                        nombreJourAbsance = tryParse(scanner.nextLine());
                                        System.out.println("Entrez le nombre des jour ou employe est malade");
                                        nombreJourMaladie = tryParse(scanner.nextLine());
                                        boolean Resultat = societeDao.ajouterJourTravailleEmploye(nombreJourAbsance,nombreJourMaladie,matrecule);
                                        if (Resultat == true){
                                            System.out.println("les jours sont ajoutes");
                                        }else {
                                            System.out.println("les jours ne sont pas ajoutes");
                                        }
                                        break;
                                    case 6:
                                        createNabarSociete(preferences.get("NOM", "Nom De Societe"));
                                        System.out.println("Entrez matrecule d'employe: ");
                                        matrecule = scanner.nextLine();
                                        Resultat = patientDao.changeSociete(matrecule,preferences.get("MATRECULE", "Matrecule De Societe"));
                                        if (Resultat == true){
                                            System.out.println("l'employe a ete ajoute avec son matrecule");
                                        }else {
                                            System.out.println("l'employe n'a pas ete ajoute avec son matrecule");
                                        }
                                        break;
                                    case 0:
                                        createNabarSociete(preferences.get("NOM", "Nom De Societe"));
                                        break;
                                    default:
                                        createNabarSociete(preferences.get("NOM", "Nom De Societe"));
                                        System.out.println("Choix invalide. Selectionnez un choix valide.");
                                }
                            } else {
                                System.out.println("Societe ne se trouve pas");
                            }
                            break;
                        case 2:
                            System.out.println("Entrez votre email: ");
                            email = scanner.nextLine();
                            System.out.println("Entrez votre password");
                            password = scanner.nextLine();
                            System.out.println("Entrez le nom de votre societe");
                            nom = scanner.nextLine();
                            System.out.println("Entrez le nom de directeur");
                            prenom = scanner.nextLine();

                            societe = Societe.builder()
                                    .email(email)
                                    .password(password)
                                    .nom(nom)
                                    .prenom(prenom)
                                    .build();
                            boolean resultat = societeDao.inscrireSociete(societe);
                            if (resultat == true){
                                System.out.println("Votre compte a été créé");
                            }else {
                                System.out.println("Votre compte n'a pas été créé");
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Choix invalide. Selectionnez un choix valide.");
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Choix invalide. Selectionnez un choix valide.");
            }
            preferences.put("EMAIL", "null");
            preferences.put("NOM", "null");
            preferences.put("PRENOM", "null");
            preferences.put("MATRECULE", "null");
            preferences.putInt("ID",-1);

            if (choix != 0){
                System.out.print("Voulez-vous continuer? (1 = Oui, 0 = Non): ");
                choix = tryParse(scanner.nextLine());
                clearConsole(30);
            }
        } while (choix != 0);
        FermetureProgramme();
        System.out.println("Soyez les bienvenus !");
    }
}