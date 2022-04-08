/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

import java.util.Objects;

/**
 *
 * @author alumne
 */
public class Pokemon {
    private final String name;
    private int CP;

    public Pokemon(String name) {
        this.name = name;
    }

    public Pokemon(String name, int CP) {
        this.name = name;
        this.CP = CP;
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pokemon other = (Pokemon) obj;
        return this.name.equalsIgnoreCase(other.name);
    }

    @Override
    public String toString() {
        return "Pokemon: " + name + "\n"
                + " CP: " + CP;
    }
    
    
}
