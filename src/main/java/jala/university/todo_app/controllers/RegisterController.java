package jala.university.todo_app.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
public class RegisterController {
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
    private DatabaseConnection dbConnection = DatabaseConnection.getInstance();

    @FXML
    void onRegisterButtonClick() {
        try {
            String userName = userNameField.getText();
            String userEmail = userEmailField.getText();
            String password = userPasswordField.getText();
            String repeatPassword = repeatPasswordField.getText();
            boolean areValidInputs = fieldValidation(userName, userEmail, password, repeatPassword);
            if (areValidInputs){
                boolean registrationSuccess = dbConnection.createUser(userName, userEmail, password);
                if (registrationSuccess) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Estado de registro");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuario registrado con éxito.");
                    alert.show();
                    loadLogin();
                } else if (!registrationSuccess){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de registro");
                    alert.setHeaderText(null);
                    alert.setContentText("El registro de usuario ha fallado. Por favor, siga las instrucciones e inténtelo de nuevo.");
                    alert.showAndWait();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Estado de registro");
                alert.setHeaderText(null);
                alert.setContentText("El registro de usuario ha fallado. Por favor siga las instrucciones.");
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
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    private boolean fieldValidation(String userName, String userEmail, String password, String repeatPassword) {
        boolean isValid = true;
        if (userName.isEmpty() && userEmail.isEmpty() && password.isEmpty() && repeatPassword.isEmpty()){
            nameFieldAlertLabel.setText("Debe introducir su nombre.");
            emailFieldAlertLabel.setText("Debe introducir su correo electrónico.");
            passwordFieldAlertLabel.setText("Campo de contraseña vacío.");
            repeatPasswordAlertLabel.setText("Debe repetir su contraseña.");
            isValid = false;
        }else if (userName.isEmpty()) {
            nameFieldAlertLabel.setText("Campo vacío");
            isValid = false;
        } else if(userEmail.isEmpty()){
            emailFieldAlertLabel.setText("Campo de email vacío.");
            isValid = false;
        } else if (password.isEmpty()) {
            passwordFieldAlertLabel.setText("Campo de contraseña vacío.");
        } else if (repeatPassword.isEmpty()) {
            repeatPasswordAlertLabel.setText("Campo de contraseña vacío.");
            isValid = false;
        }

        if (!validateEmail(userEmail)){
        emailFieldAlertLabel.setText("Correo electrónico inválido.");
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
    private void loadLogin(){
        Parent root = null;
        try{
            Stage stage = (Stage) registerUser.getScene().getWindow();
            stage.close();
            stage.setTitle("Login");
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/jala/university/todo_app/login-view.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}