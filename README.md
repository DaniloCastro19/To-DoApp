# ## To do list
### Descripción del programa
Es un programa de gestión de tareas que permite a los usuarios crear, editar, eliminar y ver tareas, las tareas se almacenan en una base de datos y se pueden acceder a través de una interfaz gráfica intuitiva.
### Funcionalidades principales
#### Crear tarea
Permite a los usuarios crear nuevas tareas proporcionando un titulo, descripción categoría y prioridad.
#### Ver tareas
Los usuarios pueden ver todas las tareas creadas en una lista.
#### Editar tareas
Los usuarios pueden editar las tareas existentes ya sea el título, la descripción, la categoría o la prioridad.
#### Eliminar tareas
Los usuarios pueden eliminar tareas que ya no son necesarias.
#### Crear usuario
Permite crear un usuario proporcionando un correo, un nombre y una contraseña.
#### Loguearse con un usuario
Permite ingresar al programa con una cuenta en específico.
### Configuración del entorno de desarrollo
Para configurar el entorno debemos usar:
Requisitos Previos
- Java 17
  [Guia para la instalación en Ubuntu](https://www.youtube.com/watch?v=rJn6sH_tRGY "Guía para Ubuntu")
  [Guia para la instalación en Windows](https://www.youtube.com/watch?v=fe1_KQOWJxM "Guia para Windows")
- Un entorno de desarrollo como sugerencia Intellij IDE
  [Guia para su instalación en Ubuntu ](https://www.youtube.com/watch?v=7rQNf0m_hfI "guia para su instalación")
  [Guia de instalación para Windows](https://www.youtube.com/watch?app=desktop&v=ugGNy9RmX_M "Guia de instalación para Windows")
- Instalar JavaFX y Scene Builder
  [Guia para instalar JavaFX en Ubuntu](https://dam.org.es/javafx-con-ide-idea/ "Guía para instalar en Ubuntu")
  [Guia para instalar Scene Builder en Ubuntu](https://www.youtube.com/watch?v=qi6lYqZ6Rew "Guia para instalar Scene Builder en Ubuntu")
  [Guia para instalar JavFX y Scene Builder en Windows](https://www.youtube.com/watch?v=lb1F1R4T__U "Guia para instalar en Windows")
- Instalar maven
  Aquí tenemos una guía para la [instalación en Ubuntu, Windows](https://www.arteco-consulting.com/post/instalacion-de-maven "instalacion en Ubuntu, Windows y Mac OSX")

### Despliegue del proyecto
Para ello debemos correrlo en la clase de ToDoApp
![Inicio del programa.png](Inicio%20del%20programa.png)
### Base de datos
Como trabajamos con MongoDB Atlas debemos crear una base de datos en la nube
Tomando en cuenta este modelo:
![diagrama noSQL.jpeg](diagrama%20noSQL.jpeg)
[Guia para crear una base de datos en la nube con Mongo](https://www.youtube.com/watch?v=pGAa-2mJuMo&t=159s "Guia ra crear una base de datos en la nube con Mongo")

### Dependencias y librerias
Debemos descargar la libreria [jBCrypt](https://jar-download.com/artifacts/de.svenkubiak/jBCrypt "jBCrypt") que la usamos para encriptar la contraseña del usuario, debemos extraer el .jar e implementarlo en “Project Structure”, luego Modules
![Insertar jar jBCrypt.png](Insertar%20jar%20jBCrypt.png)
Clic en el signo + y clic e jar y buscar la libreria.
