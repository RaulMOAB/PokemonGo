/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemongo;

import DAO.PokemonDAO;
import Utilities.Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumne
 */
public class PokemonGo {
    private PokemonDAO bag;
    private Menu main_menu;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PokemonGo app = new PokemonGo();
        app.runApp();
    }

    private void runApp() {
        //bag = new PokemonDAO();
        displayLogo();
        main_menu = new Menu("---- Menu ----");
    }

    private void displayLogo() {
        File logo = new File("logo/logo.pok");
        Scanner read;
        try {
            read = new Scanner(logo);
            while(read.hasNext()){
            System.out.println(read.nextLine());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
