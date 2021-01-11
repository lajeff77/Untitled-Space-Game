/**
 * <h2>Instruction State class</h2>
 *
 * <p>This class show the instructions.</p>
 *
 * <p>Created:7/13/18</p>
 * @version 7/13/18
 *
 * @author Lauryn Jefferson
 */
package states;

import main.Application;
import main.Window;

import java.awt.*;
import utils.Button;

public class InstructionState implements State
{
    //objects
    private String[] instructionText;
    private Button back;

    /**
     * <h2>InstructionState() constructor</h2>
     *
     * <p>This constructor sets up the instructions state.</p>
     */
    public InstructionState()
    {
        instructionText = new String[2];
        instructionText[0] = "Avoid the oncoming trash in the road.";
        instructionText[1] = "Use the left right arrows or a and d.";

        back = new Button("Back",Window.getWidth() - Window.getCanvas().getGraphics().getFontMetrics().stringWidth("Back") - 60, Window.getHeight()- Application.SMALL_FONT.getSize()-40,Window.getCanvas().getGraphics().getFontMetrics().stringWidth("Back") + 40, Application.SMALL_FONT.getSize()+20,0 );
    }
    /**
     * <h2>update() method</h2>
     *
     * <p>This method will serve to update all
     * the events and changes in the instructions State.</p>
     */
    @Override
    public void update()
    {
        //check for button
        back.update();

        if(back.isClicked())
            StateManager.setCurrentState(new MenuState());
    }

    /**
     * <h2>render() method</h2>
     *
     * <p>This method will use the given graphics
     * object in order to draw all of the instructions graphics
     * to the screen.</p>
     *
     * @param g Graphics of where we want to render to
     */
    @Override
    public void render(Graphics2D g)
    {
        //draw the button
        back.render(g);

        //set color and text
        g.setColor(Color.BLACK);
        g.setFont(Application.SMALL_FONT);

        //draw text
        g.drawString("INSTRUCTIONS",Window.getWidth()/2 - (int)(g.getFontMetrics().stringWidth("INSTRUCTIONS"))/2,100);

        g.setFont(Application.SMALL_FONT);
        for(int i=0; i<instructionText.length; i++)
            g.drawString(instructionText[i],Window.getWidth()/2 - (int)(g.getFontMetrics().stringWidth(instructionText[i]))/2, 200 + i*40);
    }
}
