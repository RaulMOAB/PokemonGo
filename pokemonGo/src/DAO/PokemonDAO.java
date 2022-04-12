/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Objects.Pokemon;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alumne
 */
public class PokemonDAO implements Basic_operations {

    @Override
    public boolean catchPokemon(Pokemon obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Pokemon> displayBag() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void transferPokemon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getTransferedPokemon() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int validateUser(String user_name, String password) throws FileNotFoundException, IOException {
        File users = new File("users/" + user_name);
        Scanner file = new Scanner(users);

        if (users.exists()) {
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
         FileWriter writeNewUser = new FileWriter("users/"+user_name);
         writeNewUser.write(password);
         writeNewUser.close();
    }

}
