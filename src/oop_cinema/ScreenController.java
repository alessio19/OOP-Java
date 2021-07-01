/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_cinema;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Alessio
 */
public class ScreenController {
    
    private final HashMap<String, Pane> screens = new HashMap<>();
    private Scene mainScene;
    
    public ScreenController() {
    }
    
    public ScreenController(Scene s) {
        this.mainScene = s;
    }
    
    protected void addScreen(String name, Pane pane) {
        this.screens.put(name, pane);
        this.activate(name);
    }
    
    protected void removeScreen(String name) {
        this.screens.remove(name);
    }
    
    protected void activate(String name) {
        this.mainScene.setRoot(this.screens.get(name));
    }
    
}
