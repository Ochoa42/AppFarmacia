package com.example.farmaciaflores3.Model;

import javafx.beans.property.*;

import java.sql.Date;

public class Producto {

    private final IntegerProperty Id;
    private final StringProperty Nombre;
    private final StringProperty Descripcion;
    private final IntegerProperty Precio;
    private final IntegerProperty Stock;
    private final StringProperty NombreProveedor;
  //  private final IntegerProperty PrecioVenta;
  //  private final ObjectProperty<Date> fecha;  // Cambiado a java.sql.Date
  //  private final ObjectProperty<byte[]> image;



    public Producto(int id, String nombre, String descripcion, int precio, int stock, String nombreProveedor) {
        this.Id = new SimpleIntegerProperty(id);
        this.Nombre = new SimpleStringProperty(nombre);
        this.Descripcion = new SimpleStringProperty(descripcion);
        this.Precio = new SimpleIntegerProperty(precio);
        this.Stock = new SimpleIntegerProperty(stock);
        this.NombreProveedor = new SimpleStringProperty(nombreProveedor);
        //this.fecha = new SimpleObjectProperty<>(fecha);
        //this.image = new SimpleObjectProperty<>(image);
        //this.PrecioVenta = new SimpleIntegerProperty(precioventa);
    }

    public int getId(){
        return Id.get();
    }
    public String getNombre(){
        return Nombre.get();
    }
    public String getDescripcion(){
        return Descripcion.get();
    }
    public int getPrecio(){
        return Precio.get();
    }
    public int getStock(){
        return Stock.get();
    }
    public String getNombreProveedor(){
        return NombreProveedor.get();
    }

    public void setId(int id) {
        this.Id.set(id);
    }

    public void setNombre(String nombre) {
        this.Nombre.set(nombre);
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion.set(descripcion);
    }

    public void setPrecio(int precio) {
        this.Precio.set(precio);
    }

    public void setStock(int stock) {
        this.Stock.set(stock);
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.NombreProveedor.set(nombreProveedor);
    }

    public IntegerProperty getIdProperty() {
        return Id;
    }

    public StringProperty getNombreProperty() {
        return Nombre;
    }

    public StringProperty getDescripcionProperty() {
        return Descripcion;
    }

    public IntegerProperty getPrecioProperty() {
        return Precio;
    }

    public IntegerProperty getStockProperty() {
        return Stock;
    }

    public StringProperty getIdProveedorProperty() {
        return NombreProveedor;
    }
}
