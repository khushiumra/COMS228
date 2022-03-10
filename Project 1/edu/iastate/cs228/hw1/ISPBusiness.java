package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner;
import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Town;
import java.util.Objects;


/**
 * @author Khushveen Kaur Umra
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */

public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	
	public static Town updatePlain(Town tOld) 
	{
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		
		for(int i = 0; i < tOld.getLength(); i++)
		{
			for(int j = 0; j < tOld.getWidth(); j++)
			{
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
			}
		}
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	
	public static int getProfit(Town town) 
	{
		int user = 0;
		
		for(int i =0; i < town.getLength(); i++)
		{
			for(int j = 0; j < town.getWidth(); j++)
			{
				if(town.grid[i][j].who() == State.CASUAL)
					
					user++;
			}
		}
		
		return user;
	}
	

	/**
	 * Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should only print the integer part (Just print the 
	 *  integer value. Example if profit is 35.56%, your output should be just: 35).
	 *  
	 * Note that this method does not throws any exception, thus you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) throws FileNotFoundException
	{
		
		Scanner scan = new Scanner(System.in);
		Town town = null;
		System.out.println("Poplulate a grid: 1: Random. 2: From Files \n");
		
		int choice = scan.nextInt();
		
		if(choice == 1)
		{
			System.out.print("Enter file path: \n");
			
			scan = new Scanner(System.in);
			
			String fileNew = scan.nextLine();
			
			town = new Town(fileNew);
			
			System.out.println();
		}
		
		else
			if (choice == 2)
			{
				System.out.print("Enter rows, columns and seed integer (Spaces): \n");
				
				scan = new Scanner(System.in);
				
				String logic = scan.nextLine();
				
				int length = Integer.parseInt(logic.substring(0, logic.indexOf(" ")));
				
				logic = logic.substring(logic.indexOf(" ") + 1);
				
				int width = Integer.parseInt(logic.substring(0, logic.indexOf(" ")));
				
				logic = logic.substring(logic.indexOf(" ") + 1);
				
				int seed = Integer.parseInt(logic);
				
				town = new Town(length, width);
				
				town.randomInit(seed);
				
				System.out.println(town);
				
			}
		
		int increasePlus = 0;
		
		int furtherIncrease = Objects.requireNonNull(town).getLength() * town.getWidth();
		
		
		for(int i = 0; i < 12; i++)
		{
			increasePlus += getProfit(town);
			town = updatePlain(town);
		}
		
		System.out.println((double) increasePlus / (furtherIncrease*12) * 100.0);
		
	}
}
