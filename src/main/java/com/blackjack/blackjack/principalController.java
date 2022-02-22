package com.blackjack.blackjack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class principalController {
    @FXML
    private Label saldoRestante;
    @FXML
    private Button salir2, pedirCarta, plantarse, doblar;
    @FXML
    private TextField cantidadApuesta;
    @FXML
    private Button hacerApuesta;
    @FXML
    private Label jugadorCarta1, jugadorCarta2, jugadorCarta3, jugadorCarta4;
    @FXML
    private Label crupierCarta1, crupierCarta2;



    int saldo = 5000;
    int cantidadApuesta1;
    int sumaParcialJugador, sumaParcialCrupier = 0;
    int aleatorio[]= new int[15];


    @FXML
    public void apuesta(ActionEvent event){

        if(!validarApuesta(cantidadApuesta.getText().trim())){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            alerta.setHeaderText("Error, tu apuesta es incorrecta, vuelve a digitar un valor");
            alerta.showAndWait();
            cantidadApuesta.clear();
        }
        else{
            cantidadApuesta1= Integer.parseInt(cantidadApuesta.getText().trim());
            if(cantidadApuesta1>saldo){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Error");
                alerta.setHeaderText("Error, tu apuesta es incorrecta, digita un valor igual o menor a tu saldo");
                alerta.showAndWait();
                cantidadApuesta.clear();
            }
            else{
                saldo-=cantidadApuesta1;
                saldoRestante.setText(String.valueOf(saldo));
                cantidadApuesta.clear();
                aleatorio[0] = new Jugador(0).getAleatorio();
                aleatorio[1] = new Jugador(0).getAleatorio();
                aleatorio[2] = new Jugador(0).getAleatorio();
                jugadorCarta1.setText(String.valueOf(aleatorio[0]));
                jugadorCarta2.setText(String.valueOf(aleatorio[1]));
                crupierCarta1.setText(String.valueOf(aleatorio[2]));
                crupierCarta2.setText("?");
                sumaParcialJugador = aleatorio[0]+aleatorio[1];
                sumaParcialCrupier += aleatorio[2];
                hacerApuesta.setDisable(true);
            }
        }
    }

    @FXML
    public void pedirCarta(ActionEvent event){
        jugadorCarta1.setText(String.valueOf(sumaParcialJugador));
        jugadorCarta2.setText("");
        aleatorio[3] = new Jugador(0).getAleatorio();
        jugadorCarta2.setText(String.valueOf(aleatorio[3]));
        sumaParcialJugador+=aleatorio[3];
        doblar.setDisable(true);
        if (sumaParcialJugador>21){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            alerta.setHeaderText("Haz perdido, te pasaste 21");
            alerta.showAndWait();
            sumaParcialJugador = 0;
            sumaParcialCrupier = 0;
            hacerApuesta.setDisable(false);
        }
    }

    @FXML
    public void plantarse(){
        while (sumaParcialCrupier<=17){
            crupierCarta1.setText(String.valueOf(sumaParcialCrupier));
            aleatorio[3] = new Jugador(0).getAleatorio();
            crupierCarta2.setText(String.valueOf(aleatorio[3]));
            sumaParcialCrupier += aleatorio[3];
        }
        if (sumaParcialCrupier>21){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Ganaste");
            alerta.setHeaderText("Ganaste, el crupier saco: "+sumaParcialCrupier);
            alerta.showAndWait();
            saldo += (cantidadApuesta1*2);
            saldoRestante.setText(String.valueOf(saldo));
            sumaParcialJugador = 0;
            sumaParcialCrupier = 0;
            hacerApuesta.setDisable(false);
            activarBotones();
        }
        else if(sumaParcialCrupier==sumaParcialJugador){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            alerta.setHeaderText("Haz empatado, el crupier saco "+sumaParcialCrupier);
            alerta.showAndWait();
            saldo += cantidadApuesta1;
            saldoRestante.setText(String.valueOf(saldo));
            sumaParcialJugador = 0;
            sumaParcialCrupier = 0;
            hacerApuesta.setDisable(false);
            activarBotones();
        }
        else if(sumaParcialCrupier<sumaParcialJugador){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Ganaste");
            alerta.setHeaderText("Ganaste, el crupier saco: "+sumaParcialCrupier);
            alerta.showAndWait();
            saldo += (cantidadApuesta1*2);
            saldoRestante.setText(String.valueOf(saldo));
            sumaParcialJugador = 0;
            sumaParcialCrupier = 0;
            hacerApuesta.setDisable(false);
            activarBotones();
        }
        else {
            if (sumaParcialCrupier>sumaParcialJugador){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Error");
                alerta.setHeaderText("Haz perdido, el crupier saco "+sumaParcialCrupier);
                alerta.showAndWait();
                sumaParcialJugador = 0;
                sumaParcialCrupier = 0;
                hacerApuesta.setDisable(false);
                activarBotones();
            }
        }
    }

    @FXML
    public void doblar(){
        jugadorCarta1.setText(String.valueOf(sumaParcialJugador));
        jugadorCarta2.setText("");
        aleatorio[3] = new Jugador(0).getAleatorio();
        jugadorCarta2.setText(String.valueOf(aleatorio[3]));
        doblar.setDisable(true);
        pedirCarta.setDisable(true);
        plantarse.setDisable(true);
        if (sumaParcialJugador>21){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            alerta.setHeaderText("Haz perdido, te pasaste 21");
            alerta.showAndWait();
            sumaParcialJugador = 0;
            hacerApuesta.setDisable(false);
        }
        else{
            while (sumaParcialCrupier<=17){
                crupierCarta1.setText(String.valueOf(sumaParcialCrupier));
                aleatorio[3] = new Jugador(0).getAleatorio();
                crupierCarta2.setText(String.valueOf(aleatorio[3]));
                sumaParcialCrupier += aleatorio[3];
            }
            if (sumaParcialCrupier>21){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Ganaste");
                alerta.setHeaderText("Ganaste, el crupier saco: "+sumaParcialCrupier);
                alerta.showAndWait();
                saldo += (cantidadApuesta1*2);
                saldoRestante.setText(String.valueOf(saldo));
                sumaParcialJugador = 0;
                sumaParcialCrupier = 0;
                activarBotones();
            }
            else if(sumaParcialCrupier==sumaParcialJugador){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Error");
                alerta.setHeaderText("Haz empatado, el crupier saco "+sumaParcialCrupier);
                alerta.showAndWait();
                saldo += cantidadApuesta1;
                saldoRestante.setText(String.valueOf(saldo));
                sumaParcialJugador = 0;
                sumaParcialCrupier = 0;
                activarBotones();
            }
            else if(sumaParcialCrupier<sumaParcialJugador){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Ganaste");
                alerta.setHeaderText("Ganaste, el crupier saco: "+sumaParcialCrupier);
                alerta.showAndWait();
                saldo += (cantidadApuesta1*2);
                saldoRestante.setText(String.valueOf(saldo));
                sumaParcialJugador = 0;
                sumaParcialCrupier = 0;
                hacerApuesta.setDisable(false);
                activarBotones();
            }
            else {
                if (sumaParcialCrupier>sumaParcialJugador){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setHeaderText(null);
                    alerta.setTitle("Error");
                    alerta.setHeaderText("Haz perdido, el crupier saco "+sumaParcialCrupier);
                    alerta.showAndWait();
                    sumaParcialJugador = 0;
                    sumaParcialCrupier = 0;
                    hacerApuesta.setDisable(false);
                    activarBotones();
                }
            }
        }
    }

    public static boolean validarApuesta(String apuesta){
        return apuesta.matches("[0-9]*");
    }

    @FXML
    public void cerrarVentana2(ActionEvent event)throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root1 = loader.load();
        Scene scene1 = new Scene(root1);
        Stage stage1 = (Stage) salir2.getScene().getWindow();
        stage1.setScene(scene1);
        stage1.show();
    }

    public void limpiarEtiquetas(){
        jugadorCarta1.setText("");
        jugadorCarta2.setText("");
        crupierCarta1.setText("");
        crupierCarta2.setText("");
    }

    public void activarBotones(){
        hacerApuesta.setDisable(false);
        doblar.setDisable(false);
        pedirCarta.setDisable(false);
        plantarse.setDisable(false);
    }

    public void desactivarBotones(){
        hacerApuesta.setDisable(true);
        doblar.setDisable(true);
        pedirCarta.setDisable(true);
        plantarse.setDisable(true);
    }


}
