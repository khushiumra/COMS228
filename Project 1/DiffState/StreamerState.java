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

public class StreamerState extends TownCell
{
   
	public StreamerState(Town town, int row, int col)
	{
		super(town, row, col);
	}
	
	public State who()
	{
		return State.STREAMER;
	}
	
	/**
	 * It determines the cell type in the next cycle
	 * The rules are as follows:
	 * 1)If there is at most one empty neighbor or one outage neighbor, it will convert to Reseller
	 * 2)If there is any reseller in its neighborhood, it will convert to Outage
	 * 3)If there is any neighbor who is an Outage, it will convert to Empty
	 * 4) If none of the above, it will remain the same.
	 * 
	 * @param NewT - Town of the next cycle
	 */
	
	public TownCell next(Town NewT)
	{
		census(TownCell.nCensus);
		
		if((TownCell.nCensus[Changer.EMPTY] + TownCell.nCensus[Changer.OUTAGE]) <= 1)
			return new Reseller(NewT, row, col);
		
		else
			if(TownCell.nCensus[Changer.RESELLER] >= 1)
				return new Outage(NewT, row, col);
		
			else
				if(TownCell.nCensus[Changer.OUTAGE] >= 1)
					return new Empty(NewT, row, col);
		
				else
					return new Streamer(NewT, row, col);
	}
}
