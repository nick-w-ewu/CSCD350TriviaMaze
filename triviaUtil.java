/**
 * Created by David on 11/14/2015.
 */

import jdk.management.cmm.SystemResourcePressureMXBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class triviaUtil
{
    private static Scanner kb = new Scanner(System.in);

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
                        //create new game
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

        System.out.println(" s " + player.getName() + " " + player.getNumItems() + " " + player.getqCorrect());

        save.println(player.getName());
        save.println(player.getNumItems());
        save.println(player.getqCorrect());
        save.close();
    }
/*
    private String name;
    private int numItems;
    private int qCorrect;
  */

    public static void loadSavedGame()
    {
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

    public static void readSaveFile(Scanner save)
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
    }

    public static int difficultMenu()
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
