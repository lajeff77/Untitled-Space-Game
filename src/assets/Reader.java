package assets;

import org.newdawn.slick.Game;
import org.newdawn.slick.Graphics;
import utils.ResourceLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    private static final String MASTER_PATH = "masterlist.txt";//"/assets/masterlist.txt";
    private static final String ABSOLUTE = "/Users/laurynjefferson/Documents/Games/Untitled-Space-Game/res/asset_data/";
    private static Scanner scanner;
    private static String[] assetList;

    public static void init()
    {
        assetList = new String[255];
        readMasterList();
//        for(int i = 0; i < assetList.length; i++)
//            System.out.println(i+ ": " + assetList[i]);
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

    public static ArrayList<GameAsset> readInAssets(GameAsset asset, float x, float y)
    {
        ArrayList<GameAsset> specificAssets = new ArrayList<>();
        char pos;
        try {
            BufferedImage img = ImageIO.read(new File(asset.getRef()));
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    if (isRGBA(img, i, j, 0, 255, 0, 255)) {
                        pos = checkPixel(img, i, j);
                        if (pos != 'f') {
                            int index = readCodeNumber(img, pos, i, j);
                            //check for valid index
                            if(assetList[index] != null && assetList[index].compareTo("void.txt") != 0)
                            {
                                GameAsset newAsset = initializeGameAsset(index, x + i, y + j, pos, asset);
                                if(newAsset != null)
                                {
                                    //set position in the center
                                    newAsset.setX(x+i - (newAsset.getWidth()/2));
                                    newAsset.setY(y+j - (newAsset.getHeight()/2));
                                    specificAssets.add(newAsset);
                                }
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return specificAssets;
    }

    private static GameAsset initializeGameAsset(int index, float x, float y, char pos, GameAsset parent)
    {
        GameAsset asset = null;
        try {
            scanner = new Scanner(new File(ABSOLUTE + assetList[index]));
            String path = scanner.next();
            String classification = scanner.next();
            switch (classification){
                case "user":
                case "ship":
                case "null":
                    break;
                case "animation":
                    int duration = scanner.nextInt();
                    int width = scanner.nextInt();
                    int height = scanner.nextInt();
                    asset = new AnimatedAsset2(path,x,y,duration,width,height,false, parent);
                    break;
                default:
                    asset = new StillAsset(path,x,y,false, parent);
                    break;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return asset;
    }

    /**
     * <h2>checkPixel() method</h2>
     *
     * <p>This helper method checks the particular pixel
     * from the middle to see if it contains the border of
     * a code. It returns a char representing the orientation
     * of the code.</p>
     *
     * <p>char orientations</p>
     *
     * <ul>
     *     <li>u: up</li>
     *     <li>r: right</li>
     *     <li>d: down</li>
     *     <li>l: left</li>
     *     <li>f: false</li>
     * </ul>
     *
     * @param startX middle x coord
     * @param startY middle y coord
     * @return orientation/ false
     */
    private static char checkPixel(BufferedImage img, int startX, int startY)
    {
        //System.out.println("checking");
        int boxSize = 5;
        int x = startX - boxSize/2;
        int y = startY - boxSize/2;
        char dir = 'f';
        boolean firstPass = true;

        //up
        for(int offset = 0; offset < boxSize -1; offset++)
        {
            if(!isRGBA(img,x  + offset,y,0,255,0,255)) {
                if (firstPass) {
                    if (isRGBA(img,x  + offset, y, 255, 0, 0, 255))
                        dir = 'u';
                    else
                        return 'f';
                }
                else return 'f';
            }
            firstPass = false;
        }
        firstPass = true;

        //right
        for(int offset = 0; offset < boxSize - 1; offset++)
        {
            if(!isRGBA(img,x + boxSize-1 ,y + offset ,0,255,0,255)) {
                if (firstPass) {
                    if (isRGBA(img,x + boxSize-1, y + offset, 255, 0, 0, 255))
                        if (dir == 'f')
                            dir = 'r';
                        else
                            return 'f';
                    else
                        return 'f';
                }
                else return 'f';
            }
            firstPass = false;
        }
        firstPass = true;

        //down
        for(int offset = boxSize - 1; offset >= 1; offset--)
        {
            if(!isRGBA(img,x + offset, y + boxSize - 1,0,255,0,255)) {
                if (firstPass) {
                    if (isRGBA(img,x+ offset, y + boxSize - 1, 255, 0, 0, 255))
                        if(dir =='f')
                            dir = 'd';
                        else
                            return 'f';
                    else
                        return 'f';
                }
                else return 'f';
            }
            firstPass = false;
        }
        firstPass = true;

        //left
        for(int offset = boxSize - 1; offset >= 1; offset--)
        {
            if(!isRGBA(img,x, y + offset,0,255,0,255)) {
                if (firstPass) {
                    if (isRGBA(img, x, y + offset, 255, 0, 0, 255))
                        if(dir =='f')
                            dir = 'l';
                        else
                            return 'f';
                    else
                        return 'f';
                }
                else return 'f';
            }
            firstPass = false;
        }
        return dir;
    }

    /**
     * <h2>readCodeNumber() method</h2>
     *
     * <p>This method takes a verified valid code box and
     * produces the number it represents.</p>
     *
     * @param dir direction the box is oriented
     * @param x x coordinate of middle of code
     * @param y y coordinate of middle of code
     * @return number the picrture reps
     */
    private static int readCodeNumber(BufferedImage img, char dir, int x, int y)
    {
        //working off middle value
        int startX = x - 1;
        int startY = y - 1;

        int boxSize = 3;

        int multiplier[] = {128,64,32,16,8,4,2,1};
        int count;
        int codeNum =0;

        switch(dir)
        {
            case 'u':
                count = 0;
                break;
            case 'l':
                count = 2;
                break;
            case 'd':
                count = 4;
                break;
            case 'r':
                count = 6;
                break;
            default:
                return -1;
        }

        //up
        for (int offset = 0; offset < boxSize-1; offset++)
        {
            if (isRGBA(img,startX + offset, startY, 255, 255, 255, 255))
                codeNum += multiplier[count];
            count++;
            if (count >= 8)
                count = 0;
        }

        //right
        for (int offset = 0; offset < boxSize-1; offset++)
        {
            if (isRGBA(img,startX + boxSize-1, startY + offset, 255, 255, 255, 255))
                codeNum += multiplier[count];
            count++;
            if (count >= 8)
                count = 0;
        }

        //left
        for (int offset = boxSize - 1; offset >= 1; offset--)
        {
            if (isRGBA(img,startX + offset, startY + boxSize-1, 255, 255, 255, 255))
                codeNum += multiplier[count];
            count++;
            if (count >= 8)
                count = 0;
        }

        //right
        for (int offset = boxSize - 1; offset >= 1; offset--)
        {
            if (isRGBA(img,startX , startY + offset, 255, 255, 255, 255))
                codeNum += multiplier[count];
            count++;
            if (count >= 8)
                count = 0;
        }
        return codeNum;
    }

    /**
     * <h2>isRGBA() method</h2>
     *
     * <p>This method determines whether the pixel
     * at the different coordinate has the given color
     * values of the TYPE_INT_RGBA.</p>
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param r red level
     * @param g green level
     * @param b blue level
     * @param a alpha level
     *
     * @return if the levels match the pixel in the given coordinate
     */
    private static boolean isRGBA(BufferedImage img, int x, int y, int r, int g, int b, int a)
    {
        int color = img.getRGB(x,y);
        return a == ((color >> 24) & 0xFF )&& r == ((color >> 16) & 0xFF) && g == ((color >> 8) & 0xFF)&& b == ((color) & 0xFF);
    }
}
