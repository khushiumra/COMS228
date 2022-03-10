package edu.iastate.cs228.hw2;

import java.util.Comparator;


/**
 * An implementation of {@link Sorter} that performs insertion sort
 * to sort the list.
 * 
 * @author Khushveen Kaur Umra
 */
public class InsertionSorter extends Sorter
{
	
  public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException
  {
  int length = toSort.getArray().length;
  
  for(int i=1;i<length;i++)
  {
    int j=i;
    
    while(j>0)
    {
      if(comp.compare(toSort.get(j), toSort.get(j - 1)) < 0 )
      {
        String temp = toSort.get(j);
        toSort.set(j, toSort.get(j-1) );
        toSort.set(j-1, temp);
      }
      
      j--;
    }
  }
 }
}
