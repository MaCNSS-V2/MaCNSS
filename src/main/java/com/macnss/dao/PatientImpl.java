package com.macnss.dao;

import com.macnss.DBconnection.DBconnection;
import com.macnss.Model.Dossier;
import com.macnss.Model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.macnss.helpers.helpers.calculePrixRetraite;

public class PatientImpl implements PatientDao{
    @Override
    public List<Dossier> voirHistorique(String matrecule, int id_d) {
        Connection con = DBconnection.getConnection();

        List<Dossier> dossiers = new LinkedList<>();

        if (id_d == 0){
            String query = "SELECT * FROM dossier WHERE matrecule = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement((query))){
                preparedStatement.setString(1,matrecule);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    Dossier dossier = new Dossier (resultSet.getInt("id"),
                            resultSet.getFloat("prix_retour"),
                            resultSet.getString("status"),
                            resultSet.getInt("id_medicament"),
                            resultSet.getInt("id_consultation_medicale"),
                            resultSet.getInt("id_A"),
                            resultSet.getString("matrecule"));
                    dossiers.add(dossier);
                }

            } catch (SQLException se){
                se.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }else {
            String query = "SELECT * FROM dossier WHERE matrecule = ? AND id = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement((query))){
                preparedStatement.setString(1,matrecule);
                preparedStatement.setInt(2,id_d);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    Dossier dossier = new Dossier (resultSet.getInt("id"),
                            resultSet.getFloat("prix_retour"),
                            resultSet.getString("status"),
                            resultSet.getInt("id_medicament"),
                            resultSet.getInt("id_consultation_medicale"),
                            resultSet.getInt("id_A"),
                            resultSet.getString("matrecule"));
                    dossiers.add(dossier);
                }

            } catch (SQLException se){
                se.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException se){
                    se.printStackTrace();
                }
            }
        }

        return dossiers;
    }

    @Override
    public Patient login(String matrecule) {
        Patient patient;
        Connection con = DBconnection.getConnection();


        String query = "SELECT * FROM `patient` WHERE `matrecule`=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);){
            preparedStatement.setString(1,matrecule);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                    patient = new Patient(resultSet.getString("matrecule"),
                            resultSet.getString("nom_P"),
                            resultSet.getString("prenom_P"),
                            resultSet.getString("email"),
                            resultSet.getFloat("salere"),
                            resultSet.getString("statusRetraite"),
                            resultSet.getFloat("prixRetraite"),
                            resultSet.getInt("totaleJourTravail"),
                            resultSet.getString("matriculeSociete"),
                            resultSet.getString("date_naissance"));
                    return patient;
            }else {
                patient = Patient.builder()
                        .matrecule("null")
                        .email("null")
                        .nom("null")
                        .prenom("null")
                        .build();
                return patient;
            }
        } catch (SQLException se){
            se.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        patient = Patient.builder()
                .matrecule("null")
                .email("null")
                .nom("null")
                .prenom("null")
                .build();
        return patient;
    }

    @Override
    public boolean changeSociete(String matreculePatient, String matreculeEntreprise) {
        Connection con = DBconnection.getConnection();
        String query2 = "UPDATE `patient` SET `matriculeSociete`=? WHERE `matrecule`=?";
        try (PreparedStatement preparedStatement2 = con.prepareStatement(query2);){
            preparedStatement2.setString(1,matreculeEntreprise);
            preparedStatement2.setString(2,matreculePatient);

            preparedStatement2.executeUpdate();
            return true;
        } catch (SQLException se){
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public String checkRetraite(String matrecule) {
        Connection con = DBconnection.getConnection();

        String statusRetraite = null;
        String  prixRetraite = null;
        boolean resultat = false;
        String returnStatus= null;

        String query = "SELECT * FROM patient WHERE `matrecule` = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement((query))){
            preparedStatement.setString(1,matrecule);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                statusRetraite = resultSet.getString("statusRetraite");
                prixRetraite = resultSet.getString("prixRetraite");
                resultat = prendreRetraite(matrecule);
            }

        } catch (SQLException se){
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
        assert statusRetraite != null;
        if (statusRetraite.equals("Retraite")) {
            if (resultat == true){
                returnStatus = "vous pouvez prendre votre retraite et vous avez " + prixRetraite + "DH dans votre Retraite";
            } else {
                returnStatus = "vous pouvez etre retraite mais ton age est inferieur a 55ans";
            }
        }else {
            returnStatus="Vous etes "+statusRetraite;
        }
        return returnStatus;
    }

    @Override
    public void checkRetraiteCalculerPrixRetraite() {
        float PrixRetraite;
        Connection con = DBconnection.getConnection();

        String query = "SELECT * FROM patient";
        try (PreparedStatement preparedStatement = con.prepareStatement((query))){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String StatusRetraite;
                String matrecule = resultSet.getString("matrecule");
                int totaleJourTravail = resultSet.getInt("totaleJourTravail");
                int salere = resultSet.getInt("salere");
                if (totaleJourTravail >= 3240) {
                    StatusRetraite = "Retraite";
                    PrixRetraite = (float) calculePrixRetraite(salere,totaleJourTravail);
                } else {
                    StatusRetraite = "Non Retraite";
                    PrixRetraite = 0F;
                }
                String query2 = "UPDATE `patient` SET `statusRetraite`=?, `prixRetraite`=? WHERE `matrecule`=?";
                try (PreparedStatement preparedStatement2 = con.prepareStatement(query2);){
                    preparedStatement2.setString(1,StatusRetraite);
                    preparedStatement2.setFloat(2,PrixRetraite);
                    preparedStatement2.setString(3,matrecule);

                    preparedStatement2.executeUpdate();

                } catch (SQLException se){
                    se.printStackTrace();
                }
            }

        } catch (SQLException se){
            se.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
    }

    @Override
    public boolean prendreRetraite(String matrecule) {
        Connection con = DBconnection.getConnection();


        String query = "SELECT * FROM `patient` WHERE `matrecule`=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);){
            preparedStatement.setString(1,matrecule);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String date_naissance = String.valueOf(resultSet.getDate("date_naissance"));
                Date date_aujourdHui = new Date();

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date_aujourdHui);

                int anneeAujourdHui = calendar.get(Calendar.YEAR);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(date_naissance);
                java.util.Calendar calendar2 = java.util.Calendar.getInstance();
                calendar2.setTime(date);

                int anneeNaissance = calendar2.get(java.util.Calendar.YEAR);

                int deffirence_anneeNaissance_anneeAujourdHui = anneeAujourdHui - anneeNaissance;

                if (deffirence_anneeNaissance_anneeAujourdHui >= 55){
                    return true;
                }else {
                    return false;
                }

            }
        } catch (SQLException se){
            se.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
        return false;
    }
}
