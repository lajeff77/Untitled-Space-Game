package assets;

import org.newdawn.slick.SlickException;
import server.GameClient;

public class RotatingAsset extends StillAsset {

    protected short id;
    protected double angle;

    public RotatingAsset(String ref, float x, float y, boolean hasAssets, GameAsset parent, short clientID, short id) throws SlickException {
        super(ref, x, y, hasAssets, parent, clientID);;
        this.asset.setCenterOfRotation((this.asset.getWidth()/2), (this.asset.getHeight()/2));
        this.id = id;
        GameClient.sendRotationPacket(clientID, id,x - this.asset.getWidth()/2,y-this.asset.getWidth()/2);
    }

    @Override
    public void update() {
        super.update();

        //set angle
        asset.setRotation((float)angle);
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public short getId()
    {
        return id;
    }
}
