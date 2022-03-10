package DiffState;

import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Changer;
import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.TownCell;

/**
 * @author Khushveen Kaur Umra
 * This class implements the abstract methods of the TownCell Class
 *
 */
public class EmptyState extends TownCell
{
	
	public EmptyState(Town town, int row, int col)
	{
		super(town, row, col);
	}
	
	public State who()
	{
		return State.EMPTY;
	}
	
	/**
	 * To determine the cell type in the next cycle
	 * The rules are as follows:
	 * 1) If at most there is one empty neighbor or one outage neighbor, it will convert to Reseller
	 * 2) If there are 5 or more casual neighbors, it will convert to Streamer
	 * 3) If none of the above, it remains the same
	 * @param NewT - Town of the next cycle
	 */

	public TownCell next(Town NewT)
	{
		census(TownCell.nCensus);
		
		if((TownCell.nCensus[Changer.EMPTY] - 1 + TownCell.nCensus[Changer.OUTAGE]) <= 1)
			return new Reseller(NewT, row, col);
		
		else
			return new Casual(NewT, row, col);
	}
}
