package Controller;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * @author Alessio
 * @author Adam
 * details: Class created to facilitate the changement between different screen
 * Also allow to keep previous view in memory
 */
public class ScreenController {
    
    private final HashMap<String, Pane> screens = new HashMap<>();
    private Scene mainScene;
    
    /**
     * Constructor
     */
    public ScreenController() {
    }
    
    /**
     * Constructor
     * @param s
     */
    public ScreenController(Scene s) {
        this.mainScene = s;
    }
    
    /**
     * Find out if a screen view already exist in the HashMap
     * @param name
     * @return boolean: result
     */
    protected boolean alreadyExist(String name) {
        return this.screens.keySet().contains(name);
    }
    
    /**
     * Add a new screen view in the HashMap
     * @param name
     * @param pane
     */
    protected void addScreen(String name, Pane pane) {
        this.screens.put(name, pane);
    }
    
    /**
     * Remove a screen view in the HashMap
     * @param name
     */
    protected void removeScreen(String name) {
        this.screens.remove(name);
    }
    
    /**
     * Activate a screen view and put in frontground 
     * @param name
     */
    protected void activate(String name) {
        this.mainScene.setRoot(this.screens.get(name));
    }
    
    /**
     * getter
     * @return Scene: main scene
     */
    public Scene getScene() {
        return this.mainScene;
    }
    
    /**
     * getter
     * @param name
     * @return Pane: pane choosen
     */
    public Pane getPane(String name) {
        return this.screens.get(name);
    }
    
}
