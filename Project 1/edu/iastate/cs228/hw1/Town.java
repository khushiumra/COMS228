package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.Objects;
import edu.iastate.cs228.hw1.Changer;


/**
 *  @author Khushveen Kaur Umra
 *
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) 
	{
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		//TODO: Write your code here.
		
		FileHandler handle = new FileHandler(inputFileName);
		
		if(!handle.exists())
		{
			throw new FileNotFoundException();
		}
		
		String amount = handle.getLine(0);
		this.length = Integer.parseInt(amount.substring(amount.indexOf(" ")));
		this.width = Integer.parseInt(amount.substring(amount.indexOf(" ")+1));
		grid = new TownCell[length][width];
		
		for(int i = 0; i < length; i++)
		{
			String row1 = handle.getLine(i+1).replaceAll(" ", "");
			
			for(int j = 0; j < width; j++)
			{
				State state = Changer.returnStringState(row1.charAt(j));
				grid[i][j] = Changer.returnTownCellFromState(Objects.requireNonNull(state), this, i, j);
			}
		}
	}
	
	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() 
	{
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) 
	{
		Random rand = new Random(seed);
		
		for(int i = 0; i < getLength(); i++)
		{
			for(int j = 0; j < getWidth(); j++)
			{
				int ranNum = rand.nextInt(5);
				grid[i][j] = Changer.returnTownCellFromState(Objects.requireNonNull(Changer.returnTheState(ranNum)), this, i, j);
			}
		}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";
		
		for(int i = 0; i < getLength(); i++)
		{
			for(int j = 0; j < getWidth(); j++)
			{
				s.append(Changer.returnTypeCell(grid[i][j].who())).append(" ");
			}
			
			s.append("\n");
		}
		return s.toString();
	}
}
