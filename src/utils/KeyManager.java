/**
 * <h1>Key Manager class</h1>
 * 
 * <p>This class keeps track of the keys
 * being pushed by the user and the effects
 * that they have.</p>
 * 
 * <p>Created: 7/13/18</p>
 * @version 7/13/18
 * 
 * @author Lauryn Jefferson
 */
package utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener 
{
	
	private static boolean[] keys;
	public static boolean left, right, space;
	
	/**
	 * <h2>init() constructor</h2>
	 * 
	 * <p>This constructor sets up the key manager.</p>
	 */
	public static void init()
	{
		keys = new boolean[256];
	}
	
	/**
	 * <h2>update() method</h2>
	 *
	 * <p>This method keeps track of the variables that let
	 * us know when a key is pushed by the user.</p>
	 */
	public static void update()
	{
		left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
		space = keys[KeyEvent.VK_SPACE];
	}

	/**
	 * <h2>keyPressed() method</h2>
	 * 
	 * <p>This method handles the event e
	 * of the key being pressed.</p>
	 * 
	 * @param e key event
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
	}

	/**
	 * <h2>keyReleased() method</h2>
	 * 
	 * <p>This method handles the event e
	 * of the key being released.</p>
	 * 
	 * @param e key event
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
	}

	/**
	 * <h2>keyTyped() method</h2>
	 * 
	 * <p>This method handles the event e
	 * of the key being typed.</p>
	 * 
	 * @param e key event
	 */
	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}

}
