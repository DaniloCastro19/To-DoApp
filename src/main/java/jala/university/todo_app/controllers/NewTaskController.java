package jala.university.todo_app.controllers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jala.university.todo_app.DatabaseConnection;
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

    LocalDateTime fechaHoraActual;


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
        ObjectId userId = LoginController.getUserId();
        DatabaseConnection.createTask(newTaskTitleTextField.getText(),
            newTaskDescriptionArea.getText(),
            categoryField.getText(),
            priorityChoiceBox.getValue(),
            userId);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        priorityChoiceBox.getItems().addAll(priorities);
    }
}
