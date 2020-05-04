/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exception.ItemAlreadyExistsException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        System.out.println("Is it a normal priority item or high priority (hp) item?");
        System.out.println("Chose between 'normal' and 'hp' high priority");
        String choice=input.nextLine();
        
        if(choice.equals("normal")){
        String toAdd = input.nextLine();
        BaseToDo toDo = new BaseToDo(toAdd);
        if (chores.contains(toDo)) {
            throw new ItemAlreadyExistsException("The item already exists");
        } else {
            chores.add(toDo);
        }}
        
        if(choice.equals("hp")){
            LocalDateTime deadline=LocalDateTime.now();
            System.out.println("Input item");
        String toAdd = input.nextLine();
        System.out.println("Input deadline(insert number of seconds from now):");
       long time=Integer.parseInt(input.nextLine());
       deadline=deadline.plusSeconds(time);
        HighPriorityToDo toDo = new HighPriorityToDo(deadline,toAdd);
        if (chores.contains(toDo)) {
            throw new ItemAlreadyExistsException("The item already exists");
        } else {
            chores.add(toDo); 
            List<Future<HighPriorityToDo>> futures = new ArrayList<>();
            ExecutorService es = Executors.newCachedThreadPool();
            
            Future<HighPriorityToDo> future = (Future<HighPriorityToDo>) es.submit(new CustomRunnable(toDo));
                futures.add(future);
        }
        }
        else System.out.println("You will have to choose a valid option");
        
    }

    public void displayItems() {
        int counter = 1;
        for (BaseToDo chore : chores) {
            System.out.println(counter + ". item " + chore.getContent());
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
