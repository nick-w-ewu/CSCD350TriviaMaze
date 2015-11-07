import java.util.Scanner;

/**
 * TriviaMain.java
 * Author: David Walker
 * Revision: 0
 * Date: 10/04/2015
 * This is the main for the Trivia Maze
 */

public class TriviaMain
{
    public static void main(String args[])
    {
        Scanner kb = new Scanner(System.in);
        Player user = new Player();
        Player namedUser = new Player("David");

        System.out.println(user.getName() + " " + user.getNumItems() + " " + user.getqCorrect());
        System.out.println(namedUser.getName() + " " + namedUser.getNumItems() + " " + namedUser.getqCorrect());

        /* Temporary location for a difficulty menu
        * will eventually be moved to Maze or some other class, Maze factory?
        */

        int difficulty;
        boolean goodInput = false;

        do {
            try
            {
                System.out.println("What difficulty would you like to play?");
                System.out.println("1) Normal");
                System.out.println("2) Hard");
                difficulty = kb.nextInt();
                if(difficulty > 2 || difficulty < 1)
                {
                    System.out.println("Input is not a valid choice, try again");
                    goodInput = false;
                }
                else
                {
                    goodInput = true;
                }
            }
            catch (Exception e)
            {
                System.out.println("Input is not a valid integer, try again");
                goodInput = false;
                String clear = kb.nextLine();
            }
        } while(!goodInput);


    }

}
