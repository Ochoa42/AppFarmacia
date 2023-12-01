package com.example.farmaciaflores3.Model.Dao;

import com.example.farmaciaflores3.DataBase.DataBaseConnection;
import com.example.farmaciaflores3.Model.Producto;
import com.example.farmaciaflores3.Utils.Alerts;
import javafx.scene.chart.PieChart;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public List<Producto> obtenerProductosConProveedor() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT P.ID_Producto, P.Nombre, P.Descripcion, P.Precio, P.Stock, PR.Nombre AS Proveedor " +
                "FROM Productos P " +
                "INNER JOIN Proveedores PR ON P.ID_Proveedor = PR.ID_Proveedor";
        try {
            con = DataBaseConnection.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID_Producto");
                String nombre = rs.getString("Nombre");
                String descripcion = rs.getString("Descripcion");
                int precio = rs.getInt("Precio");
                int stock = rs.getInt("Stock");
                String proveedor = rs.getString("Proveedor");
                Producto producto = new Producto(id, nombre, descripcion, precio, stock, proveedor);
                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }

    public void AddProducto(String nombre, String descripcion, int precio, int stock, String proveedor){
        String sql = "INSERT INTO Productos (Nombre, Descripcion, Precio, Stock, ID_Proveedor) VALUES (?, ?, ?, ?, ?)";
        ProveedorDao proveedorDao = new ProveedorDao();
        int idProveedor = proveedorDao.obtenerIdProveedorPorNombre(proveedor);

        try {
            con = DataBaseConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setInt(3, precio);
            ps.setInt(4, stock);
            ps.setInt(5, idProveedor);
           // ps.setBytes(6,imagen);
           // ps.setInt(7,precioVenta);
           // ps.setDate(8,fechaCaducidad);


            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                Alerts.showConfirmationAlert("Agregar Producto","Se ha Agregado el Producto");
            } else {
                Alerts.showErrorMessage("No se pudo agregar el producto.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProducto(int idProducto){
        String sqlDeleteReferences = "DELETE FROM detalle_ventas WHERE ID_Producto = ?";
        String sqlDeleteProducto = "DELETE FROM Productos WHERE ID_Producto = ?";

        try {
            con = DataBaseConnection.getConnection();

            // Eliminar las referencias en detalle_ventas
            try (PreparedStatement psDeleteReferences = con.prepareStatement(sqlDeleteReferences)) {
                psDeleteReferences.setInt(1, idProducto);
                int rowsAffectedReferences = psDeleteReferences.executeUpdate();

                // Continuar solo si se eliminaron las referencias con éxito
                if (rowsAffectedReferences > 0) {
                    // Eliminar el producto después de eliminar las referencias
                    try (PreparedStatement psDeleteProducto = con.prepareStatement(sqlDeleteProducto)) {
                        psDeleteProducto.setInt(1, idProducto);
                        int rowsAffectedProducto = psDeleteProducto.executeUpdate();

                        if (rowsAffectedProducto > 0) {
                            // Producto eliminado con éxito
                            // Puedes mostrar un mensaje de éxito o realizar acciones adicionales si es necesario
                        } else {
                            // No se eliminó el producto, puede que no exista con el ID proporcionado
                            Alerts.showErrorMessage("No se pudo encontrar el producto. Verifica el ID del producto.");
                        }
                    }
                } else {
                    // No se eliminaron referencias, producto no eliminado
                    Alerts.showErrorMessage("No se pudo eliminar el producto. Existen ventas asociadas a este producto.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProducto(int idProducto, String nuevoNombre, String nuevaDescripcion, int nuevoPrecio, int nuevoStock, String nuevoProveedor) {
        String sql = "UPDATE Productos " +
                "SET Nombre = ?, Descripcion = ?, Precio = ?, Stock = ?, ID_Proveedor = ? " +
                "WHERE ID_Producto = ?";

        ProveedorDao proveedorDao = new ProveedorDao();
        int idProveedor = proveedorDao.obtenerIdProveedorPorNombre(nuevoProveedor);

        try {
            con = DataBaseConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevaDescripcion);
            ps.setInt(3, nuevoPrecio);
            ps.setInt(4, nuevoStock);
            ps.setInt(5, idProveedor);
            ps.setInt(6, idProducto);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                Alerts.showConfirmationAlert("Actualizar Producto", "Se ha actualizado el producto con éxito.");
            } else {
                Alerts.showErrorMessage("No se pudo actualizar el producto. Verifica el ID del producto o los datos ingresados.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] getListProducts(){
        String sql = "SELECT Nombre FROM Productos";
        List<String> productList = new ArrayList<>();
        try {
            con = DataBaseConnection.getConnection();
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombreProducto = rs.getString("Nombre");
                productList.add(nombreProducto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList.toArray(new String[0]);
    }

    public Producto DatesProduct(String Name){
        String sql = "SELECT * FROM Productos WHERE Nombre = ?";
        Producto producto = null;

        try {
            con = DataBaseConnection.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("ID_Producto");
                String descripcion = rs.getString("Descripcion");
                int precio = rs.getInt("Precio");
                int stock = rs.getInt("Stock");
                String nombreProveedor = rs.getString("ID_Proveedor");

                // Crear un objeto Producto con los detalles obtenidos de la base de datos
                producto = new Producto(id, Name, descripcion, precio, stock, nombreProveedor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return producto;
    }

}
