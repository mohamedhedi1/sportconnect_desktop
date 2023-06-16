/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sportconnectnutritionmanagement;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class FrontController implements Initializable {

    @FXML
    private Button admin;
    @FXML
    private AnchorPane reservation_form;
    @FXML
    private Button btnEvent;
    @FXML
    private AnchorPane add_reservation_form;
    @FXML
    private DatePicker dpdate;
    @FXML
    private ComboBox<?> cbmatch;
    @FXML
    private ComboBox<?> cbuser;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField tfnbrbillet;
    @FXML
    private Button btnretour;
    @FXML
    private AnchorPane reservation_details;
    @FXML
    private ListView<?> reservationList;
    @FXML
    private Button btnupdate;
    @FXML
    private AnchorPane update_reservation_form;
    @FXML
    private Button btnretour1;
    @FXML
    private TextField tfnbrbillet1;
    @FXML
    private Button btnmodifier;
    @FXML
    private ComboBox<?> cbuser1;
    @FXML
    private ComboBox<?> cbmatch1;
    @FXML
    private DatePicker dpdate1;
    @FXML
    private TextField tfid;
    @FXML
    private AnchorPane classement_form;
    @FXML
    private TableView<?> classement_table;
    @FXML
    private TableColumn<?, ?> rangCol;
    @FXML
    private TableColumn<?, ?> equipeCol;
    @FXML
    private TableColumn<?, ?> mjCol;
    @FXML
    private TableColumn<?, ?> victoireCol;
    @FXML
    private TableColumn<?, ?> nulCol;
    @FXML
    private TableColumn<?, ?> defaiteCol;
    @FXML
    private TableColumn<?, ?> bmCol;
    @FXML
    private TableColumn<?, ?> beCol;
    @FXML
    private TableColumn<?, ?> dbCol;
    @FXML
    private TableColumn<?, ?> pointsCol;
    @FXML
    private void evenement(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("Participer.fxml"));
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
    private void Blog(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("BlogClient.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void coach(ActionEvent event) {
    }
    
}
