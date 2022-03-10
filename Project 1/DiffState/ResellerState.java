package DiffState;

import edu.iastate.cs228.hw1.State;
import edu.iastate.cs228.hw1.Changer;
import edu.iastate.cs228.hw1.Town;
import edu.iastate.cs228.hw1.TownCell;

/**
 * @author Khushveen Kaur Umra
 *
 *This class implements the abstract methods of the TownCell class
 */
public class ResellerState extends TownCell
{
    
	public ResellerState(Town town, int row, int col)
	{
		super(town, row, col);
	}
	
	public State who()
	{
		return State.RESELLER;
	}
	
	/**
	 * It determines the cell type in the next cycle
	 * It has rules as follows:
	 * 1) If there are three or more neighboring cells that are empty will convert to Empty
	 * 2) If there are three or less neighboring cells are casual will convert to Empty
	 * 3) If there are 5 or more casual neighbors converts to Streamer
	 * 4) If none of these apply, it stays the same
	 * 
	 * @param NewT - Town of the next cycle
	 */
	
	public TownCell next(Town NewT)
	{
		census(TownCell.nCensus);
		
		if(TownCell.nCensus[Changer.EMPTY] >= 3)
			return new Empty(NewT, row, col);
		
		else
			if(TownCell.nCensus[Changer.CASUAL] <= 3)
				return new Empty(NewT, row, col);
		
			else
				if((TownCell.nCensus[Changer.CASUAL]) >= 5)	
					return new Streamer(NewT, row, col);
		
				else
					return new Reseller(NewT, row, col);
		
}
}
