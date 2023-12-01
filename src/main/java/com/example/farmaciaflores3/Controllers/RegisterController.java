package com.example.farmaciaflores3.Controllers;

import com.example.farmaciaflores3.Model.Dao.EmpleadoDao;
import com.example.farmaciaflores3.Utils.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    private LoginController loginController;
    @FXML
    private TextField RNombre;
    @FXML
    private TextField RApellido;
    @FXML
    private TextField RCorreo;
    @FXML
    private ComboBox<String> RCargoBox;
    private final String[] Status = {"CAJERO", "FARMACEUTICO", "ASISTENTE FARMACEUTICO"};
    private Stage stage;
    private EmpleadoDao empleadoDao;

    public void init(Stage stage1, LoginController loginController) {
        this.loginController = loginController;
        this.stage = stage1;
    }

    @FXML
    public void goNext(){
        loginController.show();
        stage.close();
    }
    private void Limpiar(){
        RNombre.clear();
        RApellido.clear();
        RCorreo.clear();
    }
    @FXML
    public void RegisterEmployer(){
        empleadoDao = new EmpleadoDao();
        String Nombre = RNombre.getText();
        String Apellido = RApellido.getText();
        String Correo = RCorreo.getText();
        String Cargo = RCargoBox.getValue();
        if (!Nombre.isEmpty() && !Apellido.isEmpty() && !Correo.isEmpty() && !Cargo.isEmpty()){
            empleadoDao.RegistrarEmployed(Nombre,Apellido,Correo,Cargo);
            Limpiar();
        }else {
            Alerts.showErrorMessage("Por Favor Complete todos los Datos");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RCargoBox.getItems().addAll(Status);
    }
}
