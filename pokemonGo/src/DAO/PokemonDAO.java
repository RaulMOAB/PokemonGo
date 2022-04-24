/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Objects.Pokemon;
import Persistence.FilePersistence;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author alumne
 */
public class PokemonDAO implements Basic_operations {

    private final ArrayList<String> pokemonNameList = new ArrayList<>();
    private ArrayList<Pokemon> pokemonBag = new ArrayList<>();

    /**
     *
     * @return @throws FileNotFoundException
     */
    @Override
    public Pokemon appearsPokemon() throws FileNotFoundException {
        Pokemon wildPokemon = FilePersistence.catchWildPokemon(pokemonNameList);
        return wildPokemon;
    }

    @Override
    public boolean catchPokemon(int key, int answer, Pokemon wild) {
        if (key == answer) {
            pokemonBag.add(wild);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ArrayList<Pokemon> displayBag() {
        return pokemonBag;
    }

    @Override
    public Pokemon transferPokemon(String user_name, Pokemon aux) throws FileNotFoundException, IOException, ClassNotFoundException {
        int index = pokemonBag.indexOf(aux);
        if (index != -1) {// encuentra el pokemon a transferir
            FilePersistence.transfer(user_name, aux);
            return pokemonBag.remove(index);
        } else {
            return null;
        }
    }

    @Override
    public Pokemon getTransferedPokemon(String user_name) throws IOException, ClassNotFoundException, FileNotFoundException {
        Pokemon pokemonTransfered = FilePersistence.readTransferFile(user_name);
        FilePersistence.deleteFile(user_name);
        return pokemonTransfered;
    }

    /**
     * Validar si el usuario introducuido existe, y si la contraseña es
     * correcta.
     *
     * @param user_name
     * @param password
     * @return 1 si El usuario existe y la contraseña es correcta, 0 si el
     * usuario existe pero la contraseña es incorrecta, y -1 si el usuario ni
     * siquiera existe.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public int validateUserPassword(String user_name, String password) throws FileNotFoundException, IOException {
        File users = new File("users/" + user_name);
        Scanner file = new Scanner(users);
        if (users.exists()) {//validamos contraseña
            String pass = "";
            while (file.hasNext()) {
                pass = file.nextLine();
            }
            file.close();

            if (password.equals(pass)) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return -1; //no existe user
        }
    }

    /**
     * Se registrara cualquier usuario incorrecto introducido y lo guardara en
     * la carpeta de users.
     *
     * @param user_name
     * @param password
     * @throws IOException
     */
    public void newUserLogin(String user_name, String password) throws IOException {
        FileWriter writeNewUser = new FileWriter("users/" + user_name);
        writeNewUser.write(password);
        writeNewUser.close();
    }

    @Override
    public int getNumPokemon() {
        return pokemonBag.size();
    }

    /**
     * @param wildPokemon
     * @return devolvera un numero entre el 1 al 10, dependiendo de la division
     * del CP del pokemon pasado por parametro entre 10, en caso que de menor
     * que 1 devolvera 1.
     */
    public int getDifficult(Pokemon wildPokemon) {
        int i = wildPokemon.getCP() / 10;
        if (i == 0) {
            i = 1;
        }
        return i;
    }

    /**
     * Guardara la mochila del usuario pasado como parametro
     *
     * @param user_name
     * @return Cantidad de pokemons guardados
     * @throws IOException
     */
    public int userBag(String user_name) throws IOException {
        if (FilePersistence.saveBag(pokemonBag, user_name)) {
            return pokemonBag.size();
        } else {
            return 0;
        }
    }

    /**
     * Recuperara la mochila del usuario pasado como parametro
     *
     * @param user_name
     * @return Cantidad de pokemons recuperados.
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public int recoverBag(String user_name) throws IOException, FileNotFoundException, ClassNotFoundException {
        pokemonBag = FilePersistence.readBag(user_name);
        return pokemonBag.size();
    }

    /**
     * Comprueba si existe ya el pokemon salvaje (por nombre) en la mochila.
     *
     * @param wildPokemon
     * @return TRUE si existe, FALSE si no existe.
     */
    public boolean isPokemonInBag(Pokemon wildPokemon) {
        return pokemonBag.contains(wildPokemon);
    }

    /**
     * Comprueba si existe el usuario pasado por parametro
     *
     * @param user_transfer
     * @return TRUE si existe, FALSE si no existe
     */
    public boolean checkUserName(String user_transfer) {
        File validate = new File("users/user_" + user_transfer + ".dat");
        return validate.exists();
    }

    /**
     * @return ArrayList con los nombres de todos los usuarios.
     * @throws FileNotFoundException
     */
    public ArrayList<String> getPlayers() throws FileNotFoundException {
        return FilePersistence.readPlayers();
    }

    public int getNumOfPokemonBag(String user_name) throws IOException, FileNotFoundException, ClassNotFoundException {
        return FilePersistence.getNumPokemonInBag(user_name);
    }

    public boolean getJSONBag(String user_name) throws IOException {
        return FilePersistence.saveBagJSON(pokemonBag, user_name);
    }

    public List loadJSON(String user_name) throws IOException {
        return FilePersistence.loadJSONBag(user_name);
    }

    public boolean deletePokemonInBag(int poke_index) {
        if (pokemonBag.remove(poke_index) != null) {
            return true;
        } else {
            return false;
        }
    }
/**
 * Método que devuelve la cantidad de Pokemon repetidos
 * @param select index del Pokemon escogido por el usuario
 * @return número de Pokemons repetidos
 */
    public int numRepeatPokemon(String select) {
        int count = 0;
        Pokemon aux = new Pokemon(select);
        for (int i = 0; i < pokemonBag.size(); i++) {
            if (pokemonBag.get(i).getName().equals(aux.getName())) {
                count++;
            }
        }
        return count;
    }
/**
 * Método para obtener una listya de todos los Pokemons sin repetir
 * @return 
 */
    public ArrayList<Pokemon> getNonRepeatBag() {
        ArrayList<Pokemon> aux = displayBag();
        ArrayList<Pokemon> pokeList = new ArrayList<>();

        Iterator it = aux.iterator();
        while (it.hasNext()) {
            Pokemon addPokemon = (Pokemon) it.next();
            if (!pokeList.contains(addPokemon)) {
                pokeList.add(addPokemon);
            }
        }
        return pokeList;
    }

    /**
     * Metodo para obtener el pokemon que quieres evolucionar.
     * @param select
     * @return Pokemon a evolucionar
     */
    public Pokemon getPokemonToEvolve(String select) {
        Pokemon aux = new Pokemon(select);
        for (int i = 0; i < pokemonBag.size(); i++) {
            if (pokemonBag.get(i).getName().equals(aux.getName())) {
                return pokemonBag.get(i);
            }
        }
        return null;
        
    }
/**
 * Método para eliminar los 3 Pokemon iguales necesarios para evolucionar
 * @param aux Pokemon a eliminar
 * @return true si lo elimina 3 veces, false si no es así
 */
    public boolean deletePokemonAfterEvo(Pokemon aux) {
        final int CONT = 3;
        int delete;
        for (int i = 0; i < CONT; i++) {
            delete = pokemonBag.indexOf(aux);
            if (pokemonBag.remove(delete) == null) {
                return false;
            }

        }
        return true;
    }
    
    /**
     * Metodo para devolver un random del 100 al 300 para el CP de un pokemon evolucionado
     * @param aux
     * @return CP Random del 100 al 300.
     */
    private int getCPPokemonAfterEvo(){
        Random r = new Random();
        return r.nextInt(201)+100;
    }
    
    /**
     * Metodo para obtener el Pokemon evolucionado segun el que pokemon va a evolucionar
     * @param aux
     * @return Pokemon evolucionado
     */
    public Pokemon getEnvolvedPokemon(Pokemon aux){
        if(aux.getName().equalsIgnoreCase("Bulbasaur")){
            return new Pokemon("Ivysaur",getCPPokemonAfterEvo());
        }else if(aux.getName().equalsIgnoreCase("Caterpie")){
            return new Pokemon("Metapod",getCPPokemonAfterEvo());
        }
        else if(aux.getName().equalsIgnoreCase("Charmander")){
            return new Pokemon("Charmaleon",getCPPokemonAfterEvo());
        }
        else if(aux.getName().equalsIgnoreCase("Squirtle")){
            return new Pokemon("Wartortle",getCPPokemonAfterEvo());
        }else{
            return null;
        }
    }

}
