package jala.university.todo_app.controllers;

import jala.university.todo_app.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UpdateTaskController implements Initializable {

  @FXML
  private AnchorPane anchorPane;

  @FXML
  private CheckBox completeTask;

  @FXML
  private Label date;

  @FXML
  private Button delete;

  @FXML
  private Label priority;

  @FXML
  private Button update;

  @FXML
  private TextArea updateTask;

  @FXML
  private TextField taskTitle;

  @FXML
  void updateTask(MouseEvent event) {
    String taskId = "6525a6518b6b300d4ad24dcb";
    DatabaseConnection.updateTask(taskId, updateTaskTitle.getText(), updateTask.getText());

  }

  @FXML
  void deleteTask(MouseEvent event) {
    String taskId = "6525a6518b6b300d4ad24dcb";
    DatabaseConnection.deleteTask(taskId);
  }
}
