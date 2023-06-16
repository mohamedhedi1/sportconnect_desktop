/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectnutritionmanagement;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Session;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class BackController implements Initializable {
    @FXML
    private AnchorPane main_form;
    @FXML
    private Button btnNutrition;
    @FXML
    private Button btnUser;
    @FXML
    private Button btnCoaching;
    @FXML
    private Button btnEvent;
    
    @FXML
    private Button btnBlog;
    @FXML
    private TextField match_search;
    @FXML
    private Button idreturn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        // TODO
    }    


    @FXML
    private void evenement(ActionEvent event) throws IOException {
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource("EvenComposant.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void OnReturn(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }


@FXML
    private void logout(ActionEvent event) {
        try {
            Session.getInstance().logout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginF.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            
            
            ((Node)(event.getSource())).getScene().getWindow().hide();      // Fermer la fenêtre actuelle
        } catch (IOException ex) {
            Logger.getLogger(SportConnectNutritionManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
