/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolist;

import exception.ItemAlreadyExistsException;
import exception.ParsingException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    public static void main(String[] args) throws ItemAlreadyExistsException, IOException, ParsingException {
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

    public static void handleUserInput() throws ItemAlreadyExistsException, IOException, ParsingException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input your username:");
        String user = input.nextLine();
        System.out.println("Hello " + user);
        User firstuser = new User(user, new ArrayList<BaseToDo>());
        String choice;
        System.out.print("1. Display \n2. Add \n3. Remove \n4. Update \n5. Generate File \n6. Import file \n7. Exit\n");
        do {
            choice = input.nextLine();
            switch (choice) {
                case ("1"):
                    firstuser.displayItems();
                    break;
                case ("2"):
                    firstuser.addItem();
                    break;
                case ("3"):
                    firstuser.removeItem();
                    break;
                case ("4"):
                    firstuser.updateItem();
                    break;
                case ("5"):
                    generateToDoFile(firstuser);
                    break;
                case ("6"):
                    importToDoList(firstuser);
                    break;
                case ("7"):
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Please chose a valid option");
            }

        } while (!choice.equals("7"));

    }

    public static void generateToDoFile(User user) throws IOException {
        Scanner input = new Scanner(System.in);
        String value;
        if (!user.getChores().isEmpty()) {
            System.out.println("Are you sure you want to generate the file?");
            System.out.println("Enter yes if you want to proceed:");
            if (input.nextLine().equals("yes")) {

                try {
                    int counter = 1;
                    FileWriter writer = new FileWriter(user.getName() + ".txt", true);
                    writer.write("TODO Items for:\n");
                    for (BaseToDo chore : user.getChores()) {
                        writer.write(counter + ". item " + chore.getContent() + "\n");
                        counter++;
                    }
                    writer.close();
                    System.out.println("File has been generated");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Input a valid command");
            }

        } else {
            System.out.println("There is not content to generate file");
        }

    }

    public static void importToDoList(User user) throws FileNotFoundException, IOException, ParsingException {
        try (Stream<Path> walk = Files.walk(Paths.get("A:\\repos\\ToDoList"))) {

            List<String> result = walk.map(x -> x.toString())
                    .filter(f -> f.endsWith(".txt")).collect(Collectors.toList());

            result.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Choose the file you want to import");
        Scanner input = new Scanner(System.in);
        String importfile = input.nextLine();
        StringBuilder build = new StringBuilder();
        String[] array;
        BufferedReader br = new BufferedReader(new FileReader(importfile + ".txt"));
        String txtNum;
        int counter = 1;
        String firstline = br.readLine();
        if (!firstline.startsWith("TODO Items for:")) {
            throw new ParsingException("File is corrupt");
        }

        while ((txtNum = br.readLine()) != null) {
            if(!txtNum.startsWith(counter+".")) throw new ParsingException("File is corrupt");
            array = txtNum.split(" ");
            for (int i = 2; i < array.length; i++) {
                build.append(array[i] + " ");
            }
            user.getChores().add(new BaseToDo(build.toString()));
            build.delete(0, build.length()); counter++;
        }
        System.out.println("File has been imported");
    }

}
