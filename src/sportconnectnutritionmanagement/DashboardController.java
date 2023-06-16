/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sportconnectnutritionmanagement;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import entities.client;
import entities.utilisateur;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.Front;
import services.Session;
import services.User;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class DashboardController implements Initializable {

    @FXML
    private Button home_btn;
    @FXML
    private Button nut_btn;
    @FXML
    private Button addRepas_btn;
    @FXML
    private Button addIngredient_btn;
    @FXML
    private AnchorPane addRepas_form;
    @FXML
    private TableView<RepasData> addRepas_tableView;
    @FXML
    private TableColumn<RepasData, Integer> addRepas_col_id;
    @FXML
    private TableColumn<RepasData, String> addRepas_col_name;
    @FXML
    private TableColumn<RepasData, String> addRepas_col_description;
    @FXML
    private TableColumn<RepasData, String> addRepas_col_heure;
    @FXML
    private TableColumn<RepasData, String> addRepas_col_calories;
    @FXML
    private TableColumn<RepasData, Integer> addRepas_col_dayofweek;
    @FXML
    private TextField addRepas_search;
    @FXML
    private TextField addRepas_id;
    @FXML
    private TextField addRepas_name;
    @FXML
    private TextField addRepas_heure;
    @FXML
    private TextArea addRepas_description;
    @FXML
    private TextField addRepas_calories;
    @FXML
    private TextField addRepas_dayofweek;
    @FXML
    private AnchorPane addIngredient_form;
    @FXML
    private TextField addIngredient_search;
    @FXML
    private TextField addIngredient_id;
    @FXML
    private TextField addIngredient_name;
    @FXML
    private TextField addIngredient_quantite;
    @FXML
    private TextField addIngredient_calories;
    
    
    @FXML
    private TableView<IngredientData> addIngredient_tableView;
    @FXML
    private TableColumn<IngredientData, Integer> addIngredient_col_id;
    @FXML
    private TableColumn<IngredientData, String> addIngredient_col_name;
    @FXML
    private TableColumn<IngredientData, String> addIngredient_col_quantite;
    @FXML
    private TableColumn<IngredientData, String> addIngredient_col_calories;
    @FXML
    private ImageView addRepas_image;
    @FXML
    private AnchorPane main_form;
@FXML
    private AnchorPane nut_form;
    
    
    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    private Image image;
    @FXML
    private AnchorPane home_form;
    @FXML
    private WebView webView;
    @FXML
    private Label home_totalRepas;
    @FXML
    private Label home_totalIngredient;
    
    @FXML
    private Button imagebtn;
    @FXML
    private Button addRepas_addbtn;
    @FXML
    private Button addRepas_updatebtn;
    @FXML
    private Button addRepas_deletebtn;
    @FXML
    private Button addRepas_clearbtn;
    @FXML
    private Button addIngredient_addbtn;
    @FXML
    private Button addIngredient_updatebtn;
    @FXML
    private Button addIngredient_deletebtn;
    @FXML
    private Button addIngredient_clearbtn;
    @FXML
    private Button logout;
    @FXML
    private TableView<client> addClient_tableView;
    @FXML
    private TableColumn<client, Integer> tcIdClient;
    @FXML
    private TableColumn<client, String> tcNomClient;
    @FXML
    private TableColumn<client, String> tcPrenomClient;
    @FXML
    private TableColumn<client, String> tcUserClient;
    @FXML
    private TableColumn<client, String> tcEmailClient;
    @FXML
    private TableColumn<client, String> tcMdpClient;
    @FXML
    private TableColumn<client, Integer> tcTelClient;
    @FXML
    private TableColumn<client, LocalDate> tcDate;
    @FXML
    private TableColumn<client, File> tcPhoto;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfMdp;
    @FXML
    private ImageView PhotoClient;
    @FXML
    private Button addEmployee_importBtn;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button annuler;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfTel;
    @FXML
    private TextField tfDate;
    @FXML
    private Button pdf;
    @FXML
    private Button excel;
    @FXML
    private AnchorPane homeAdmin_form;
    @FXML
    private TableView<utilisateur> addAdmin_tableView;
    @FXML
    private TableColumn<utilisateur, Integer> tcIdAdmin;
    @FXML
    private TableColumn<utilisateur, String> tcNomAdmin;
    @FXML
    private TableColumn<utilisateur, String> tcPrenomAdmin;
    @FXML
    private TableColumn<utilisateur, String> tcUserAdmin;
    @FXML
    private TableColumn<utilisateur, String> tcEmailAdmin;
    @FXML
    private TableColumn<utilisateur, String> tcMdpAdmin;
    @FXML
    private TableColumn<utilisateur, Integer> tcTelAdmin;
    @FXML
    private TextField tfNomAdmin;
    @FXML
    private TextField tfPrenomAdmin;
    @FXML
    private TextField tfUserAdmin;
    @FXML
    private TextField tfMdpAdmin;
    @FXML
    private Button ajouterAdmin;
    @FXML
    private Button modifierAdmin;
    @FXML
    private Button supprimerAdmin;
    @FXML
    private Button annulerAdmin;
    @FXML
    private TextField tfEmailAdmin;
    @FXML
    private TextField tfTelAdmin;
    @FXML
    private Button pdfAdmin;
    @FXML
    private Button excelAdmin;
    @FXML
    private Button homeAdmin_btn;
    Connection cnx = dataBase.getInstance().getCnx();
    @FXML
    private Button event_btn2;
    @FXML
    private Button event_btn1;
    @FXML
    private Button coach_btn1;
    @FXML
    private Button coach_btn2;
    @FXML
    private Button blog_btn2;
    @FXML
    private Button blog_btn1;
    @FXML
    private AnchorPane AP;
    @FXML
    private BorderPane bp;
    
    
    
    
    public void homeTotalRepas() {

        String sql = "SELECT COUNT(id) FROM repas_entity";

        connect = dataBase.connectDb();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            home_totalRepas.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    public void homeTotalIngredient() {

        String sql = "SELECT COUNT(id) FROM ingredient_entity";

        connect = dataBase.connectDb();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            home_totalIngredient.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    

    //************** RepasForm ***************//
    
    @FXML
    public void addRepasAdd() {

        String sql = "INSERT INTO repas_entity"
                + "(id,name,description,heure,image,calories,day_of_week)"
                + "VALUES(?,?,?,?,?,?,?)";

        connect = dataBase.connectDb();

        try {
            Alert alert;
            if (addRepas_id.getText().isEmpty()
                    || addRepas_name.getText().isEmpty()
                    || addRepas_description.getText().isEmpty()
                    || addRepas_heure.getText().isEmpty()
                    || addRepas_calories.getText().isEmpty()
                    || addRepas_dayofweek.getText().isEmpty()
                    
                    || getData.path == null || getData.path == "") {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Blanc Fields");
                alert.showAndWait();

            } else {

                String check = "SELECT id FROM repas_entity WHERE id = '"
                        + addRepas_id.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Repas ID: " + addRepas_id.getText() + "Was Already exist");
                    alert.showAndWait();
                } else {
                    
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addRepas_id.getText());
                    prepare.setString(2, addRepas_name.getText());
                    prepare.setString(3, addRepas_description.getText());
                    prepare.setString(4, addRepas_heure.getText());
                    prepare.setString(6, addRepas_calories.getText());
                    prepare.setString(7, addRepas_dayofweek.getText());
                    
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    addRepasShowListData();
                    addRepasReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addRepasUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE repas_entity SET name = '"
                + addRepas_name.getText() + "', description = '"
                + addRepas_description.getText() + "', heure = '"
                + addRepas_heure.getText() + "', calories = '"
                + addRepas_calories.getText() + "', day_of_week = '"
                + addRepas_dayofweek.getText()+ "' WHERE id ='"
                + addRepas_id.getText() + "'";

        connect = dataBase.connectDb();

        try {
            Alert alert;
            if (addRepas_id.getText().isEmpty()
                    || addRepas_name.getText().isEmpty()
                    || addRepas_description.getText().isEmpty()
                    || addRepas_heure.getText().isEmpty()
                    || addRepas_calories.getText().isEmpty()
                    || addRepas_dayofweek.getText().isEmpty()
                    
                    || getData.path == null || getData.path == "") {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Blanc Fields");
                alert.showAndWait();

            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are You Sure You Want To UPDATE Repas ID :" + addRepas_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addRepasShowListData();
                    addRepasReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM repas_entity WHERE id = " + id;
            statement = connect.createStatement();
            statement.executeUpdate(req);
            System.out.println("Repas supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addRepasDelete(ActionEvent event) {

        RepasData u = addRepas_tableView.getSelectionModel().getSelectedItem();

        if (u != null) {
            supprimer(u.getId());
            addRepasShowListData();
        } else {
            System.out.println("Aucun Repas sélectionné");
        }
        addRepasReset();
    }

    @FXML
    public void addRepasReset() {
        addRepas_id.setText("");
        addRepas_name.setText("");
        addRepas_description.setText("");
        addRepas_heure.setText("");
        addRepas_image.setImage(null);
        addRepas_calories.setText("");
        addRepas_dayofweek.setText("");
        
        getData.path = "";
    }

    @FXML
    public void addRepasInsertImage() {

        FileChooser open = new FileChooser();
        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 101, 127, false, true);
            addRepas_image.setImage(image);
        }
    }

    @FXML
    public void addRepasSearch() {
        FilteredList<RepasData> filter = new FilteredList<>(addRepasList, e -> true);

        addRepas_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateRepasData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                return Stream.of(
                        String.valueOf(predicateRepasData.getId()),
                        predicateRepasData.getName().toLowerCase(),
                        predicateRepasData.getDescription().toLowerCase(),
                        predicateRepasData.getHeure().toLowerCase(),
                        predicateRepasData.getCalories().toLowerCase(),
                        predicateRepasData.getDayOfWeek().toLowerCase()
                        
                ).anyMatch(value -> value.contains(searchKey));
            });
        });

        SortedList<RepasData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(addRepas_tableView.comparatorProperty());
        addRepas_tableView.setItems(sortedList);
    }

    public ObservableList<RepasData> addRepasListData() {

        ObservableList<RepasData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM repas_entity";

        connect = dataBase.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            RepasData repasD;

            while (result.next()) {
                repasD = new RepasData(result.getInt("id"),
                        result.getString("name"),
                        result.getString("description"),
                        result.getString("heure"),
                        result.getString("image"),
                        result.getString("calories"),
                        result.getString("day_of_week")
                        );

                listData.add(repasD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<RepasData> addRepasList;

    private void addRepasShowListData() {
        addRepasList = addRepasListData();

        addRepas_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        addRepas_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        addRepas_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        addRepas_col_heure.setCellValueFactory(new PropertyValueFactory<>("heure"));
        addRepas_col_calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
        addRepas_col_dayofweek.setCellValueFactory(new PropertyValueFactory<>("day_of_week"));
        

        addRepas_tableView.setItems(addRepasList);
    }

    @FXML
    public void addRepasSelect() {
        RepasData repasD = addRepas_tableView.getSelectionModel().getSelectedItem();

        if (repasD != null) {

            addRepas_id.setText(String.valueOf(repasD.getId()));
            addRepas_name.setText(repasD.getName());
            addRepas_description.setText(repasD.getDescription());
            addRepas_heure.setText(repasD.getHeure());
            addRepas_calories.setText(repasD.getCalories());
            addRepas_dayofweek.setText(repasD.getDayOfWeek());
            

            getData.path = repasD.getImage();

            String uri = "file:" + repasD.getImage();
            image = new Image(uri, 101, 127, false, true);
            addRepas_image.setImage(image);
        }
    }

    @FXML
    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            nut_form.setVisible(false);
            addRepas_form.setVisible(false);
            addIngredient_form.setVisible(false);
            homeAdmin_form.setVisible(false);
           
            

            

            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            nut_btn.setStyle("-fx-background-color:transparent");
            addRepas_btn.setStyle("-fx-background-color:transparent");
            addIngredient_btn.setStyle("-fx-background-color:transparent");
            homeAdmin_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == addRepas_btn) {
            home_form.setVisible(false);
            nut_form.setVisible(false);
            addRepas_form.setVisible(true);
            addIngredient_form.setVisible(false);
            homeAdmin_form.setVisible(false);
            
            

            addRepas_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            home_btn.setStyle("-fx-background-color:transparent");
            nut_btn.setStyle("-fx-background-color:transparent");
            addIngredient_btn.setStyle("-fx-background-color:transparent");
            homeAdmin_btn.setStyle("-fx-background-color:transparent");
            
            

        } else if (event.getSource() == addIngredient_btn) {
            home_form.setVisible(false);
            nut_form.setVisible(false);
            addRepas_form.setVisible(false);
            addIngredient_form.setVisible(true);
            homeAdmin_form.setVisible(false);
           
            

            addIngredient_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addRepas_btn.setStyle("-fx-background-color:transparent");
            nut_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            homeAdmin_btn.setStyle("-fx-background-color:transparent");
            
            

        }else if (event.getSource() == nut_btn) {
            home_form.setVisible(false);
            nut_form.setVisible(true);
            addRepas_form.setVisible(false);
            addIngredient_form.setVisible(false);
            homeAdmin_form.setVisible(false);
            
            
            

            nut_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addIngredient_btn.setStyle("-fx-background-color:transparent");
            addRepas_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            homeAdmin_btn.setStyle("-fx-background-color:transparent");
            
            

        }
        else if (event.getSource() == homeAdmin_btn) {
            home_form.setVisible(false);
            nut_form.setVisible(false);
            addRepas_form.setVisible(false);
            addIngredient_form.setVisible(false);
            homeAdmin_form.setVisible(true);
            
            

            homeAdmin_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3a4368, #28966c);");
            addIngredient_btn.setStyle("-fx-background-color:transparent");
            addRepas_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            nut_btn.setStyle("-fx-background-color:transparent");
            

        
        }
        
    }
    


    
    //**************** Ingredient Form **************//
    
    @FXML
    public void addIngredientAdd() {

        String sql = "INSERT INTO ingredient_entity"
                + "(id,name,quantite,calories)"
                + "VALUES(?,?,?,?)";

        connect = dataBase.connectDb();

        try {
            Alert alert;
            if ( 
                    addIngredient_name.getText().isEmpty()
                    || addIngredient_quantite.getText().isEmpty()
                    || addIngredient_calories.getText().isEmpty()
                    
                    ) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Blanc Fields");
                alert.showAndWait();

            } else {

                String check = "SELECT id FROM ingredient_entity WHERE id = '"
                        + addIngredient_id.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Ingredient ID: " + addIngredient_id.getText() + "Was Already exist");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addIngredient_id.getText());
                    prepare.setString(2, addIngredient_name.getText());
                    prepare.setString(3, addIngredient_quantite.getText());
                    prepare.setString(4, addIngredient_calories.getText());
                    

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    addRepasShowListData();
                    addRepasReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    public void addIngredientUpdate() {

        String sql = "UPDATE ingredient_entity SET name = '"
                + addIngredient_name.getText() + "', quantite = '"
                + addIngredient_quantite.getText() + "', calories = '"
                + addIngredient_calories.getText() + "' WHERE id ='"
                + addIngredient_id.getText() + "'";

        connect = dataBase.connectDb();

        try {
            Alert alert;
            if (addIngredient_id.getText().isEmpty()
                    || addIngredient_name.getText().isEmpty()
                    || addIngredient_quantite.getText().isEmpty()
                    || addIngredient_calories.getText().isEmpty()
                    || getData.path == null || getData.path == "") {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill All Blanc Fields");
                alert.showAndWait();

            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are You Sure You Want To UPDATE Ingredient ID :" + addIngredient_id.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addIngredientShowListData();
                    addIngredientReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void supprimerIn(int id) {
        try {
            String req = "DELETE FROM ingredient_entity WHERE id = " + id;
            statement = connect.createStatement();
            statement.executeUpdate(req);
            System.out.println("Ingredient supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addIngredientDelete(ActionEvent event) {

        IngredientData i = addIngredient_tableView.getSelectionModel().getSelectedItem();

        if (i != null) {
            supprimerIn(i.getId());
            addIngredientShowListData();
        } else {
            System.out.println("Aucun ingredient sélectionné");
        }
        addIngredientReset();
    }
    
    
    @FXML
    public void addIngredientReset() {
        addIngredient_id.setText("");
        addIngredient_name.setText("");
        addIngredient_quantite.setText("");
        addIngredient_calories.setText("");
        
        getData.path = "";
    }

    
    
    
    public ObservableList<IngredientData> addIngredientListData() {

        ObservableList<IngredientData> listIData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM ingredient_entity";

        connect = dataBase.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            IngredientData ingredientD;

            while (result.next()) {
                ingredientD = new IngredientData(result.getInt("id"),
                        result.getString("name"),
                        result.getString("quantite"),
                        result.getString("calories"));

                listIData.add(ingredientD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listIData;
    }

    private ObservableList<IngredientData> addIngredientList;

    private void addIngredientShowListData() {
        addIngredientList = addIngredientListData();

        addIngredient_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        addIngredient_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        addIngredient_col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        addIngredient_col_calories.setCellValueFactory(new PropertyValueFactory<>("calories"));

        addIngredient_tableView.setItems(addIngredientList);
    }
    
    @FXML
    public void addIngredientSearch() {
        FilteredList<IngredientData> filter = new FilteredList<>(addIngredientList, e -> true);

        addIngredient_search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateIngredientData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                return Stream.of(
                        String.valueOf(predicateIngredientData.getId()),
                        predicateIngredientData.getName().toLowerCase(),
                        predicateIngredientData.getQuantite().toLowerCase(),
                        predicateIngredientData.getCalories().toLowerCase()
                        
                ).anyMatch(value -> value.contains(searchKey));
            });
        });

        SortedList<IngredientData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(addIngredient_tableView.comparatorProperty());
        addIngredient_tableView.setItems(sortedList);
    }

    @FXML
    public void addIngredientSelect() {
        IngredientData ingredientD = addIngredient_tableView.getSelectionModel().getSelectedItem();

        if (ingredientD != null) {

            addIngredient_id.setText(String.valueOf(ingredientD.getId()));
            addIngredient_name.setText(ingredientD.getName());
            addIngredient_quantite.setText(ingredientD.getQuantite());
            
            addIngredient_calories.setText(ingredientD.getCalories());
            

            
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listUser();
        listClient();
        
        addRepasShowListData();
        addIngredientShowListData();
        
        homeTotalRepas();
        homeTotalIngredient();
        

//        WebEngine webEngine = webView.getEngine();
//        webEngine.load("https://app.wotnot.io/bot-preview/5cGENESCfKq5065725423953ZknjfBbD");

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
    
    /****************** Table Admin ******************/

    @FXML
    private void addAdmin(ActionEvent event) {
        String nom = tfNomAdmin.getText();
    String prenom = tfPrenomAdmin.getText();
    String username = tfUserAdmin.getText();
    String email = tfEmailAdmin.getText();
    String password = tfMdpAdmin.getText();
    //int tel = Integer.parseInt(tfTel.getText());
    String tel = tfTelAdmin.getText();
    User us = new User();
    if (us.verifierSaisie(nom, prenom, username, email, password, tel)) {
        utilisateur u = new utilisateur(nom, prenom, username, email, password, tel);
        us.ajouter(u);
        listUser();
        clearAdmin();
    } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText(us.getMessageErreur()); // affichage du message d'erreur
        alert.showAndWait();
    }
    }

    @FXML
    private void UpdateAdmin(ActionEvent event) {
         utilisateur u = addAdmin_tableView.getSelectionModel().getSelectedItem();
    User us = new User();
    if (u != null) {
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String username = tfUsername.getText();
        String email = tfEmail.getText();
        String password = tfMdp.getText();
        String tel = tfTel.getText();
        if (us.verifierSaisie(nom, prenom, username, email, password, tel)) {
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setUsername(username);
            u.setEmail(email);
            u.setPassword(password);
            u.setTel(tel);
            boolean modifie = us.modifier(u);
    } else {
        System.out.println("Veuillez sélectionner un utilisateur.");
    }
    clearAdmin();
    addAdmin_tableView.refresh();
    }   
    }

    @FXML
    private void deleteAdmin(ActionEvent event) {
         // Récupérer l'utilisateur sélectionné
    utilisateur u = addAdmin_tableView.getSelectionModel().getSelectedItem();
    
    if (u != null) {
        User us = new User();
        us.supprimer(u.getId());
        listUser();
    } else {
        System.out.println("Aucun utilisateur sélectionné");
    }
    clearAdmin();
    }

    private void clearAdmi(ActionEvent event){
        clearAdmin();
        
    }
    @FXML
    public void clearAdmin() {
        tfNomAdmin.clear();
    tfPrenomAdmin.clear();
    tfUserAdmin.clear();
    tfEmailAdmin.clear();
    tfMdpAdmin.clear();
    tfTelAdmin.clear();
    }
    
    public void listUser()
    {
        User us = new User();
        ObservableList<utilisateur> list = us.getAll();
        System.out.println("list");
        
        tcIdAdmin.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcNomAdmin.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tcPrenomAdmin.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tcUserAdmin.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcEmailAdmin.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcMdpAdmin.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcTelAdmin.setCellValueFactory(new PropertyValueFactory<>("tel"));
        addAdmin_tableView.setItems(list);
    }
    
    /********** EXCEL***********/
    
     public void exportToExcel(String fileName, ObservableList<utilisateur> data) {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("admin");

    // Créer une rangée pour les en-têtes de colonnes
    XSSFRow headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("ID");
    headerRow.createCell(1).setCellValue("Nom");
    headerRow.createCell(2).setCellValue("Prénom");
    headerRow.createCell(3).setCellValue("Nom d'utilisateur");
    headerRow.createCell(4).setCellValue("E-mail");
    headerRow.createCell(5).setCellValue("Mot de passe");
    headerRow.createCell(6).setCellValue("Téléphone");

    // Ajouter les données à la feuille de calcul
    int rowNum = 1;
    for (utilisateur c : data) {
        XSSFRow row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(c.getId());
        row.createCell(1).setCellValue(c.getNom());
        row.createCell(2).setCellValue(c.getPrenom());
        row.createCell(3).setCellValue(c.getUsername());
        row.createCell(4).setCellValue(c.getEmail());
        row.createCell(5).setCellValue(c.getPassword());
        row.createCell(6).setCellValue(c.getTel());
        //row.createCell(7).setCellValue(c.getDate_naissance().toString());
    }

    // Écrire le contenu dans le fichier
    try {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        workbook.write(outputStream);
        workbook.close();
        System.out.println("Le fichier " + fileName + " a été créé avec succès !");
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    

}

    @FXML
    private void exportToExcel(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Enregistrer sous");
        fileChooser.setInitialFileName("admins.xlsx");
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Fichier Excel", "*.xlsx")
        );
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            User us = new User();
            ObservableList<utilisateur> data = us.getAll();
            exportToExcel(selectedFile.getAbsolutePath(), data);
        }try {
                Desktop.getDesktop().open(selectedFile);
            } catch (IOException e) {
                e.printStackTrace();
}
}
    
    @FXML
    private void exportToPdf(ActionEvent event) {
    com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
    try {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save PDF file");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
        );
        File file = fileChooser.showSaveDialog(null);
        if (file == null) {
            return;
        }
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        writer.setPageEvent(new DateAndTime());
        document.open();
        
        PdfPTable table = new PdfPTable(7);
        table.addCell("ID");
        table.addCell("Nom");
        table.addCell("Prenom");
        table.addCell("Username");
        table.addCell("Email");
        table.addCell("Password");
        table.addCell("Telephone");
        
        User us = new User();
        ObservableList<utilisateur> data = us.getAll();
        
        for(utilisateur user : data) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getNom());
            table.addCell(user.getPrenom());
            table.addCell(user.getUsername());
            table.addCell(user.getEmail());
            table.addCell(user.getPassword());
            table.addCell(new PdfPCell(new Phrase(String.valueOf(user.getTel()))));
        }
        
        document.add(table);
        
        /* Image image3 = Image.getInstance("C:\\Users\\aymen\\OneDrive\\Documents\\NetBeansProjects\\sportConnectNutritionManagement\\src\\images\\logo3.png");
        float x = (PageSize.A4.getWidth() - image3.getWidth()) / 2f;
        float y = (PageSize.A4.getHeight() - table.getTotalHeight() - 100f - image3.getHeight()) / 2f;
        image3.setAbsolutePosition(x, y);
        document.add(image3);
        */
        document.close();
        try {
    Desktop.getDesktop().open(file);
    } catch (IOException e) {
        e.printStackTrace();
}

        
    } catch (DocumentException e) {
        e.printStackTrace();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}



    private static class DateAndTime extends PdfPageEventHelper {
    public void onEndPage(PdfWriter writer, com.itextpdf.text.Document document) {
        try {
            Phrase phrase = new Phrase("Exporté le " + LocalDateTime.now().toString(),
            FontFactory.getFont(FontFactory.HELVETICA, 9));
            ColumnText.showTextAligned(writer.getDirectContent(),
            Element.ALIGN_LEFT, phrase,
            240, 30, 0); // Position de la marge gauche et du bas en pixels

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
    
    /********************* Table Client *****************************/
    
    private void addClient(ActionEvent event) {
        String nom = tfNom.getText();
    String prenom = tfPrenom.getText();
    String username = tfUsername.getText();
    String email = tfEmail.getText();
    String password = tfMdp.getText();
    
    String tel = tfTel.getText();
    User us = new User();
    if (us.verifierSaisie(nom, prenom, username, email, password, tel)) {
        utilisateur u = new utilisateur(nom, prenom, username, email, password, tel);
        us.ajouter(u);
        listUser();
        clearAdmin();
    } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur de saisie");
        alert.setContentText(us.getMessageErreur()); // affichage du message d'erreur
        alert.showAndWait();
    }
    }
    
    @FXML
    private void supprimerClient(ActionEvent event) {
        client c = addClient_tableView.getSelectionModel().getSelectedItem();
    
    if (c != null) {
        Front f = new Front();
        f.supprimer(c.getId());
        listClient();
    } else {
        System.out.println("Aucun client sélectionné");
    }
    
    }
    
    public void listClient()
{
    Front f = new Front();
    ObservableList<client> list = f.getAll();
    System.out.println("list");
    
    tcIdClient.setCellValueFactory(new PropertyValueFactory<>("id"));
    tcNomClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
    tcPrenomClient.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    tcUserClient.setCellValueFactory(new PropertyValueFactory<>("username"));
    tcEmailClient.setCellValueFactory(new PropertyValueFactory<>("email"));
    tcMdpClient.setCellValueFactory(new PropertyValueFactory<>("password"));
    tcTelClient.setCellValueFactory(new PropertyValueFactory<>("telephone"));
    tcDate.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
    
    
    addClient_tableView.setItems(list);
}
    public void selectItem() {
    client c = addClient_tableView.getSelectionModel().getSelectedItem();
    int num = addClient_tableView.getSelectionModel().getSelectedIndex();
        System.out.println(c);
    if ((num - 1)<-8) {
        return;
    }
     
}
    
    public void exportToExcel1(String fileName, ObservableList<entities.client> data) {
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("client");

    // Créer une rangée pour les en-têtes de colonnes
    XSSFRow headerRow = sheet.createRow(0);
    headerRow.createCell(0).setCellValue("ID");
    headerRow.createCell(1).setCellValue("Nom");
    headerRow.createCell(2).setCellValue("Prénom");
    headerRow.createCell(3).setCellValue("Nom d'utilisateur");
    headerRow.createCell(4).setCellValue("E-mail");
    headerRow.createCell(5).setCellValue("Mot de passe");
    headerRow.createCell(6).setCellValue("Téléphone");
    headerRow.createCell(7).setCellValue("Date de naissance");

    // Ajouter les données à la feuille de calcul
    int rowNum = 1;
    for (client c : data) {
        XSSFRow row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(c.getId());
        row.createCell(1).setCellValue(c.getNom());
        row.createCell(2).setCellValue(c.getPrenom());
        row.createCell(3).setCellValue(c.getUsername());
        row.createCell(4).setCellValue(c.getEmail());
        row.createCell(5).setCellValue(c.getPassword());
        row.createCell(6).setCellValue(c.getTelephone());
        row.createCell(7).setCellValue(c.getDate_naissance().toString());
    }

    // Écrire le contenu dans le fichier
    try {
        FileOutputStream outputStream = new FileOutputStream(fileName);
        workbook.write(outputStream);
        workbook.close();
        System.out.println("Le fichier " + fileName + " a été créé avec succès !");
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
    }
    

}

    @FXML
    private void exportToExcel1(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Enregistrer sous");
//        fileChooser.setInitialFileName("clients.xlsx");
//        fileChooser.getExtensionFilters().addAll(
//            new FileChooser.ExtensionFilter("Fichier Excel", "*.xlsx")
//        );
//        File selectedFile = fileChooser.showSaveDialog(null);
//        if (selectedFile != null) {
//            Front f = new Front();
//            ObservableList<entities.client> data = f.getAll();
//            exportToExcel(selectedFile.getAbsolutePath(), data);
//        }try {
//                Desktop.getDesktop().open(selectedFile);
//            } catch (IOException e) {
//                e.printStackTrace();
//}
  }

    @FXML
    private void exportToPdf1(ActionEvent event) {
    }
    
    /***********************************************/

    @FXML
    private void afficherEq(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
    
    @FXML
    private void evenement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Back.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
    
    @FXML
    private void blog(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Backblogg.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }
    

}
