package jala.university.todo_app.controllers;

import static com.mongodb.client.model.Filters.eq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

public class DashboardController {

    @FXML
    private Button allTaskButton;

    @FXML
    private Button categoriesButton;


    @FXML
    private Button newTaskButton;

    @FXML
    private VBox newTaskContainer;


    @FXML
    private BorderPane dashboard;

    @FXML
    private Pane sidebar;

    @FXML
    private Label userNameLabel;
    @FXML
    private Button todayTaskButton;
    private DatabaseConnection dbConnection = DatabaseConnection.getInstance();

    @FXML
    void allTask(MouseEvent event) throws IOException {
        loadPage("/jala/university/todo_app/allTask-view.fxml");

    }
    @FXML
    public void completedTask(MouseEvent mouseEvent) {
        loadPage("/jala/university/todo_app/completedTask-view.fxml");
    }

    @FXML
    void createNewTask(MouseEvent mouseEvent) throws IOException {
        loadPage("/jala/university/todo_app/newTask-view.fxml");
    }

    @FXML
    public void initialize(){
        //Mostrar el nombre de usuario actual (próximamente una foto).
        ObjectId userId = dbConnection.getUserId();
        Document usuario = dbConnection.getCollectionUsuarios().find(eq("_id", userId)).first();
        assert usuario != null;
        String userName = (String) usuario.get("nombre");
        userNameLabel.setText(userName);
    }

    private void loadPage(String page){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(page));
            dashboard.setCenter(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadLogin(String page){
        Parent root = null;
        try{
            Stage stage = (Stage) dashboard.getScene().getWindow();
            stage.close();
            stage.setTitle("Login");
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(page))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void logout(ActionEvent actionEvent) {
        loadLogin("/jala/university/todo_app/login-view.fxml");

    }
    @FXML
    public void changeUserImg(MouseEvent mouseEvent) {
    }

}
