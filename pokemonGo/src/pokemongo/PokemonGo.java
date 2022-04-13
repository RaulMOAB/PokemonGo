/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pokemongo;

import DAO.PokemonDAO;
import Objects.Pokemon;
import Utilities.Menu;
import Utilities.Option;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
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
       // login();
        main_menu = new Menu("---- Menu ----");
        addOptionsMenu();
        int option;
        do {
            main_menu.displayMenu();
            option = main_menu.choose();
            switch (option) {
                case 1:
                    catchPokemon();
                    break;
                case 2:
                    displayMyBag();
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        } while (option != 0);
        
    }

    private void displayLogo() {//Hacer la funcion en el Dao?
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

    private void catchPokemon() {
        try {
            Pokemon wildPokemon = bag.appearsPokemon();
            if (wildPokemon != null) {              
                displayPokemonAscii(wildPokemon);
                System.out.println(wildPokemon.toString());
                startHunting(wildPokemon);
            }
  
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addOptionsMenu() {
            main_menu.add(new Option("Salir"));
            main_menu.add(new Option("Cazar Pokemons"));
            main_menu.add(new Option("Listar Pokemons de la mochila"));

    }

    private void displayPokemonAscii(Pokemon wildPokemon) {
        File readFile = new File("pokemons/ascii/"+ wildPokemon.getName() + ".pok");
        try {
            Scanner asciiPokemon = new Scanner(readFile);
            while(asciiPokemon.hasNext()){
                String ascii = asciiPokemon.nextLine();
                String pokeFormat= ascii.replace("printf(\"", "");
                System.out.println(pokeFormat);
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PokemonGo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    //FASE 6
    private void displayMyBag() {
        ArrayList<Pokemon> mybag = bag.displayBag();
        //FASE 8
        Collections.sort(mybag);
        for (Pokemon pokemon : mybag) {
            System.out.println(pokemon.toString());
        }
        displayAmountOfPokemons();
    }

    private void displayAmountOfPokemons() {
        System.out.println(bag.getAmount());
    }
    //FIN FASE 6
    
    //FASE 7
    private void startHunting(Pokemon wildPokemon) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int i = bag.getDifficult(wildPokemon);
        System.out.println("Numero del 1 a "+i);
        int key = r.nextInt(i)+1;
        System.out.println("chuleta:"+key);
        int answer = sc.nextInt();
        
        if(bag.hunted(key,answer,wildPokemon)){
            System.out.println("Felicidades, has capturado a "+wildPokemon.getName()+".");
        }else{
            System.out.println(wildPokemon.getName()+" se ha escapado.");
        }
    }
    //FIN FASE 7

}
