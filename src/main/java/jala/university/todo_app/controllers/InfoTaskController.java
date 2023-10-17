package jala.university.todo_app.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class InfoTaskController implements Initializable {

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
  private Label taskCreationDateLabel;

  @FXML
  private Label taskPriorityLabel;

  @FXML
  private TextArea taskDescription;

  @FXML
  private TextField taskTitle;
  private DatabaseConnection dbConnection = DatabaseConnection.getInstance();

  @FXML
  public void updateTask(MouseEvent event) throws IOException {
    loadPage("/jala/university/todo_app/updateTask-view.fxml");
  }

  @FXML
  void deleteTask(MouseEvent event) {
    String taskId = dbConnection.getCurrentTask().getObjectId("_id").toString();
    dbConnection.deleteTask(taskId);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Estado tarea.");
    alert.setHeaderText(null);
    alert.setContentText("La tarea se ha eliminado con exito.");
    alert.showAndWait();

  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    taskTitle.setText(dbConnection.getCurrentTask().getString("nombre"));
    taskTitle.setEditable(false);
    taskDescription.setText(dbConnection.getCurrentTask().getString("descripcion"));
    taskDescription.setEditable(false);
    taskCreationDateLabel.setText(String.valueOf(dbConnection.getCurrentTask().getDate("FechaCreacion")));
    taskPriorityLabel.setText(dbConnection.getCurrentTask().getString("prioridad"));
  }

  private void loadPage(String page){
    Parent root = null;
    try{
      root = FXMLLoader.load(getClass().getResource(page));
      AnchorPane.setTopAnchor(root, 0.0);
      AnchorPane.setBottomAnchor(root, 0.0);
      AnchorPane.setLeftAnchor(root, 0.0);
      AnchorPane.setRightAnchor(root, 0.0);
      anchorPane.getChildren().add(root);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
