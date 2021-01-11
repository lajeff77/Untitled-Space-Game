package game;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import assets.MappedAsset;
import assets.Asset;
import assets.Turret;
import main.Window;

import java.awt.Graphics2D;

public class ShipImage extends MappedAsset
{
    private int prevX, prevY;
    private ArrayList<Asset> shipAssets;
    private double currentAngle, destAngle;
    private AffineTransform current, backup;

    public ShipImage(String imgPath,String classification, int x, int y, char dir)
    {
        //System.out.println("Making another ship?");
        this.classification = classification;
        initialized  = true;
        read(imgPath,dir);
        prevX = this.x = x;
        prevY = this.y = y;
        shipAssets = readInAssets(x,y);
        currentAngle = destAngle = 0;
    }
    public void update()
    {
        for (Asset a:shipAssets)
        {
            a.update();
            a.changeX(x - prevX);
            a.changeY(y - prevY);
        }

        prevX = x;
        prevY = y;

       /* if(destAngle < 180)
            //go left
        else
            //go right*/
       /*
            if (currentAngle < destAngle)
            {
                System.out.println("current angle: " + currentAngle + "\n dest angle: " + destAngle);
                if (Math.abs(currentAngle - destAngle) < 180)
                    currentAngle++;
                else
                    currentAngle--;
            }
            else
            {
                if (Math.abs(currentAngle - destAngle) < 180)
                    currentAngle--;
                else
                    currentAngle++;
            }
        }

        if (currentAngle > 360)
            currentAngle = 0;
        if (currentAngle < 0)
            currentAngle = 360;*/
    }

    public void render(Graphics2D g)
    {
        backup = g.getTransform();
        current = new AffineTransform();
        //rotate around this point
        current.rotate( Math.toRadians(currentAngle), x+img.getWidth()/2, y+ img.getHeight()/2);
        g.transform( current );

        //draw here
        g.drawImage(img, x, y, null);
        for (Asset a:shipAssets)
            a.render(g);


        // restore previous transform
        g.setTransform( backup );

    }

    public void setDestAngle(double destAngle)
    {
        currentAngle = this.destAngle = destAngle;
    }

    public void fire()
    {
        System.out.println("shipimg");

        for(Asset a: shipAssets)
        {
            System.out.println(a.getClassification());
            if(a.getClassification().compareTo("turret") == 0 ){
                System.out.println("shipimg");
                ((Turret)a).fire();
            }

        }
    }
}

