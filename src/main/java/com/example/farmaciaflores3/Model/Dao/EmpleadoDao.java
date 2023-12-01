package com.example.farmaciaflores3.Model.Dao;

import com.example.farmaciaflores3.DataBase.DataBaseConnection;
import com.example.farmaciaflores3.Utils.Alerts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpleadoDao {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Boolean State;

    public boolean ValidarAdministrador(String User, String Password){

        String sql = "SELECT * FROM empleados WHERE BINARY Nombre=? AND Contrasena=?";

        try {
            con = DataBaseConnection.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ps.setString(1,User);
            ps.setString(2,Password);
            rs = ps.executeQuery();
            if (rs.next()){
                State = true;
            }else {
                State = false;
            }

        }catch (Exception e){
            System.out.println("Hubo error de ejecucion");
        }
        return State;
    }

    public void RegistrarEmployed(String nombre,String apellido,String correo, String Cargo){
            String sql = "INSERT INTO Empleados (Nombre, Apellido, Correo_Electronico,Cargo ) VALUES (?, ?, ?, ?)";

            try {
                con = DataBaseConnection.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, nombre);
                ps.setString(2, apellido);
                ps.setString(3, correo);
                ps.setString(4, Cargo);
                // ps.setBytes(6,imagen);
                // ps.setInt(7,precioVenta);
                // ps.setDate(8,fechaCaducidad);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    Alerts.showConfirmationAlert("Agregar Empleado","Se ha Registrado el Empleado");
                } else {
                    Alerts.showErrorMessage("No se pudo agregar al empleado.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

}
