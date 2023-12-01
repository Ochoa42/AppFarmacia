package com.example.farmaciaflores3.Model.Dao;

import com.example.farmaciaflores3.DataBase.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Boolean State;

    public boolean ValidarAdministrador(String User, String Password){

        String sql = "SELECT * FROM administradores WHERE BINARY Nombre=? AND Contrasena=?";

        try {
            con = DataBaseConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, User);  // Set the first parameter
            ps.setString(2, Password);  // Set the second parameter
            rs = ps.executeQuery();

            if (rs.next()) {
                State = true;
            } else {
                State = false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println(State);
        return State;
    }



}
