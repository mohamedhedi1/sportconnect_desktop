/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sportconnectnutritionmanagement;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.type.PhoneNumber;
import entities.client;
import entities.utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.Front;
import services.User;
import static sportconnectnutritionmanagement.InscrireController.calculerAge;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class InscriptionClientController implements Initializable {

    @FXML
    private AnchorPane su_signupForm;
    @FXML
    private TextField tfNom;
    @FXML
    private PasswordField tfMdp;
    @FXML
    private TextField tfPrenom;
    @FXML
    private Button su_signupBtn;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfUsername;
    @FXML
    private AnchorPane side_form;
    @FXML
    private Button side_alreadyHave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    private void inscriptionC(ActionEvent event) {
     String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfMdp.getText();
        String tel = tfTel.getText();
        
        
        User u = new User();
        if (u.verifierSaisie(nom, prenom, username, email, password,tel )) {
           
            utilisateur c = new utilisateur(nom, prenom, username, email, password,tel);
            u.inscriptionC(nom, prenom, username, email, password, tel);

            
            try {
            Twilio.init("ACa073b7c0dcf7051fc3cfa35c390d4c31", "836e7eb87c8ad0313f4b863e32d98d99"); 
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
                new PhoneNumber(tel),
                new PhoneNumber("+1 620 349 6558"), // Numéro de téléphone Twilio
                "Bienvenue " + prenom + nom + "!\n Votre nom d'utilisateur est " + username + "et Votre mot de passe est " + password + "\n sportconnect.")
                .create();
        System.out.println(message.getSid());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message envoyé");
        alert.setContentText("Le message a été envoyé avec succès !");
        alert.showAndWait();
    } catch (ApiException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur d'envoi");
        alert.setContentText("Une erreur est survenue lors de l'envoi du message : " + e.getMessage());
        alert.showAndWait();
    }

        
            
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginClient.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText(u.getMessageErreur()); // affichage du message d'erreur
            alert.showAndWait();
        }
    }
    
    @FXML
    private void loginC(ActionEvent event) {
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
