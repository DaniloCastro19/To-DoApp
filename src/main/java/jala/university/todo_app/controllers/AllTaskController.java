package jala.university.todo_app.controllers;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Iterator;

import static com.mongodb.client.model.Filters.eq;

public class AllTaskController {

    private MongoClient mongoClient;
    private MongoDatabase database;

    private MongoCollection<Document> collectionUsuarios;

    private MongoCollection<Document> collectionTareas;

    @FXML
    private BorderPane AllTaskBorderPane;

    @FXML
    private Label allTaskTitleLabel;

    @FXML
    private AnchorPane filterByAnchorPane;

    @FXML
    private ImageView fliterByIcon;

    @FXML
    private ImageView priority;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView searchIcon;

    @FXML
    private AnchorPane task;

    @FXML
    private VBox taskContainer;

    @FXML
    private TextField taskName;

    @FXML
    public void initialize(){
        //Mostrar el nombre de usuario actual (próximamente una foto).
        connectToDatabase();
        ObjectId userId = LoginController.getUserId();
        FindIterable<Document> tareasDelUsuario = collectionTareas.find(Filters.eq("usuario", userId));
        for (Document tarea: tareasDelUsuario){
            //TODO: Crear componentes.

            taskName.setText(tarea.getString("nombre"));
            System.out.println(tarea.getString("nombre"));
        }

    }

    void connectToDatabase() {
        mongoClient = MongoClients.create("mongodb+srv://losmakias:losmakias1@cluster0.m1zizil.mongodb.net/?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("ToDoApp");
        collectionUsuarios = database.getCollection("Usuarios");
        collectionTareas = database.getCollection("Tareas");
    }

}
