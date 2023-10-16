package jala.university.todo_app.controllers;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.IOException;

public class AllTaskController {



    private Parent root;

    private Stage stage;

    private Scene scene;



    @FXML
    private AnchorPane topAnchorPane;

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
    private ImageView moreInfoImg;


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
    private DatabaseConnection dbConnection = DatabaseConnection.getInstance();
    @FXML
    public void searchTask(MouseEvent mouseEvent) {
        ObjectId userId = dbConnection.getUserId();
        FindIterable<Document> tareasDelUsuario = dbConnection
            .getCollectionTareas().find(Filters.eq("usuario", userId));
        int cantidadTareas = 0;
        int iteration = 0;

        task.setManaged(false);

        for (Document tarea : tareasDelUsuario) {
            cantidadTareas++;
        }
        String [] taskName = new String[cantidadTareas];

        for (Document tarea : tareasDelUsuario) {
            taskName[iteration] = tarea.getString("nombre");
            iteration++;
        }
        if (searchField.getText().isEmpty() || searchField.getText() == null) {
            noResultsLabel();
        } else {
            for (int iterator = 0; iterator < taskName.length; iterator++){
                if(!(searchField.getText().equals(taskName[iterator]))){
                    noResultsLabel();
                }else {

                }
            }
        }

    }

    @FXML
    public void initialize(){
        ObjectId userId = dbConnection.getUserId();
        FindIterable<Document> tareasDelUsuario = dbConnection
            .getCollectionTareas().find(Filters.eq("usuario", userId));
        int cantidadTareas = 0;
        int iteration = 0;

        task.setManaged(false);

        for (Document tarea: tareasDelUsuario){
            cantidadTareas++;
        }

        for (Document tarea: tareasDelUsuario) {
            if(!(tarea.getBoolean("completada"))){
                taskComponentCreator(tarea, cantidadTareas, iteration);
            }
        }

    }
    
    @FXML
    public void doneTask(MouseEvent mouseEvent) {
    }

    @FXML
    public void moreDetailsRedirection(MouseEvent event) throws IOException {
        loadPage("/jala/university/todo_app/infoTask-view.fxml");
    }

    private void loadPage(String page){
        Parent root = null;
        //BorderPane dashboard = getDashboard();
        try{
            root = FXMLLoader.load(getClass().getResource(page));
            AllTaskBorderPane.setCenter(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void noResultsLabel(){
        taskContainer.getChildren().removeIf(node -> node instanceof AnchorPane);
        Label label = new Label("No hay resultados.");
        VBox labelContainer = new VBox(label);
        labelContainer.setAlignment(Pos.CENTER);
        label.setTextFill(Color.WHITE);
        Font font = new Font("Berlin Sans FB", 15);
        label.setFont(font);
        taskContainer.getChildren().add(labelContainer);
    }

    public void taskComponentCreator(Document tarea, int cantidadTareas, int iteration){
        dbConnection.setCurrentTask(tarea);
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

            loadPage("/jala/university/todo_app/infoTask-view.fxml");
            filterByAnchorPane.setVisible(false);
            topAnchorPane.setVisible(false);
        });

        //Componente CheckBox
        CheckBox checkTask = new CheckBox();
        checkTask.setLayoutX(markAsDone.getLayoutX());
        checkTask.setLayoutY(markAsDone.getLayoutY());
        checkTask.setPrefHeight(markAsDone.getPrefHeight());
        checkTask.setPrefWidth(markAsDone.getPrefWidth());
        checkTask.setFont(markAsDone.getFont());
        checkTask.setText("Mark as done");
        checkTask.setOnMouseClicked(mouseEvent -> {
            String idTarea = String.valueOf(tarea.get("_id"));
            dbConnection.updateTask(idTarea, tarea.getString("nombre"), tarea.getString("descripcion"),true);
            userTask.setVisible(false);
            userTask.setManaged(false);

        });
        userTask.getChildren().add(checkTask);

        taskContainer.getChildren().add(userTask);
    }

}
