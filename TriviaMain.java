import java.io.FileNotFoundException;
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
    public static void main(String args[]) throws FileNotFoundException
    {
        Scanner kb = new Scanner(System.in);
        Player user = new Player();
        Player namedUser = new Player("David");

        System.out.println(user.getName() + " " + user.getNumItems() + " " + user.getqCorrect());
        System.out.println(namedUser.getName() + " " + namedUser.getNumItems() + " " + namedUser.getqCorrect());

        TriviaUtil.saveGame(namedUser);
        Player player = (Player)TriviaUtil.loadSaveMenu();

        System.out.println(player.getName() + " " + player.getNumItems() + " " + player.getqCorrect());



    }

}