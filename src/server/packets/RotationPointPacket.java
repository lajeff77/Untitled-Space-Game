package server.packets;

public class RotationPointPacket {
    private float x, y;
    private short shipId, id;

    public RotationPointPacket()
    {
        this((short)-1,(short)-1,0f,0f);
    }

    public RotationPointPacket(short shipId, short id, float x, float y)
    {
        this.shipId = shipId;
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public short getShipId()
    {
        return shipId;
    }

    public short getId()
    {
        return id;
    }
}
