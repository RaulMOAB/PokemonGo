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
        int compare = getName().compareTo(o.getName()); 
        if( compare == 0 ) {
            // si compare es 0 significa que su nombre es igual, compara y retorna en base a CP
            return getCP() - o.getCP(); //al restar va a dar negativo si es inferior al CP, positivo si es superior al CP o 0 si son iguales en CP
        } else {
           return compare;
        }
    }

    @Override
    public boolean equals(Object obj) {//esta un poco raro puesto
        boolean exist = false;
        if(obj instanceof Pokemon){
            Pokemon p = (Pokemon) obj;
            exist=this.name.equalsIgnoreCase(p.name);
        }
        return exist;
    }
    
    
    
    
}
