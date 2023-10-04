package jala.university.todo_app.controllers;
import com.mongodb.client.*;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;


public class RegisterController {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private static StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
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
    private Label repeatPasswordAlertLabel; //TODO: implementar lógica para verificar la contraseña.
    @FXML
    void onRegisterButtonClick() {
        try{
            eraseAlerts();
            mongoClient = MongoClients.create("mongodb+srv://losmakias:losmakias1@cluster0.m1zizil.mongodb.net/?retryWrites=true&w=majority");
            database = mongoClient.getDatabase("ToDoApp");
            MongoCollection<Document> collection = database.getCollection("Usuarios");

            if (fieldValidation()){
                //TODO: Implementar el resto de validaciones.
                nameFieldAlertLabel.setText("Este campo no puede estar vacío.");
                emailFieldAlertLabel.setText("");
            }else {
                fieldValidation();
                encryptor.setPassword(userPasswordField.getText());
                String userPass = encryptor.encrypt(userPasswordField.getText());
                Document newUsuario = new Document("nombre", userNameField.getText()).append("email",userEmailField.getText()).append("password", userPass);
                collection.insertOne(newUsuario);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //TODO: agregar redireccionamiento en la app hacia el apartado de login.

    boolean fieldValidation(){
        //TODO: Hacer el resto de validaciones.
        if(userNameField.getText().isEmpty()){
            System.out.println("name field empty");
            return true;
        }
        return false;
    }

    //TODO: Implementar validación del email.
    private boolean isValidEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    void eraseAlerts(){
        nameFieldAlertLabel.setText("");
    }

}


