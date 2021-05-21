package assets;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class StillAsset extends GameAsset
{
    protected Image asset;
    protected ArrayList<GameAsset> assetsList;

    public StillAsset(String ref, float x, float y, boolean hasAssets, GameAsset parent) throws SlickException {
        this.ref = ref;
        asset = new Image(ref);
        if(parent != null)
        {
            this.parent = parent;
            oldParentX = parent.getX();
            oldParentY = parent.getY();
        }
        this.x = x;
        this.y = y;
        this.width = asset.getWidth();
        this.height = asset.getHeight();
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
        asset.draw(x,y);
        if(assetsList != null && !assetsList.isEmpty())
        {
            for(GameAsset a: assetsList)
                a.render(g);
        }
    }
}
