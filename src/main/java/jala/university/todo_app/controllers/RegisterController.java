package jala.university.todo_app.controllers;

import jala.university.todo_app.DatabaseConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.mongodb.client.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
public class RegisterController {

    public static MongoClient mongoClient;
    public static MongoDatabase database;
    private Parent root;
    private Scene scene;
    private Stage stage;
    Alert alertUsuarioRegistrado = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private Label appSectionLabel;
    @FXML
    private Label appTitleLabel;
    @FXML
    private Label loginLinkLabel;
    @FXML
    private Label loginQuestionLabel;
    @FXML
    private Button registerButton;
    @FXML
    private Pane registerFormPane;
    @FXML
    private ImageView registerImg;
    @FXML
    private Pane registerUser;
    @FXML
    private PasswordField repeatPasswordField;
    @FXML
    private TextField userEmailField;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField userPasswordField;
    @FXML
    private Label nameFieldAlertLabel;
    @FXML
    private Label emailFieldAlertLabel;
    @FXML
    private Label passwordFieldAlertLabel;
    @FXML
    private Label repeatPasswordAlertLabel;

    @FXML
    void onRegisterButtonClick() {
        try {
            eraseAlerts();
            String userName = userNameField.getText();
            String userEmail = userEmailField.getText();
            String password = userPasswordField.getText();
            String repeatPassword = repeatPasswordField.getText();
            fieldValidation(userName, userEmail, password, repeatPassword);
            boolean registrationSuccess = DatabaseConnection.createUser(userName, userEmail, password);

            if (registrationSuccess) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Estado de registro");
                alert.setHeaderText(null);
                alert.setContentText("Usuario registrado con éxito.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de registro");
                alert.setHeaderText(null);
                alert.setContentText("El registro de usuario ha fallado. Por favor, inténtalo de nuevo.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void logInRedirection(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/jala/university/todo_app/login-view.fxml"));
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    private boolean fieldValidation(String userName, String userEmail, String password, String repeatPassword) {
        boolean isValid = true;
        if (userName.isEmpty()) {
            nameFieldAlertLabel.setText("Campo vacío");
            isValid = false;
        }
        if (!validateEmail(userEmail)) {
            emailFieldAlertLabel.setText("Correo electrónico inválido.");
            isValid = false;
        }else if (registerUser != null) {
            emailFieldAlertLabel.setText("Correo electrónico ya registrado.");
            isValid = false;
        }
        if (userPasswordField.getText().isEmpty()) {
            passwordFieldAlertLabel.setText("Campo de contraseña vacío.");
            isValid = false;
        }
        if (!password.equals(repeatPassword)) {
            repeatPasswordAlertLabel.setText("Las contraseñas no coinciden.");
            isValid = false;
        }
        return isValid;
    }

    boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    void eraseAlerts() {
        nameFieldAlertLabel.setText("");
    }


}