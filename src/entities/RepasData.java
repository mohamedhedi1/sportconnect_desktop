/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sportconnectnutritionmanagement;


/**
 *
 * @author aymen
 */
public class RepasData {
    
    private int id;
    private String name;
    private String description;
    private String heure;
    private String image;
    private String calories;
    private String dayOfWeek;
    private Double price;
    

    public RepasData() {
    }

    public RepasData(int id, String name, String description, String heure, String image, String calories, String dayOfWeek, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.heure = heure;
        this.image = image;
        this.calories = calories;
        this.dayOfWeek = dayOfWeek;
        this.price=price;
    }

    public RepasData(int id,String name, String description, String heure, String image, String calories, String dayOfWeek) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.heure = heure;
        this.image = image;
        this.calories = calories;
        this.dayOfWeek = dayOfWeek;
        
    }

    public RepasData(String name, String description, String heure, String calories, String dayOfWeek, Double price) {
        this.name = name;
        this.description = description;
        this.heure = heure;
        this.calories = calories;
        this.dayOfWeek = dayOfWeek;
        this.price=price;
    }

    public RepasData(int id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        
    }
    

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    
    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHeure() {
        return heure;
    }

    public String getImage() {
        return image;
    }

    public String getCalories() {
        return calories;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    
}
