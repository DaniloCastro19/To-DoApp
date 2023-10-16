package jala.university.todo_app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
  private Label priority;

  @FXML
  private Button update;
  @FXML
  private Label taskPriorityLabel;
  @FXML
  private Label taskCreationDateLabel;


  @FXML
  private TextArea taskDescription;

  @FXML
  private TextField taskTitle;
  private DatabaseConnection dbConnection = DatabaseConnection.getInstance();

  @FXML
  void updateTask(MouseEvent event) {
    String taskId = dbConnection.getCurrentTask().getObjectId("_id").toString();
    boolean isTaskCompleted = completeTask.isSelected();
    dbConnection.updateTask(taskId, taskTitle.getText(), taskDescription.getText(), isTaskCompleted);

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    taskTitle.setText(dbConnection.getCurrentTask().getString("nombre"));
    taskTitle.setEditable(true);
    taskDescription.setText(dbConnection.getCurrentTask().getString("descripcion"));
    taskDescription.setEditable(true);
    taskCreationDateLabel.setText(String.valueOf(dbConnection.getCurrentTask().getDate("FechaCreacion")));
    taskPriorityLabel.setText(dbConnection.getCurrentTask().getString("prioridad"));
  }

  private void loadPage(String page) {
    Parent root = null;
    try {
      root = FXMLLoader.load(getClass().getResource(page));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
