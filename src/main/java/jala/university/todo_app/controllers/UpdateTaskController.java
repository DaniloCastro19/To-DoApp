package jala.university.todo_app.controllers;

import java.net.URL;
import java.util.ResourceBundle;
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
  private TextField updateTaskTitle;

  @FXML
  void createTask(MouseEvent event) {
    System.out.println(AllTaskController.tareaActual.toJson());
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    updateTaskTitle.setText(AllTaskController.tareaActual.getString("nombre"));
    updateTaskTitle.setEditable(true);
    /*updateTask.setText(AllTaskController.tareaActual.getString("descripcion"));
    updateTask.setEditable(false);*/
  }
}
