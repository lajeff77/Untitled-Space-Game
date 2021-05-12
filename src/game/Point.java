package game;

public class Point
{
    private float x,y;

    public Point()
    {
        this(0f,0f);
    }

    public Point(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public void setCoords(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
}
