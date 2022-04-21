/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Objects.Pokemon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Raul
 */
public class FilePersistence {

    private final static String FILE_NAME = "_mochila.dat";
    private final static String FILE_TRANSFER = "pokemons/transfers/";
    private final static String FILE_BAG = "users/mochilas/";
    private final static String FILE_JSON_BAG = "users/mochilasJson/";

    /**
     * Creara un fichero en la ruta de mochilas, el archivo contendra en el
     * nombre, el nombre del usuario pasado como parametro. Leera el ArrayList
     * pasado como parametro para traducirlo a binario y escribira en el archivo
     * creado, o ya existente, todos los objetos que tenga la mochila. En
     * resumen guarda la mochila.
     *
     * @param bag
     * @param user_name
     * @return TRUE si se ha guardado correctamente, FALSE si no se ha guardado.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean saveBag(ArrayList<Pokemon> bag, String user_name) throws FileNotFoundException, IOException {
        FileOutputStream write;
        write = new FileOutputStream(FILE_BAG + user_name + FILE_NAME);
        ObjectOutputStream StreamData = new ObjectOutputStream(write);//si no existe lo crea
        StreamData.writeObject(bag);
        return true;
    }

    /**
     * Leera el archivo de la mochila del nombre de usuario pasado como
     * parametro Cogera el contenido del archivo y lo metera en un Arraylist
     *
     * @param user_name
     * @return ArrayList con el contenido del archivo de la mochila del usuario
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static ArrayList<Pokemon> readBag(String user_name) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(FILE_BAG + user_name + FILE_NAME);
        ObjectInputStream StreamData = new ObjectInputStream(file);
        ArrayList<Pokemon> read = (ArrayList<Pokemon>) StreamData.readObject();
        return read;
    }

    /**
     * Lee el archivo donde se encuentran los nombres de los pokemons para luego
     * añadirlos de linea en linea a un ArrayList de Strings
     *
     * @param nameList
     * @return Retorna un objeto creado con el nombre aleatorio que haya salido
     * en el Arraylist.(CP aleatorio tambien gracias al setter que esta en el
     * constructor)
     * @throws FileNotFoundException
     */
    public static Pokemon catchWildPokemon(ArrayList<String> nameList) throws FileNotFoundException {
        nameList = new ArrayList<>();
        Random r = new Random();
        File nombres = new File("pokemons/nombres.pok");
        Scanner read = new Scanner(nombres);

        while (read.hasNextLine()) {
            String name = read.nextLine();
            nameList.add(name);
        }
        read.close();

        int randomName = r.nextInt(nameList.size());
        Pokemon wildPokemon = new Pokemon(nameList.get(randomName));

        return wildPokemon;
    }

    /**
     * Creara un archivo de transferencia con el nombre pasado como parametro
     * del usuario al que se lo quiere transferir Escribira el Pokemon pasado
     * por parametro que va a transferir el usuario en el archivo de
     * transaferencia.
     *
     * @param user_name
     * @param aux
     * @return TRUE si se ha tranferido correctamente, FALSE si no se ha podido
     * transferir.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static boolean transfer(String user_name, Pokemon aux) throws FileNotFoundException, IOException {
        FileOutputStream write;
        write = new FileOutputStream(FILE_TRANSFER + user_name + ".dat");
        ObjectOutputStream StreamData = new ObjectOutputStream(write);
        StreamData.writeObject(aux);
        return true;

    }

    /**
     * Leera el archivo con el nombre pasado por parametro del usuario que va a
     * recoger el pokemon transferido. Una vez leido lo pasara en formato de
     * Objeto Pokemon
     *
     * @param user_name
     * @return devolvera el Pokemon pasado por el archivo de transferencia,si no
     * hay nada devolvera null
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Pokemon readTransferFile(String user_name) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(FILE_TRANSFER + user_name + ".dat");
        ObjectInputStream StreamData = new ObjectInputStream(file);
        Pokemon pokemonTransfered = (Pokemon) StreamData.readObject();
        return pokemonTransfered;
    }

    /**
     * Borrara el archivo de transferencia ya usado
     *
     * @param user_name
     * @return TRUE si lo elimina o FALSE si no lo elimina
     */
    public static boolean deleteFile(String user_name) {
        File fileToDelete = new File(FILE_TRANSFER + user_name + ".dat");
        if (fileToDelete.exists()) {
            fileToDelete.delete();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Recogera un array de archivos, donde se limpiara todos los nombres de los
     * archivos solo dejando el nombre del usuario
     *
     * @return ArrayList de String de los nombres de todos los usuarios
     * registrados del momento.
     * @throws FileNotFoundException
     */
    public static ArrayList<String> readPlayers() throws FileNotFoundException {
        ArrayList<String> playerNameList = new ArrayList<>();
        File players = new File(FILE_BAG);//"users/mochilas/"

        if (players.isDirectory()) {
            File[] playerList = players.listFiles();
            for (File file : playerList) {
                String format_name = file.getName().replace("_mochila.dat", "");
                playerNameList.add(format_name);
            }
            return playerNameList;
        } else {
            return null;
        }

    }

    public static int getNumPokemonInBag(String user_name) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(FILE_BAG + user_name + FILE_NAME);
        ObjectInputStream StreamData = new ObjectInputStream(file);
        ArrayList<Pokemon> read = (ArrayList<Pokemon>) StreamData.readObject();
        return read.size();

    }

    public static boolean saveBagJSON(ArrayList<Pokemon> JSON_bag, String user_name)  {
        Gson json_bag = new Gson();
        String json = json_bag.toJson(JSON_bag);
        FileWriter write_json;
        try {
            write_json = new FileWriter(FILE_JSON_BAG + user_name + ".json");
            write_json.write(json);
            write_json.close();
        } catch (IOException ex) {
           return false;        }
        
        return true;

    }

    public static List loadJSONBag(String user_name) throws FileNotFoundException, IOException {
        String file = "";
        BufferedReader br = new BufferedReader(new FileReader(FILE_JSON_BAG + user_name + ".json"));
        String line;
        
        while((line = br.readLine()) != null){
            file += line;
        }
        
        br.close();
        Gson read = new Gson();
        List<Pokemon> aux;
        aux = read.fromJson(file, new TypeToken<List<Pokemon>>() {}.getType());

        return aux;
        
    }
}
