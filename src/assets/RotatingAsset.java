package assets;

import utils.MouseManager;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class RotatingAsset extends MappedAsset {

    //constants

    //objects
    private AffineTransform current, backup;

    //variables
    private double currentAngle, destAngle;

    public RotatingAsset(String path,int x, int y, String classification, char dir)
    {
        read(path,dir);
        this.classification = classification;
        this.x = x - img.getWidth()/2;
        this.y = y - img.getHeight()/2;
        currentAngle = destAngle = 0;
    }
    @Override
    public void update()
    {

        destAngle = Math.toDegrees(Math.atan2(MouseManager.getMouseX() - x,MouseManager.getMouseY()- y));//+180;
        if (destAngle>360){ destAngle=destAngle-360; }

        /*if (Math.abs(currentAngle - destAngle) > 1)
        {
            if (currentAngle < destAngle)
            {
                //System.out.println("current angle: " + currentAngle + "\n dest angle: " + destAngle);
                if (destAngle < 180)
                    currentAngle++;
                else
                    currentAngle--;
            }
            else
            {
                if (destAngle >= 180)
                    currentAngle--;
                else
                    currentAngle++;
            }
        }
        if (currentAngle > 360)
            currentAngle = 0;
        if (currentAngle < 0)
            currentAngle = 360;

        if(MouseManager.isReleased())
            System.out.println("Current Angle: "+currentAngle);*/
        currentAngle = destAngle;
    }

    @Override
    public void render(Graphics2D g)
    {
        /*backup = g.getTransform();
        current = new AffineTransform();
        //rotate around this point
        current.rotate( currentAngle, x, y);
        g.transform( current );
        //draw image translated
        g.drawImage( img, x, y, null );
        // restore previous transform
        g.setTransform( backup );*/

        backup = g.getTransform();
        current = new AffineTransform();
        //rotate around this point
        current.rotate( Math.toRadians(-currentAngle), x+img.getWidth()/2, y+ img.getHeight()/2);
        g.transform( current );

        //draw here
        g.drawImage(img, x, y, null);

        // restore previous transform
        g.setTransform( backup );
    }
}
