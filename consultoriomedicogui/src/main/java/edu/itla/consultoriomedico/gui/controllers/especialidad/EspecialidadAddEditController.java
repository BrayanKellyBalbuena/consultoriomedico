package edu.itla.consultoriomedico.gui.controllers.especialidad;

import com.jfoenix.controls.*;
import edu.itla.consultoriomedico.business.entity.EspecialidadMedica;
import edu.itla.consultoriomedico.business.entity.Medico;
import edu.itla.consultoriomedico.business.enums.ServiceEnum;
import edu.itla.consultoriomedico.business.services.EspecialidadMedicaService;
import edu.itla.consultoriomedico.business.services.MedicoService;
import edu.itla.consultoriomedico.business.services.impl.EspecialidadMedicaServiceImpl;
import edu.itla.consultoriomedico.business.services.impl.MedicoServiceImpl;
import edu.itla.consultoriomedico.gui.controllers.medico.MedicoController;
import edu.itla.consultoriomedico.gui.util.MessageDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EspecialidadAddEditController implements Initializable {

    private EspecialidadMedicaService service;
    private EspecialidadMedica especialidadTemp;
    private boolean isEdit = false;
    ApplicationContext context;
    EspecialidadController p;

    @FXML
    private JFXTextField txtNombre;


    @FXML
    private JFXTextField txtid;


    @FXML
    private JFXTextArea txtDescripcion;


    @FXML
    private JFXButton btnGuadar;

    @FXML
    private JFXButton btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
        service = (EspecialidadMedicaServiceImpl)
                context.getBean(ServiceEnum.ESPECIALIDAD_SERVICE.getValue());

    }

    public void setPersonEdit(EspecialidadController controller, EspecialidadMedica especialidad) {
        p = controller;
        this.service = service;

        if (especialidad != null) {
            this.isEdit = true;
            this.txtid.setText(especialidad.getId().toString());
            this.txtNombre.setText(especialidad.getNombre());
            this.txtDescripcion.setText(String.valueOf(especialidad.getDescripcion()));
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
                    service.update(getEspecialidadTempFull());
                    showPopup("Informacion", "Especialida Medica Actualizado con exito", Alert.AlertType.INFORMATION);
                    p.refrescar();
                } catch (Exception ex) {
                    showPopup("Informacion", "Error al guardar la Especialidad Medica", Alert.AlertType.WARNING);
                }
            } else {
                try {
                    service.save(getEspecialidadTempFull());
                    showPopup("Informacion", "Especialidad Medica Guardado con exito", Alert.AlertType.INFORMATION);

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
            MessageDialog.showPopup("text", "El nombre es obligatorios",
                    Alert.AlertType.WARNING).show();
            return false;
        }
        return true;
    }

    private EspecialidadMedica getEspecialidadTempFull() {
        especialidadTemp = new EspecialidadMedica();
        if (isEdit) {
            especialidadTemp.setId(Long.parseLong(txtid.getText()));
        }
        especialidadTemp.setNombre(txtNombre.getText());

        especialidadTemp.setDescripcion(txtDescripcion.getText());

        return especialidadTemp;
    }

    private void showPopup(String title, String message, Alert.AlertType typeAlert) {

        MessageDialog.showPopup(title, message, typeAlert).show();
    }
}
