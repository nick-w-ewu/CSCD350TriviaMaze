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

        TriviaUtil.loadSaveMenu();

        int t;
        do
        {
            t = TriviaUtil.playMenu();
        }while(t==1);






        TriviaUtil.traverseMaze();






    }

}