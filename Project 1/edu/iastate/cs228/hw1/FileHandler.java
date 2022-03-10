package edu.iastate.cs228.hw1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
*@author Khushveen Kaur Umra
*A class to handle all the files
*/

public class FileHandler extends File 
{

	private final String nameFile;
	
	/**
	 *Creating a new file object
	 * @param nameFile
	 */
	
	public FileHandler (String nameFile)
	{
		super(nameFile);
		this.nameFile = nameFile;
	}
	
	/**
	 * @param line
	 * @return string of the spcified line
	 */
	
	public String getLine(int line)
	{
		try
		{
			return Files.readAllLines(Paths.get(nameFile)).get(line);
			
		}
		
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
