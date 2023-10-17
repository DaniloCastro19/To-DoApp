package jala.university.todo_app.controllers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {

    private Parent root;
    private Scene scene;
    private Stage stage;
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
    private DatabaseConnection dbConnection = DatabaseConnection.getInstance();

    @FXML
    void registerRedirection(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/jala/university/todo_app/register-view.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loginEvent(ActionEvent event) {
        try {
            if (dbConnection.login(emailField.getText(), passwordField.getText())) {
                root = FXMLLoader.load(getClass().getResource("/jala/university/todo_app/dashboard-view.fxml"));
                scene = new Scene(root);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Dashboard");
                stage.setScene(scene);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Estado de inicio de sesión.");
                alert.setHeaderText(null);
                alert.setContentText("No se encontro el usuario. Por favor verifique el correo y contraseña ingresados.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
