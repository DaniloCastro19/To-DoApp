package jala.university.todo_app.controllers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class NewTaskController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button createTaskButton;

    @FXML
    private TextArea newTaskDescriptionArea;

    @FXML
    private TextField newTaskTitleTextField;

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collectionTareas;


    @FXML
    void updateTask(MouseEvent event) {
        connectToDatabase();
        Document query = new Document("_id", new ObjectId("6525a6518b6b300d4ad24dcb"));
        Bson updates = Updates.combine(
            Updates.set("nombre", newTaskTitleTextField.getText()),
            Updates.set("descripcion", newTaskDescriptionArea.getText()));
        UpdateOptions options = new UpdateOptions().upsert(true);
        collectionTareas.updateOne(query, updates, options); //Updating
    }

    @FXML
    void deleteTask(MouseEvent event) {
        connectToDatabase();
        Document query = new Document();
        collectionTareas.deleteOne(query);
    }

    @FXML
    void createTask(MouseEvent event) {
        connectToDatabase();
        ObjectId userId = LoginController.getUserId();
        Document tarea = new Document("nombre", newTaskTitleTextField.getText())
            .append("descripcion", newTaskDescriptionArea.getText())
            .append("usuario", userId)
            .append("completada", false);
        collectionTareas.insertOne(tarea);
        System.out.println("Tarea " + newTaskTitleTextField.getText() + " creada.");
    }

    void connectToDatabase() {
        mongoClient = MongoClients.create("mongodb+srv://losmakias:losmakias1@cluster0.m1zizil.mongodb.net/?retryWrites=true&w=majority");
        database = mongoClient.getDatabase("ToDoApp");
        collectionTareas = database.getCollection("Tareas");
    }

}
