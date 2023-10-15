package jala.university.todo_app.controllers;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import jala.university.todo_app.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.net.URL;
import java.util.ResourceBundle;

public class CompletedTaskController implements Initializable {
    private MongoClient mongoClient;
    private MongoDatabase database;

    private MongoCollection<Document> collectionUsuarios;

    private MongoCollection<Document> collectionTareas;

    @FXML
    private ImageView completedTaskIcon;


    @FXML
    private BorderPane AllTaskBorderPane;

    @FXML
    private Label categoriesTitleLabel;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView searchIcon;

    @FXML
    private AnchorPane task;

    @FXML
    private TextField taskCategory;

    @FXML
    private VBox taskContainer;

    @FXML
    private TextField taskName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToDatabase();
        ObjectId userId = DatabaseConnection.getUserId();
        FindIterable<Document> tareasDelUsuario = collectionTareas.find(Filters.eq("usuario", userId));
        task.setManaged(false);
        task.setVisible(false);

        for (Document tarea: tareasDelUsuario) {
            if(tarea.getBoolean("completada")){
                taskComponentCreator(tarea);
            }
        }


    }


    public void taskComponentCreator(Document tarea){
        //Componente de Tareas
        AnchorPane userTask = new AnchorPane();
        VBox.setMargin(userTask, new Insets(0,0,20,0));
        userTask.setPrefWidth(task.getPrefWidth());
        userTask.setPrefHeight(task.getPrefHeight());
        userTask.setStyle(task.getStyle());


        //Título de la tarea
        TextField userTaskName = new TextField();
        userTaskName.setStyle(taskName.getStyle());
        userTaskName.setPrefWidth(taskName.getPrefWidth());
        userTaskName.setPrefHeight(taskName.getPrefHeight());
        userTaskName.setEditable(false);
        userTaskName.setText(tarea.getString("nombre"));
        userTaskName.setLayoutX(taskName.getLayoutX());
        userTaskName.setLayoutY(taskName.getLayoutY());
        userTaskName.setFont(taskName.getFont());
        userTaskName.setAlignment(Pos.CENTER);
        userTask.getChildren().add(userTaskName);


        //Categoría de la tarea
        TextField userTaskCategory= new TextField();
        userTaskCategory.setStyle(taskCategory.getStyle());
        userTaskCategory.setPrefWidth(taskCategory.getPrefWidth());
        userTaskCategory.setPrefHeight(taskCategory.getPrefHeight());
        userTaskCategory.setEditable(false);
        userTaskCategory.setText(tarea.getString("categoria"));
        userTaskCategory.setLayoutX(taskCategory.getLayoutX());
        userTaskCategory.setLayoutY(taskCategory.getLayoutY());
        userTaskCategory.setFont(taskCategory.getFont());
        userTaskCategory.setAlignment(Pos.CENTER);
        userTask.getChildren().add(userTaskCategory);


        //Icono completado
        ImageView iconoCompletado = new ImageView();
        iconoCompletado.setFitHeight(completedTaskIcon.getFitHeight());
        iconoCompletado.setFitWidth(completedTaskIcon.getFitWidth());
        iconoCompletado.setLayoutX(completedTaskIcon.getLayoutX());
        iconoCompletado.setLayoutY(completedTaskIcon.getLayoutY());
        Image icon = new Image(getClass().getResourceAsStream("/img/icons8-tarea-completada-48.png"));
        iconoCompletado.setImage(icon);
        iconoCompletado.cursorProperty().set(Cursor.HAND);
        Tooltip tooltip = new Tooltip("Tarea completada");
        Tooltip.install(iconoCompletado,tooltip);
        userTask.getChildren().add(iconoCompletado);
        taskContainer.getChildren().add(userTask);
    }

    void connectToDatabase() {
        mongoClient = MongoClients.create("mongodb+srv://losmakias:losmakias1@cluster0.m1zizil.mongodb.net/?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("ToDoApp");
        collectionUsuarios = database.getCollection("Usuarios");
        collectionTareas = database.getCollection("Tareas");
    }

}
