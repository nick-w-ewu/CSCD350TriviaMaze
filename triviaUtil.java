/**
 * TriviaUtil.java
 * Author: David Walker
 * Revision: 2
 * Date: 11/10/2015
 * This file is the Utility class of the Trivia Maze
 * currently holds:
 * -Save/Load functions
 * -difficulty menu
 * -Tied together with Maze/Player classes
 */

import com.sun.org.apache.xpath.internal.SourceTree;
import jdk.management.cmm.SystemResourcePressureMXBean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class TriviaUtil
{
    private static Scanner kb = new Scanner(System.in);
    private static Maze maze;
    private static Player player;

    /*
    loadSaveMenu function
    prompts the player with the option to load from save file, or to start a new game
    if the choice is to load from save file, function loadSavedGame is called
     */
    public static void loadSaveMenu()
    {
        int choice = -1;
        boolean goodInput;

        do {
            try
            {
                System.out.println("What would you like to do?");
                System.out.println("1) Load saved game");
                System.out.println("2) Start a new game");
                choice = kb.nextInt();
                if(choice != 1 && choice != 2)
                {
                    System.out.println("Input is not a valid choice, try again");
                    goodInput = false;
                }
                else
                {
                    goodInput = true;
                    if(choice == 1)
                    {
                        loadSavedGame();
                    }
                    else
                    {
                        createNewGame();
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("Input is not a valid choice, try again");
                goodInput = false;
                String clear = kb.nextLine();
            }
        } while(!goodInput);
    }

    public static void saveGame() throws FileNotFoundException
    {
        File save = new File("saved.ser");
        boolean exists = save.exists();
        boolean overwrite = false;
        String choice;

        if(exists)
        {
            System.out.println("Would you like to overwrite your save? (Y/N)");
            kb.nextLine();
            choice = kb.nextLine();

            if(choice.compareToIgnoreCase("Yes") == 0 || choice.compareToIgnoreCase("y") == 0)
            {
                createSave(save);
            }
            else
            {
                System.out.println("Game not saved");
            }
        }

    }

    public static void createSave(File s) throws FileNotFoundException
    {
        PrintWriter save = new PrintWriter(s);

        System.out.println(" saving: " + player.getName() + " " + player.getNumItems() + " " + player.getqCorrect());

        save.println(player.getName());
        save.println(player.getNumItems());
        save.println(player.getqCorrect());
        maze.printMaze(save); //save maze so it can be loaded
        save.close();
    }

    public static void createNewGame()
    {
        maze = new Maze(3,3);
        player = new Player();
    }

/*
loadSavedGame function, attempts to create the file saved.ser then calls the readSaveFile
*/
    public static void loadSavedGame()
    {
        Object obj = null;
        try
        {
            File fin = new File("saved.ser");
            Scanner save = new Scanner(fin);

            //open/read save file
            readSaveFile(save);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Save file not found");
        }
    }

    /*
    readSaveFile function, takes a Scanner created from the save file, reads the data and recreates the objects
     */
    public static void readSaveFile(Scanner save)
    {
        String name;
        int numItems, qCorrect;
        while(save.hasNextLine())
        {

            player.setName(save.nextLine());
            player.setNumItems(Integer.parseInt(save.nextLine()));
            player.setqCorrect(Integer.parseInt(save.nextLine()));

            while(save.hasNextLine())
            {
                //load in maze
            }

            System.out.println(player.getName() + " " + player.getNumItems() + " " + player.getqCorrect());
        }
    }

    public static int difficultyMenu()
    {
        int difficulty = -1;
        boolean goodInput = false;

        do
        {
            try
            {
                System.out.println("What difficulty would you like to play?");
                System.out.println("1) Normal");
                System.out.println("2) Hard");
                difficulty = kb.nextInt();
                if (difficulty > 2 || difficulty < 1)
                {
                    System.out.println("Input is not a valid choice, try again");
                    goodInput = false;
                }
                else
                {
                    goodInput = true;
                }
            } catch (Exception e)
            {
                System.out.println("Input is not a valid integer, try again");
                goodInput = false;
                String clear = kb.nextLine();
            }
        } while (!goodInput);

        return difficulty;
    }

    public static int playMenu()
    {
        int choice = -1;
        boolean goodInput = false;

        do
        {
            try
            {
                System.out.println("What would you like to do?");
                System.out.println("1) Traverse Maze");
                System.out.println("2) Save Game");
                System.out.println("3) Quit");

                choice = kb.nextInt();

                if (choice > 3 || choice < 1)
                {
                    System.out.println("Input is not a valid choice, try again");
                    goodInput = false;
                }
                else
                {
                    goodInput = true;
                }
            } catch (Exception e)
            {
                System.out.println("Input is not a valid integer, try again");
                goodInput = false;
                String clear = kb.nextLine();
            }
        } while (!goodInput);

        return choice;
    }

    public static void traverseMaze()
    {
            maze.print();
            maze.getDirection();
    }


    public static void quitGame()
    {
        player.seteTime(System.currentTimeMillis());
        long end = player.geteTime() - player.getsTime();

        long second = (end / 1000) % 60;
        long minute = (end / (1000 * 60)) % 60;
        long hour = (end / (1000 * 60 * 60)) % 24;

        String fDate = String.format("%02d:%02d:%02d.%d", hour, minute, second, end);

        System.out.println("");
        System.out.println(player.getName() + ": ");
        System.out.println("You answered " + player.getqCorrect() + " questions correct");
        System.out.println("Your game lasted " + fDate);
    }
}
