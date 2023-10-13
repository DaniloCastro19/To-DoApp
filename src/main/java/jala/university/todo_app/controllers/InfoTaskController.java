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
  private TextArea taskDescription;

  @FXML
  private TextField taskTitle;

  @FXML
  public void updateTask(MouseEvent event) throws IOException {
    loadPage("/jala/university/todo_app/updateTask-view.fxml");
  }

  @FXML
  void deleteTask(MouseEvent event) {
    System.out.println(DatabaseConnection.getCurrentTask().toJson());
    String taskId = DatabaseConnection.getCurrentTask().getObjectId("_id").toString();
    DatabaseConnection.deleteTask(taskId);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    taskTitle.setText(DatabaseConnection.getCurrentTask().getString("nombre"));
    taskTitle.setEditable(false);
    taskDescription.setText(DatabaseConnection.getCurrentTask().getString("descripcion"));
    taskDescription.setEditable(false);
  }

  private void loadPage(String page){
    Parent root = null;
    //BorderPane dashboard = getDashboard();
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

    System.out.println(page);
  }
}
