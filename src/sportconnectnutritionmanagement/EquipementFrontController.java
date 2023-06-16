/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectnutritionmanagement;

import entities.Equipement;
import java.awt.Insets;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.EquipementCRUD;
import services.Session;

/**
 * FXML Controller class
 *
 * @author Mohamed
 */
public class EquipementFrontController implements Initializable {

    @FXML
    private ListView<Equipement> listEquipementF;

    private ObservableList<Equipement> equipements;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        EquipementCRUD eq = new EquipementCRUD();
        try {
            equipements = eq.afficher();
        } catch (SQLException ex) {
            Logger.getLogger(EquipementFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

        listEquipementF.setItems(equipements);
        listEquipementF.setCellFactory(equipementListView -> new EquipementListCell());
    }

    private class EquipementListCell extends ListCell<Equipement> {

        private ImageView imageView = new ImageView();
        private Text nomEquipementText = new Text();

        @Override
        protected void updateItem(Equipement equipement, boolean empty) {
            super.updateItem(equipement, empty);

            if (empty || equipement == null) {
                setGraphic(null);
                setText(null);
            } else {

                // Création d'une instance de l'ImageView et configuration de la taille de l'image
                ImageView imageView = new ImageView(new Image("file:/" + equipement.getImageEquipement()));
                imageView.setFitWidth(150);
                imageView.setFitHeight(150);

// Création du texte
                Text nomEquipementText = new Text(equipement.getNomEquipement());

// Création de la VBox et ajout de l'image et du texte
                VBox vbox = new VBox(imageView, nomEquipementText);
                vbox.setAlignment(Pos.CENTER);
                vbox.setSpacing(10);

// Affichage de la VBox
                setGraphic(vbox);
                setText(null);

            }
        }
    }
    
    @FXML
    private void coach(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("EquipementFront.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    @FXML
    private void Event(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    @FXML
    private void Blog(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("BlogClient.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    @FXML
    private void nutrition(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("UserView.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    @FXML
    private void ExerciceFront(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("ExerciceFront.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    @FXML
    private void SerieFront(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("SerieFront.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
    
    @FXML
    private void logout(ActionEvent event) {
        try {
            Session.getInstance().logout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginClient.fxml"));
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
