package jala.university.todo_app.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class RegisterController {

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
    void onRegisterButtonClick(ActionEvent event) {

    }

}
