package assets;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.util.ArrayList;

public class AnimatedAsset2 extends GameAsset {
    protected Animation asset;
    protected ArrayList<GameAsset> assetsList;

    public AnimatedAsset2(String ref, float x, float y, int duration, int width, int height, boolean hasAssets, GameAsset parent) throws SlickException
    {
        this.ref = ref;
        asset = new Animation(new SpriteSheet(ref, width, height), duration);
        if(parent != null)
        {
            this.parent = parent;
            oldParentX = parent.getX();
            oldParentY = parent.getY();
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initialized = true;
        if(hasAssets)
            assetsList = Reader.readInAssets(this,x,y);
    }

    @Override
    public void update()
    {
        if(parent != null)
        {
            x += parent.getX() - oldParentX;
            y += parent.getY() - oldParentY;
            oldParentX = parent.getX();
            oldParentY = parent.getY();
        }
        if(assetsList != null && !assetsList.isEmpty())
        {
            for(GameAsset a: assetsList)
                a.update();
        }
    }

    @Override
    public void render(Graphics g)
    {
        asset.draw(x, y);
        if(assetsList != null && !assetsList.isEmpty())
        {
            for(GameAsset a: assetsList)
                a.render(g);
        }
    }

}
