/**
 * <h1>Launcher class</h1>
 * 
 * <p>This class is the launching point for the whole project.</p>
 * 
 * <p>Created: 7/13/18</p>
 * @version 7/13/18
 * 
 * @author Lauryn Jefferson
 */
package main;

import main.Application.AlreadyRunningException;

public class Launcher 
{
	/**
	 * <h2>main() method</h2>
	 * 
	 * <p>This method is the starting point that the JVM
	 * recognizes when running this program.</p>
	 * 
	 * @param args arguments for the starting conditions of the program
	 */
	public static void main(String[] args)
	{
		//sets up application with a title and window size of 800 by 600
		Application game = new Application("Project SPaCe THiNGY",800,600);
		
		//hands over the duties to the application thread
		try
		{
			//try to start up the game
			game.start();
		}
		catch(AlreadyRunningException e)
		{
			//error in starting up the game
			e.printStackTrace();
		}
	}
}
