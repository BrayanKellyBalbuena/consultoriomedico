package edu.itla.consultoriomedico.gui.controllers;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.itla.consultoriomedico.business.enums.RepositoryEnum;
import edu.itla.consultoriomedico.business.factory.ApplicationContext;
import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.repository.impl.PacienteRepositoryImpl;
import edu.itla.consultoriomedico.business.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.swing.*;

public class PacienteController implements Initializable {

    private PacienteRepositoryImpl repo;
    private ObservableList<Paciente> pacienteDatos;
    Alert alert;



    @FXML
    private ImageView imgPaciente;
    private Image img;

    @FXML
    private TableView<Paciente> tblPacientes;

    @FXML
    private TableColumn<Paciente, Long> idColumn;

    @FXML
    private TableColumn<Paciente, String> nombreColumn;

    @FXML
    private TableColumn<Paciente, String> apellidoColumn;

    @FXML
    private TableColumn<Paciente, String> telefonoColumn;

    @FXML
    private TableColumn<Paciente, Date> fechaColumn;

    @FXML
    private TableColumn<Paciente, String> direccionColumn;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtApellido;

    @FXML
    private JFXTextField txtTelefono;

    @FXML
    private JFXButton btnNuevo;

    @FXML
    private JFXButton btnActualizar;

    @FXML
    private JFXButton btnEliminar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        repo = (PacienteRepositoryImpl) ApplicationContext.getReposiory(RepositoryEnum.PACIENTE_REPOSITORY);
        pacienteDatos = FXCollections.observableArrayList(repo.findAll());

        idColumn.setCellValueFactory(
                new PropertyValueFactory<Paciente, Long>("ID"));


        tblPacientes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPacienteDetalles(newValue));

        tblPacientes.setItems(pacienteDatos);
    }

    private void refrescar() {
        pacienteDatos = FXCollections.observableArrayList(repo.findAll());
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

    @FXML
    void nuevo(ActionEvent event) {
        Paciente p;
        p = new Paciente(Long.parseLong(txtId.getText().toString()),txtNombre.getText(),
                          txtApellido.getText(),Integer.parseInt(txtTelefono.getText().toString()));
        repo.crear(p);
        try {
            repo.saveData();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registro Pacientes");
            alert.setHeaderText("Guardado con exito");
            refrescar();
            limpiarCampos();

            alert.show();
        } catch (IOException e) {
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registro Paciente");
            alert.setHeaderText("Error al guardar los datos");
            alert.show();
        }
    }

    void limpiarCampos(){
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
    }

    private void showPacienteDetalles(Paciente paciente) {
        if (paciente != null) {
            // Fill the labels with info from the person object.
            txtNombre .setText(paciente.getNombre());
            txtApellido.setText(paciente.getApellido());
            txtId.setText(String.valueOf(paciente.getId()));
            txtTelefono.setText(Integer.toString(paciente.getTelefono()));

        } else {
            limpiarCampos();
        }
    }
}
