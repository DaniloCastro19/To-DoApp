package jala.university.todo_app.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DashboardControllerTest {

    @Test
    void loadPage(String page) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(page+"fxml"));
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}