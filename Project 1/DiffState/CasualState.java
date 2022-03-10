package DiffState;

import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Changer;
import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.TownCell;

/**
 * @author Khushveen Kaur Umra
 *
 *This class implements the abstract methods of the class TownCell
 */
public class CasualState extends TownCell
{

	public CasualState(Town town, int row, int col)
	{
		super(town, row, col);
	}

/**
 * Method returns the state of the Towncell
 * 
 */

public State who()
{
	return State.CASUAL;
}

/**
 * To determine the cell type in the next cycle
 * The rules are as follows:
 * 1) If there is any reseller in its neighborhood, it will convert to outage
 * 2) If there is any neighbor that is a streamer, it will convert to streamer
 * 3) If there is at most one empty neighborhood or one outage neighbor, it will convert to reseller
 * 4) If there are five or more casual neighbors, it will convert to streamer
 * 5) If none of the above, it will remain the same
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
			if(TownCell.nCensus[Changer.STREAMER] >=1)
				return new Streamer(NewT, row, col);
	
			else
				if((TownCell.nCensus[Changer.CASUAL] - 1) >= 5)
					return new Streamer(NewT, row, col);
				else
					return new Casual(NewT, row, col);
}
}

