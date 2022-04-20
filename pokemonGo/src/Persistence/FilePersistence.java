/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistence;

import Objects.Pokemon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
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

    public static boolean saveBag(ArrayList<Pokemon> bag, String user_name) throws FileNotFoundException, IOException {
        FileOutputStream write;
        write = new FileOutputStream(FILE_BAG + user_name + FILE_NAME);
        ObjectOutputStream StreamData = new ObjectOutputStream(write);//si no existe lo crea
        StreamData.writeObject(bag);
        return true;
    }

    public static ArrayList<Pokemon> readBag(String user_name) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(FILE_BAG + user_name + FILE_NAME);
        ObjectInputStream StreamData = new ObjectInputStream(file);
        ArrayList<Pokemon> read = (ArrayList<Pokemon>) StreamData.readObject();
        return read;
    }

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

    public static boolean transfer(String user_name, Pokemon aux) throws FileNotFoundException, IOException {
        FileOutputStream write;
        write = new FileOutputStream(FILE_TRANSFER + user_name + ".dat");
        ObjectOutputStream StreamData = new ObjectOutputStream(write);
        StreamData.writeObject(aux);
        return true;

    }

    public static Pokemon readTransferFile(String user_name) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(FILE_TRANSFER + user_name + ".dat");
        ObjectInputStream StreamData = new ObjectInputStream(file);
        Pokemon pokemonTransfered = (Pokemon) StreamData.readObject();
        return pokemonTransfered;
    }

    public static boolean deleteFile(String user_name) {
        File fileToDelete = new File(FILE_TRANSFER + user_name + ".dat");
        if (fileToDelete.exists()) {
            fileToDelete.delete();
            return true;
        } else {
            return false;
        }
    }

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
        }else{
            return null;
        }
        
    }

}
