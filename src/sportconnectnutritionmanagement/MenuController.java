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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.Session;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class MenuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane AP;
    @FXML
    private AnchorPane main_form;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPage("AfficherEquipement");
    }    
    
     private void loadPage(String page){          
        Parent root = null;
        try {
        root = FXMLLoader.load(getClass().getResource(page+".fxml"));

        } catch (Exception ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE,null,ex);
        }
        bp.setCenter(root);
    }

    @FXML
    private void AjouterEq(ActionEvent event) {
        loadPage("AjouterEquipement");
    }

    @FXML
    private void AjouterEx(ActionEvent event) {
        loadPage("AjouterExercice");
    }

    @FXML
    private void AffEquip(ActionEvent event) {
        loadPage("AfficherEquipement");
    }

    @FXML
    private void AffSerie(ActionEvent event) {
        loadPage("AfficherSerie");
    }

    @FXML
    private void AffEx(ActionEvent event) {
        loadPage("AfficherExercice");
    }

    @FXML
    private void AjouterSerie(ActionEvent event) {
        loadPage("AjouterSerie");
    }

    @FXML
    private void AffEqFront(ActionEvent event) {
        loadPage("EquipementFront");
    }

    @FXML
    private void AffExFront(ActionEvent event) {
        loadPage("ExerciceFront");
    }

    @FXML
    private void AffSerieFront(ActionEvent event) {
        loadPage("SerieFront");
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
}
