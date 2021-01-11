/**
 * <h1>Button Class</h1>

 * <p>The button class makes a button that can be clicked.</p>
 *
 * <p>Created:7/13/18</p>
 * @version 7/13/18
 *
 * @author Lauryn Jefferson
 *
 */
package utils;

import main.Application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Button
{
    //constants
    private final Font MY_FONT = Application.SMALL_FONT;

    //objects
    private static Color outline = Color.GRAY,body = Color.LIGHT_GRAY, highlightColor = Color.WHITE, textColor = Color.BLACK;
    private String label;

    //variables
    private int width, height;
    private int x,y;
    private int id; //the id we gave the button to tell if it's selected
    private boolean highlight;

    /**
     * <h2>Button() constructor</h2>
     *
     * <p>This sets up a button and where it will
     * be placed on the screen.</p>
     *
     * @param label description for user to read explaining function
     * @param x x coordinate
     * @param y y coordinate
     * @param width how wide it will be
     * @param height how tall it will be
     * @param id identification
     */
    public Button(String label, int x, int y,int width, int height,int id)
    {
        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        highlight = false;
    }

    /**
     * <h2>update() method</h2>
     *
     * <p>This updates the button.</p>
     */
    public void update()
    {
        setHiglight();
    }


    /**
     * <h2>render() method</h2>
     *
     * <p>Draws the image of the button onto the screen
     * with the graphics object.</p>
     *
     * @param g Graphics of the window
     */
    public void render(Graphics g)
    {
        //set colors and font
        g.setFont(MY_FONT);

        //outline
        g.setColor(outline);
        g.fillRect(x,y,width,height);

        //determine fill and draw body
        if(highlight)
            g.setColor(highlightColor);
        else
            g.setColor(body);
        g.fillRect(x+3, y+3, width-6, height-6);

        //text
        g.setColor(textColor);
        g.drawString(label,(x+(width/2))-(int)(g.getFontMetrics().stringWidth(label)/2),y+height/2+MY_FONT.getSize()/2);
    }

    /**
     * <h2>isClicked() method</h2>
     *
     * <p>This method returns whether the button is
     * clicked.</p>
     *
     * @return clicked or not
     */
    public boolean isClicked()
    {
        if (MouseManager.getMouseX() > x && MouseManager.getMouseX() < x +width && MouseManager.getMouseX() > x && MouseManager.getMouseY() > y && MouseManager.getMouseY() < y + height)
            if(MouseManager.isReleased())
                return true;

        return false;
    }

    /**
     * <h2>setHighlight() method</h2>
     *
     * <p>This method sets whether the button is
     * highlighted or not.</p>
     */
    private void setHiglight()
    {
        if (MouseManager.getMouseX() > x && MouseManager.getMouseX() < x +width && MouseManager.getMouseX() > x && MouseManager.getMouseY() > y && MouseManager.getMouseY() < y + height)
            highlight = true;
        else
            highlight = false;
    }

    /**
     * <h2>getX() method</h2>
     *
     * <p>This method returns the value of x.</p>
     *
     * @return x
     */
    public int getX()
    {
        return x;
    }

    /**
     * <h2>getY() method</h2>
     *
     * <p>This method returns the value of y.</p>
     *
     * @return y
     */
    public int getY()
    {
        return y;
    }

    /**
     * <h2>getWidth() method</h2>
     *
     * p>This method returns the value of width.</p>
     *
     * @return width
     */
    public int getWidth()
    {
        return width;
    }

    /**
     * <h2>getHeight() method</h2>
     *
     * <p>This method returns the value of height.</p>
     *
     * @return height
     */
    public int getHeight()
    {
        return height;
    }

    /**
     * <h2>setOutline() method</h2>
     *
     * p>This method returns the value of outline.</p>
     *
     * @param outline
     */
    public static void setOutline(Color outline)
    {
        Button.outline = outline;
    }

    /**
     * <h2>setBody() method</h2>
     *
     * <p>This method changes the color of the body.</p>
     *
     * @param body
     */
    public static void setBody(Color body)
    {
        Button.body = body;
    }

    /**
     * <h2>setHighlightColor() method</h2>
     *
     * <p>This method changes the color of the highlight.</p>
     *
     * @param highlightColor color of higlight
     */
    public static void setHighlightColor(Color highlightColor)
    {
        Button.highlightColor = highlightColor;
    }

    /**
     * <h2>setTextColor() method</h2>
     *
     * <p>This method changes the color of the text.</p>
     *
     * @param textColor color for text
     */
    public static void setTextColor(Color textColor)
    {
        Button.textColor = textColor;
    }
}
