/**
 * <h1>ResourceLoader class</h1>
 * 
 * <p>This class is used to load all the resources
 * for the application.</p>
 * 
 * <p>Created:7/13/18</p>
 * @version 7/13/18
 * 
 * @author Lauryn Jefferson
 */
package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceLoader 
{
	//objects
	static ResourceLoader rl = new ResourceLoader();
	
	/**
	 * <h2>getImage() method</h2>
	 * 
	 * <p>This method loads an image in a way
	 * that is friendly to jars.</p>
	 * 
	 * @param filename path of image
	 * @return image loaded
	 */
	public static Image getImage(String filename)
	{
		return Toolkit.getDefaultToolkit().getImage(rl.getClass().getResource(filename));
	}
	
	/**
	 * <h2>getBufferedImage() method</h2>
	 * 
	 * <p>This method loads a buffered image in
	 * a way that is friendly to jars.</p>
	 * 
	 * @param filename path of image
	 * @return buffered image loaded
	 */
	public static BufferedImage getBufferedImage(String filename)
	{
		try 
		{
			return ImageIO.read(ResourceLoader.class.getResource(filename));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * <h2>getFont() method</h2>
	 * 
	 * <p>This method loads the ttf or otf of a
	 * custom font and returns a Font object of that
	 * file.</p>
	 * 
	 * @param path where font is stored
	 * @param size size of font
	 * @return Font object of file
	 */
	public static Font getFont(String path, int size)
	{
		try 
		{
			return Font.createFont(Font.TRUETYPE_FONT, rl.getClass().getResourceAsStream(path)).deriveFont(Font.BOLD, size);
		}
		catch (FontFormatException e)
		{
			e.printStackTrace();
			System.out.println("This font is not supported");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
