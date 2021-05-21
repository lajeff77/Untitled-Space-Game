package server.packets;

public class PlayerPacket {
    private short id;
    private float playerX, playerY;

    public PlayerPacket()
    {
        this((short)0,0f,0f);
    }

    public PlayerPacket(short id, float playerX, float playerY)
    {
        this.id = id;
        this.playerX = playerX;
        this.playerY = playerY;
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

}
