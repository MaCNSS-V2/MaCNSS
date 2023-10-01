package com.macnss.dao;

import com.macnss.DBconnection.DBconnection;
import com.macnss.Model.ConsultationMedicale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConsultationMedicaleImpl implements ConsultationMedicaleDao {
    @Override
    public List<ConsultationMedicale> affichageConsultationMedicales() {
        Connection con = DBconnection.getConnection();

        List<ConsultationMedicale> consultationMedicales = new LinkedList<>();

        String query = "SELECT * FROM consultationmedicale CM, typeconsultation TC WHERE CM.id_type_consultation_medicale = TC.id";
        try (PreparedStatement preparedStatement = con.prepareStatement((query))){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ConsultationMedicale consultationMedicale = new ConsultationMedicale (resultSet.getInt("id"),
                        resultSet.getString("nom_CM"),
                        resultSet.getFloat("prix_consultation_medicale"));
                consultationMedicales.add(consultationMedicale);
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

        return consultationMedicales;
    }
}
