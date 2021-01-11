/**
 * <h1>State Interface</h1>
 *
 * <p>This interface is designed to work with the
 * StateManager to control the current state of the
 * program.</p>
 *
 * <p>Created:7/13/18</p>
 * @version 7/13/18
 *
 * @author Lauryn Jefferson
 */
package states;

import java.awt.*;

public interface State
{
    /**
     * <h2>update() method</h2>
     *
     * <p>This method will serve to update all
     * the events and changes in the State.</p>
     */
    public void update();

    /**
     * <h2>render() method</h2>
     *
     * <p>This method will use the given graphics
     * object in order to draw all of the states graphics
     * to the screen.</p>
     *
     * @param g Graphics of where we want to render to
     */
    public void render(Graphics2D g);
}