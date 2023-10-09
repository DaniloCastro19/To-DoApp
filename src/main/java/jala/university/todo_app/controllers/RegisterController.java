package jala.university.todo_app.controllers;


import org.mindrot.jbcrypt.BCrypt;

import com.mongodb.client.*;

import com.mongodb.client.result.InsertOneResult;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.bson.Document;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;

public class RegisterController {

    public static MongoClient mongoClient;

    public static MongoDatabase database;

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
            mongoClient = MongoClients.create("mongodb+srv://losmakias:losmakias1@cluster0.m1zizil.mongodb.net/?retryWrites=true&w=majority");
            database = mongoClient.getDatabase("ToDoApp");
            MongoCollection<Document> collection = database.getCollection("Usuarios");
            Document existingUser = collection.find(new Document("email", userEmailField.getText())).first();
            if(fieldValidation(existingUser)){
                return;
            }else {
                String password = userPasswordField.getText();
                String repeatPassword = repeatPasswordField.getText();

                if(password.equals(repeatPassword)){
                    String contra = BCrypt.hashpw(password, BCrypt.gensalt());
                    Document newUsuario = new Document("nombre", userNameField.getText()).append("email", userEmailField.getText()).append("password", contra);
                    System.out.println(contra);
                    InsertOneResult result = collection.insertOne(newUsuario);
                }else {
                    System.out.println("Contraseña incorrecta");
                }

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    boolean fieldValidation(Document existingUser ) {
        if(userNameField.getText().isEmpty()){
            nameFieldAlertLabel.setText("Campo vacio");
            return true;
        }
        if (!validateEmail(userEmailField.getText())) {
            emailFieldAlertLabel.setText("Correo electrónico inválido.");
            return true;
        }
        if (existingUser != null) {
            emailFieldAlertLabel.setText("Correo electrónico ya registrado.");
            return true;
        }
        if (userPasswordField.getText().isEmpty()) {
            passwordFieldAlertLabel.setText("Campo de contraseña vacío.");
            return true;
        }
        if (!userPasswordField.getText().equals(repeatPasswordField.getText())) {
            repeatPasswordAlertLabel.setText("Las contraseñas no coinciden.");
            return true;
        }
        return false;
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