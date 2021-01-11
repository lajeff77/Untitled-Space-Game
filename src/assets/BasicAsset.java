/**
 * <h1>BasicAsset class</h1>
 *
 * <p>This if for standard assets with no
 * special actions.</p>
 *
 * <p>Created:7/16/18</p>
 * @version 7/17/18
 *
 * @author Lauryn Jefferson
 */
package assets;

import java.awt.*;

public class BasicAsset extends Asset
{

    public BasicAsset(String path,int x, int y, String classification, char dir)
    {
        read(path,dir);
        this.x = x - img.getWidth()/2;
        this.y = y - img.getHeight()/2;
        this.classification = classification;
    }
    @Override
    public void update()
    {
        //nothting
    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(img, x, y, null);
    }
}
