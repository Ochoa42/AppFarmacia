package com.example.farmaciaflores3.Model.Dao;

import com.example.farmaciaflores3.DataBase.DataBaseConnection;
import com.example.farmaciaflores3.Model.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {


    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public List<Proveedor> obtenerProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();

        String sql = "SELECT * FROM proveedores";

        try {
            con = DataBaseConnection.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID_Proveedor");
                String nombre = rs.getString("Nombre");
                String direccion = rs.getString("Direccion");
                String correoElectronico = rs.getString("Correo_Electronico");
                String telefono = rs.getString("Telefono");

                Proveedor proveedor = new Proveedor(id, nombre, direccion, correoElectronico, telefono);
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proveedores;
    }

    public int obtenerIdProveedorPorNombre(String nombreProveedor) {
        String sql = "SELECT ID_Proveedor FROM proveedores WHERE Nombre = ?";
        int idProveedor = -1;

        try {
            con = DataBaseConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreProveedor);
            rs = ps.executeQuery();

            if (rs.next()) {
                idProveedor = rs.getInt("ID_Proveedor");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idProveedor;
    }

}
