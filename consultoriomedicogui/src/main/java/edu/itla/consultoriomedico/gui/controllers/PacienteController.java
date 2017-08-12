package edu.itla.consultoriomedico.gui.controllers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.swing.*;

public class PacienteController implements Initializable {

    @FXML
    private ImageView imgPaciente;
    private Image img;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadImage(ActionEvent event) {
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Imagenes files (*.png), (*jpg)", "*.png", "*.jpg");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione una imagen");
        fileChooser.getExtensionFilters().add(extFilter);
        File imgFile =  fileChooser.showOpenDialog(null);
        setImgPaciente(imgFile);

    }

    private void setImgPaciente(File file){

        try {
            img = new Image(file.toURI().toURL().toString());
            System.out.print(file.toURI().toURL().toString());
            imgPaciente.setImage(img);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
