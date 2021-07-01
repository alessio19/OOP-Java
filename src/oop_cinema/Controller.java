/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop_cinema;

import Controller.DataAccessModule.DBConnection;
import javafx.scene.Scene;

/**
 *
 * @author Alessio
 */
public abstract class Controller {
    
    protected final DBConnection connection;

    public Controller(String username, String password) {
        this.connection = new DBConnection("root", "root");
    }
    
}
