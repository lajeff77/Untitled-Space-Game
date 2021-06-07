package server.packets;

public class PlayerPacket {
    private short id;
    private float playerX, playerY;
    private double[] angles;

    public PlayerPacket()
    {
        this((short)0,0f,0f, new double[1]);
    }

    public PlayerPacket(short id, float playerX, float playerY, double[] angles)
    {
        this.id = id;
        this.playerX = playerX;
        this.playerY = playerY;
        this.angles = angles;
    }

    public short getId()
    {
        return id;
    }

    public void setId(short id)
    {
        this.id = id;
    }

    public float getPlayerX()
    {
        return playerX;
    }

    public float getPlayerY()
    {
        return playerY;
    }

    public double[] getAngles() {
        return angles;
    }
}
