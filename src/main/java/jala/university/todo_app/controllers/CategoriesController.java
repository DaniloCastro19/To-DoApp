package jala.university.todo_app.controllers;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import jala.university.todo_app.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class CategoriesController implements Initializable {


    private MongoClient mongoClient;
    private MongoDatabase database;

    private MongoCollection<Document> collectionUsuarios;

    private MongoCollection<Document> collectionTareas;


    @FXML
    private BorderPane AllTaskBorderPane;

    @FXML
    private TitledPane categorie;

    @FXML
    private Accordion categoriesContainer;

    @FXML
    private Label categoriesTitleLabel;

    @FXML
    private ImageView priority;

    @FXML
    private ImageView priority1;

    @FXML
    private ImageView priority2;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchField;

    @FXML
    private ImageView searchIcon;

    @FXML
    private AnchorPane task;

    @FXML
    private AnchorPane task1;

    @FXML
    private AnchorPane task2;


    @FXML
    private VBox taskContainer1;

    @FXML
    private TextField taskName;

    @FXML
    private TextField taskName1;

    @FXML
    private TextField taskName2;

    @FXML
    private VBox vBoxContainer;


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connectToDatabase();
        ObjectId userId = DatabaseConnection.getUserId();
        FindIterable<Document> tareasDelUsuario = collectionTareas.find(Filters.eq("usuario", userId));
        int cantidadTareas = 0;
        int iteration = 0;

        //task.setManaged(false);

        for (Document tarea: tareasDelUsuario){
            cantidadTareas++;
        }

        for (Document tarea: tareasDelUsuario){
            createCategoyComponent(tarea, cantidadTareas, iteration);
            iteration++;
        }


    }

    void createCategoyComponent(Document tarea, int cantidadTareas, int iteration){

        //Tarea
        VBox taskContainer = new VBox();
        //TODO: CREACIÓN DEL CONTENEDOR DE LAS TAREAS.
        //AnchorPane task = taskComponentCreator(tarea, cantidadTareas, iteration);

        //Contenedor de categoría.

        Accordion newCategoryContainer = new Accordion();
        newCategoryContainer.setPrefWidth(categoriesContainer.getPrefWidth());
        TitledPane tituloCategoria = new TitledPane(tarea.getString("categoria"), taskContainer);
        tituloCategoria.setAnimated(true);
        newCategoryContainer.getPanes().add(tituloCategoria);

        vBoxContainer.getChildren().add(newCategoryContainer);
    }

    void connectToDatabase() {
        mongoClient = MongoClients.create("mongodb+srv://losmakias:losmakias1@cluster0.m1zizil.mongodb.net/?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("ToDoApp");
        collectionUsuarios = database.getCollection("Usuarios");
        collectionTareas = database.getCollection("Tareas");
    }

    public void taskComponentCreator(Document tarea, int cantidadTareas, int iteration) {

        //Componente de Tareas
        AnchorPane userTask = new AnchorPane();
        VBox.setMargin(userTask, new javafx.geometry.Insets(0, 0, 20, 0));
        userTask.setPrefWidth(task.getPrefWidth());
        userTask.setPrefHeight(task.getPrefHeight());
        userTask.setStyle(task.getStyle());

        /*
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
            iconoPrioridad.cursorProperty().set(Cursor.HAND);
            Tooltip tooltip = new Tooltip("Prioridad Alta.");
            Tooltip.install(iconoPrioridad,tooltip);

        }else if(tarea.getString("prioridad").equals("Mid")){
            Image icon = new Image(getClass().getResourceAsStream("/img/icons8-prioridad-media-48.png"));
            iconoPrioridad.setImage(icon);
            iconoPrioridad.cursorProperty().set(Cursor.HAND);
            Tooltip tooltip = new Tooltip("Prioridad Media.");
            Tooltip.install(iconoPrioridad,tooltip);
        }else if(tarea.getString("prioridad").equals("Low")){
            Image icon = new Image(getClass().getResourceAsStream("/img/icons8-baja-prioridad-40.png"));
            iconoPrioridad.setImage(icon);
            iconoPrioridad.cursorProperty().set(Cursor.HAND);
            Tooltip tooltip = new Tooltip("Prioridad Baja.");
            Tooltip.install(iconoPrioridad,tooltip);
        }

        //Boton detalles

        ImageView[] iconoDetalles = new ImageView[cantidadTareas];
        Image icon = new Image(getClass().getResourceAsStream("/img/icons8-información-48.png"));
        iconoDetalles[iteration] = new ImageView(icon);
        iconoDetalles[iteration].setFitHeight(moreInfoImg.getFitHeight());
        iconoDetalles[iteration].setFitWidth(moreInfoImg.getFitWidth());
        iconoDetalles[iteration].setLayoutX(moreInfoImg.getLayoutX());
        iconoDetalles[iteration].setLayoutY(moreInfoImg.getLayoutY());
        iconoDetalles[iteration].setImage(icon);
        iconoDetalles[iteration].cursorProperty().set(Cursor.HAND);


        userTask.getChildren().add(iconoDetalles[iteration]);

        iconoDetalles[iteration].setOnMouseClicked(event -> {
            tareaActual = tarea;
            loadPage("/jala/university/todo_app/updateTask-view.fxml");
            filterByAnchorPane.setVisible(false);
            topAnchorPane.setVisible(false);
        });
        taskContainer.getChildren().add(userTask);

        return userTask;
    }
*/
    }
}
