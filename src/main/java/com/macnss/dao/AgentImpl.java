package com.macnss.dao;

import com.macnss.DBconnection.DBconnection;
import com.macnss.Model.Agent;
import com.macnss.Model.Dossier;
import com.macnss.helpers.helpers;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class AgentImpl implements AgentDao{
    @Override
    public List<Dossier> affichageDossier() {
        Connection con =DBconnection.getConnection();

        List<Dossier> dossiers = new LinkedList<>();

        String query = "SELECT * FROM dossier";
        try (PreparedStatement preparedStatement = con.prepareStatement((query))){

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

        return dossiers;
    }

    @Override
    public boolean ajoutDossier(Dossier dossier) {
        Connection con = DBconnection.getConnection();
        String query2 = "INSERT INTO `dossier`(`prix_retour`, `status`, `matrecule`, `id_A`, `id_medicament`, `id_consultation_medicale`) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement2 = con.prepareStatement(query2);){
            preparedStatement2.setFloat(1,dossier.getPrix_retour());
            preparedStatement2.setString(2,"En attente");
            preparedStatement2.setString(3,dossier.getMatrecule());
            preparedStatement2.setInt(4,dossier.getId_a());
            preparedStatement2.setInt(5,dossier.getId_m());
            preparedStatement2.setInt(6,dossier.getId_cm());

            preparedStatement2.executeUpdate();

            return true;
        } catch (SQLException se){
            se.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public boolean modifierDossier(Dossier dossier) {
        Connection con = DBconnection.getConnection();
        String query2 = "UPDATE `dossier` SET `prix_retour`=?,`status`=?,`matrecule`=?,`id_A`=?,`id_medicament`=?,`id_consultation_medicale`=? WHERE `id`=?";
        try (PreparedStatement preparedStatement2 = con.prepareStatement(query2);){
            preparedStatement2.setFloat(1,dossier.getPrix_retour());
            preparedStatement2.setString(2,"En attente");
            preparedStatement2.setString(3,dossier.getMatrecule());
            preparedStatement2.setInt(4,dossier.getId_a());
            preparedStatement2.setInt(5,dossier.getId_m());
            preparedStatement2.setInt(6,dossier.getId_cm());
            preparedStatement2.setInt(7,dossier.getId());

            preparedStatement2.executeUpdate();

            return true;
        } catch (SQLException se){
            se.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public boolean supprisionDossier(int id_dossier) {
        Connection con = DBconnection.getConnection();
        String query2 = "DELETE FROM `dossier` WHERE `id` = ?";
        try (PreparedStatement preparedStatement2 = con.prepareStatement(query2);){
            preparedStatement2.setInt(1,id_dossier);

            preparedStatement2.executeUpdate();

            return true;
        } catch (SQLException se){
            se.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public boolean comfirmationDossier(int id, int status) {
        String statusString = null;
        if (status == -1){
            statusString = "Refusé";
        }
        if (status == 0){
            statusString = "En attente";
        }
        if (status == 1){
            statusString = "Validé";
        }
        Connection con = DBconnection.getConnection();
        String query2 = "UPDATE `dossier` SET `status`=? WHERE `id`=?";
        try (PreparedStatement preparedStatement2 = con.prepareStatement(query2);){
            preparedStatement2.setString(1,statusString);
            preparedStatement2.setInt(2,id);
            preparedStatement2.executeUpdate();

            String query = "SELECT * FROM `dossier` D, `patient` P WHERE D.`id`=? AND P.matrecule = D.matrecule";
            try (PreparedStatement preparedStatement = con.prepareStatement(query);){
                preparedStatement.setInt(1,id);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (status == -1 || status == 1){
                    while (resultSet.next()){
                        String  email = resultSet.getString("email");
                        if (status == -1){
                            String body = "Votre dossier est Refusé ";
                            String subject = "Confirmation de votre dossier";
                            boolean resultat = helpers.sendMail(body,subject,email);
                            if (resultat == true){
                                System.out.println("Email de Confirmation a ete envoyer");
                                return true;
                            } else {
                                System.out.println("Email de Confirmation n'a pas ete envoyer");
                                return false;
                            }
                        }
                        if (status == 1){
                            String body = "Votre dossier est Validé ";
                            String subject = "Confirmation de votre dossier";
                            boolean resultat = helpers.sendMail(body,subject,email);
                            if (resultat == true){
                                System.out.println("Email de Confirmation a ete envoyer");
                                return true;
                            } else {
                                System.out.println("Email de Confirmation n'a pas ete envoyer");
                                return false;
                            }
                        }
                    }
                }
                return true;
            } catch (SQLException se){
                se.printStackTrace();
            }finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
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
        return false;
    }

    @Override
    public Agent login(Agent agent) {
        Connection con = DBconnection.getConnection();


        String query = "SELECT * FROM `agent` WHERE `email`=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);){
            preparedStatement.setString(1,agent.getEmail());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(agent.getPassword(), resultSet.getString("password"))) {
                    agent = new Agent(resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("nom_A"),
                            resultSet.getString("prenom_A"));
                    return agent;
                } else {
                    agent = Agent.builder()
                            .id(-1)
                            .email("null")
                            .password("null")
                            .nom("null")
                            .prenom("null")
                            .build();
                    return agent;
                }
            }else {
                agent = Agent.builder()
                        .id(-1)
                        .email("null")
                        .password("null")
                        .nom("null")
                        .prenom("null")
                        .build();
                return agent;
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
        agent = Agent.builder()
                .id(-1)
                .email("null")
                .password("null")
                .nom("null")
                .prenom("null")
                .build();
        return agent;
    }
}
