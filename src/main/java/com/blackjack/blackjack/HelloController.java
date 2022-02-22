package com.blackjack.blackjack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;


public class HelloController {
    @FXML
    private Button iniciar;
    @FXML
    private Button salir;

    @FXML
    public void onHelloButtonClick (ActionEvent event) throws IOException {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("principal.fxml"));
            Parent root1 = loader.load();
            Scene scene1 = new Scene(root1);
            Stage stage1 = (Stage) iniciar.getScene().getWindow();
            stage1.setScene(scene1);
            stage1.show();



    }

    @FXML
    public void cerrarVentana(ActionEvent event){
        Stage stage = (Stage) salir.getScene().getWindow();
        stage.close();
    }


}