/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportconnectnutritionmanagement;

/**
 *
 * @author aymen
 */
public class IngredientData {
    private int id;
    private String name;
    private String quantite;
    private String calories;

    public IngredientData() {
    }

    public IngredientData(int id, String name, String quantite, String calories) {
        this.id = id;
        this.name = name;
        this.quantite = quantite;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }
    
    
    
}
