module jala.university.todo_app {
    requires javafx.controls;
    requires javafx.fxml;


    opens jala.university.todo_app to javafx.fxml;
    exports jala.university.todo_app;
}