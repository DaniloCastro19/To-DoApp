package jala.university.todo_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Register extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Register.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Los Makiavélicos");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
