package edu.itla.consultoriomedico.gui.controllers.paciente;

import com.jfoenix.controls.JFXTextArea;
import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.enums.ServiceEnum;
import edu.itla.consultoriomedico.business.services.PacienteService;
import edu.itla.consultoriomedico.business.services.impl.PacienteServiceImpl;
import edu.itla.consultoriomedico.gui.util.MessageDialog;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PacienteAddController implements Initializable {

    private PacienteService service;
    private Paciente pacienteTemp;
    private boolean isEdit = false;
    ApplicationContext context;
    PacienteController p;

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
        context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
        service = (PacienteServiceImpl)
                context.getBean(ServiceEnum.PACIENTE_SERVICE.getValue());
    }

    public void setPersonEdit(PacienteController controller, Paciente paciente) {
        p = controller;
        this.service = service;

        if (paciente != null) {
            this.isEdit = true;
            this.txtid.setText(paciente.getId().toString());
            this.txtNombre.setText(paciente.getNombre());
            this.txtApellido.setText(paciente.getApellido());
            this.dtpFechaNac.setValue(paciente.getFechaNacimiento());
            this.txtTelefono.setText(String.valueOf(paciente.getTelefono()));
            this.txtDireccion.setText(String.valueOf(paciente.getDireccion()));
        }
    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void guarda(ActionEvent event) {
        if (validarCampos()) {
            if (isEdit) {
                try {
                    service.update(getPacienteTempFull());
                    showPopup("Informacion", "Paciente Actualizado con exito", Alert.AlertType.INFORMATION);
                    p.refrescar();
                } catch (Exception ex) {
                    showPopup("Informacion", "Error al guardar el paciente", Alert.AlertType.WARNING);
                }
            } else {
                try {
                    service.save(getPacienteTempFull());
                    showPopup("Informacion", "Paciente Guardado con exito", Alert.AlertType.INFORMATION);

                } catch (Exception ex) {

                }

            }
        } else {
            MessageDialog.showPopup("text", "El nombre, apellido y telfeono son obligatorios",
                    Alert.AlertType.WARNING).show();
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText() == null || txtNombre.getText().trim().equals("")) {
            MessageDialog.showPopup("text", "El nombre, apellido y telfeono son obligatorios",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        return true;
    }

    private Paciente getPacienteTempFull() {
        pacienteTemp = new Paciente();
        if (isEdit) {
            pacienteTemp.setId(Long.parseLong(txtid.getText()));
        }
        pacienteTemp.setNombre(txtNombre.getText());
        pacienteTemp.setApellido(txtApellido.getText());
        pacienteTemp.setFechaNacimiento(dtpFechaNac.getValue());
        pacienteTemp.setTelefono(Integer.parseInt(txtTelefono.getText()));
        pacienteTemp.setDireccion(txtDireccion.getText());

        return pacienteTemp;
    }

    private void showPopup(String title, String message, Alert.AlertType typeAlert) {

        MessageDialog.showPopup(title, message, typeAlert).show();
    }
}
