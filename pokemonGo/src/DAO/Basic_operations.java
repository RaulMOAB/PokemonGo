/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Objects.Pokemon;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author alumne
 */
public interface Basic_operations {
    public Pokemon appearsPokemon() throws FileNotFoundException;//cazar pokemon
    public boolean catchPokemon(Pokemon add);
    public ArrayList<Pokemon> displayBag();
    public void transferPokemon();
    public void getTransferedPokemon();//recibir pokemon transferido
    public int getAmount();
}
