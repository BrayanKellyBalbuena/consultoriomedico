package edu.itla.consultoriomedico.gui.controllers.medico;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import edu.itla.consultoriomedico.business.entity.Medico;
import edu.itla.consultoriomedico.business.enums.ServiceEnum;
import edu.itla.consultoriomedico.business.services.MedicoService;
import edu.itla.consultoriomedico.business.services.impl.MedicoServiceImpl;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MedicoController implements Initializable {

    private ObservableList<Medico> medicoDatos;
    private ObservableList<Medico> medicoDatosTemp;
    MedicoService medicoService;
    ApplicationContext context;



    @FXML
    private TableView<Medico> tblMedicos;

    @FXML
    private TableColumn<Medico, Long> idColumn;

    @FXML
    private TableColumn<Medico, String> nombreColumn;

    @FXML
    private TableColumn<Medico, String> apellidoColumn;

    @FXML private TableColumn<Medico, String> especialidad;

    @FXML
    private TableColumn<Medico, String> telefonoColumn;

    @FXML
    private TableColumn<Medico, LocalDate> fechaColumn;

    @FXML
    private TableColumn<Medico, String> direccionColumn;




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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
        medicoService = (MedicoServiceImpl)
                context.getBean(ServiceEnum.MEDICO_SERVICE.getValue());
        medicoDatos = FXCollections.observableArrayList(medicoService.findAll());
        initTableView();
    }

    private void initTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Medico, Long>("id"));
        nombreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        apellidoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<Medico, LocalDate>("fechaNacimiento"));
        telefonoColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTelefono()).asString());
        direccionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
        especialidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEspecialidad().getNombre()));


//        tblPacientes.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> showPacienteDetalles(newValue));

        tblMedicos.setItems(medicoDatos);
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
        medicoDatos = FXCollections.observableArrayList(medicoService.findAll());
        tblMedicos.setItems(medicoDatos);
        tblMedicos.refresh();
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
        if (getMedicoSelecciondado() == null) {
            showPopup("Error no medico", "Debe seleccionar un medico", Alert.AlertType.WARNING);
        } else
            loadNuevoEditModal("Editar medico", true);
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Medico medico = getMedicoSelecciondado();
        if (medico == null) {
            showPopup("Error no medico", "Debe seleccionar un medico", Alert.AlertType.WARNING);
            return;
        }
        try {
            medicoService.delete(getMedicoSelecciondado().getId());
            medicoDatos.remove(medico);
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/medico/FXMLMedicoAddEditModal.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            if (opcion) {
                MedicoAddEditController controller = fxmlLoader.getController();
                controller.setPersonEdit(this, getMedicoSelecciondado());
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

    private Medico getMedicoSelecciondado() {
        return tblMedicos.getSelectionModel().getSelectedItem();

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
                Medico medicoTemp = medicoService.
                        findById(Long.parseLong(txtSearch.getText()));
                if (medicoTemp != null) {
                    medicoDatos.setAll(medicoService.
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
