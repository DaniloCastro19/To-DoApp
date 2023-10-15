package jala.university.todo_app;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

public class DatabaseConnection {
  private static MongoClient mongoClient;
  private static MongoDatabase database;
  private static MongoCollection<Document> collectionTareas;
  private static MongoCollection<Document>collectionUsuarios;
  private static Document currentTask;
  private static ObjectId userId = new ObjectId("6524a67c727101572516340f");

   static {
     mongoClient = MongoClients.create(
         "mongodb+srv://losmakias:losmakias1@cluster0.m1zizil.mongodb.net/?retryWrites=true&w=majority");
     database = mongoClient.getDatabase("ToDoApp");
     collectionTareas = database.getCollection("Tareas");
     collectionUsuarios = database.getCollection("Usuarios");
   }


  public static void createTask(String taskTitle, String taskDescription, String category,
      String priority, ObjectId userId) {
    LocalDateTime fechaHoraActual = LocalDateTime.now();
    Document tarea = new Document("nombre", taskTitle)
        .append("descripcion", taskDescription)
        .append("categoria", category.toUpperCase())
        .append("prioridad", priority)
        .append("FechaCreacion", fechaHoraActual)
        .append("usuario", userId)
        .append("completada", false);
    collectionTareas.insertOne(tarea);
  }

  public static void updateTask(String taskId, String newTaskTitle, String newTaskDescription, boolean isCompleted) {
    Document query = new Document("_id", new ObjectId(taskId));
    Bson updates = Updates.combine(
        Updates.set("nombre", newTaskTitle),
        Updates.set("descripcion", newTaskDescription),
        Updates.set("completada", isCompleted)
    );
    UpdateOptions options = new UpdateOptions().upsert(true);
    collectionTareas.updateOne(query, updates, options);
  }

  public static void deleteTask(String taskId) {
    Document query = new Document("_id", new ObjectId(taskId));
    collectionTareas.deleteOne(query);
  }

  public static boolean createUser(String userName, String userEmail, String password) {
    try {
      String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
      Document newUsuario = new Document("nombre", userName)
          .append("email", userEmail)
          .append("password", hashedPassword);
      collectionUsuarios.insertOne(newUsuario);
      return true;
    }catch (Exception e){
      return false;
    }
  }

  public static boolean login(String emailField, String passwordField) {
    try {
      mongoClient = MongoClients.create(
          "mongodb+srv://losmakias:losmakias1@cluster0.m1zizil.mongodb.net/?retryWrites=true&w=majority");
      database = mongoClient.getDatabase("ToDoApp");
      MongoCollection<Document> collection = database.getCollection("Usuarios");
      if (!validateEmail(emailField) && !checkEmail(emailField)) {
        return false;
      }
      Document existingUser = collection.find(new Document("email", emailField)).first();

      if (existingUser != null && BCrypt.checkpw(passwordField,
          existingUser.getString("password"))) {
        System.out.println(
            "Usuario " + existingUser.getString("nombre") + " inicio sesion " + LocalDate.now()
        );
        userId = existingUser.getObjectId("_id");
        return true;

      } else {
        System.out.println("Usuario y/o contraseña incorrectos.");
        return false;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

  }

   private static boolean checkEmail(String email) {
    MongoCollection<Document> collection = database.getCollection("Usuarios");
    FindIterable<Document> iterable = collection.find();

    for (Document document : iterable) {
      if (document.containsValue(email)) {
        return true;
      }
    }
    return false;
  }


  private static boolean validateEmail(String email) {
    String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public static Document getCurrentTask() {
    return currentTask;
  }

  public static void setCurrentTask(Document currentTask) {
    DatabaseConnection.currentTask = currentTask;
  }

  public static ObjectId getUserId() {
    return userId;
  }

  public static void setUserId(ObjectId userId) {
    DatabaseConnection.userId = userId;
  }
}

