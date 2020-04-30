/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exception.ItemAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Bogdan
 */
public class User {

    String name;
    List<BaseToDo> chores = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", chores=" + chores + '}';
    }

    public User(String name, ArrayList<BaseToDo> chores) {
        this.name = name;
        this.chores = chores;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BaseToDo> getChores() {
        return chores;
    }

    public void setChores(List<BaseToDo> chores) {
        this.chores = chores;
    }

    public void addItem() throws ItemAlreadyExistsException {
        Scanner input = new Scanner(System.in);
        System.out.println("Add content to your to do list:");
        String toAdd = input.nextLine();
        BaseToDo toDo = new BaseToDo(toAdd);
        if (chores.contains(toDo)) {
            throw new ItemAlreadyExistsException("The item already exists");
        } else {
            chores.add(toDo);
        }
    }

    public void displayItems() {
        int counter = 1;
        for (BaseToDo chore : chores) {
            System.out.println(counter + " item " + chore.getContent());
            counter++;
        }
    }

    public void removeItem() {
        Scanner input = new Scanner(System.in);
        displayItems();
        System.out.println("Chose which item to remove");
        int remove = input.nextInt();
        if (remove > 0 && remove <= chores.size() && chores.size() > 0) {
            chores.remove(remove - 1);
            System.out.println("Item has been removed");
        } else {
            System.out.println("Item could not be removed");
        }
    }

    public void updateItem() throws ItemAlreadyExistsException {
        Scanner input=new Scanner(System.in);
        displayItems();
        System.out.println("Input item you want to update: (item number) (content)");
        String value=input.nextLine();
        String[] phrase=value.split(" ");
        int digit=Integer.parseInt(phrase[0]);
        chores.remove(digit-1);
        String toAdd="";
        for(int i=1;i<phrase.length;i++){
        toAdd=toAdd+" "+phrase[i];
        }
        BaseToDo toDo = new BaseToDo(toAdd);
        if (chores.contains(toDo)) {
            throw new ItemAlreadyExistsException("The item already exists");
        } else {
            chores.add(digit-1,toDo);
        }
    }
}
