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
    public Pokemon catchPokemon() throws FileNotFoundException;//cazar pokemon
    public ArrayList<Pokemon> displayBag();
    public void transferPokemon();
    public void getTransferedPokemon();//recibir pokemon transferido
}
