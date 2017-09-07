package edu.itla.consultoriomedico.gui.controllers.paciente;

import com.jfoenix.controls.JFXTextArea;
import edu.itla.consultoriomedico.business.entity.Paciente;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.hibernate.SessionFactory;

public class PacienteAddController implements Initializable {

    private SessionFactory sessionFactory;
    private Paciente pacienteTemp = null;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtApellido;

    @FXML
    private JFXTextField txtTelefono;

    @FXML
    private JFXTextField txtCorreo;

    @FXML
    private JFXDatePicker dtpFechaNac;

    @FXML
    private JFXTextField txtid;


    @FXML
    private JFXTextArea txtDireccion;

    @FXML
    private JFXButton btnGuadar;

    @FXML
    private JFXButton btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void guarda(ActionEvent event) {

    }

    public void setPersonEdit(SessionFactory sessionFactory, Paciente paciente) {
        if (paciente != null) {
            this.txtid.setText(paciente.getId().toString());
            this.txtNombre.setText(paciente.getNombre());
            this.txtApellido.setText(paciente.getApellido());
            this.dtpFechaNac.setValue(paciente.getFechaNacimiento());
            this.txtTelefono.setText(String.valueOf(paciente.getTelefono()));
            this.txtDireccion.setText(String.valueOf(paciente.getTelefono()));
        }
    }
}
