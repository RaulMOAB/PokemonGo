/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.util.ArrayList;
import java.util.Scanner;
import javax.lang.model.SourceVersion;

/**
 *
 * @author alumne
 */
public class Menu {
    private String title;
    private ArrayList<Option> options;

    public Menu(String title) {
        this.title = title;
        options = new ArrayList<Option>();//inicializa ArrayList
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }
    /**
     * Añade opciones al menu
     * @param nueva opcion
     */
    public void add(Option nueva){
        //se pueden poner validaciones
        options.add(nueva);
    }
    
    public boolean remove(Option opcion){
        return options.remove(opcion);
    }
    
    public Option removeByIndex(int index){
        return options.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------" + this.getTitle() + "-------" );
        sb.append("\n");
        for (Option option : options) {
            sb.append(option.toString());
        }
        return sb.toString();
    }
    
    public void displayMenu(){
        System.out.println("----" + this.title + "----");
        for (int i = 0; i < options.size(); i++) {           
            System.out.format("%d - %s \n", i, options.get(i).toString());
            //System.out.println(options.get(i).toString());
        }
    }
    
    public int choose(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select option: ");
        int option;
        do {            
            option = sc.nextInt();
        } while (option < 0 || option > options.size());
        return option;
    }


    
    
    
}
