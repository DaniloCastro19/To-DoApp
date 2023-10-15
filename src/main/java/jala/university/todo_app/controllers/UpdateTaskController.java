package jala.university.todo_app.controllers;

import jala.university.todo_app.DatabaseConnection;
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

  @FXML
  void updateTask(MouseEvent event) {
    String taskId = DatabaseConnection.getCurrentTask().getObjectId("_id").toString();
    boolean isTaskCompleted = completeTask.isSelected();
    DatabaseConnection.updateTask(taskId, taskTitle.getText(), taskDescription.getText(), isTaskCompleted);

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    taskTitle.setText(DatabaseConnection.getCurrentTask().getString("nombre"));
    taskTitle.setEditable(true);
    taskDescription.setText(DatabaseConnection.getCurrentTask().getString("descripcion"));
    taskDescription.setEditable(true);
    taskCreationDateLabel.setText(String.valueOf(DatabaseConnection.getCurrentTask().getDate("FechaCreacion")));
    taskPriorityLabel.setText(DatabaseConnection.getCurrentTask().getString("prioridad"));
  }

  private void loadPage(String page) {
    Parent root = null;
    //BorderPane dashboard = getDashboard();
    try {
      root = FXMLLoader.load(getClass().getResource(page));

      //anchorPane.setClip(root);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
