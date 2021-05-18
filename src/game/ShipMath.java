package game;

import main.GameLauncher;
import server.packets.InputPacket;

public class ShipMath {
    private short id;
    private float x, y;
    private float stepX, stepY;
    private float destX, destY;

    public ShipMath(short id, float x, float y)
    {
        this.id = id;
        destX = this.x = x;
        destY = this.y = y;
        stepX = stepY = 1;
    }

    public short getId()
    {
        return id;
    }

    public void setId(short id)
    {
        this.id = id;
    }

    public float getX()
    {
        return x;
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

    public void update()
    {
        if(x != destX)
        {
//            if (Math.abs(destX - x) < stepX)
//                x = destX;
            x -= stepX;
        }
        if(y != destY)
        {
//            if (Math.abs(destY - y) < stepY)
//                y = destY;
            y -= stepY;
        }
    }

    public void handleInput(InputPacket input)
    {
        //if mouse click set destination
        if(input.isMousePressed())
        {
            destX = input.getX();
            destY = input.getY();

            //checking for sides of screen
            if(destX > GameLauncher.screenWidth)
                destX = GameLauncher.screenWidth;
            if(destX < 0)
                destX = 0;
            if(destY > GameLauncher.screenHeight)
                destY = GameLauncher.screenHeight;
            if(destY < 0)
                destY = 0;

//            float slope = (y - destY) / (x - destX);
//            if(slope > 0)
//            {
//                stepX = 1;
//                stepY = slope;
//            }
//            else
//            {
//                stepX = slope;
//                stepY = 1;
//            }
            stepX = 1;
            stepY = 1;
            if(x < destX)
                stepX = -stepX;
            if(y < destY)
                stepY = -stepY;
        }
    }
}
