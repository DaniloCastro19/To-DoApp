package jala.university.todo_app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button allTaskButton;

    @FXML
    private Button categoriesButton;

    @FXML
    private BorderPane dashboard;

    @FXML
    private Pane sidebar;

    @FXML
    private Button todayTaskButton;

    @FXML
    void allTask(MouseEvent event) {
        loadPage("/jala/university/todo_app/allTask-view.fxml");
    }

    @FXML
    void categories(MouseEvent event) {

    }

    @FXML
    void todayTask(MouseEvent event) {

    }

    private void loadPage(String page){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(page));
            dashboard.setCenter(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(page);

    }

    public void changeUserImg(MouseEvent mouseEvent) {
    }
}
