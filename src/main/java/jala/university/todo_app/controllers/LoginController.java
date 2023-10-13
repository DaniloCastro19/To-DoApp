package jala.university.todo_app.controllers;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jala.university.todo_app.DatabaseConnection;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController {

    private Parent root;
    private Scene scene;
    private Stage stage;

    public static MongoClient mongoClient;
    public static MongoDatabase database;

    @FXML
    private TextField emailField;

    @FXML
    private Pane formPane;

    @FXML
    private Button loginButton;

    @FXML
    private Pane loginPane;

    @FXML
    private Label loginSubtitle;

    @FXML
    private Label loginSubtitle1;

    @FXML
    private Label loginSubtitle11;

    @FXML
    private TextField passwordField;

    @FXML
    void registerRedirection(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/jala/university/todo_app/register-view.fxml"));
        scene = new Scene(root);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void loginEvent(ActionEvent event) {
        try {
            if (DatabaseConnection.login(emailField.getText(), passwordField.getText())) {
                root = FXMLLoader.load(getClass().getResource("/jala/university/todo_app/dashboard-view.fxml"));
                scene = new Scene(root);

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setScene(scene);

                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
