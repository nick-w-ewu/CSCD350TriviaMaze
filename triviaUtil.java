/**
 * TriviaUtil.java
 * Author: David Walker
 * Revision: 0
 * Date: 11/10/2015
 * This file is the Utility class of the Trivia Maze
 * currently holds:
 * -Save/Load functions
 * -difficulty menu
 */

import jdk.management.cmm.SystemResourcePressureMXBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TriviaUtil
{
    private static Scanner kb = new Scanner(System.in);

    /*
    loadSaveMenu function
    prompts the player with the option to load from save file, or to start a new game
    if the choice is to load from save file, function loadSavedGame is called
     */
    public static Object loadSaveMenu()
    {
        int choice = -1;
        boolean goodInput;
        Object obj = null;

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
                        obj = loadSavedGame();
                    }
                    else
                    {
                        //create new game
                        obj = new Player();
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
        return obj;
    }

    public static void saveGame(Player player) throws FileNotFoundException
    {
        File save = new File("saved.ser");
        boolean exists = save.exists();

        //if(!exists)
        //{
            createSave(save, player);
        //}

    }

    public static void createSave(File s, Player player) throws FileNotFoundException
    {
        PrintWriter save = new PrintWriter(s);

        System.out.println(" saving: " + player.getName() + " " + player.getNumItems() + " " + player.getqCorrect());

        save.println(player.getName());
        save.println(player.getNumItems());
        save.println(player.getqCorrect());
        save.close();
    }



/*
loadSavedGame function, attempts to create the file saved.ser then calls the readSaveFile
*/
    public static Object loadSavedGame()
    {
        Object obj = null;
        try
        {
            File fin = new File("saved.ser");
            Scanner save = new Scanner(fin);

            //open/read save file
            obj = readSaveFile(save);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Save file not found");
        }
        return obj;
    }

    /*
    readSaveFile function, takes a Scanner created from the save file, reads the data and recreates the objects
     */
    public static Object readSaveFile(Scanner save)
    {
        Player player = new Player();
        String name;
        int numItems, qCorrect;
        while(save.hasNextLine())
        {

            player.setName(save.nextLine());
            player.setNumItems(Integer.parseInt(save.nextLine()));
            player.setqCorrect(Integer.parseInt(save.nextLine()));

            System.out.println(player.getName() + " " + player.getNumItems() + " " + player.getqCorrect());
        }
        return player;
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


}
