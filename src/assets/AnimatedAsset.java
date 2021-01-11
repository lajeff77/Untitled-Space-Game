/**
 * <h1>AnimatedAsset class</h1>
 *
 * <p>This if for assets with
 * animations.</p>
 *
 * <p>Created:7/17/18</p>
 * @version 7/17/18
 *
 * @author Lauryn Jefferson
 */
package assets;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class AnimatedAsset extends Asset
{
    private Animation animation;
    private BufferedImage[] images;
    int xOffset,yOffset;

    public AnimatedAsset(String path, int x, int y,String classification,char dir,int speed, int frames, int width,int height)
    {
        read(path,dir);
        this.x = x - width/2;
        this.y = y - height/2;
        this.classification = classification;
        images = AssetReader.crop(img,frames,width,height);
        animation = new Animation(speed,images);
    }

    @Override
    public void update()
    {
        animation.update();
    }

    @Override
    public void render(Graphics2D g)
    {
        g.drawImage(animation.getCurrentFrame(),x,y,null);
    }
}
