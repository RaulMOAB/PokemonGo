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
    /**
     * Aparecera un pokemon aleatorio
     * @return Pokemon aleatorio
     * @throws FileNotFoundException 
     */
    public Pokemon appearsPokemon() throws FileNotFoundException;
    
    /**
     * Si el valor introducido por el usuario es igual a la respuesta correcta el pokemon salvaje sera capturado.
     * Si el valor introducido no es correcto no sera capturado y el pokemon escapara.
     * @param key
     * @param answer
     * @param wild
     * @return TRUE si es capturado, FALSE si se ha escapado
     */
    public boolean catchPokemon(int key, int answer, Pokemon wild);
    
    /**
     * @return ArrayList  de los pokemons (Mochila).
     */
    public ArrayList<Pokemon> getPokemonBag();
    
    /**
     * Usuario hara una transferencia creando un archivo con el Pokemon pasado, llamado por el nombre del usuario al que se lo quiere transferir.
     * @param user_name
     * @param pok
     * @return Pokemon transferido para eliminarlo de la mochila del dueño.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Pokemon transferPokemon(String user_name, Pokemon pok)throws FileNotFoundException, IOException, ClassNotFoundException;
    
    /**
     * Recogera la transferencia.
     * Leera el archivo de transferencia correspondiente a su nombre y le dara el Pokemon leido del archivo.
     * @param user_transfer
     * @return Pokemon transferido.
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public Pokemon getTransferedPokemon(String user_transfer)throws IOException, ClassNotFoundException;
    
    /**
     * recogera el tamaño de la mochila.
     * @return tamaño de la mochila.
     */
    public int getNumPokemon();
}
