package jala.university.todo_app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {


    @FXML
    private Button allTaskButton;

    @FXML
    private Button categoriesButton;


    @FXML
    private Button newTaskButton;

    @FXML
    private VBox newTaskContainer;


    @FXML
    private BorderPane dashboard;

    @FXML
    private Pane sidebar;

    @FXML
    private Button todayTaskButton;

    @FXML
    void allTask(MouseEvent event) throws IOException {
        loadPage("/jala/university/todo_app/allTask-view.fxml");
    }

    @FXML
    void categories(MouseEvent event) {
        loadPage("/jala/university/todo_app/categories.fxml");
    }

    @FXML
    public void completedTask(MouseEvent mouseEvent) {
        loadPage("/jala/university/todo_app/completedTask-view.fxml");

    }


    @FXML
    void createNewTask(MouseEvent mouseEvent) throws IOException {

        loadPage("/jala/university/todo_app/newTask-view.fxml");

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
    @FXML
    public void changeUserImg(MouseEvent mouseEvent) {
    }


}
