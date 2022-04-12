/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemongo;

import DAO.PokemonDAO;
import Utilities.Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
        bag = new PokemonDAO();
        displayLogo();
        login();
        main_menu = new Menu("---- Menu ----");
    }

    private void displayLogo() {
        File logo = new File("logo/logo.pok");
        Scanner read;
        try {
            read = new Scanner(logo);
            while (read.hasNext()) {
                System.out.println(read.nextLine());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nombre de usuario");
        String name = sc.nextLine();
        String user_name = "user_" + name + ".dat";
        System.out.println("Introduce la contraseña");
        String password = sc.nextLine();

        try {
            if (bag.validateUser(user_name, password) == 1) {
                System.out.println("Login correcto");
            } else if (bag.validateUser(user_name, password) == 0) {
                System.out.println("Contraseña incorrecta");
            }

        } catch (FileNotFoundException ex) {
            System.err.println("El usuario no existe");
            System.out.println("Se añade nuevo usuario " + name);
            
            try {
                bag.newUserLogin(user_name,password);
            } catch (IOException ex1) {
                System.err.println("Error al crear usuario");
            }

        } catch (IOException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
