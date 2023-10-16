package jala.university.todo_app.controllers;

import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import org.bson.types.ObjectId;

import java.net.URL;
import java.util.ResourceBundle;

public class NewTaskController implements Initializable {
    Alert alertUsuarioRegistrado = new Alert(Alert.AlertType.INFORMATION);

    LocalDate fechaHoraActual;

    @FXML
    private Label titleAlertLabel;
    @FXML
    private Label priorityAlertLabel;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button createTaskButton;

    @FXML
    private ChoiceBox<String> priorityChoiceBox;

    private String [] priorities = {"Low", "Mid" , "High"};

    @FXML
    private TextField categoryField;

    @FXML
    private TextArea newTaskDescriptionArea;

    @FXML
    private TextField newTaskTitleTextField;
    private DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    @FXML
    void createTask(MouseEvent event) {
        fechaHoraActual =LocalDate.now();
        ObjectId userId = dbConnection.getUserId();

        if (newTaskTitleTextField.getText().isEmpty()) {
            titleAlertLabel.setText("La tarea debe tener un título.");
        } else if (priorityChoiceBox.getValue() == null) {
            priorityAlertLabel.setText("Debe establecer el nivel de prioridad para la tarea.");
        }else {
            dbConnection.createTask(newTaskTitleTextField.getText(),
                    newTaskDescriptionArea.getText(),
                    categoryField.getText(),
                    priorityChoiceBox.getValue(),
                    userId);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creación de tarea");
            alert.setHeaderText(null);
            alert.setContentText("Tarea creada con éxito.");
            alert.showAndWait();
            clearFields();
        }

    }

    void clearFields(){
        newTaskTitleTextField.clear();
        newTaskDescriptionArea.clear();
        categoryField.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priorityChoiceBox.getItems().addAll(priorities);
    }
}
