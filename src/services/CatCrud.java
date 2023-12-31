package services;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import entities.Categorie;
import interfaces.EntityCRUD;
import interfaces.EntityCRUD;
import tools.MyConnection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CatCrud implements EntityCRUD<Categorie>{

    @Override
    public void addEntity(Categorie e) {
        try {
            String requete ="INSERT INTO categorie (nom, description) VALUES (?, ?)";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, e.getNom());
            pst.setString(2, e.getDescription());
            pst.executeUpdate();
            System.out.println("categorie ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public void deleteEntity(Categorie e) {
    try {
        String requete ="DELETE FROM categorie WHERE nom = ? AND description = ?";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        pst.setString(1, e.getNom());
        pst.setString(2, e.getDescription());
        pst.executeUpdate();
        System.out.println("Categorie supprimé");
    }   catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    public List<Categorie> displayEntities() {
        List<Categorie> categories = new ArrayList<>();
        try {
            String requete = "SELECT * FROM categorie";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setDescription(rs.getString("description"));
                categories.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categories;
    }


   
    
  public void updateEntity(Categorie e) {
    try {
        String requete = "UPDATE categorie SET nom=?, description=? WHERE id=?";
        PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
        pst.setString(1, e.getNom());
        pst.setString(2, e.getDescription());
        pst.setInt(3, e.getId());
        pst.executeUpdate();
        System.out.println("Categorie mise à jour");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    @Override
    public void ajouter(Categorie t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Categorie> afficher() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void modifier(Categorie t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }




   

}
