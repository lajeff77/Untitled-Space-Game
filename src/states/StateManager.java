/**
 * <h1>StateManager class</h1>
 *
 * <p>This class manages the currentState that
 * the program is in.</p>
 *
 * <p>Created:7/13/18</p>
 * @version 7/13/18
 *
 * @author Lauryn Jefferson
 */
package states;

import java.awt.*;

public class StateManager
{
    //objects
    private static State currentState;

    /**
     * <h2>setCurrentState() method</h2>
     *
     * <p>This method will set the current state of
     * the program to the given state.</p>
     *
     * @param currentState state to change to
     */
    public static void setCurrentState(State currentState)
    {
        StateManager.currentState = currentState;
    }

    /**
     * <h2>update() method</h2>
     *
     * <p>This method will serve to update all
     * the events and changes in the current state.</p>
     */
    public static void update()
    {
        currentState.update();
    }

    /**
     * <h2>render() method</h2>
     *
     * <p>This method will use the given graphics
     * object in order to draw the current states' graphics
     * to the screen.</p>
     *
     * @param g Graphics of where we want to render to
     */
    public static void render(Graphics2D g)
    {
        currentState.render(g);
    }
}
