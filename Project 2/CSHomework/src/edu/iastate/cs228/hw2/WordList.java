package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A simple list of Strings.
 * 
 * @author Khushveen Kaur Umra
 */

public class WordList implements Cloneable
{
  /**
   * The array holding all of the elements of the list.
   */
	
  private String[] words;

  /**
   * Constructs and initializes the list to have exactly the same contents as
   * the given array.
   * 
   * @param contents
   *   the array with the contents of the new list
   * @throws NullPointerException
   *   if {@code contents} is {@code null}
   */
 
  public WordList(String[] contents) throws NullPointerException
  {
    if(contents.length == 0) throw new NullPointerException();
    words = new String[contents.length];
    System.arraycopy(contents, 0, words, 0, contents.length);
  }

  /**
   * Constructs and initializes the list by reading from the indicated file.
   * The file is read assuming that each line contains a word. The ordering in
   * the file is the order that will be used by the list.
   * 
   * @param filename
   *   the name of the file to read
   * @throws NullPointerException
   *   if {@code filename} is {@code null}
   * @throws FileNotFoundException
   *   if the file cannot be found
   */
  
  public WordList(String filename) throws NullPointerException, FileNotFoundException
  {
    if (filename == null || filename.length() == 0) 
    {
      throw new NullPointerException();
    }
    
    File file = new File(filename);
    
    if(!file.exists())throw new FileNotFoundException();

    Scanner scan = new Scanner(file);
    
    int l =0;
    
    while(scan.hasNextLine())
    {
    	l++;
      scan.nextLine();
    }
    
    scan = new Scanner(file);
    
    words = new String[l];
    l=0;
    
    while(scan.hasNextLine())
    {
      words[l] = scan.nextLine();
      l++;
    }
  }

  /**
   * Returns the number of elements in the list.
   * 
   * @return
   *   the number of elements in the list
   */
  public int length()
  {
    return words.length;
  }

  /**
   * Returns the element of the list at the indicated index.
   * 
   * @param idx
   *   the index of the element to retrieve
   * @return
   *   the element at the indicated index
   * @throws IndexOutOfBoundsException
   *   if {@code idx} is negative or greater than or equal to the length of
   *   the list
   */
  
  public String get(int idx) throws IndexOutOfBoundsException
  {
    return words[idx];
  }

  /**
   * Sets the element of the list at the indicated index to the given value.
   * 
   * @param idx
   *   the index of the element to set
   * @param newValue
   *   the new value of the element
   * @throws IndexOutOfBoundsException
   *   if {@code idx} is negative or greater than or equal to the length of the
   *   list
   */
  
  public void set(int idx, String newValue) throws IndexOutOfBoundsException
  {
   if(idx >= words.length)
    {
      throw new IndexOutOfBoundsException();
    }
   
   words[idx]=newValue;
  }

  /**
   * Swaps the indicated elements in the list.
   * 
   * @param idxA
   *   the index of one of the elements to swap
   * @param idxB
   *   the index of the other element to swap
   * @throws IndexOutOfBoundsException
   *   if either of {@code idxA} or {@code idxB} is negative or greater than or
   *   equal to the length of the list
   */
  
  public void swap(int idxA, int idxB) throws IndexOutOfBoundsException
  {
    if(idxA >= words.length || idxB >= words.length)
    {
      throw new IndexOutOfBoundsException();
    }
    
    String temp = words[idxB];
    words[idxB] = words[idxA];
    words[idxA] = temp;
  }

  /**
   * Returns the array used by the list to store its elements.
   * 
   * @return
   *   the array used by the list to store its elements
   */
  
  public String[] getArray()
  {
    return words;
  }

  /**
   * Performs a deep copy of the list.
   */
  
  @Override
  
  public WordList clone()
  {
    return new WordList(words);
  }
}