package com.macnss.dao;

import com.macnss.DBconnection.DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DossierImpl implements DossierDao{
    @Override
    public float calculeDossier(int id_m, int id_cm) {
        Connection con = DBconnection.getConnection();


        String query = "SELECT *, TC.prix_reduction TCPR, C.prix_reduction CPR FROM medicament M, categorie C, consultationmedicale CM, typeconsultation TC WHERE C.id = M.id_categorie AND TC.id = CM.id_type_consultation_medicale AND M.id = ? AND CM.id = ?;";
        try (PreparedStatement preparedStatement = con.prepareStatement((query))){

            preparedStatement.setInt(1,id_m);
            preparedStatement.setInt(2,id_cm);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                float TCPR = resultSet.getFloat("TCPR");
                float CPR = resultSet.getFloat("CPR");
                float prix_medicament = resultSet.getFloat("prix_medicament");
                float prix_consultation_medicale = resultSet.getFloat("prix_consultation_medicale");

                float prix_retour = TCPR * prix_consultation_medicale + CPR * prix_medicament;
                return prix_retour;
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

        return 0;
    }
}
