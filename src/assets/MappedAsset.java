/**
 * <h1>BasicAsset class</h1>
 *
 * <p>This if for assets that have assets
 * mapped onto themselves.</p>
 *
 * <p>Created:7/16/18</p>
 * @version 7/17/18
 *
 * @author Lauryn Jefferson
 */
package assets;

import java.util.ArrayList;

public abstract class MappedAsset extends Asset
{
    /**
     * <h2>readInAssets() method</h2>'
     *
     * <p>This method reads in assets on the ship.</p>
     */
    protected ArrayList<Asset> readInAssets(int x, int y)
    {
        ArrayList<Asset> specificAssets = new ArrayList<Asset>(10);
        char pos;
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                if (isRGBA(i, j, 0, 255, 0, 255)) {
                    pos = checkPixel(i, j);
                    if (pos != 'f') {
                        int index = readCodeNumber(pos, i, j);
                        specificAssets.add(AssetReader.setUpBasicAsset(index, x + i, y + j, pos));
                    }
                }
            }
        }
        return specificAssets;
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
    protected char checkPixel(int startX, int startY)
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
            if(!isRGBA(x  + offset,y,0,255,0,255)) {
                if (firstPass) {
                    if (isRGBA(x  + offset, y, 255, 0, 0, 255))
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
            if(!isRGBA(x + boxSize-1 ,y + offset ,0,255,0,255)) {
                if (firstPass) {
                    if (isRGBA(x + boxSize-1, y + offset, 255, 0, 0, 255))
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
            if(!isRGBA(x + offset, y + boxSize - 1,0,255,0,255)) {
                if (firstPass) {
                    if (isRGBA(x+ offset, y + boxSize - 1, 255, 0, 0, 255))
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
            if(!isRGBA(x, y + offset,0,255,0,255)) {
                if (firstPass) {
                    if (isRGBA(x, y + offset, 255, 0, 0, 255))
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
    protected int readCodeNumber(char dir, int x, int y)
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
            if (isRGBA(startX + offset, startY, 255, 255, 255, 255))
                codeNum += multiplier[count];
            count++;
            if (count >= 8)
                count = 0;
        }

        //right
        for (int offset = 0; offset < boxSize-1; offset++)
        {
            if (isRGBA(startX + boxSize-1, startY + offset, 255, 255, 255, 255))
                codeNum += multiplier[count];
            count++;
            if (count >= 8)
                count = 0;
        }

        //left
        for (int offset = boxSize - 1; offset >= 1; offset--)
        {
            if (isRGBA(startX + offset, startY + boxSize-1, 255, 255, 255, 255))
                codeNum += multiplier[count];
            count++;
            if (count >= 8)
                count = 0;
        }

        //right
        for (int offset = boxSize - 1; offset >= 1; offset--)
        {
            if (isRGBA(startX , startY + offset, 255, 255, 255, 255))
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
    boolean isRGBA(int x, int y, int r, int g, int b, int a)
    {
        int color = img.getRGB(x,y);
        return a == ((color >> 24) & 0xFF )&& r == ((color >> 16) & 0xFF) && g == ((color >> 8) & 0xFF)&& b == ((color) & 0xFF);
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}

