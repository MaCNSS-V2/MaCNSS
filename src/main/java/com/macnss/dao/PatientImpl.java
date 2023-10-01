package com.macnss.dao;

import com.macnss.DBconnection.DBconnection;
import com.macnss.Model.Dossier;
import com.macnss.Model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                            resultSet.getString("matriculeSociete"));
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

        String query = "SELECT * FROM patient WHERE `matrecule` = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement((query))){
            preparedStatement.setString(1,matrecule);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                statusRetraite = resultSet.getString("statusRetraite");
                prixRetraite = resultSet.getString("prixRetraite");
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
        statusRetraite += " et vous avez " + prixRetraite + "DH dans votre Retraite";
        return "Vous etes "+statusRetraite;
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
}
