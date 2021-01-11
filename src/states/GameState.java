/**
 * <h1>GameState class</h1>
 * 
 * <p>This class controls the state of playing the game.
 * It manages all the interactions between the other players
 * and the status of the game.</p>
 * 
 * <p>Created:7/13/18</p>
 * @version 7/13/18
 * 
 * @author Lauryn Jefferson
 */
package states;


import java.awt.Graphics2D;


import game.Background;
import game.Ship;


public class GameState implements State
{
	//constants
	
	//objects
	private Ship player;
	private Background bkg;
	
	//variable
	private boolean gameOver;

	/**
	 * <h2>GameState() constructor</h2>
	 * 
	 * <p>This constructor sets up the game.</p>
	 */
	public GameState()
	{
		bkg = new Background();
		player = new Ship(bkg.getShip(),bkg.getAssets());
		gameOver = false;
	}
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method keeps the game flowing.</p>
	 */
	public void update()
	{
		bkg.update();
		if(!gameOver)
		{
			player.update();
		}
	}
	
	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method draws the game to the screen with the given
	 * graphics object.</p>
	 * 
	 * @param g graphics for location to draw to
	 */
	public void render(Graphics2D g)
	{
		//draw entities
		bkg.render(g);
		player.render(g);
	}

}