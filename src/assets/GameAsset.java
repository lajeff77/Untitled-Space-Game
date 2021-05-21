package assets;

import org.newdawn.slick.Graphics;

public abstract class GameAsset
{
    protected String classification;
    protected String ref;
    protected boolean initialized = false;
    protected float x, y;
    protected int width, height;
    protected GameAsset parent;
    protected float oldParentX, oldParentY;

    public abstract void update();
    public abstract void render(Graphics g);

    public String getClassification()
    {
        return classification;
    }

    public boolean isInitialized()
    {
        return initialized;
    }

    public float getX()
    {
        return  x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public String getRef()
    {
        return ref;
    }
}
