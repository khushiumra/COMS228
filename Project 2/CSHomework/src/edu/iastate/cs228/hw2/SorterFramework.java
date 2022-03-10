package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;


/**
 * An class that compares various methods of sorting.
 * 
 * @author Khushveen Kaur Umra
 */
public class SorterFramework
{
  /**
   * Loads data necessary to run the sorter statistics output, and runs it.
   * The only logic within this method should be that necessary to use the
   * given file names to create the {@link AlphabetComparator},
   * {@link WordList}, and sorters to use, and then using them to run the
   * sorter statistics output.
   * 
   * @param args
   *   an array expected to contain two arguments:
   *    - the name of a file containing the ordering to use to compare
   *      characters
   *    - the name of a file containing words containing only characters in the
   *      other file
   */
	
  public static void main(String[] args) 
  {
    if(args[1] == null || args[0] == null) 
    	{ 
    	throw new NullPointerException();
    	}
    
    try
    {
    
    int count = 1000000;
    
    Alphabet alphabet = new Alphabet(args[0]);
    AlphabetComparator comparator= new AlphabetComparator(alphabet);;
    WordList words = new WordList(args[1]);
    
    Sorter[] sorters = new Sorter[3];
    
    sorters[0] = new QuickSorter();
    sorters[1] = new InsertionSorter();
    sorters[2] = new MergeSorter();

    SorterFramework toRun = new SorterFramework(sorters,comparator,words,count);
    toRun.run();
  }
    catch(FileNotFoundException e)
    {
    	
    }
  }

  /**
   * The comparator to use for sorting.
   */
  
  private Comparator<String> comparator;

  /**
   * The words to sort.
   */
  
  private WordList words;

  /**
   * The array of sorters to use for sorting.
   */
  
  private Sorter[] sorters;

  /**
   * The total amount of words expected to be sorted by each sorter.
   */
  
  private int totalToSort;

  /**
   * Constructs and initializes the SorterFramework.
   * 
   * @param sorters
   *   the array of sorters to use for sorting
   * @param comparator
   *   the comparator to use for sorting
   * @param words
   *   the words to sort
   * @param totalToSort
   *   the total amount of words expected to be sorted by each sorter
   * @throws NullPointerException
   *   if any of {@code sorters}, {@code comparator}, {@code words}, or
   *   elements of {@code sorters} are {@code null}
   * @throws IllegalArgumentException
   *   if {@code totalToSort} is negative
   */
  
  public SorterFramework(Sorter[] sorters, Comparator<String> comparator, WordList words, int totalToSort) throws NullPointerException, IllegalArgumentException
  {
    this.comparator = comparator;
    this.sorters = sorters;
    this.totalToSort = totalToSort;
    this.words = words;
  }

  /**
   * Runs all sorters using
   * {@link Sorter#sortWithStatistics(WordList, Comparator, int)
   * sortWithStatistics()}, and then outputs the following information for each
   * sorter:
   *  - the name of the sorter
   *  - the length of the word list sorted each time
   *  - the total number of words sorted
   *  - the total time used to sort words
   *  - the average time to sort the word list
   *  - the number of elements sorted per second
   *  - the total number of comparisons performed
   */
  public void run()
  
  {
    for (Sorter sorter : sorters)
    {
      sorter.sortWithStatistics(words, comparator, totalToSort);
    }
  }
}