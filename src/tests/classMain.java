/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.time.LocalDate;
import services.Front;
import services.User;
import static sportconnectnutritionmanagement.InscrireController.calculerAge;
import sportconnectnutritionmanagement.dataBase;


/**
 *
 * @author PC
 */
public class classMain {
    public static void main(String[] args) {
        dataBase db = dataBase.getInstance();
       // DataBase db2 = DataBase.getInstance();
        
        User us = new User();
        Front f=new Front();
      
        LocalDate dateNaissance = LocalDate.of(2000, 1, 1);
        System.out.println(calculerAge(dateNaissance));

    }
}