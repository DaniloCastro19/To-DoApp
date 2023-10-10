package jala.university.todo_app.controllers;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
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
    private Button todayTaskButton;

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collectionUsuarios;

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
        connectToDatabase();
        ObjectId userId = LoginController.getUserId();
        Document usuario = collectionUsuarios.find(eq("_id", userId)).first();
        assert usuario != null;
        System.out.println(usuario.get("nombre"));


        loadPage("/jala/university/todo_app/newTask-view.fxml");

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
    @FXML
    public void changeUserImg(MouseEvent mouseEvent) {
    }


    void connectToDatabase() {
        mongoClient = MongoClients.create("mongodb+srv://losmakias:losmakias1@cluster0.m1zizil.mongodb.net/?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("ToDoApp");
        collectionUsuarios = database.getCollection("Usuarios");
    }
}
