package edu.iastate.cs228.hw2;

import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs merge sort
 * to sort the list.
 * 
 * @author Khushveen Kaur Umra
 */
public class MergeSorter extends Sorter
{
  @Override
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException
  {
    
	  mergeSortRec(toSort, comp, 0, toSort.getArray().length - 1);
	  
  }

  private void mergeSortRec(WordList list, Comparator<String> comp, int start, int end)
  {
       
	  if (start < end)
	  {
		  int middle = (start + end) / 2;
		  
		  mergeSortRec(list, comp, start, middle);
		  mergeSortRec(list, comp, middle + 1, end);
		  
	  }
  }
}
