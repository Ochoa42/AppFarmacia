package com.example.farmaciaflores3;

import com.example.farmaciaflores3.Controllers.InitController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import javafx.stage.Stage;
import java.io.IOException;

import static org.burningwave.core.assembler.StaticComponentContainer.Modules;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Modules.exportAllToAll();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Init.fxml"));
        //URL fxmlUrl = getClass().getResource("/Views/Login.fxml");
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        InitController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.show();

        primaryStage.setTitle("Farmacia Flores");
    }

    public static void main(String[] args) {
        launch();
    }
}