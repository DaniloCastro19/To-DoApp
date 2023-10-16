package jala.university.todo_app.controllers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jala.university.todo_app.DatabaseConnection;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.net.URL;
import java.time.LocalDateTime;
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

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collectionTareas;


    @FXML
    void createTask(MouseEvent event) {
        fechaHoraActual =LocalDate.now();
        ObjectId userId = DatabaseConnection.getUserId();

        if (newTaskTitleTextField.getText().isEmpty()) {
            titleAlertLabel.setText("La tarea debe tener un título.");
            System.out.println("title");
        } else if (priorityChoiceBox.getValue() == null) {
            priorityAlertLabel.setText("Debe establecer el nivel de prioridad para la tarea.");
            System.out.println("priority");
        }else {
            DatabaseConnection.createTask(newTaskTitleTextField.getText(),
                    newTaskDescriptionArea.getText(),
                    categoryField.getText(),
                    priorityChoiceBox.getValue(),
                    userId);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priorityChoiceBox.getItems().addAll(priorities);
    }
}
