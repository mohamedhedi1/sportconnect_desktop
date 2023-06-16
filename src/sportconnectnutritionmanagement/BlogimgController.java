/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectnutritionmanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import entities.Blog;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import services.Session;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author manar
 */
public class BlogimgController implements Initializable {

    @FXML
    private TableView<Blog> tabimg;
    @FXML
    private TableColumn<Blog, String> image;
     @FXML
    private TableColumn<Blog, String> tftitre;
      @FXML
    private TableColumn<Blog, String> auteur;
     @FXML
    private TableColumn<Blog, String> description;
     
      @FXML
         private TableColumn<Blog, String> date;
  
    @FXML
    private Button deteil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                tftitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
                auteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
         date.setCellValueFactory(new PropertyValueFactory<>("date"));

        image.setCellFactory(column ->{
            return new TableCell<Blog,String>(){
                final ImageView imageView = new ImageView();
                    {
                        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                        setGraphic(imageView);
                    }
                protected void updateItem(String imageName, boolean empty) {
                    super.updateItem(imageName, empty);
                    if (imageName == null || empty) {
                        imageView.setImage(null);
                    }else{
                        try {
                            FileInputStream stream = new FileInputStream("C:\\xampp\\htdocs\\imgSportConnect\\" + imageName);
                            Image image = new Image(stream);
                            imageView.setImage(image);
                            imageView.setFitWidth(50); // Set the desired width and height here
                            imageView.setFitHeight(50);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        
                    }
                }    
            };
        }
        );
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        List<Blog> blogs = displayEntities();
tabimg.setItems(FXCollections.observableArrayList(blogs));
    }    
    
    
    
    
     public  List<Blog> displayEntities() {
       List<Blog> blogs = new ArrayList<>();
        tabimg.setItems(FXCollections.observableArrayList(blogs));  
        try {
        String requete = "SELECT * FROM blog";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Blog blog = new Blog();
            blog.setTitre(rs.getString("titre"));
           ;
            blog.setImage(rs.getString("image"));
             blog.setAuteur(rs.getString("auteur"));
            blog.setDescription(rs.getString("description"));
            blog.setDate(rs.getDate("date"));
             
            blogs.add(blog);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
            return blogs;
    }
     @FXML
void handleDetailButtonAction(ActionEvent event) throws IOException {
    // Récupération de l'objet Blog sélectionné dans la liste view
    Blog selectedBlog = tabimg.getSelectionModel().getSelectedItem();
    
    // Création d'une nouvelle fenêtre pour afficher les détails du blog
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Blogfront.fxml"));
    Parent root = loader.load();
    BlogfrontController detailBlogController = loader.getController();

    // Chargement des données du blog dans le contrôleur de vue des détails
    detailBlogController.setBlog(selectedBlog);

    // Affichage de la nouvelle fenêtre
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
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
    private void coach(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("EquipementFront.fxml"));
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

     

    

