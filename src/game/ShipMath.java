package game;

import main.GameLauncher;
import server.packets.InputPacket;
import states.GameState;
import java.util.Hashtable;

public class ShipMath {
    private short id;
    private float x, y;
    private float prevX, prevY;
    private float stepX, stepY;
    private float destX, destY;
    private Hashtable<Short,Point> rotationPoints;
    private Hashtable<Short,Double> angles;

    public ShipMath(short id, float x, float y)
    {
        this.id = id;
        prevX = destX = this.x = x;
        prevY = destY = this.y = y;
        stepX = stepY = 1;
        rotationPoints = new Hashtable<>();
        angles = new Hashtable<>();
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

        //update points that rotate to move with ship
        for(Point p : rotationPoints.values())
            p.setCoords(p.getX() + (x - prevX), p.getY() + (y - prevY));

        prevX = x;
        prevY = y;

    }

    public void handleInput(InputPacket input) {
        //if mouse click set destination
        if (input.isMousePressed()) {
            destX = input.getX();
            destY = input.getY();

            //checking for sides of screen
            if (destX > GameLauncher.screenWidth)
                destX = GameLauncher.screenWidth;
            if (destX < 0)
                destX = 0;
            if (destY > GameLauncher.screenHeight)
                destY = GameLauncher.screenHeight;
            if (destY < 0)
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
            if (x < destX)
                stepX = -stepX;
            if (y < destY)
                stepY = -stepY;
        }

        for(Short k: rotationPoints.keySet())
        {
            Point p = rotationPoints.get(k);
            //first update the rotation point location based off ships change in movement
            float x = p.getX();
            float y = p.getY();

            //calculate angle
            double angle = Math.toDegrees(Math.atan2(GameState.input.getAbsoluteMouseY() - y, GameState.input.getAbsoluteMouseX() - x));
            angle -= 90;

            //add to queue for server to send later
            angles.put(k,angle);
        }
    }

    public double[] getAngles(){

        double[] angles = new double[this.angles.size()];

        for (short i = 0; i < angles.length; i++) {
            if( this.angles.get(i) != null)
                angles[i] = this.angles.get(i);
            else angles[i] = 0;
        }
        return angles;
    }

    public void addPoint(short id, float x, float y)
    {
        rotationPoints.put(id,new Point(x,y));
    }
}
