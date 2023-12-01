package com.example.farmaciaflores3.Controllers;

import com.example.farmaciaflores3.Utils.InputDialogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;


public class InitController {

    private Stage stage;


    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }
    @FXML
    public void OnClickAdministrator(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farmaciaflores3/Login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        controller.setUserData("Administrador");
        controller.init(stage1,this);
        stage1.show();
        this.stage.close();
    }
    @FXML
    public void OnclickEmployee (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/farmaciaflores3/Ventas.fxml"));
        Parent root = loader.load();
        VentasController controller = loader.getController();
        Scene scene = new Scene(root);
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        controller.init(stage1,this);
        stage1.show();
        this.stage.close();
    }


}


