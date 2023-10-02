package com.macnss.helpers;

import java.util.Random;

import static java.lang.String.format;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.Supplier;

public class helpers {
    public static int generateCode(){
        Random rand = new Random();

        int code = rand.nextInt(999999);

        String codeString = format("%06d", code);

        return Integer.parseInt(codeString);
    }
    public static Boolean sendMail(String body,String subject ,String email) {
        final String username = "USERNAME";
        final String password = "PASSWORD";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
    static String jauneColor = "\u001B[93m";
    static String purpleColor = "\u001B[35m";
    static String defautColor = "\u001B[0m";
    static String vertClairColor = "\u001B[92m";
    static String rougeClairColor = "\u001B[91m";
    static int td_lenght;
    static int td_lenght_empty;
    public static String createTable(String td,String color){
        String tr = jauneColor + "|";
        if (td == ""){
            tr = jauneColor + "+";
            for (int i=0; i<90; i++){
                tr += "-";
            }
            tr += "+";
            tr += defautColor;
        } else if (td == "style2") {
            tr = jauneColor + "+";
            for (int i=0; i<59; i++){
                tr += "-";
            }
            tr += "+";
            for (int i=0; i<30; i++){
                tr += "-";
            }
            tr += "+";
            tr += defautColor;
        } else {
            td_lenght = td.length();
            td_lenght_empty = (90 - td_lenght)/2;
            tr += color;
            for (int i=0; i<td_lenght_empty; i++){
                tr += " ";
            }
            tr += td;
            if (((90 - td_lenght) % 2) == 1){
                for (int i=0; i<td_lenght_empty+1; i++){
                    tr += " ";
                }
            }else {
                for (int i=0; i<td_lenght_empty; i++){
                    tr += " ";
                }
            }
            tr += jauneColor + "|";
            tr += defautColor;
        }
        return tr;
    }
    public static String createTableAgents(String td){
        String tr = jauneColor + "+";
        if (td == "empty1"){
            for (int i=0; i<151; i++){
                tr += "-";
            }
            tr += "+";
            tr += defautColor;
        }
        else if (td == "empty2"){
            for (int i=0; i<37; i++){
                tr += "-";
            }
            tr += "+";
            for (int i=0; i<37; i++){
                tr += "-";
            }
            tr += "+";
            for (int i=0; i<37; i++){
                tr += "-";
            }
            tr += "+";
            for (int i=0; i<37; i++){
                tr += "-";
            }
            tr += "+";
            tr += defautColor;
        }
        else {
            tr = jauneColor + "|";
            td_lenght = td.length();
            td_lenght_empty = (151 - td_lenght)/2;
            tr += rougeClairColor;
            for (int i=0; i<td_lenght_empty; i++){
                tr += " ";
            }
            tr += td;
            if (((153 - td_lenght) % 2) == 1){
                for (int i=0; i<td_lenght_empty+1; i++){
                    tr += " ";
                }
            }else {
                for (int i=0; i<td_lenght_empty; i++){
                    tr += " ";
                }
            }
            tr += jauneColor;
            tr += "|";
            tr += defautColor;
        }
        return tr;
    }
    public static String createTrAgents(){
        String td1 = "Id";
        String td2 = "Nom Agents";
        String td3 = "Prenom Agents";
        String td4 = "Email";
        int td1_lenght = td1.length();
        int td1_lenght_empty = 37 - td1_lenght;
        int demi_td1_lenght_empty = td1_lenght_empty / 2;

        int td2_lenght = td2.length();
        int td2_lenght_empty = 37 - td2_lenght;
        int demi_td2_lenght_empty = td2_lenght_empty / 2;

        int td3_lenght = td3.length();
        int td3_lenght_empty = 37 - td3_lenght;
        int demi_td3_lenght_empty = td3_lenght_empty / 2;

        int td4_lenght = td4.length();
        int td4_lenght_empty = 37 - td4_lenght;
        int demi_td4_lenght_empty = td4_lenght_empty / 2;

        String tr = jauneColor + "|" +purpleColor;
        for (int i=0; i<demi_td1_lenght_empty+1; i++){
            tr += " ";
        }
        tr += td1;
        for (int i=0; i<demi_td1_lenght_empty; i++){
            tr += " ";
        }
        tr += jauneColor + "|" +purpleColor;
        for (int i=0; i<demi_td2_lenght_empty; i++){
            tr += " ";
        }
        tr += td2;
        for (int i=0; i<demi_td2_lenght_empty+1; i++){
            tr += " ";
        }
        tr += jauneColor + "|" +purpleColor;
        for (int i=0; i<demi_td3_lenght_empty; i++){
            tr += " ";
        }
        tr += td3;
        for (int i=0; i<demi_td3_lenght_empty; i++){
            tr += " ";
        }
        tr += jauneColor + "|" +purpleColor;
        for (int i=0; i<demi_td4_lenght_empty; i++){
            tr += " ";
        }
        tr += td4;
        for (int i=0; i<demi_td4_lenght_empty; i++){
            tr += " ";
        }
        tr += jauneColor + "|" + defautColor;
        return tr;
    }
    public static String createTrDossiers(String sans){
        String td1 = "Id Dossier";
        String td2 = "Prix Retour";
        String td3 = "Status";
        String td4 = "Id Agent";
        String td5 = "Id Medicament";
        String td6 = "Id Consultation M.";
        String td7 = "Matrecule";
        int td1_lenght = td1.length();
        int td1_lenght_empty = 20 - td1_lenght;
        int demi_td1_lenght_empty = td1_lenght_empty / 2;

        int td2_lenght = td2.length();
        int td2_lenght_empty = 20 - td2_lenght;
        int demi_td2_lenght_empty = td2_lenght_empty / 2;

        int td3_lenght = td3.length();
        int td3_lenght_empty = 20 - td3_lenght;
        int demi_td3_lenght_empty = td3_lenght_empty / 2;

        int td4_lenght = td4.length();
        int td4_lenght_empty = 20 - td4_lenght;
        int demi_td4_lenght_empty = td4_lenght_empty / 2;

        int td5_lenght = td5.length();
        int td5_lenght_empty = 20 - td5_lenght;
        int demi_td5_lenght_empty = td5_lenght_empty / 2;

        int td7_lenght = td7.length();
        int td7_lenght_empty = 20 - td7_lenght;
        int demi_td7_lenght_empty = td7_lenght_empty / 2;

        String tr = jauneColor + "|" +purpleColor;
        if (sans == "sans1"){
            tr = jauneColor + "+";
            for (int i=0; i<146; i++){
                tr += "-";
            }
            tr += jauneColor + "+" + defautColor;
        } if (sans == "sans2"){
            tr = jauneColor + "+";
            for (int j = 0 ; j<7; j++){
                for (int i=0; i<20; i++){
                    tr += "-";
                }
                tr += jauneColor + "+" ;
            }
            tr += defautColor;
        }if (sans == "sans3"){
            for (int i=0; i<demi_td1_lenght_empty; i++){
                tr += " ";
            }
            tr += td1;
            for (int i=0; i<demi_td1_lenght_empty; i++){
                tr += " ";
            }
            tr += jauneColor + "|" +purpleColor;
            for (int i=0; i<demi_td2_lenght_empty; i++){
                tr += " ";
            }
            tr += td2;
            for (int i=0; i<demi_td2_lenght_empty+1; i++){
                tr += " ";
            }
            tr += jauneColor + "|" +purpleColor;
            for (int i=0; i<demi_td3_lenght_empty; i++){
                tr += " ";
            }
            tr += td3;
            for (int i=0; i<demi_td3_lenght_empty; i++){
                tr += " ";
            }
            tr += jauneColor + "|" +purpleColor;
            for (int i=0; i<demi_td4_lenght_empty; i++){
                tr += " ";
            }
            tr += td4;
            for (int i=0; i<demi_td4_lenght_empty; i++){
                tr += " ";
            }
            tr += jauneColor + "|" +purpleColor;
            for (int i=0; i<demi_td5_lenght_empty; i++){
                tr += " ";
            }
            tr += td5;
            for (int i=0; i<demi_td5_lenght_empty+1; i++){
                tr += " ";
            }
            tr += jauneColor + "| " +purpleColor + td6 + jauneColor + " |" +purpleColor;
            for (int i=0; i<demi_td7_lenght_empty; i++){
                tr += " ";
            }
            tr += td7;
            for (int i=0; i<demi_td7_lenght_empty+1; i++){
                tr += " ";
            }
            tr += jauneColor + "|" + defautColor;
        }else {
            td_lenght = sans.length();
            td_lenght_empty = (146 - td_lenght)/2;
            tr += getRougeClairColor();
            for (int i=0; i<td_lenght_empty; i++){
                tr += " ";
            }
            tr += sans;
            if (((146 - td_lenght) % 2) == 1){
                for (int i=0; i<td_lenght_empty+1; i++){
                    tr += " ";
                }
            }else {
                for (int i=0; i<td_lenght_empty; i++){
                    tr += " ";
                }
            }
            tr += jauneColor + "|";
            tr += defautColor;
        }
        return tr;
    }
    public static String createTrMedicamentConsultationMedicale(String sans){

        String td1 = "Id";
        String td2 = "Nom";
        String td3 = "Prix";

        int td1_lenght = td1.length();
        int td1_lenght_empty = 48 - td1_lenght;
        int demi_td1_lenght_empty = td1_lenght_empty / 2;

        int td2_lenght = td2.length();
        int td2_lenght_empty = 48 - td2_lenght;
        int demi_td2_lenght_empty = td2_lenght_empty / 2;

        int td3_lenght = td3.length();
        int td3_lenght_empty = 48 - td3_lenght;
        int demi_td3_lenght_empty = td3_lenght_empty / 2;

        String tr = jauneColor + "|" +purpleColor;
        if (sans == "sans1"){
            tr = jauneColor + "+";
            for (int i=0; i<146; i++){
                tr += "-";
            }
            tr += jauneColor + "+" + defautColor;
        } if (sans == "sans2"){
            tr = jauneColor + "+";
            for (int j = 0 ; j<3; j++){
                for (int i=0; i<48; i++){
                    tr += "-";
                }
                tr += jauneColor + "+" ;
            }
            tr += defautColor;
        }if (sans == "sans3"){
            for (int i=0; i<demi_td1_lenght_empty; i++){
                tr += " ";
            }
            tr += td1;
            for (int i=0; i<demi_td1_lenght_empty; i++){
                tr += " ";
            }
            tr += jauneColor + "|" +purpleColor;
            for (int i=0; i<demi_td2_lenght_empty; i++){
                tr += " ";
            }
            tr += td2;
            for (int i=0; i<demi_td2_lenght_empty+1; i++){
                tr += " ";
            }
            tr += jauneColor + "|" +purpleColor;
            for (int i=0; i<demi_td3_lenght_empty; i++){
                tr += " ";
            }
            tr += td3;
            for (int i=0; i<demi_td3_lenght_empty; i++){
                tr += " ";
            }
            tr += jauneColor + "|" + defautColor;
        }else {
            td_lenght = sans.length();
            td_lenght_empty = (146 - td_lenght)/2;
            tr += getRougeClairColor();
            for (int i=0; i<td_lenght_empty; i++){
                tr += " ";
            }
            tr += sans;
            if (((146 - td_lenght) % 2) == 1){
                for (int i=0; i<td_lenght_empty+1; i++){
                    tr += " ";
                }
            }else {
                for (int i=0; i<td_lenght_empty; i++){
                    tr += " ";
                }
            }
            tr += jauneColor + "|";
            tr += defautColor;
        }
        return tr;
    }
    public static String createTrTdAgents(String td){
        int td_lenght = td.length();
        int td_lenght_empty = 37 - td_lenght;
        int demi_td_lenght_empty = td_lenght_empty / 2;
        int modulo_td_lenght_2 = td_lenght_empty % 2;
        String tr = vertClairColor;
        for (int i=0; i<demi_td_lenght_empty; i++){
            tr += " ";
        }
        tr += td;
        if (modulo_td_lenght_2 == 1){
            for (int i=0; i<demi_td_lenght_empty+1; i++){
                tr += " ";
            }
        }else {
            for (int i=0; i<demi_td_lenght_empty; i++){
                tr += " ";
            }
        }
        tr += jauneColor + "|" + defautColor;
        return tr;
    }
    public static String createTrTdDossier(String td){
        int td_lenght = td.length();
        int td_lenght_empty = 20 - td_lenght;
        int demi_td_lenght_empty = td_lenght_empty / 2;
        int modulo_td_lenght_2 = td_lenght_empty % 2;
        String tr = vertClairColor;
        for (int i=0; i<demi_td_lenght_empty; i++){
            tr += " ";
        }
        tr += td;
        if (modulo_td_lenght_2 == 1){
            for (int i=0; i<demi_td_lenght_empty+1; i++){
                tr += " ";
            }
        }else {
            for (int i=0; i<demi_td_lenght_empty; i++){
                tr += " ";
            }
        }
        tr += jauneColor + "|" + defautColor;
        return tr;
    }
    public static String createTrTdTrMedicamentConsultationMedicale(String td){
        int td_lenght = td.length();
        int td_lenght_empty = 48 - td_lenght;
        int demi_td_lenght_empty = td_lenght_empty / 2;
        int modulo_td_lenght_2 = td_lenght_empty % 2;
        String tr = vertClairColor;
        for (int i=0; i<demi_td_lenght_empty; i++){
            tr += " ";
        }
        tr += td;
        if (modulo_td_lenght_2 == 1){
            for (int i=0; i<demi_td_lenght_empty+1; i++){
                tr += " ";
            }
        }else {
            for (int i=0; i<demi_td_lenght_empty; i++){
                tr += " ";
            }
        }
        tr += jauneColor + "|" + defautColor;
        return tr;
    }
    public static void clearConsole(int taille) {
        for (int i = 0; i < taille; i++) {
            System.out.println();
        }
    }
    public static void FermetureProgramme(){

        System.out.print(getVertClairColor() +"Fermeture du programme" + getDefautColor());
        try {
            String points = "";
            for (int i = 0; i < 3; i++) {
                points += " .";
                System.out.flush();
                Thread.sleep(250);
                System.out.print("\r");
                System.out.print(getVertClairColor() +"Fermeture du programme" + points + getDefautColor());
                Thread.sleep(250);
            }
            clearConsole(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String getJauneColor() {
        return jauneColor;
    }
    public static String getDefautColor() {
        return defautColor;
    }
    public static String getVertClairColor() {
        return vertClairColor;
    }
    public static String getRougeClairColor() {
        return rougeClairColor;
    }
    public static String generateMatrecule(String nom, String prenom, String email,int Taille){
        String Matrecule = null;

        String nomReturn = nom.substring(0, Taille).toUpperCase();
        String prenomReturn = prenom.substring(0, Taille).toUpperCase();
        String emailReturn = email.substring(0, Taille).toUpperCase();

        Matrecule = nomReturn + prenomReturn + emailReturn;

        return Matrecule;
    };
    public static int calculerNombreMois(int nombreDeJours) {
        final double JOURS_PAR_MOIS = 30.44;
        Function<Integer, Integer> calculMois = (jours) -> {
            double nombreDeMoisDouble = jours / JOURS_PAR_MOIS;
            return (int) Math.round(nombreDeMoisDouble);
        };
        return calculMois.apply(nombreDeJours);
    }//lamda1
    public static double calculePrixRetraite(float salaire, int totaleJourTravail){
        int nombreDeMois = calculerNombreMois(totaleJourTravail);
        int nombreDeFoisDe216 = (totaleJourTravail-3240)/216;
        Supplier<Double> calculPrixRetraite = () ->
                (salaire * nombreDeMois) * ((50 + nombreDeFoisDe216) / 100.0);

        double prixRetraite = calculPrixRetraite.get();
        return prixRetraite;
    }//lamda2
    public static void createNabarSociete(String nomSociete){
        nomSociete=nomSociete.toUpperCase();
        String nomSocieteUpperCasePlusSpace = "";
        String space="";
        for (int i = 0; i < nomSociete.length(); i++) {
            nomSocieteUpperCasePlusSpace += nomSociete.charAt(i) + " ";
        }
        int spaceFull = 141-nomSocieteUpperCasePlusSpace.length();
        for (int i = 0; i < spaceFull; i++) {
            space+=" ";
        }
        String logo = purpleColor + "+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n"+defautColor;
              logo += purpleColor + "|"+jauneColor+"\t+----------------------+"+defautColor+"                                                                                                                                                   " +purpleColor + "|\n"+defautColor;
              logo += purpleColor + "|"+jauneColor+"\t|"+vertClairColor+"         +  +         "+jauneColor+"|"+defautColor+"                                                                                                                                                   " +purpleColor + "|\n"+defautColor;
              logo += purpleColor + "|"+jauneColor+"\t|"+vertClairColor+"         +  +         "+jauneColor+"|"+defautColor+"                                                                                                                                                   " +purpleColor + "|\n"+defautColor;
              logo += purpleColor + "|"+jauneColor+"\t|"+vertClairColor+"   + + + +  + + + +   "+jauneColor+"|"+defautColor+"                                                                                                                                                   " +purpleColor + "|\n"+defautColor;
              logo += purpleColor + "|"+jauneColor+"\t|"+vertClairColor+"   + + + +  + + + +   "+jauneColor+"|"+rougeClairColor+ space + nomSocieteUpperCasePlusSpace + defautColor + "      " +purpleColor + "|\n"+defautColor;
              logo += purpleColor + "|"+jauneColor+"\t|"+vertClairColor+"         +  +         "+jauneColor+"|"+defautColor+"                                                                                                                                                   " +purpleColor + "|\n"+defautColor;
              logo += purpleColor + "|"+jauneColor+"\t|"+vertClairColor+"         +  +         "+jauneColor+"|"+defautColor+"                                                                                                                                                   " +purpleColor + "|\n"+defautColor;
              logo += purpleColor + "|"+jauneColor+"\t|"+vertClairColor+"     M A  C N S S     "+jauneColor+"|"+defautColor+"                                                                                                                                                   " +purpleColor + "|\n"+defautColor;
              logo += purpleColor + "|"+jauneColor+"\t+----------------------+                                                                                                                                                   " +purpleColor + "|\n"+defautColor;
              logo += purpleColor + "+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+\n"+defautColor;
        System.out.println(logo);
    }
    public static String createTableEmployer(String emptyNoEmpty, String style){
        String tr = jauneColor;

        String td1 = "Matrecule";
        String td2 = "Nom";
        String td3 = "Prenom";
        String td4 = "Salere";
        String td5 = "Status Retraite";
        String td6 = "Les Jours Travails";

        int td1_lenght = td1.length();
        int td1_lenght_empty = 28 - td1_lenght;
        int demi_td1_lenght_empty = td1_lenght_empty / 2;

        int td2_lenght = td2.length();
        int td2_lenght_empty = 28 - td2_lenght;
        int demi_td2_lenght_empty = td2_lenght_empty / 2;

        int td3_lenght = td3.length();
        int td3_lenght_empty = 28 - td3_lenght;
        int demi_td3_lenght_empty = td3_lenght_empty / 2;

        int td4_lenght = td4.length();
        int td4_lenght_empty = 28 - td4_lenght;
        int demi_td4_lenght_empty = td4_lenght_empty / 2;

        int td5_lenght = td5.length();
        int td5_lenght_empty = 28 - td5_lenght;
        int demi_td5_lenght_empty = td5_lenght_empty / 2;

        int td6_lenght = td6.length();
        int td6_lenght_empty = 28 - td6_lenght;
        int demi_td6_lenght_empty = td6_lenght_empty / 2;

        String td = "List des Employes";

        int td_lenght = td.length();
        int td_lenght_empty = 174 - td_lenght;
        int demi_td_lenght_empty = td_lenght_empty / 2;

        if (emptyNoEmpty == "Empty" && style == "Style1"){
            tr += "+";
            for (int i = 0; i < 173; i++) {
                tr += "-";
            }
            tr += "+" + defautColor;
        } else if (emptyNoEmpty == "Empty" && style == "Style2") {
            tr += "+";
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 28; j++) {
                    tr += "-";
                }
                tr += "+";
            }
            tr += defautColor;
        } else if (emptyNoEmpty == "NoEmpty" && style == "Style1"){
            tr += "|";
            for (int i = 0; i < demi_td_lenght_empty; i++) {
                tr += " ";
            }
            tr += rougeClairColor + td + jauneColor;
            for (int i = 0; i < demi_td_lenght_empty; i++) {
                tr += " ";
            }
            tr += "|" + defautColor;
        } else if (emptyNoEmpty == "NoEmpty" && style == "Style2") {
            tr += "|";
            for (int j = 0; j < demi_td1_lenght_empty; j++) {
                tr += " ";
            }
            tr += purpleColor + td1 + jauneColor;
            for (int j = 0; j < demi_td1_lenght_empty + 1; j++) {
                tr += " ";
            }
            tr += "|";
            for (int j = 0; j < demi_td2_lenght_empty; j++) {
                tr += " ";
            }
            tr += purpleColor + td2 + jauneColor;
            for (int j = 0; j < demi_td2_lenght_empty + 1; j++) {
                tr += " ";
            }
            tr += "|";
            for (int j = 0; j < demi_td3_lenght_empty; j++) {
                tr += " ";
            }
            tr += purpleColor + td3 + jauneColor;
            for (int j = 0; j < demi_td3_lenght_empty; j++) {
                tr += " ";
            }
            tr += "|";
            for (int j = 0; j < demi_td4_lenght_empty; j++) {
                tr += " ";
            }
            tr += purpleColor + td4 + jauneColor;
            for (int j = 0; j < demi_td4_lenght_empty; j++) {
                tr += " ";
            }
            tr += "|";
            for (int j = 0; j < demi_td5_lenght_empty; j++) {
                tr += " ";
            }
            tr += purpleColor + td5 + jauneColor;
            for (int j = 0; j < demi_td5_lenght_empty + 1; j++) {
                tr += " ";
            }
            tr += "|";
            for (int j = 0; j < demi_td6_lenght_empty; j++) {
                tr += " ";
            }
            tr += purpleColor + td6 + jauneColor;
            for (int j = 0; j < demi_td6_lenght_empty; j++) {
                tr += " ";
            }
            tr += "|" + defautColor;
        } else {
            int emptyNoEmpty_lenght = emptyNoEmpty.length();
            int emptyNoEmpty_lenght_empty = 28 - emptyNoEmpty_lenght;
            int demi_emptyNoEmpty_lenght_empty = emptyNoEmpty_lenght_empty / 2;
            int modulo_emptyNoEmpty_lenght_2 = emptyNoEmpty_lenght % 2;

            tr = vertClairColor;
            for (int i=0; i<demi_emptyNoEmpty_lenght_empty; i++){
                tr += " ";
            }
            tr += emptyNoEmpty;
            if (modulo_emptyNoEmpty_lenght_2 == 1){
                for (int i=0; i<demi_emptyNoEmpty_lenght_empty+1; i++){
                    tr += " ";
                }
            }else {
                for (int i=0; i<demi_emptyNoEmpty_lenght_empty; i++){
                    tr += " ";
                }
            }
            tr += jauneColor + "|" + defautColor;
        }
        return tr;
    }
    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return 100;
        }
    }
}
