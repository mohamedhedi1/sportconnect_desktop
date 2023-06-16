/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectnutritionmanagement;


import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.type.PhoneNumber;
import entities.client;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.mail.Message;
import services.Front;
import services.User;



/**
 * FXML Controller class
 *
 * @author PC
 */
public class InscrireController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMdp;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfUsername;
    @FXML
    private Button parcourirPhoto;
    @FXML
    private DatePicker dpDateNaissance;
    @FXML
    private TextField tfPhoto;
    @FXML
    private ImageView image;
    @FXML
    private TextField tfAge;
    @FXML
    private AnchorPane su_signupForm;
    @FXML
    private Button su_signupBtn;
    @FXML
    private AnchorPane side_form;
    @FXML
    private Button side_alreadyHave;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpDateNaissance.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int age = calculerAge(newValue);
                tfAge.setText(Integer.toString(age));
            }
        });
        
    }    
    
    public void setTextAge(String message) {
        this.tfAge.setText(message);
    }

    public static int calculerAge(LocalDate dateNaissance) {
    LocalDate aujourdHui = LocalDate.now();
    int age = aujourdHui.getYear() - dateNaissance.getYear();
    if (aujourdHui.getMonthValue() < dateNaissance.getMonthValue()) {
        age--;
    } else if (aujourdHui.getMonthValue() == dateNaissance.getMonthValue() 
               && aujourdHui.getDayOfMonth() < dateNaissance.getDayOfMonth()) {
        age--;
    }
    return age;
}

    

    @FXML
    private void inscription(ActionEvent event) {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfMdp.getText();
        String tel = tfTel.getText();
        LocalDate date_naissance = dpDateNaissance.getValue();
       // File photoFile = tfPhoto.getText().isEmpty() ? null : new File(tfPhoto.getText());
        File photoFile = tfPhoto.getText().isEmpty() ? null : new File(tfPhoto.getText());
            if (photoFile != null && photoFile.exists()) {
                Path sourcePath = photoFile.toPath();
                Path destinationPath = Paths.get("C:\\Users\\aymen\\OneDrive\\Documents\\NetBeansProjects\\sportConnectNutritionManagement\\src\\images" + photoFile.getName());
                try {
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        
        //int age = calculerAge(dateNaissance);
        setTextAge(""+calculerAge(date_naissance)); // calculer l'âge
        //setTextAge(Integer.toString(age)); // afficher l'âge dans le champ texte
        
        Front f = new Front();
        if (f.verifierSaisie2(nom, prenom, username, email, password ,date_naissance,photoFile)) {
           
            client c = new client(nom, prenom, username, password, email, tel, photoFile, date_naissance);
            f.inscription(nom, prenom, username, email, password, tel, date_naissance, photoFile);

            annulerS();
            try {
            Twilio.init("ACa073b7c0dcf7051fc3cfa35c390d4c31", "836e7eb87c8ad0313f4b863e32d98d99"); 
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
                new PhoneNumber(tel),
                new PhoneNumber("+1 620 349 6558"), // Numéro de téléphone Twilio
                "Bienvenue " + prenom + nom + "!\n Votre nom d'utilisateur est " + username + "et Votre mot de passe est " + password + "\n SportConnect.")
                .create();
        System.out.println(message.getSid());
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Message envoyé");
        alert.setContentText("Le message a été envoyé avec succès !");
        alert.showAndWait();
    } catch (ApiException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur d'envoi");
        alert.setContentText("Une erreur est survenue lors de l'envoi du message : " + e.getMessage());
        alert.showAndWait();
    }

        
            
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginF.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie");
            alert.setContentText(f.getMessageErreur()); // affichage du message d'erreur
            alert.showAndWait();
        }
    }

        @FXML
    private void parcourirPhoto(ActionEvent event) {
    
         FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une photo");
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Fichiers images", "*.png", "*.jpg", "*.gif"),
        new ExtensionFilter("Tous les fichiers", "*.*")
    );
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        try {
            javafx.scene.image.Image img = new javafx.scene.image.Image(selectedFile.toURI().toURL().toString());
            image.setImage(img);
            tfPhoto.setText(selectedFile.getAbsolutePath());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    }

    private void annuler(ActionEvent event) {
        annulerS();
    }

    public void annulerS()
    {
        tfEmail.clear();
        tfMdp.clear();
        tfPhoto.clear();
        tfTel.clear();
        dpDateNaissance.setValue(null);
        tfUsername.clear();
        tfPrenom.clear();
        tfNom.clear();
        tfPhoto.clear();
        tfAge.clear();
    }
    @FXML
    private void loginF(ActionEvent event) {
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
