/**
 * <h1>Asset class</h1>
 *
 * <p>This class defines the qualities
 * of an asset and makes them compatable
 * with each other.</p>
 *
 * <p>Created:7/15/18</p>
 * @version 7/16/18
 *
 * @author Lauryn Jefferson
 */
package assets;

import utils.ResourceLoader;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public abstract class Asset
{
    //contants

    //obejects
    protected BufferedImage img;
    protected String classification;

    //variables
    protected boolean initialized = false;
    protected int x, y;

    public abstract void update();
    public abstract void render(Graphics2D g);

    public void read(String path,char dir)
    {
        img = ResourceLoader.getBufferedImage(path);
        switch(dir)
        {
            case 'd':
                AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
                tx.translate(-img.getWidth(null), -img.getHeight(null));
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                img = op.filter(img, null);
                break;
            case 'l':
                break;
            case 'r':
                break;
            default:
                break;
        }
    }

    public BufferedImage getImage()
    {
        return img;
    }

    public int getWidth()
    {
        return img.getWidth();
    }

    public int getHeight()
    {
        return img.getHeight();
    }

    public void changeX(int change)
    {
        x += change;
    }

    public void changeY(int change)
    {
        y+= change;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String getClassification() {
        return classification;
    }

    public boolean isInitialized()
    {
        return initialized;
    }
}
