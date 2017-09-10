package edu.itla.consultoriomedico.gui.controllers.paciente;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import edu.itla.consultoriomedico.business.entity.Paciente;
import edu.itla.consultoriomedico.business.enums.ServiceEnum;
import edu.itla.consultoriomedico.business.services.PacienteService;
import edu.itla.consultoriomedico.business.services.impl.PacienteServiceImpl;
import edu.itla.consultoriomedico.gui.controllers.paciente.PacienteAddController;
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

public class PacienteController implements Initializable {

    private ObservableList<Paciente> pacienteDatos;
    private ObservableList<Paciente> pacienteDatosTemp;
    PacienteService pacienteService;
    ApplicationContext context;


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
    private TableColumn<Paciente, LocalDate> fechaColumn;

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
    private JFXTextField txtCorreo;

    @FXML
    private JFXDatePicker dtpFechaNac;

    @FXML
    private JFXTextField txtid;

    @FXML
    private JFXButton btnGuadar;

    @FXML
    private JFXButton btnCancelar;





    @Override
    public void initialize(URL location, ResourceBundle resources) {

            context = new ClassPathXmlApplicationContext("/spring/applicationContext.xml");
            pacienteService = (PacienteServiceImpl)
                    context.getBean(ServiceEnum.PACIENTE_SERVICE.getValue());
        pacienteDatos = FXCollections.observableArrayList(pacienteService.findAll());
        initTableView();
    }

    private void initTableView() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Paciente, Long>("id"));
        nombreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        apellidoColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellido()));
        fechaColumn.setCellValueFactory(new PropertyValueFactory<Paciente, LocalDate>("fechaNacimiento"));
        telefonoColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTelefono()).asString());
        direccionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));

//        tblPacientes.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> showPacienteDetalles(newValue));

        tblPacientes.setItems(pacienteDatos);
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
        pacienteDatos = FXCollections.observableArrayList(pacienteService.findAll());
        tblPacientes.setItems(pacienteDatos);
        tblPacientes.refresh();
        ;
    }



    public void loadImage(ActionEvent event) {
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Imagenes files (*.png), (*jpg)", "*.png", "*.jpg");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione una imagen");
        fileChooser.getExtensionFilters().add(extFilter);
        File imgFile =  fileChooser.showOpenDialog(null);
//        setImgPaciente(imgFile);

    }
//
//    private void setImgPaciente(File file){
//
//        try {
//            img = new Image(file.toURI().toURL().toString());
//            System.out.print(file.toURI().toURL().toString());
//            imgPaciente.setImage(img);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    void nuevo(ActionEvent event) {
//        Paciente p;
//        p = new Paciente();
//
//        p.setNombre(txtNombre.getText());
//        p.setApellido(txtApellido.getText());
//        p.setTelefono(Integer.parseInt(txtTelefono.getText()));
//        try {
//            services.save(p);
//            showPopup("Registro Pacientes", "Guardado con exito", Alert.AlertType.INFORMATION);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            showPopup("Registro Pacientes", "error al guardar", Alert.AlertType.ERROR);
//        }
//    }

    @FXML
    void editar(ActionEvent event) {
        if (getPacienteSelecciondado() == null) {
            showPopup("Error no paciente", "Debe seleccionar un paciente", Alert.AlertType.WARNING);
        } else
            loadNuevoEditModal("Editar Paciente", true);
    }

    @FXML
    private void eliminar(ActionEvent event) {
        Paciente paciente = getPacienteSelecciondado();
        if (paciente == null) {
            showPopup("Error no paciente", "Debe seleccionar un paciente", Alert.AlertType.WARNING);
            return;
        }
        try {
            pacienteService.delete(getPacienteSelecciondado().getId());
            pacienteDatos.remove(paciente);
            showPopup("Estatus eliminar paciente", "Paciente Eliminado con exito", Alert.AlertType.WARNING);
        } catch (Exception e) {
            showPopup("Eliminar paciente estatus", "Error al eliminar el paciente", Alert.AlertType.WARNING);
        }

    }

    @FXML
    private void nuevo(ActionEvent event) {
        loadNuevoEditModal("Agregar nuevo Paciente", false);
    }

    private void loadNuevoEditModal(String titulo, Boolean opcion) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/paciente/FXMLPacienteAddModal.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            if (opcion) {
                PacienteAddController controller = fxmlLoader.getController();
                controller.setPersonEdit(this, getPacienteSelecciondado());
            }
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UTILITY);
            stage.setTitle("Agregar Paciente");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        refrescar();
    }

    private Paciente getPacienteSelecciondado() {
        return tblPacientes.getSelectionModel().getSelectedItem();

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
                Paciente pacienteTemp = pacienteService.
                        findById(Long.parseLong(txtSearch.getText()));
                if (pacienteTemp != null) {
                    pacienteDatos.setAll(pacienteService.
                            findById(Long.parseLong(txtSearch.getText())));
                } else {
                    showPopup("Mensaje busqueda paciente", "Paciente no encontrado",
                            Alert.AlertType.INFORMATION);
                }

            } catch (NumberFormatException ex) {
                showPopup("Error", "error", Alert.AlertType.WARNING);
            }
        }

    }

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
    }
}
