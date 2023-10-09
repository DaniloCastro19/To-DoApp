module jala.university.todo_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires jasypt;
    requires jbcrypt;


    opens jala.university.todo_app to javafx.fxml;
    exports jala.university.todo_app;
    exports jala.university.todo_app.controllers;
    opens jala.university.todo_app.controllers to javafx.fxml;
}