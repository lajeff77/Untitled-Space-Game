package server.packets;

import org.newdawn.slick.Input;

public class InputPacket
{
    private short id;
    private boolean mousePressed;
    private float x, y;

    public InputPacket()
    {
        this((short)0,false, 0f, 0f);
    }
    public InputPacket(short id,boolean mousePressed,float x, float y)
    {
        this.id = id;
        this.mousePressed = mousePressed;
        this.x = x;
        this.y = y;
    }
    public short getId()
    {
        return id;
    }

    public boolean isMousePressed()
    {
        return mousePressed;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }
}
