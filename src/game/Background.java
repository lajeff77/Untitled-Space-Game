/**
 * <h2>Background class</h2>
 *
 * <p>This class manages to background of the game. It
 * keeps track of its animation.</p>
 *
 * <p>Created:7/13/18</p>
 * @version 12/28/18
 */
package game;

import assets.Asset;
import assets.MappedAsset;
import main.Window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Background extends MappedAsset
{
    private static Color bkgColor;
    private final String path  = "/images/TESTmapV1.png";
    protected ArrayList<Asset> bkgAssets;
    private ArrayList<Line> screenLines;

    /**
     * <h2>init() method</h2>
     *
     * <p>This method sets up the background.
     * It must be call before any other method in
     * this class.</p>
     */
    public Background()
    {
        read(path, 'u');
        x = y = 0;
        bkgAssets = readInAssets(x,y);
        createLines();
    }

    /**
     * <h2>update() method</h2>
     *
     * <p>This method keep the backgrounds frame up
     * to date.</p>
     */
    @Override
    public void update()
    {
        for(Asset a: bkgAssets)
            if(a!= null)
                a.update();
        for(Line l: screenLines)
            l.update();
    }

    /**
     * <h2>render() method</h2>
     *
     * <p>This method draws the background to the screen
     * witht he given graphics object.</p>
     * @param g graphics for screen
     */
    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(img,x,y,null);
        for(Asset a: bkgAssets)
            if(a != null)
                a.render(g);

        for(Line l: screenLines)
            l.render(g);
    }

    public ShipImage getShip()
    {
        for(Asset a : bkgAssets)
            if(a!= null && a.getClassification().compareTo("user") == 0)
            {
                bkgAssets.remove(a);
                return (ShipImage)a;
            }
        return null;
    }

    private void createLines()
    {
        screenLines = new ArrayList<Line>();
        for(int x = 0; x < Window.getWidth(); x+= Line.LINE_WIDTH)
            screenLines.add(new Line(x));
    }

    public ArrayList<Asset> getAssets()
    {
        return bkgAssets;
    }
}
