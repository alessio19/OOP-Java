/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.customer.Customer;
import Model.customer.CustomerDAO;
import Model.payment.Order;
import Model.payment.OrderDAO;
import Model.product.MovieGenre;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;

/**
 * FXML Controller class
 *
 * @author Alessio
 */
public class ProfileController {
    
    private boolean updated;
    
    private Customer customer;
    
    @FXML
    private Circle profilePicture;

    @FXML
    private Label mainNameLabel;
    
    @FXML
    private TextField mailAdressField;

    @FXML
    private TextField pwdField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastNameField;
    
    @FXML
    private Label editButtonLabel;
    
    @FXML
    private PieChart profileChart;
    
    @FXML
    public void initialize() {
        this.disableInputs(true);
        this.mainNameLabel.setAlignment(Pos.CENTER);
        this.updated = false;
    }    
    
    @FXML
    void editHandle(MouseEvent event) {        
        if (this.mailAdressField.disableProperty().get()) {
            this.disableInputs(false);
            this.editButtonLabel.setText("Apply");
        } else {
            this.updated = true;
            this.editButtonLabel.setText("Edit");
            this.customer.setMail(this.mailAdressField.getText());
            this.customer.setPassword(this.pwdField.getText());
            this.customer.setName(this.nameField.getText());
            this.customer.setLastName(this.lastNameField.getText());            
            new CustomerDAO().updateCustomer(this.customer);
            this.setCustomer(customer);
            this.disableInputs(true);
        }
    }
    
    private void disableInputs(boolean state) {
        this.mailAdressField.setDisable(state);
        this.pwdField.setDisable(state);
        this.nameField.setDisable(state);
        this.lastNameField.setDisable(state);  
    }

    @FXML
    void exportHandle(MouseEvent event) throws IOException {
        SnapshotParameters param = new SnapshotParameters();
        param.setFill(Color.TRANSPARENT);
        Image img = this.profileChart.snapshot(
                param, 
                new WritableImage(
                        (int) this.profileChart.getWidth(), (int) this.profileChart.getHeight()
                )
        );        
        File file = new File(FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + "\\chart.png");
        ImageIO.write(SwingFXUtils.fromFXImage(img, null), "png", file);        
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.mainNameLabel.setText(this.customer.getName() + " " + this.customer.getLastName());
        if (this.customer.getProfilePicture().isEmpty())
            this.profilePicture.setFill(new ImagePattern(new Image("/Resources/images/profile.png")));
        else
            this.profilePicture.setFill(new ImagePattern(new Image(this.customer.getProfilePicture())));
        this.mailAdressField.setText(this.customer.getMail());
        this.pwdField.setText(this.customer.getPwd());
        this.nameField.setText(this.customer.getName());
        this.lastNameField.setText(this.customer.getLastName());
        this.initializeChart();
    }
    
    private void initializeChart() {
        ArrayList<Order> orders = new OrderDAO().getOrdersForUsrId(this.customer.getId());        
        ArrayList<PieChart.Data> list = new ArrayList<>();
        HashMap<MovieGenre, Integer> genres = new HashMap<>();
        for(MovieGenre mg : MovieGenre.values())
            genres.put(mg, 0);
        orders.forEach(order -> {
            genres.put(order.getProduct().getMovie().getGenre(), genres.get(order.getProduct().getMovie().getGenre())+1);
        });        
        genres.keySet().forEach(mg -> {
            if (genres.get(mg) > 0)
                list.add(new PieChart.Data(mg.name(), genres.get(mg)));
        });
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(list);        
        this.profileChart.setData(data);   
        
    }
    
    @FXML
    void backHandle(MouseEvent event) throws IOException {
        if(this.updated) {
            OOP_Cinema.removeScreen("mainMenuCusto");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainMenuCustomer.fxml"));       
            OOP_Cinema.addScene("mainMenuCusto", loader.load());
            MainMenuCustomerController controller = loader.getController();
            controller.setCustomer(customer);                  
        }
        OOP_Cinema.changeScene("mainMenuCusto");
    }
    
    @FXML
    void logoutHandler(MouseEvent event) {
        OOP_Cinema.changeScene("login");
    }
    
}
