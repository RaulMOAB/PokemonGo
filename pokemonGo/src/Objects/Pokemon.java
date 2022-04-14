/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author alumne
 */
public class Pokemon implements Serializable, Comparable<Pokemon>{
    private final String name;
    private int CP;

    public Pokemon(String name) {
        this.name = name;
         setCP(CP);
    }

    public Pokemon(String name, int CP) {
        this.name = name;
         setCP(CP);
    }

    public int setCP(int CP) {
        Random r = new Random();
        return this.CP = r.nextInt(100) + 1;
    }

    public String getName() {
        return name;
    }

    public int getCP() {
        return CP;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public String toString() {
        return "\t"+ name + "\t"+ " CP: " + CP;
    }

    @Override
    public int compareTo(Pokemon o) {
        if(this.getCP()== o.getCP()){
            return 0;
        }
        else if(this.getCP()>o.getCP()){
            return 1;
        }else{
            return -1;
        }
    }
    
    
}
