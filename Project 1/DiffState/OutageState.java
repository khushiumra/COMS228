package DiffState;

import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Changer;
import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.TownCell;

/**
 * @author Khushveen Kaur Umra
 * This class implements the abstract methods of the class TownCell
 *
 */

public class OutageState extends TownCell
{
   
	public OutageState(Town town, int row, int col)
	{
		super(town, row, col);
	}
	
	public State who()
	{
		return State.OUTAGE;
	}
	
	/**
	 * To determine the cell type in the next cycle
	 * The rules are as follows:
	 * 1) If there are 5 or more casual neighbors, it will convert to Streamer
	 * 2) If not, it stays the same
	 * 
	 * @param NewT - Town of the next cycle
	 */
	@Override
	
	public TownCell next(Town NewT)
	{
		census(TownCell.nCensus);
		
		if((TownCell.nCensus[Changer.CASUAL]) >= 5)
			return new Streamer(NewT, row, col);
		
		else
			return new Empty(Newt, row, col);
	}
}
