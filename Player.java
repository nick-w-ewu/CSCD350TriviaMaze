/**
 * Player.java
 * Author: David Walker
 * Revision: 0
 * Date: 10/04/2015
 * This is a class for the User
 * keeps track of some basic stats and tools
 */

import java.io.Serializable;
import java.util.Scanner;

public class Player implements Serializable
{
    private String name;
    private int numItems;
    private int qCorrect;

    //Default constructor,
    Player()
    {
        this.name = playerName();
        this.numItems = 0;
        this.qCorrect = 0;
    }

    //Constructor that initializes the Player obj with a name
    Player(String name)
    {
        this.name = name;
        this.numItems = 0;
        this.qCorrect = 0;
    }

    String playerName()
    {
        //Player player = new Player();
        Scanner kb = new Scanner(System.in);
        String n = "";
        do
        {
            System.out.println("What's your name?");
            n = kb.nextLine();

            if (n.length() < 1)
            {
                System.out.println("Name must be at least 1 character");
            }
        }while(n.length() >= 1);

        //player.setName(name);
        return n;
    }

    //Not sure of what all gets/sets we're gonna end up needing, so just have them all for now

    public int getNumItems()
    {
        return numItems;
    }

    public void setNumItems(int numItems)
    {
        this.numItems = numItems;
    }

    public int getqCorrect()
    {
        return qCorrect;
    }

    public void setqCorrect(int qCorrect)
    {
        this.qCorrect = qCorrect;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
