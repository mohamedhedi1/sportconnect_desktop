/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package sportconnectnutritionmanagement;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.Session;

/**
 * FXML Controller class
 *
 * @author aymen
 */
public class UserViewController implements Initializable {

    @FXML
    private WebView webViewQR;
    @FXML
    private AnchorPane main_form;

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
    private TableColumn<RepasData, String> addRepas_col_dayofweek;
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

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    @FXML
    private AnchorPane nutrition_form;
    @FXML
    private Button logout;
  

    public void generateQRCode(String data) {
        int width = 250;
        int height = 250;
        String charset = "UTF-8";
        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        BitMatrix bitMatrix;
        try {
            bitMatrix = new QRCodeWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height, hintMap);
            WritableImage qrCodeImage = new WritableImage(width, height);
            PixelWriter pixelWriter = qrCodeImage.getPixelWriter();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pixelWriter.setColor(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            webViewQR.getEngine().loadContent("<img src='data:image/png;base64," + encodeToString(toByteArray(qrCodeImage), true) + "'/>");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static byte[] toByteArray(Image image) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    private static String encodeToString(byte[] input, boolean isUrl) {
        Base64.Encoder encoder = (isUrl ? Base64.getUrlEncoder() : Base64.getEncoder());
        return encoder.encodeToString(input);
    }

    //*************** Table Repas **********************//
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
                        result.getString("day_of_week"));

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

    //*************** Table Ingredient **********************//
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

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateQRCode("geo:36.896598,10.186282?q=ESPRIT");

        addRepasShowListData();
        addIngredientShowListData();
        

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
    private void Nutri(ActionEvent event) throws IOException {
        
    Parent root = FXMLLoader.load(getClass().getResource("UserView.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
