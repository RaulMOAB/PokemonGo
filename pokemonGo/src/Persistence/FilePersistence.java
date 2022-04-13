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
    private final static String file_name = "mochila.dat";
    
    public static boolean saveBag(ArrayList<Pokemon> bag) throws FileNotFoundException, IOException{
        FileOutputStream write = null;
        write = new FileOutputStream(file_name);
        ObjectOutputStream StreamData = new ObjectOutputStream(write);
        StreamData.writeObject(bag);
        
        return true;
    }
    
    public static ArrayList<Pokemon> readBag(ArrayList<Pokemon> bag) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream file = new FileInputStream(file_name);
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
    
  
}
