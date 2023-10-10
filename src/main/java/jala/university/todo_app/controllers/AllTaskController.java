package jala.university.todo_app.controllers;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
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
    private ImageView priorityImg;

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

    @FXML
    private CheckBox markAsDone;
    @FXML
    public void initialize(){
        //Mostrar el nombre de usuario actual (próximamente una foto).
        connectToDatabase();
        ObjectId userId = LoginController.getUserId();
        FindIterable<Document> tareasDelUsuario = collectionTareas.find(Filters.eq("usuario", userId));
        for (Document tarea: tareasDelUsuario){


            //Componente de Tareas
            AnchorPane userTask = new AnchorPane();
            VBox.setMargin(userTask, new javafx.geometry.Insets(0,0,20,0));
            userTask.setPrefWidth(task.getPrefWidth());
            userTask.setPrefHeight(task.getPrefHeight());
            userTask.setStyle(task.getStyle());

            //Componente CheckBox
            CheckBox checkTask = new CheckBox();
            checkTask.setLayoutX(markAsDone.getLayoutX());
            checkTask.setLayoutY(markAsDone.getLayoutY());
            checkTask.setPrefHeight(markAsDone.getPrefHeight());
            checkTask.setPrefWidth(markAsDone.getPrefWidth());
            checkTask.setFont(markAsDone.getFont());
            checkTask.setText("Mark as done");
            userTask.getChildren().add(checkTask);

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


            //Icono de prioridad
            ImageView iconoPrioridad = new ImageView();
            iconoPrioridad.setFitHeight(priorityImg.getFitHeight());
            iconoPrioridad.setFitWidth(priorityImg.getFitWidth());
            iconoPrioridad.setLayoutX(priorityImg.getLayoutX());
            iconoPrioridad.setLayoutY(priorityImg.getLayoutY());
            userTask.getChildren().add(iconoPrioridad);

            if(tarea.getString("prioridad").equals("High")){
                Image icon = new Image(getClass().getResourceAsStream("/img/icons8-alta-prioridad-48.png"));
                iconoPrioridad.setImage(icon);
            }else if(tarea.getString("prioridad").equals("Mid")){
                Image icon = new Image(getClass().getResourceAsStream("/img/icons8-prioridad-media-48.png"));
                iconoPrioridad.setImage(icon);
            }else if(tarea.getString("prioridad").equals("Low")){
                Image icon = new Image(getClass().getResourceAsStream("/img/icons8-baja-prioridad-40.png"));
                iconoPrioridad.setImage(icon);
            }

            taskContainer.getChildren().add(userTask);

        }


    }

    void connectToDatabase() {
        mongoClient = MongoClients.create("mongodb+srv://losmakias:losmakias1@cluster0.m1zizil.mongodb.net/?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("ToDoApp");
        collectionUsuarios = database.getCollection("Usuarios");
        collectionTareas = database.getCollection("Tareas");
    }
    @FXML
    public void doneTask(MouseEvent mouseEvent) {
    }
}
