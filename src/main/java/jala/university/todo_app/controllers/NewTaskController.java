package jala.university.todo_app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class NewTaskController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button createTaskButton;

    @FXML
    private TextArea newTaskDescriptionArea;

    @FXML
    private TextField newTaskTitleTextField;

    @FXML
    void createTask(MouseEvent event) {
        //TODO: Funcionalidad para enviar a la base de datos.
    }

}
