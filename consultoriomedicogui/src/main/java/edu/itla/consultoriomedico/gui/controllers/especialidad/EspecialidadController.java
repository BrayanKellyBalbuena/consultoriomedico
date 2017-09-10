package edu.itla.consultoriomedico.gui.controllers.especialidad;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.itla.consultoriomedico.business.entity.EspecialidadMedica;
import edu.itla.consultoriomedico.business.enums.ServiceEnum;
import edu.itla.consultoriomedico.business.services.EspecialidadMedicaService;
import edu.itla.consultoriomedico.business.services.MedicoService;
import edu.itla.consultoriomedico.business.services.impl.EspecialidadMedicaServiceImpl;
import edu.itla.consultoriomedico.gui.controllers.medico.MedicoAddEditController;
import edu.itla.consultoriomedico.gui.util.MessageDialog;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EspecialidadController implements Initializable {

    private ObservableList<EspecialidadMedica> especialidadDatos;
    private ObservableList<EspecialidadMedica> medicoDatosTemp;
    EspecialidadMedicaService especialidadService;
    ApplicationContext context;
    

    @FXML
    private TableView<EspecialidadMedica> tblEspecialidades;

    @FXML
    private TableColumn<EspecialidadMedica, Long> idColumn;

    @FXML
    private TableColumn<EspecialidadMedica, String> nombreColumn;
    

    @FXML
    private TableColumn<EspecialidadMedica, String> descripcionColumn;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnNuevo;

    @FXML
    private JFXButton btnActualizar;

    @FXML
    private JFXButton btnEliminar;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton btnGuadar;

    @FXML
    private JFXButton btnCancelar;





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
        especialidadService = (EspecialidadMedicaServiceImpl)
                context.getBean(ServiceEnum.ESPECIALIDAD_SERVICE.getValue());
        especialidadDatos = FXCollections.observableArrayList(especialidadService.findAll());
        initTableView();
    }

    private void initTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<EspecialidadMedica, Long>("id"));
        nombreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));;
        descripcionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));

//        tblPacientes.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> showPacienteDetalles(newValue));

        tblEspecialidades.setItems(especialidadDatos);
    }


//    private void showPacienteDetalles(Paciente paciente) {
//        if (paciente != null) {
//            // Fill the labels with info from the person object.
//            txtNombre.setText(paciente.getNombre());
//            txtApellido.setText(paciente.getApellido());
//            txtId.setText(String.valueOf(paciente.getId()));
//            txtTelefono.setText(Integer.toString(paciente.getTelefono()));
//
//        } else {
//            limpiarCampos();
//        }
//    }

    public void refrescar() {
        especialidadDatos = FXCollections.observableArrayList(especialidadService.findAll());
        tblEspecialidades.setItems(especialidadDatos);
        tblEspecialidades.refresh();
        ;
    }


    public void loadImage(ActionEvent event) {
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Imagenes files (*.png), (*jpg)", "*.png", "*.jpg");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione una imagen");
        fileChooser.getExtensionFilters().add(extFilter);
        File imgFile = fileChooser.showOpenDialog(null);
//        setImgPaciente(imgFile);

    }


    @FXML
    void editar(ActionEvent event) {
        if (getEspecialidadSelecciondado() == null) {
            showPopup("Error no medico", "Debe seleccionar un una especialidad", Alert.AlertType.WARNING);
        } else
            loadNuevoEditModal("Editar medico", true);
    }

    @FXML
    private void eliminar(ActionEvent event) {
        EspecialidadMedica especialidad = getEspecialidadSelecciondado();
        if (especialidad == null) {
            showPopup("Error no especialidad", "Debe seleccionar una especialidad", Alert.AlertType.WARNING);
            return;
        }
        try {
            especialidadService.delete(getEspecialidadSelecciondado().getId());
            especialidadDatos.remove(especialidad);
            showPopup("Estatus eliminar medico", "Medico Eliminado con exito", Alert.AlertType.WARNING);
        } catch (Exception e) {
            showPopup("Eliminar medico estatus", "Error al eliminar el medico", Alert.AlertType.WARNING);
        }

    }

    @FXML
    private void nuevo(ActionEvent event) {
        loadNuevoEditModal("Agregar nuevo Medico", false);
    }

    private void loadNuevoEditModal(String titulo, Boolean opcion) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/especialidad/FXMLEspecialidadAddEditModal.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            if (opcion) {
                EspecialidadAddEditController controller = fxmlLoader.getController();
                controller.setPersonEdit(this, getEspecialidadSelecciondado());
            }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Agregar Medico");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        refrescar();
    }

    private EspecialidadMedica getEspecialidadSelecciondado() {
        return tblEspecialidades.getSelectionModel().getSelectedItem();

    }

    private void showPopup(String title, String message, Alert.AlertType typeAlert) {

        MessageDialog.showPopup(title, message, typeAlert).show();
    }

    @FXML
    private void search(ActionEvent event) {
        if (txtSearch.getText().isEmpty()) {
            refrescar();
        } else {
            try {
                EspecialidadMedica medicoTemp = especialidadService.
                        findById(Long.parseLong(txtSearch.getText()));
                if (medicoTemp != null) {
                    especialidadDatos.setAll(especialidadService.
                            findById(Long.parseLong(txtSearch.getText())));
                } else {
                    showPopup("Mensaje busqueda medico", "Medico no encontrado",
                            Alert.AlertType.INFORMATION);
                }

            } catch (NumberFormatException ex) {
                showPopup("Error", "error", Alert.AlertType.WARNING);
            }
        }

    }
}