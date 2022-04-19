/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Objects.Pokemon;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author alumne
 */
public interface Basic_operations {
    public Pokemon appearsPokemon() throws FileNotFoundException;//aparece pokemon
    public boolean catchPokemon(int key, int answer, Pokemon wild);
    public ArrayList<Pokemon> displayBag();
    public Pokemon transferPokemon(String user_name, Pokemon pok)throws FileNotFoundException, IOException, ClassNotFoundException;
    public void getTransferedPokemon();//recibir pokemon transferido
    public int getAmount();
}
