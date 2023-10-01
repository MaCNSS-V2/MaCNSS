package com.macnss.dao;

import com.macnss.DBconnection.DBconnection;
import com.macnss.Model.Admin;
import com.macnss.Model.Agent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class AdminImpl implements AdminDao{
    @Override
    public boolean ajoutAgent(Agent agent) {
        Connection con = DBconnection.getConnection();


        String query = "SELECT * FROM `agent` WHERE `email`=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);){
            preparedStatement.setString(1,agent.getEmail());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }else {
                String salt = BCrypt.gensalt();

                String hashedPassword = BCrypt.hashpw(agent.getPassword(), salt);

                String query2 = "INSERT INTO agent (email, password, nom_A, prenom_A) VALUES (?,?,?,?)";
                try (PreparedStatement preparedStatement2 = con.prepareStatement(query2);){
                    preparedStatement2.setString(1,agent.getEmail());
                    preparedStatement2.setString(2,hashedPassword);
                    preparedStatement2.setString(3,agent.getNom());
                    preparedStatement2.setString(4,agent.getPrenom());

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
    public boolean modiferAgent(Agent agent) {
        Connection con = DBconnection.getConnection();


        String query = "SELECT * FROM `agent` WHERE `email`=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);){
            preparedStatement.setString(1,agent.getEmail());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String salt = BCrypt.gensalt();

                String hashedPassword = BCrypt.hashpw(agent.getPassword(), salt);

                String query2 = "UPDATE `agent` SET `password`=?,`nom_A`=?, `prenom_A`=? WHERE `email`=?";
                try (PreparedStatement preparedStatement2 = con.prepareStatement(query2);){
                    preparedStatement2.setString(1,hashedPassword);
                    preparedStatement2.setString(2,agent.getNom());
                    preparedStatement2.setString(3,agent.getPrenom());
                    preparedStatement2.setString(4,agent.getEmail());

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
            }else {
                return false;
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
    public boolean supprissionAgent(String email) {
        Connection con = DBconnection.getConnection();

        String query = "SELECT * FROM `agent` WHERE `email`=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);){
            preparedStatement.setString(1,email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                String query2 = "DELETE FROM `agent` WHERE `email` = ?";
                try (PreparedStatement preparedStatement2 = con.prepareStatement(query2);){
                    preparedStatement2.setString(1,email);

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
            }else {
                return false;
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
    public List<Agent> affichageAgent() {
        Connection con =DBconnection.getConnection();

        List<Agent> agents = new LinkedList<>();

        String query = "SELECT * FROM agent";
        try (PreparedStatement preparedStatement = con.prepareStatement((query))){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Agent agent = new Agent (resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("nom_A"),
                        resultSet.getString("prenom_A"));
                agents.add(agent);
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

        return agents;
    }

    @Override
    public Admin login(Admin admin) {
        Connection con = DBconnection.getConnection();


        String query = "SELECT * FROM `admin` WHERE `email`=?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query);){
            preparedStatement.setString(1,admin.getEmail());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(admin.getPassword(), resultSet.getString("password"))) {
                    admin = new Admin (resultSet.getInt("id"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"));
                    return admin;
                } else {
                    admin = Admin.builder()
                            .id(-1)
                            .email("null")
                            .password("null")
                            .nom("null")
                            .prenom("null")
                            .build();
                    return admin;
                }
            }else {
                admin = Admin.builder()
                        .id(-1)
                        .email("null")
                        .password("null")
                        .nom("null")
                        .prenom("null")
                        .build();
                return admin;
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
        admin = Admin.builder()
                .id(-1)
                .email("null")
                .password("null")
                .nom("null")
                .prenom("null")
                .build();
        return admin;
    }
}
