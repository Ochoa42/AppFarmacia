package com.example.farmaciaflores3.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Proveedor {

    private final IntegerProperty Id;
    private final StringProperty Nombre;
    private final StringProperty Direccion;
    private final StringProperty CorreoElectronico;
    private final StringProperty Telefono;


    public Proveedor(int id, String nombre, String direccion, String correoElectronico, String telefono) {
        this.Id = new SimpleIntegerProperty(id);
        this.Nombre = new SimpleStringProperty(nombre);
        this.Direccion = new SimpleStringProperty(direccion);
        this.CorreoElectronico = new SimpleStringProperty(correoElectronico);
        this.Telefono = new SimpleStringProperty(telefono);
    }

    public int getId() {
        return Id.get();
    }

    public IntegerProperty idProperty() {
        return Id;
    }

    public void setId(int id) {
        this.Id.set(id);
    }

    public String getNombre() {
        return Nombre.get();
    }

    public StringProperty nombreProperty() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre.set(nombre);
    }

    public String getDireccion() {
        return Direccion.get();
    }

    public StringProperty direccionProperty() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        this.Direccion.set(direccion);
    }

    public String getCorreoElectronico() {
        return CorreoElectronico.get();
    }

    public StringProperty correoElectronicoProperty() {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.CorreoElectronico.set(correoElectronico);
    }

    public String getTelefono() {
        return Telefono.get();
    }

    public StringProperty telefonoProperty() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        this.Telefono.set(telefono);
    }
}
