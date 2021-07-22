package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Alessio
 * @author Adam
 * details: main class to our project
 */
public class OOP_Cinema extends Application {
    
    private static ScreenController controller;
    
    /**
     * Start method
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml"));       
        Scene scene = new Scene(root);                
        stage.setScene(scene);
        controller = new ScreenController(scene);
        controller.addScreen("login", (Pane) root);
        LoginController lc = new LoginController();
        stage.show();
    }   
    
    /**
     * Add scene to the screen controller
     * @param sceneName
     * @param parent
     */
    public static void addScene(String sceneName, Pane parent) {
        controller.addScreen(sceneName, parent);
    }
    
    /**
     * Find out if the scene already exist
     * @param name
     * @return boolean: existing
     */
    public static boolean alreadyExist(String name) {
        return controller.alreadyExist(name);
    }
    
    /**
     * Change the main screen
     * @param sceneName
     */
    public static void changeScene(String sceneName) {
        controller.activate(sceneName);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Retrieve scene
     * @return Scene: scene
     */
    public static Scene getScene() {
        return controller.getScene();
    }
    
    /**
     * Retrieve pane by its name
     * @param name
     * @return Pane: pane
     */
    public static Pane getPane(String name) {
        return controller.getPane(name);
    }
    
    /**
     * Remove a screen by its name
     * @param name
     */
    public static void removeScreen(String name) {
        controller.removeScreen(name);
    }
    
}
