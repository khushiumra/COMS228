package edu.iastate.cs228.hw1;

/**
 * @author Khushveen Kaur Umra
 * 	This class is for the easy transition between states
 */

public class Changer 
{
	public static final int RESELLER = 0;
	public static final int EMPTY = 1;
	public static final int CASUAL = 2;
	public static final int OUTAGE = 3;
	public static final int STREAMER = 4;
	
	/**
	 * @param value - Some number between 0-4
	 * @return state that corresponds to the specific value
	 */
	
	public static State returnTheState(int value)
	{
		switch (value)
		{
		case RESELLER:
			return State.RESELLER;
		case EMPTY:
			return State.EMPTY;
		case CASUAL:
			return State.CASUAL;
		case OUTAGE:
			return State.OUTAGE;
		case STREAMER:
			return State.STREAMER;
		}
		
		return null;
	}

	/** 
	 * Returning an integer that corresponds to its state
	 * @param The state that would convert into an integer
	 * @return The integer
	 */
	
	public static int returnValueofTheState(State state)
	{
		switch (state)
		{
		case RESELLER:
			return RESELLER;
		case EMPTY:
			return EMPTY;
		case CASUAL:
			return CASUAL;
		case OUTAGE:
			return OUTAGE;
		case STREAMER:
			return STREAMER;
		}
		
		return -1;
	}
	
	/**
	 * Returns a single character string
	 * @param state of the town
	 * @return the letter string
	 */
	
	public static String returnTypeCell(State state)
	{
		switch (state)
		{
		case RESELLER:
			return "R";
		case EMPTY:
			return "E";
		case CASUAL:
			return "C";
		case OUTAGE:
			return "O";
		case STREAMER:
			return "S";
		}
		
		return null;
	}
	
	/**
	 * Returns the state that corresponds to it's single letter string
	 * @param letter - The specific letter for the state
	 * @return 
	 */
	
	public static State returnStringState(char letter)
	{
		switch (letter)
		{
		
		case 'R':
			return State.RESELLER;
		case 'E':
			return State.EMPTY;
		case 'C':
			return State.CASUAL;
		case 'O':
		    return State.OUTAGE;
		case 'S':
			return State.STREAMER;
		}
		
		return null;
	}
	
	/**
	 * @param state
	 * @param town - to which towncell it belongs to
	 * @param row - the specific row
	 * @param col - the specific column
	 * @return
	 */
	
	public static TownCell returnTownCellFromState(State state, Town town, int row, int col)
	{
		switch (state)
		{
		case RESELLER:
			return new DiffState.Reseller(town, row, col);
		case EMPTY:
			return new DiffState.Empty(town, row, col);
		case CASUAL:
			return new DiffState.Casual(town, row, col);
		case OUTAGE:
			return new DiffState.Outage(town, row, col);
		case STREAMER:
			return new DiffState.Streamer(town, row, col);
		}
		
		return null;
	}
}

