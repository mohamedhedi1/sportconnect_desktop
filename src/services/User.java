/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import entities.utilisateur;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;
import sportconnectnutritionmanagement.dataBase;


/**
 *
 * @author PC
 */
public class User {

    Connection cnx = dataBase.getInstance().getCnx();
    
    private String messageErreur; // propriété pour stocker le message d'erreur

    public String getMessageErreur() {
        return messageErreur;
    }
    public void ajouterrrr(utilisateur u) {
        try {
            String req = "INSERT INTO `admin` (`nom`, `prenom`, `username`, `email`, `password`, `telephone`) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(req); //statement pour les requettes statique et ps pour les dynamiques 
            pst.setString(1, u.getNom());
            pst.setString(2, u.getPrenom());
            pst.setString(3, u.getUsername());
            pst.setString(4, u.getEmail());
            pst.setString(5, u.getPassword());       
            pst.setString(6, u.getTel());    
            pst.executeUpdate();
            System.out.println("ajouté avec succes");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean verifierSaisie(String nom, String prenom, String username, String email, String password, String tel) {
    // Vérification du champ nom
    if (nom.isEmpty()) {
        this.messageErreur = "Veuillez saisir un nom !";
        return false;
    }
    
    // Vérification du champ prenom
    if (prenom.isEmpty()) {
        this.messageErreur = "Veuillez saisir un prénom !";
        return false;
    }
    
    // Vérification du champ username
    if (username.isEmpty()) {
        this.messageErreur = "Veuillez saisir un nom d'utilisateur !";
        return false;
    }
    
    // Vérification du champ email
    if (email.isEmpty() || !email.matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,5}")) {
        this.messageErreur = "Veuillez saisir une adresse email valide !";
        return false;
    }
    
    // Vérification du champ password
    if (password.isEmpty()) {
        this.messageErreur = "Veuillez saisir un mot de passe !";
        return false;
    }
    
    // Vérification du champ tel
    if (tel.isEmpty()) {
    this.messageErreur = "Veuillez saisir un numéro de téléphone valide !";
    return false;
}

try {
    int num = Integer.parseInt(tel);
} catch (NumberFormatException e) {
    this.messageErreur = "Veuillez saisir un numéro de téléphone valide !";
    return false;
}

    
    return true;
}
    
    public void ajouter(utilisateur u) {
    if (!verifierSaisie(u.getNom(), u.getPrenom(), u.getUsername(), u.getEmail(), u.getPassword(), u.getTel())) {
        return;
    }

    try {
        String email = u.getEmail();
        String req = "SELECT COUNT(*) FROM admin WHERE email = ?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        if (count > 0) {
            JOptionPane.showMessageDialog(null, "L'email '" + email + "' est déjà utilisé par un autre utilisateur.");
            return;
        }

        req = "INSERT INTO admin (nom, prenom, username, email, password, telephone) VALUES (?, ?, ?, ?, ?, ?)";
        pst = cnx.prepareStatement(req);
        pst.setString(1, u.getNom());
        pst.setString(2, u.getPrenom());
        pst.setString(3, u.getUsername());
        pst.setString(4, email);
        pst.setString(5, u.getPassword());
        pst.setString(6, u.getTel());
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Utilisateur ajouté avec succès !");
        System.out.println("ajouté avec succès");
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout de l'utilisateur : " + ex.getMessage());
        System.out.println(ex.getMessage());
    }
}


    
    public List<utilisateur> afficher() {
        List<utilisateur> list = new ArrayList<>();
        try {
            String req = "Select * from admin";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                utilisateur u = new utilisateur();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setTel(rs.getString("telephone"));
                list.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `admin` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("utilisateur supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public void modifierU(utilisateur u) {
    try {
        String req = "UPDATE `admin` SET `nom` = '" + u.getNom() 
                + "', `prenom` = '" + u.getPrenom() 
                + "', `username` = '" + u.getUsername()
                + "', `email` = '" + u.getEmail()
                + "', `password` = '" + u.getPassword()
                + "', `telephone` = '" + u.getTel()
                + "' WHERE `admin`.`id` = " + u.getId();
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.executeUpdate(req);
        System.out.println("utilisateur modifié !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        System.out.println("Impossible de modifier l'utilisateur " + u.getNom());
    }
}

public boolean modifier(utilisateur u) {
    boolean modifie = false;
    if (!verifierSaisie(u.getNom(), u.getPrenom(), u.getUsername(), u.getEmail(), u.getPassword(), u.getTel())) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(messageErreur);
        alert.showAndWait();
        
    }
    try {
        String req = "UPDATE admin SET nom=?, prenom=?, username=?, email=?, password=?, telephone=? WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, u.getNom());
        pst.setString(2, u.getPrenom());
        pst.setString(3, u.getUsername());
        pst.setString(4, u.getEmail());
        pst.setString(5, u.getPassword());
        pst.setString(6, u.getTel());
        pst.setInt(7, u.getId());
        int result = pst.executeUpdate();
        if(result > 0) {
            System.out.println("utilisateur modifié !");
        } else {
            System.out.println("Impossible de modifier l'utilisateur !");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return modifie;
}


    public ObservableList<utilisateur> getAll() {
        ObservableList<utilisateur> user = FXCollections.observableArrayList();
try {
    
            String req = "Select * from admin";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                utilisateur u = new utilisateur();
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setTel(rs.getString("telephone"));
                user.add(u);
            }
            System.out.println("user");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return user;    
    }
    public boolean inscriptionC(String nom, String prenom, String username, String email, String password, String telephone)
    
    {
    boolean success = false;
    try {
        // Vérifier que tous les champs obligatoires sont remplis
        if (nom.isEmpty() || prenom.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || telephone.isEmpty()) {
            System.err.println("Tous les champs obligatoires doivent être remplis !");
            return success;
        }
        
        // Vérifier que l'email n'est pas déjà utilisé par un autre client
        String checkEmailReq = "SELECT COUNT(*) FROM admin WHERE email = ?";
        PreparedStatement checkEmailPst = cnx.prepareStatement(checkEmailReq);
        checkEmailPst.setString(1, email);
        ResultSet checkEmailRs = checkEmailPst.executeQuery();
        checkEmailRs.next();
        int emailCount = checkEmailRs.getInt(1);
        if (emailCount > 0) {
            System.err.println("L'email est déjà utilisé par un autre client !");
            return success;
        }
        
        // Vérifier que le nom d'utilisateur n'est pas déjà utilisé par un autre client
        String checkUsernameReq = "SELECT COUNT(*) FROM admin WHERE username = ?";
        PreparedStatement checkUsernamePst = cnx.prepareStatement(checkUsernameReq);
        checkUsernamePst.setString(1, username);
        ResultSet checkUsernameRs = checkUsernamePst.executeQuery();
        checkUsernameRs.next();
        int usernameCount = checkUsernameRs.getInt(1);
        if (usernameCount > 0) {
            System.err.println("Le nom d'utilisateur est déjà utilisé par un autre client !");
            return success;
        }
        
        
        // Insérer les données du client dans la base de données
        String insertReq = "INSERT INTO admin (nom, prenom, username, email, password, telephone) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement insertPst = cnx.prepareStatement(insertReq);
        insertPst.setString(1, nom);
        insertPst.setString(2, prenom);
        insertPst.setString(3, username);
        insertPst.setString(4, email);
        insertPst.setString(5, password);
        insertPst.setString(6, telephone);
        
        
        int rowsInserted = insertPst.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Client inséré avec succès !");
            success = true;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    } finally {
        return success;
    }
}
   
}
