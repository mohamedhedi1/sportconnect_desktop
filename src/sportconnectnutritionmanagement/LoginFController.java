/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectnutritionmanagement;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Session;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class LoginFController implements Initializable {

    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtmdp;
    @FXML
    private Hyperlink mdp_oublie;
    @FXML
    private AnchorPane si_loginForm;
    @FXML
    private AnchorPane side_form;
    @FXML
    private Button side_alreadyHave;
    @FXML
    private Button se_connecter;
    @FXML
    private Button inscription;
     private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private Alert alert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         mdp_oublie.setOnMouseClicked(event -> {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mdpOublie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
    } catch (IOException e) {
        e.printStackTrace();
    }
});
       
    }    
    
     public void loginBtn() {
        
        if (txtemail.getText().isEmpty() || txtmdp.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Email/Password");
            alert.showAndWait();
        } else {
            
            String selctData = "SELECT email, password FROM client WHERE email = ? and password = ?";
            
            connect = dataBase.connectDb();
            
            try {
                
                prepare = connect.prepareStatement(selctData);
                prepare.setString(1, txtemail.getText());
                prepare.setString(2, txtmdp.getText());
                
                result = prepare.executeQuery();
                // IF SUCCESSFULLY LOGIN, THEN PROCEED TO ANOTHER FORM WHICH IS OUR MAIN FORM 
                if (result.next()) {
                    // TO GET THE USERNAME THAT USER USED
                    getData.email = txtemail.getText();
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    
                    // LINK YOUR MAIN FORM
                    Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                    
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    stage.setTitle("Coaching Online Management System");
                    stage.setMinWidth(1100);
                    stage.setMinHeight(600);
                    
                    stage.setScene(scene);
                    stage.show();
                    
                    se_connecter.getScene().getWindow().hide();
                    
                } else { // IF NOT, THEN THE ERROR MESSAGE WILL APPEAR
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                } 
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }

    public void annuler()
    {
    txtemail.clear();
    txtmdp.clear();
    }

    @FXML
    private void inscription(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inscrire.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    @FXML
    private void clientForm(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginClient.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
    
   




}
