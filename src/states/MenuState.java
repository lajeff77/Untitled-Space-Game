/**
 * <h1>MenuState class</h1>
 *
 * <p>This class is the menu for the game when you
 * first load the game.</p>
 *
 * <p>Created:7/13/18</p>
 * @version 7/13/18
 *
 * @author Lauryn Jefferson
 */
package states;

import java.awt.Graphics2D;
import java.util.ArrayList;
import utils.Button;

public class MenuState implements State
{
    private ArrayList<Button> buttons;

    /**
     * <h2>MenuState() constructor</h2>
     *
     * <p>This constructor sets up the menu.</p>
     */
    public MenuState()
    {
        buttons = new ArrayList<Button>();

        //menu options
        buttons.add(new Button("Play",250,200,300,100,1));
        buttons.add(new Button("Instructions",250,325,300,100,2));
        buttons.add(new Button("Exit",250,450,300,40,3));
    }

    /**
     * <h2>update() method</h2>
     *
     * <p>This method will serve to update all
     * the events and changes in the menu.</p>
     */
    @Override
    public void update()
    {
        //update buttons
        for(Button b: buttons)
            b.update();

        //check for buttons input
        for (int i = 0; i < buttons.size(); i++)
            if (buttons.get(i).isClicked())
                makeSelection(i + 1);
    }

    /**
     * <h2>render() method</h2>
     *
     * <p>This method will use the given graphics
     * object in order to draw all of the menu graphics
     * to the screen.</p>
     *
     * @param g Graphics of where we want to render to
     */
    @Override
    public void render(Graphics2D g)
    {
        //draw buttons
        for(Button b: buttons)
            b.render(g);
    }

    private void makeSelection(int selection)
    {
        switch(selection)
        {
            case 1:
                //play
                StateManager.setCurrentState(new GameState());
                break;
            case 2:
                //instructions
                StateManager.setCurrentState(new InstructionState());
                break;
            case 3:
                //quit
                System.exit(0);
            default:
                break;
        }

    }
}