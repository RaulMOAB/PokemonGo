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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumne
 */
public class PokemonDAO implements Basic_operations {

    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<Pokemon> pokemonBag = new ArrayList<>();

    @Override
    public Pokemon appearsPokemon() throws FileNotFoundException {
        Pokemon wildPokemon = FilePersistence.catchWildPokemon(nameList);
        
       return wildPokemon;
    }
    public boolean catchPokemon(int key, int answer, Pokemon wild){
        if(key==answer){
            pokemonBag.add(wild);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Pokemon> displayBag() {
        return pokemonBag;
    }

    @Override
    public Pokemon transferPokemon(String user_name, Pokemon aux)throws FileNotFoundException, IOException, ClassNotFoundException {
          int index = pokemonBag.indexOf(aux);
          System.out.println("Indice encontrado " + index);
          if (index != -1) {// encuentra el pokemon a transferir
            FilePersistence.transfer(user_name, aux);
            //si ha ido bien borras por posicion
            return pokemonBag.remove(index);
        }else{
              return null;
          }
    }

    @Override
    public void getTransferedPokemon(String user_transfer) throws IOException, ClassNotFoundException{
        System.out.println(user_transfer);
            FilePersistence.readTransferFile(user_transfer);
   
    }

    public int validateUser(String user_name, String password) throws FileNotFoundException, IOException {
        File users = new File("users/" + user_name);
        Scanner file = new Scanner(users);

        if (users.exists()) {//
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

    public void newUserLogin(String user_name, String password) throws IOException {
        FileWriter writeNewUser = new FileWriter("users/" + user_name);
        writeNewUser.write(password);
        writeNewUser.close();
    }

    @Override
    public int getAmount() {
        return pokemonBag.size();
    }

    public int getDifficult(Pokemon wildPokemon) {
        int i = wildPokemon.getCP() / 10;
        if(i==0){
            i=1;
        }
        return i;
    }
    
      public int userBag(String user_name) throws IOException{
       if( FilePersistence.saveBag(pokemonBag, user_name)){
           return pokemonBag.size();
       }else{
           return 0;
       }
    }
    
    public int recoverBag(String user_name) throws IOException, FileNotFoundException, ClassNotFoundException{
      pokemonBag = FilePersistence.readBag(user_name);

      return pokemonBag.size();
    }

    public boolean existPokemonInMyBag(Pokemon wildPokemon) {
        return pokemonBag.contains(wildPokemon);
    }


}
