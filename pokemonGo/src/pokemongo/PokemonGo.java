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
    private String user_name;

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
        boolean acess = login();
        main_menu = new Menu("---- Menu ----");
        addOptionsMenu();
        int option = -1;

        while (option != 0 && acess) {
            main_menu.displayMenu();
            option = main_menu.choose();
            switch (option) {
                case 1:
                    catchPokemon();
                    break;
                case 2:
                    displayMyBag();
                    break;
                case 3:
                    transferPokemon(user_name);
                    break;
                case 4:
                    getPokemon(user_name);
                    break;
                case 5:
                    displayPlayers();
                    break;
                case 0:
                    saveAndExit(user_name);
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }

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
            System.err.println("No se ha encontrado el archivo");
        }

    }

    /**
     * Método para loguearse, si el usuario n oexiste se creará uno Permite o no
     * el inicio de sesión si la contraseña es correcta
     *
     * @return true si es valido para loguearse, false si la contraseña es
     * incorrecta
     */
    private boolean login() {
        Scanner sc = new Scanner(System.in);
        Boolean acess = false;
        System.out.println("Introduzca el nombre de usuario");
        user_name = sc.nextLine();
        String user_File_name = "user_" + user_name + ".dat";
        String password;
        int tries=0;
        try {
            do{
                System.out.println("Introduce la contraseña");
                password = sc.nextLine();
                try {
                    
                    if (bag.validateUserPassword(user_File_name, password) == 1) {
                        System.out.println("Login correcto");
                        System.out.println("Se han cargado " + bag.recoverBag(user_name) + " pokemons");
                        acess = true;
                        
                    } else if (bag.validateUserPassword(user_File_name, password) == 0) {
                        System.err.println("Contraseña incorrecta");
                        tries++;
                    }
                    
                } catch (FileNotFoundException ex) {
                    System.err.println("El usuario no existe");
                    System.out.println("Se añade nuevo usuario " + user_name);
                    acess = true;
                    
                    try {
                        bag.newUserLogin(user_File_name, password);
                    } catch (IOException ex1) {
                        System.err.println("Error al crear usuario");
                    }
                } catch (ClassNotFoundException ex) {
                    System.err.println("Objeto no encontrado.");
                }
                if(tries == 3){
                    System.err.println("Demasiados intentos...");
                }
            }while(bag.validateUserPassword(user_File_name, password) == 0 && tries != 3);
        } catch (IOException ex) {
            System.err.println("Error archivo no encontrado.");
        }
        return acess;
    }

    /**
     * Método para cazar un Pokemon salvaje, primero aparece y luego el usuario
     * decide si cazarlo o no
     */
    private void catchPokemon() {
        try {
            Pokemon wildPokemon = bag.appearsPokemon();
            if (wildPokemon != null) {
                displayPokemonAscii(wildPokemon);
                System.out.println(wildPokemon.toString());
                huntPokemon(wildPokemon);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Pokemon no encontrado.");
        }
    }

    private void addOptionsMenu() {
        main_menu.add(new Option("Salir"));
        main_menu.add(new Option("Cazar Pokemons"));
        main_menu.add(new Option("Listar Pokemons de la mochila"));
        main_menu.add(new Option("Transefir Pokemon"));
        main_menu.add(new Option("Recibir Pokemon"));
        main_menu.add(new Option("Listar Entrenadores Pokemon"));

    }

    /**
     * Se muestran los Pokemons en formato ascii
     *
     * @param wildPokemon Pokemon salvaje que aparece
     */
    private void displayPokemonAscii(Pokemon wildPokemon) {
        File readFile = new File("pokemons/ascii/" + wildPokemon.getName() + ".pok");
        try {
            Scanner asciiPokemon = new Scanner(readFile);
            while (asciiPokemon.hasNext()) {
                String ascii = asciiPokemon.nextLine();
                String pokeFormat = ascii.replace("printf(\"", "");
                System.out.println(pokeFormat);

            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer archivo.");
        }

    }

    private void displayMyBag() {
        ArrayList<Pokemon> mybag = bag.displayBag();
        Collections.sort(mybag);
        for (Pokemon pokemon : mybag) {
            System.out.println(pokemon.toString());
        }
        displayAmountOfPokemons();
    }

    private void displayAmountOfPokemons() {
        System.out.println("Pokemons en la mochila:" + bag.getNumPokemon());
    }

    /**
     * Método de caza de Pokemons en el que el usuario debe inbtroduce un número
     * sio es correcto lo caza
     *
     * @param wildPokemon
     */
    private void huntPokemon(Pokemon wildPokemon) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        boolean exist = bag.isPokemonInBag(wildPokemon);
        boolean capture = false;
        if (exist) {
            capture = decision(exist, wildPokemon);
        }
        if (!exist || (exist && capture)) {
            int i = bag.getDifficult(wildPokemon);
            System.out.println("Numero del 1 a " + i);
            int key = r.nextInt(i) + 1;
            System.out.println("chuleta:" + key);//Borrar
            int answer = sc.nextInt();
            boolean isHunted = bag.catchPokemon(key, answer, wildPokemon);
            if (isHunted) {
                System.out.println("!Felicidades¡, has capturado a " + wildPokemon.getName() + ".");
            } else {
                System.out.println(wildPokemon.getName() + " se ha escapado.");
            }
        } else {
            System.out.println("Has dejado escapar a " + wildPokemon.getName());
        }
    }

    private void saveAndExit(String user_name) {
        try {
            System.out.println("Se han guardado " + bag.userBag(user_name) + " pokemons correctamente");

        } catch (IOException ex) {
            System.out.println("Error al guardar el Pokemon.");
        }
    }

    /**
     * Metodo en el que se decide si cazar un Pokemon ya existente en la mochila
     *
     * @param exist comprobación de que exista en la mochila
     * @param wildPokemon Pokemon salvaje qeu aparece
     * @return true si lo quiere cazar, false si decide no cazarlo
     */
    private boolean decision(boolean exist, Pokemon wildPokemon) {
        if (exist) {
            char answer;
            Scanner sc = new Scanner(System.in);
            System.out.println("Ya tienes en tu mochila un " + wildPokemon.getName() + "\n"
                    + "¿Quieres capturarlo igualmente? (s/n)");
            do {
                answer = sc.nextLine().toLowerCase().charAt(0);
            } while (answer != 'n' && answer != 's');

            return answer == 's';

        } else {
            return false;
        }
    }

    /**
     * Método para transferir un Pokemon a otro jugador
     *
     * @param user_name nombre del usuario que va a transferir el Pokemon
     */
    private void transferPokemon(String user_name) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿A qué jugador quieres transferir un Pokemon?");
        String user_to_transfer = sc.nextLine();
        String pokemon_name;

        if (bag.checkUserName(user_to_transfer)) {
            System.out.println("¿Qué Pokemon quieres transferir?");
            pokemon_name = sc.nextLine();
            String format_name = pokemon_name.toUpperCase().charAt(0) + pokemon_name.substring(1, pokemon_name.length()).toLowerCase();
            Pokemon aux = new Pokemon(format_name);
            try {
                if (bag.transferPokemon(user_name, aux) != null) {
                    System.out.println("Se ha transferido el Pokemon " + aux.getName() + " correctamente.");
                    System.out.println("El Pokemon " + aux.getName() + " se ha borrado de tu mochila.");
                } else {
                    System.err.println("Pokemon no encontrado, imposible transferir");
                }

            } catch (IOException ex) {
                System.err.println("Error al leer el archivo.");
            } catch (ClassNotFoundException ex) {
                System.err.println("Objeto no encontrado.");
            }
        } else {
            System.err.println("El usuario introducido no existe");
        }

    }

    /**
     * Método para recibir el Pokemon transferido de otro usuario
     *
     * @param user_name nombre de usuari ode la sesión activa
     */
    private void getPokemon(String user_name) {
        try {
            Pokemon pokemonTransfered = bag.getTransferedPokemon(user_name);
            if (pokemonTransfered != null) {
                bag.catchPokemon(0, 0, pokemonTransfered);
                System.out.println("El Pokemon " + pokemonTransfered.getName() + " se ha añadido a tu mochilla.");
                System.out.println("Pokemons actualizados en tu mochila: " + bag.getNumPokemon());

            } else {
                System.err.println("Error en la transferencia. ");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("No hay transferencias pendientes.");
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo.");
        } catch (ClassNotFoundException ex) {
            System.err.println("Objeto no encontrado.");
        }
    }
//testear más a fondo
    private void displayPlayers() {
        try {
            bag.recoverBag(user_name);
            int i = 1;
            for (String player : bag.getPlayers()) {
                System.out.println("Entrenador " + i + "\t " +player.toUpperCase() + "\t" + bag.recoverBag(player) + " Pokemons en la mochila");
                i++;
                bag.userBag(player);
            }
            
        } catch (FileNotFoundException ex) {
            System.err.println("Error al leer el fichero");
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error al encontrar en objeto");
        }
    }
}
