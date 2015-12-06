/**
 * TriviaUtil.java
 * Author: David Walker
 * Revision: 3
 * Date: 11/10/2015
 * This file is the Utility class of the Trivia Maze
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
    private static DatabaseUtility db = new DatabaseUtility();
    private static QuestionHandler qh = new QuestionHandler();

    /*
    loadSaveMenu
    this function prompts the player with the option to load from save file, or to start a new game
    if the choice is to load from save file, function loadSavedGame is called to handle further details
     */

    public static void loadSaveMenu()
    {
        int choice = -1;
        boolean goodInput;

        do {
            //try
           // {
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
           // }
           // catch (Exception e)
           // {
           //     System.out.println("Input is not valid, try again");
           //     goodInput = false;
           //     String clear = kb.nextLine();
           // }
        } while(!goodInput);
    }

     /*
	 * saveGame method
	 * Creates a file object for the save file, if it already exists, prompts the user asking to overwrite or not
	 * if Yes, the function createSave is called, passing it the file object
	 * Parameters:
	 * none
	 */

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

     /*
	 * createSave Method, receives the File Object passed by the saveGame method
	 * Creates a PrintWriter object from the File object passed in
	 * then writes the data of the game to the file, calls the method printMaze to print the maze to the file
	 * Parameters:
	 * File object from saveGame
	 */

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

    /*
    * createNewGame method, creates new instances of the objects of the Game (Player and Maze)
    * Parameters:
    * None
    */
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
    Parameters:
    receives a Scanner object, which is created from the saved file
     */
    public static void readSaveFile(Scanner save)
    {
        String name;
        int numItems, qCorrect;

        maze = new Maze(3, 3);
        player = new Player("loadingfromSave");

        int r = 9;
        int c = 9;

        System.out.println(" r + c " + r + " " + c);

        if(save.hasNext())
        {
            player.setName(save.nextLine());
            player.setNumItems(Integer.parseInt(save.nextLine()));
            player.setqCorrect(Integer.parseInt(save.nextLine()));

            for(int x = 0; x < r; x++)
            {
                for(int y = 0; y < c; y++)
                {
                    String cell = save.nextLine();
                    //System.out.print(cell + " ");
                    loadMaze(maze.getMaze(), x, y, cell);
                }
                //System.out.println("");
            }

            //System.out.println(player.getName() + " " + player.getNumItems() + " " + player.getqCorrect());
        }
    }

    /*
    * difficultMenu - currently Unused
    * Prompts the user for which difficulty of Maze they'd like to play
    * would return an int dictating the difficulty they'd like to play
    */

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

    /*
    * playMenu Method, Prompts the user for 3 options, Traverse Maze, Save, Quit
    * Depending on the choice, each respective method would be called to carried out the action
    * Parameters:
    * None
    */

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

    /*
    * traverseMaze method - prints the maze to the user, then gets a direction/quit to main menu response from getDirection method call
    * if the user chooses to move in a direction which lands them on a question, question is retrieved from the question handler
    * if the user gets the question correct, they stay, if not they are returned to their previous location or something
    * Parameters:
    * None
    */

    public static void traverseMaze()
    {
        Question question;
        boolean isQuestionCell = false;
        int checkQuit;
        String questionType = "";
        boolean questionCorrect;

       do
       {
            maze.print();
            checkQuit = maze.getDirection();
            isQuestionCell = maze.getLandOnQuestion();

            if(isQuestionCell && checkQuit != -1)
            {
                questionType = maze.getQuestionType();
                try
                {
                    question = qh.checkFlagsReset(questionType, db);
                    questionCorrect = qh.handleQuestion(question, maze); //also updates the maze
                    if(questionCorrect)
                    {
                        player.setqCorrect(player.getqCorrect() + 1);
                    }
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }//end if

        } while(maze.pathExists() && !maze.getIsEnd() && checkQuit != -1);
        
            
    }

    /*
    * quitGame method - prints out the statistics of the game stores by the Player object
    * Parameters:
    * None
    */

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

    /*
    * loadMaze method - is called by the loadSaveFile method, it's passed the params below
    * it uses these to recreate a CellType, then stores that inside of the location of the maze i/j passed in
    * Parameters:
    * CellType[][] maze - the maze array from the main maze object to recreate the maze from save
    * int i, j - row/col location
    * String cell - the string from the save file dictating what the location will be
    */

    public static void loadMaze(CellType[][] m, int i, int j, String cell)
    {
        CellType c;

        if(cell.equals("S"))
        {
            c = CellType.START;
        }
        else if(cell.equals("G"))
        {
            c =  CellType.END;
        }
        else if(cell.equals("O"))
        {
            c =  CellType.OPEN;
        }
        else if(cell.equals("W"))
        {
            c =  CellType.WALL;
        }
        else if(cell.equals("?"))
        {
            c =  CellType.QUESTION;
        }
        else if(cell.equals("V"))
        {
            c =  CellType.VISITED;
        }
        else if(cell.equals("."))
        {
            c =  CellType.SUCCESS;
        }
        else if(cell.equals("#"))
        {
            c = CellType.BEEN_HERE;
        }
        else if(cell.equals("_"))
        {
            c =  CellType.BLANK;
        }
        else if(cell.equals("P"))
        {
            System.out.println("cur pos: " + i + " " + j);
            c =  CellType.PLAYER;
            maze.setPosition(i, j);
        }
        else if(cell.equals(" "))
        {
            c =  CellType.EMPTY;
        }
        else if(cell.equals("1"))
        {
            c =  CellType.TFQUESTION;
        }
        else if(cell.equals("2"))
        {
            c =  CellType.MCQUESTION;
        }
        else if(cell.equals("3"))
        {
            c =  CellType.SAQUESTION;
        }
        else
        {
            c = CellType.BLANK;
        }

        m[i][j] = c;
    }//end setPosition

}
