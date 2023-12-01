package com.example.farmaciaflores3.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Empleado {
    private final IntegerProperty Id;
    private final StringProperty Nombre;
    private final StringProperty Apellido;
    private final StringProperty Correo;
    private final StringProperty Cargo;

    public Empleado(IntegerProperty id, StringProperty nombre, StringProperty apellido, StringProperty correo, StringProperty cargo) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
        Correo = correo;
        Cargo = cargo;
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

    public String getApellido() {
        return Apellido.get();
    }

    public StringProperty apellidoProperty() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        this.Apellido.set(apellido);
    }

    public String getCorreo() {
        return Correo.get();
    }

    public StringProperty correoProperty() {
        return Correo;
    }

    public void setCorreo(String correo) {
        this.Correo.set(correo);
    }

    public String getCargo() {
        return Cargo.get();
    }

    public StringProperty cargoProperty() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        this.Cargo.set(cargo);
    }
}
