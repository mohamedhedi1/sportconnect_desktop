/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sportconnectnutritionmanagement;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class LoginClientController implements Initializable {

    @FXML
    private AnchorPane si_loginForm;
    @FXML
    private TextField txtemail;
    @FXML
    private PasswordField txtmdp;
    @FXML
    private Button se_connecter;
    @FXML
    private Hyperlink mdp_oublie;
    @FXML
    private AnchorPane side_form;
    @FXML
    private Button inscription;
    @FXML
    private Button side_alreadyHave;
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

    @FXML
    private void loginBtn(ActionEvent event)  throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
//         if (txtemail.getText().isEmpty() || txtmdp.getText().isEmpty()) {
//            alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Incorrect Email/Password");
//            alert.showAndWait();
//        } else {
//            
//            String selctData = "SELECT email, password FROM admin WHERE email = ? and password = ?";
//            
//            connect = dataBase.connectDb();
//            
//            try {
//                
//                prepare = connect.prepareStatement(selctData);
//                prepare.setString(1, txtemail.getText());
//                prepare.setString(2, txtmdp.getText());
//                
//                result = prepare.executeQuery();
//                // IF SUCCESSFULLY LOGIN, THEN PROCEED TO ANOTHER FORM WHICH IS OUR MAIN FORM 
//                if (result.next()) {
//                    // TO GET THE USERNAME THAT USER USED
//                    getData.email = txtemail.getText();
//                    
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Information Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Successfully Login!");
//                    alert.showAndWait();
//                    
//                    // LINK YOUR MAIN FORM
//                    Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
//                    
//                    Stage stage = new Stage();
//                    Scene scene = new Scene(root);
//                    
//                    stage.setTitle("Coaching Online Management System");
//                    stage.setMinWidth(1100);
//                    stage.setMinHeight(600);
//                    
//                    stage.setScene(scene);
//                    stage.show();
//                    
//                    se_connecter.getScene().getWindow().hide();
//                    
//                } else { // IF NOT, THEN THE ERROR MESSAGE WILL APPEAR
//                    alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("Incorrect Username/Password");
//                    alert.showAndWait();
//                } 
//                
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            
//        }
//    }

    @FXML
    private void inscription(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InscriptionClient.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
    
    @FXML
    private void adminForm(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginF.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
    
}
