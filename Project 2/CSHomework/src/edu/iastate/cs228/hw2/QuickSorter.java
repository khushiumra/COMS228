package edu.iastate.cs228.hw2;

import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs quick sort
 * to sort the list.
 * 
 * @author Khushveen Kaur Umra
 */
public class QuickSorter extends Sorter
{
  @Override
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException
  {
      quickSortRec(toSort,comp, 0, toSort.getArray().length - 1);
  }

  private void quickSortRec(WordList list, Comparator<String> comp, int start, int end)
  {
        if (start >= end)
        	return;
        
        	int parting = partition(list, comp, start, end);
        
            quickSortRec(list, comp, start, parting - 1);
            quickSortRec(list, comp, parting + 1, end);
       
  }

  private int partition(WordList list, Comparator<String> comp, int start, int end)
  {
	  String[] clone = list.getArray();
	 String pivot = clone[end];
	 
	 int initial = (start - 1);
	 
	 for (int centre = start; centre < end; centre++)
	 {
		 if(comp.compare(clone[centre], pivot) <= 0 )
		 {
			 initial++;
			 
			 String temp = clone[initial];
			 clone[initial] = clone[centre];
			 clone[centre] = temp;
			 
		 }
	 }
	 
	 String temp = clone[initial + 1];
	 clone[initial + 1] = clone[end];
	 clone[end] = temp;
	 
    return initial + 1;
  }
}
