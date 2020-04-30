/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import exception.ItemAlreadyExistsException;
import java.util.ArrayList;
import java.util.Scanner;
import model.BaseToDo;
import model.User;

/**
 *
 * @author Bogdan
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ItemAlreadyExistsException {
       handleUserInput();
        
     /*   User firstuser=new User("Bogdan",new ArrayList<BaseToDo>());
        firstuser.addItem();
        firstuser.addItem();
        firstuser.addItem();
       // firstuser.removeItem();
       firstuser.updateItem();
        firstuser.displayItems();
        //System.out.println(firstuser);    */
        
    }
     public static void handleUserInput() throws ItemAlreadyExistsException{
    Scanner input=new Scanner(System.in);
    System.out.println("Input your username:");
    String user=input.nextLine();
    System.out.println("Hello " + user);
    User firstuser=new User(user,new ArrayList<BaseToDo>());
    String choice;
    System.out.print("1. Display \n2. Add \n3. Remove \n4. Update \n5. Exit \n");
    do{
         choice=input.nextLine();
         switch(choice){
         case("1"): firstuser.displayItems();break;
         case("2"): firstuser.addItem(); break;
         case("3"): firstuser.removeItem(); break;
         case("4"): firstuser.updateItem(); break;
         case("5"): System.out.println("Goodbye!"); break;
         default: System.out.println("Please chose a valid option");
         }
    
    }
    while(!choice.equals("5"));
    }
}
