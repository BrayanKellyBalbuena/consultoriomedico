package edu.itla.consultoriomedico.gui.controllers.medico;

import com.jfoenix.controls.JFXTextArea;
import edu.itla.consultoriomedico.business.entity.Medico;
import edu.itla.consultoriomedico.business.enums.ServiceEnum;
import edu.itla.consultoriomedico.business.services.MedicoService;
import edu.itla.consultoriomedico.business.services.impl.MedicoServiceImpl;
import edu.itla.consultoriomedico.gui.controllers.MedicoController;
import edu.itla.consultoriomedico.gui.util.MessageDialog;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MedicoAddEditController implements Initializable {

    private MedicoService service;
    private Medico medicoTemp;
    private boolean isEdit = false;
    ApplicationContext context;
    MedicoController p;

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
        service = (MedicoServiceImpl)
                context.getBean(ServiceEnum.MEDICO_SERVICE.getValue());
    }

    public void setPersonEdit(MedicoController controller, Medico medico) {
        p = controller;
        this.service = service;

        if (medico != null) {
            this.isEdit = true;
            this.txtid.setText(medico.getId().toString());
            this.txtNombre.setText(medico.getNombre());
            this.txtApellido.setText(medico.getApellido());
            this.dtpFechaNac.setValue(medico.getFechaNacimiento());
            this.txtTelefono.setText(String.valueOf(medico.getTelefono()));
            this.txtDireccion.setText(String.valueOf(medico.getDireccion()));
        }
    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {
        if (validarCampos()) {
            if (isEdit) {
                try {
                    service.update(getMedicoTempFull());
                    showPopup("Informacion", "Medico Actualizado con exito", Alert.AlertType.INFORMATION);
                    p.refrescar();
                } catch (Exception ex) {
                    showPopup("Informacion", "Error al guardar el nedico", Alert.AlertType.WARNING);
                }
            } else {
                try {
                    service.save(getMedicoTempFull());
                    showPopup("Informacion", "Medico Guardado con exito", Alert.AlertType.INFORMATION);

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

    private Medico getMedicoTempFull() {
        medicoTemp = new Medico();
        if (isEdit) {
            medicoTemp.setId(Long.parseLong(txtid.getText()));
        }
        medicoTemp.setNombre(txtNombre.getText());
        medicoTemp.setApellido(txtApellido.getText());
        medicoTemp.setFechaNacimiento(dtpFechaNac.getValue());
        medicoTemp.setTelefono(Integer.parseInt(txtTelefono.getText()));
        medicoTemp.setDireccion(txtDireccion.getText());

        return medicoTemp;
    }

    private void showPopup(String title, String message, Alert.AlertType typeAlert) {

        MessageDialog.showPopup(title, message, typeAlert).show();
    }
}
