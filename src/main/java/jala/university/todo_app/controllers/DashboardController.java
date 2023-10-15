package jala.university.todo_app.controllers;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import jala.university.todo_app.DatabaseConnection;
import jala.university.todo_app.Login;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.model.Filters.*;

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

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collectionUsuarios;
    private MongoCollection<Document> collectionTareas ;



    @FXML
    void allTask(MouseEvent event) throws IOException {
        loadPage("/jala/university/todo_app/allTask-view.fxml");

    }

    @FXML
    void categories(MouseEvent event) {
        loadPage("/jala/university/todo_app/categories.fxml");
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
        connectToDatabase();
        ObjectId userId = DatabaseConnection.getUserId();
        Document usuario = collectionUsuarios.find(eq("_id", userId)).first();
        assert usuario != null;
        String userName = (String) usuario.get("nombre");
        System.out.println(userName);
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

        System.out.println(page);

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

        System.out.println(page);

    }
    @FXML
    public void changeUserImg(MouseEvent mouseEvent) {
        loadLogin("/jala/university/todo_app/login-view.fxml");
    }

    void connectToDatabase() {
        mongoClient = MongoClients.create("mongodb+srv://losmakias:losmakias1@cluster0.m1zizil.mongodb.net/?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("ToDoApp");
        collectionUsuarios = database.getCollection("Usuarios");
        collectionTareas = database.getCollection("Tareas");
    }

    public BorderPane getDashboard(){
        return dashboard;
    }


}
