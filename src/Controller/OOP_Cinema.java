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
 */
public class OOP_Cinema extends Application {
    
    private static ScreenController controller;
    
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
    
    public static void addScene(String sceneName, Pane parent) {
        controller.addScreen(sceneName, parent);
    }
    
    public static boolean alreadyExist(String name) {
        return controller.alreadyExist(name);
    }
    
    public static void changeScene(String sceneName) {
        controller.activate(sceneName);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Scene getScene() {
        return controller.getScene();
    }
    
}
