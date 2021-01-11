/**
 * <h1>AssetReader class</h1>
 *
 * <p>This class is a utility to read in assets and determine
 * thier types.</p>
 *
 * <p>Created:7/15/18</p>
 * @version 7/15/18
 *
 * @author Lauryn Jefferson
 */
package assets;

import game.ShipImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

public class AssetReader
{
    private static final String MASTER_PATH = "masterlist.txt";//"/assets/masterlist.txt";
    private static final String ABSOLUTE = "C:/Users/lajef/Desktop/Project SPaCe THiNGY JaVa/res/asset_data/";
    private static Scanner scanner;
    private static String[] assetList;

    public static void init()
    {
        assetList = new String[255];
        readMasterList();
        /*for(int i = 0; i < assetList.length; i++)
            System.out.println(assetList[i]);*/
    }

    private static void readMasterList()
    {
        try{
            scanner = new Scanner(new File(ABSOLUTE + MASTER_PATH));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        int count = 0;

        while(scanner.hasNext())
            assetList[count++] = scanner.next();
    }

    public static Asset setUpBasicAsset(int index, int x, int y, char dir)
    {
        if(index == 39 || index == 0)
            return null;
        Asset asset = null;
        try
        {
            scanner = new Scanner(new File(ABSOLUTE + assetList[index]));
            String path = scanner.next();
            String classification = scanner.next();
            switch(classification)
            {
                case "animation":
                    asset = new AnimatedAsset(path,x,y, classification,dir,scanner.nextInt(), scanner.nextInt(),scanner.nextInt(), scanner.nextInt());
                    break;
                case "user":
                    asset = new ShipImage(path,classification,x,y,dir);
                    break;
                //case "ship":
                case "rotating":
                    asset = new RotatingAsset(path,x,y,classification,dir);
                    break;
                case "turret":
                    asset = new Turret(path,x,y,classification,dir,scanner.next(),scanner.nextLong());
                    break;
                case "null":
                    break;
                default:
                    asset = new BasicAsset(path,x,y, classification,dir);
                    break;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return asset;
    }

    /**
     *
     * @param img
     * @param frames
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage[] crop(BufferedImage img, int frames, int width, int height)
    {
        BufferedImage[] images = new BufferedImage[frames];
        int x = 0, y =0;
        int count = 0;
        while(y <= img.getHeight()- height)
        {
            while(x <= img.getWidth()- width)
            {
                //crop here
                images[count++] = img.getSubimage(x,y,width,height);
                x+=width;
            }
            x = 0;
            y += height;
        }

        return images;
    }
}
