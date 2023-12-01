package com.example.farmaciaflores3.Controllers;

import com.example.farmaciaflores3.Model.Dao.AdministratorDao;
import com.example.farmaciaflores3.Model.Dao.EmpleadoDao;
import com.example.farmaciaflores3.Utils.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {


    @FXML
    private TextField TxtUser;
    @FXML
    private PasswordField TxtPassword;
    private Stage stage;

    private String StadoLogin;

    private Boolean IsUser;

    private InitController initController;

    public void init(Stage stage1, InitController initController) {
        this.initController = initController;
        this.stage = stage1;
    }
    @FXML
    public void OnClickBtnLogin (ActionEvent event) throws IOException {

        if (!TxtUser.getText().isEmpty() && !TxtPassword.getText().isEmpty()){
            if(StadoLogin.equals("Administrador")){
                AdministratorDao administratorDao = new  AdministratorDao();
                IsUser = administratorDao.ValidarAdministrador(TxtUser.getText(),TxtPassword.getText());
                if (IsUser){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farmaciaflores3/AdminForm.fxml"));
                    Parent root = loader.load();
                    AdminFormController controller = loader.getController();
                    Scene scene = new Scene(root);
                    Stage stage1 = new Stage();
                    stage1.setScene(scene);
                    controller.init(stage1,this);
                    stage1.show();
                    this.stage.close();
                }else {
                    Alerts.showErrorMessage("Usuario o Contrasena Incorrectos");
                }
            }

        }else {
            Alerts.showConfirmationAlert("Login","Por Favor Complete todos los Campos");
        }


    }

    @FXML
    public void BtnRegisterLogin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farmaciaflores3/Register.fxml"));
        Parent root = loader.load();
        RegisterController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        controller.init(stage1,this);
        stage1.show();
        this.stage.close();
    }
    public void setUserData(String stadoLogin){
        this.StadoLogin = stadoLogin;
    }

    public void show() {
            stage.show();
    }
}
