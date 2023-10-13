package jala.university.todo_app;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import java.time.LocalDateTime;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.mindrot.jbcrypt.BCrypt;

public class DatabaseConnection {
  private static MongoClient mongoClient;
  private static MongoDatabase database;
  private static MongoCollection<Document> collectionTareas;
  private static MongoCollection<Document>collectionUsuarios;

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
        .append("Fecha de creación", fechaHoraActual)
        .append("usuario", userId)
        .append("completada", false);
    collectionTareas.insertOne(tarea);
  }

  public static void updateTask(String taskId, String newTaskTitle, String newTaskDescription) {
    Document query = new Document("_id", new ObjectId(taskId));
    Bson updates = Updates.combine(
        Updates.set("nombre", newTaskTitle),
        Updates.set("descripcion", newTaskDescription)
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
}

