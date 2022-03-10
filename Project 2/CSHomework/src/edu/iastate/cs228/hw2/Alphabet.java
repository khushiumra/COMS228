package edu.iastate.cs228.hw2;

import java.io.*;
import java.util.Scanner;
import static java.util.Arrays.sort;

/**
 * A class representing an ordering of characters that can be queried to know
 * the position of a given character.
 * 
 * @author Khushveen Kaur Umra
 */
public class Alphabet
{
  /**
   * A lookup table containing characters and their positions.
   * Sorted by the character of each entry.
   */
  private CharAndPos[] lookup;

  /**
   * Constructs and initializes the ordering to have exactly the ordering of
   * the elements in the given array.
   * 
   * @param ordering
   *   the array containing the characters, in the ordering desired
   * @throws NullPointerException
   *   if {@code ordering} is {@code null}
   */
  
  public Alphabet(char[] ordering) throws NullPointerException 
  {
	  
      if (ordering == null || ordering.length == 0) 
      {
      throw new NullPointerException("Given list of chatracters is null");
      } 
      
      else 
      {
      lookup = new CharAndPos[ordering.length];
      for (int i = 0; i < ordering.length; i++) 
      {
        lookup[i] = new CharAndPos(ordering[i], i);
      }
      
      sort(lookup);
    }
  }

  /**
   * Constructs and initializes the ordering by reading from the indicated
   * file. The file is expected to have a single character on each line, and
   * the ordering in the file is the order that will be used.
   * 
   * @param filename
   *   the name of the file to read
   * @throws NullPointerException
   *   if {@code filename} is {@code null}
   * @throws FileNotFoundException
   *   if the file cannot be found
   */
  
  public Alphabet(String filename) throws NullPointerException, FileNotFoundException
  {

    if (filename == null || filename.length() == 0) 
    {
      throw new NullPointerException();
    }
    
    File file = new File(filename);
    Scanner scan = new Scanner(file);
    
    int size = 0;
    
      while (scan.hasNextLine()) 
      {
        size++;
        scan.nextLine();
      }
      
   scan = new Scanner(file);
   
    lookup = new CharAndPos[size];
    
    int i = 0;
    
    while (scan.hasNextLine()) 
    {
      lookup[i] = new CharAndPos(scan.nextLine().charAt(0), i);
      i++;
    }
    
    sort(lookup);
  }


  /**
   * Returns true if and only if the given character is present in the
   * ordering.
   * 
   * @param c
   *   the character to test
   * @return
   *   true if and only if the given character is present in the ordering
   */
  
  public boolean isValid(char c)
  {
	  
    for (CharAndPos charAndPos : lookup) 
    {
      if (charAndPos.character == c) 
      {
        return true;
      }
      
    }
    return false;
  }

  /**
   * Returns the position of the given character in the ordering.
   * Returns a negative value if the given character is not present in the
   * ordering.
   * 
   * @param c
   *   the character of which the position will be determined
   * @return
   *   the position of the given character, or a negative value if the given
   *   character is not present in the ordering
   */
  
  public int getPosition(char c)
  {
    for (CharAndPos charAndPos : lookup) 
    {
      if (charAndPos.character == c) 
      {
        return charAndPos.position;
      }
      
    }
    return -1;
  }

  /**
   * Returns the index of the entry containing the given character within the
   * lookup table {@link #lookup}.
   * Returns a negative value if the given character does not have an entry in
   * the table.
   * 
   * @param toFind
   *   the character for which to search
   * @return
   *   the index of the entry containing the given character, or a negative
   *   value if the given character does not have an entry in the table
   */
  
  private int binarySearch (char toFind)
  {

    int l = 0;
    int e = lookup.length - 1;
    
    while (l <= e) 
    {
      int mid = l + (e-l) / 2;
      
      if (toFind < lookup[mid].character) 
      {
        e = mid - 1;
      } 
      
      else if (toFind > lookup[mid].character) 
      {
        l = mid + 1;
      } 
      
      else 
      {
        return mid;
      }
      
    }
    return -1;
  }

  /**
   * A PODT class containing a character and a position.
   * Used as the entry type within {@link Alphabet#lookup lookup}.
   */
  
  /* already completed */
  
  private static class CharAndPos{
    /**
     * The character of the entry.
     */
    public char character;

    /**
     * The position of the entry in the ordering.
     */
    public int position;

    /**
     * Constructs and initializes the entry with the given values.
     * 
     * @param character
     *   the character of the entry
     * @param position
     *   the position of the entry
     */
    
    public CharAndPos(char character, int position)
    {
      this.character = character;
      this.position = position;
    }

    @Override
    
    public boolean equals(Object obj)
    {
      if (null == obj || this.getClass() != obj.getClass())
      {
        return false;
      }

      CharAndPos o = (CharAndPos) obj;

      return this.character == o.character && this.position == o.position;
    }

    @Override
    
    public int hashCode()
    {
      return character ^ position;
    }

    @Override public String toString()
    {
      return "{" + character + ", " + position + "}";
    }
  }
}

