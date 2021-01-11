package assets;

import org.jcp.xml.dsig.internal.MacOutputStream;
import utils.MouseManager;

public class Projectile extends BasicAsset {

    private boolean fired;
    private int speed;
    private double velX, velY;
    public Projectile(String path,int x, int y, String classification, char dir)
    {
        super(path,x,y,classification,dir);
        fired = true;
        speed = 8;
        double angle = Math.atan2( MouseManager.getMouseY()-y, MouseManager.getMouseX()-x);
        velX = Math.cos(angle);
        velY = Math.sin(angle);
        //System.out.println("creating projectile");
    }

    @Override
    public void update()
    {
        //System.out.println("updating projectile");
        if(fired)
            x =  x +(int)(speed * velX);
            y = y + (int)(speed * velY);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void fire()
    {
        fired = true;
    }
}
