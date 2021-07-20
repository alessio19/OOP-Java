package Controller;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * @author Alessio
 * @author Adam
 */
public class ScreenController {
    
    private final HashMap<String, Pane> screens = new HashMap<>();
    private Scene mainScene;
    
    public ScreenController() {
    }
    
    public ScreenController(Scene s) {
        this.mainScene = s;
    }
    
    protected boolean alreadyExist(String name) {
        return this.screens.keySet().contains(name);
    }
    
    protected void addScreen(String name, Pane pane) {
        this.screens.put(name, pane);
    }
    
    protected void removeScreen(String name) {
        this.screens.remove(name);
    }
    
    protected void activate(String name) {
        this.mainScene.setRoot(this.screens.get(name));
    }
    
    public Scene getScene() {
        return this.mainScene;
    }
    
    public Pane getPane(String name) {
        return this.screens.get(name);
    }
    
}
