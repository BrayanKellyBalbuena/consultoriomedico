package edu.itla.consultoriomedico.gui;

import edu.itla.consultoriomedico.business.entity.Paciente;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {

    Stage primaryStage;
    BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {

       this.primaryStage = primaryStage;
       this.primaryStage.setTitle("Consultorio Medico");

       initRootLayout();
       //mostrarPacienteView();
    }



    private void mostrarMedicoView() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(""));
    }

    public void initRootLayout(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/FXMLMainLayout.fxml"));
            rootLayout = loader.load();

            //Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
