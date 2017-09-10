package edu.itla.consultoriomedico.gui.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainLayoutController implements Initializable {

    @FXML private BorderPane mainLayout;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void mostrarPacienteView(ActionEvent evt) {
        try {
            Parent pacienteScene = FXMLLoader.load(getClass().getResource("/fxml/paciente/FXMLPacienteView.fxml"));
            mainLayout.setCenter(pacienteScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarMedicoView(ActionEvent evt) {
        try {
            Parent medicoView = FXMLLoader.load(getClass().getResource("/fxml/medico/FXMLMedicoView.fxml"));
            mainLayout.setCenter(medicoView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEspecialidad (ActionEvent evt) {
        try {
            Parent medicoView = FXMLLoader.load(getClass().getResource("/fxml/especialidad/FXMLEspecialidadView.fxml"));
            mainLayout.setCenter(medicoView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarProcedimientoView(ActionEvent evt) {
        try {
          Parent  procedimientoView = FXMLLoader.load(getClass().getResource("/fxml/procedimiento/FXMLProcedimientoMedico.fxml"));
          mainLayout.setCenter(procedimientoView);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mostrarCitasView(ActionEvent evt) {
        try {
            Parent citaView = FXMLLoader.load(getClass().getResource("/fxml/cita/FXMLCitaView.fxml"));
            mainLayout.setCenter(citaView);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
