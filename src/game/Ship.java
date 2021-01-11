/**
 * <h1>Ship class</h1>
 *
 * <p>This class is that the user controls as a player.</p>
 * 
 * <p>Created:7/13/18</p>
 * @version 12/28/18
 * 
 * @author Lauryn Jefferson
 */
package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import assets.Asset;
import assets.BasicAsset;
import utils.MouseManager;
import main.Window;

public class Ship
{
	//constants
	private final int SPEED = 4;
	
	//objects
	private ShipImage ship;
	private ArrayList<BasicAsset> enemies;
	private AffineTransform originalOrientataion;
	
	//variables
	private double x,y;
	private double stepX, stepY;
	private double destX, destY;
	private double relX, relY;
	private double angle, currAngle, prevAngle;

	/**
	 * <h2>Ship() constructor</h2>
	 * 
	 * <p>This constructor sets up the ship.</p>
	 */
	public Ship(ShipImage shipImg,ArrayList<Asset> bkgassets)
	{
		ship = shipImg;
		destX = x = ship.getX();
		destY = y = ship.getY();
		prevAngle = currAngle = angle = 0;
		enemies = new ArrayList<BasicAsset>();
		originalOrientataion = new AffineTransform();

		for(Asset a: bkgassets)
		{
			if (a != null && a.getClassification().compareTo("ship") == 0)
				enemies.add((BasicAsset)a);
		}
	}
	
	/**
	 * <h2>update() method</h2>
	 * 
	 * <p>This method updates the conditions of
	 * the trash.</p>
	 */
	public void update()
	{
		if(MouseManager.isReleased())
		{
			int newX = MouseManager.getMouseX();
			int newY = MouseManager.getMouseY();

			boolean fire = false;
			//angle = Math.toDegrees(Math.atan2(MouseManager.getMouseX() - y,MouseManager.getMouseY()- x))+180;
			//if (angle>360){ angle=angle-360; }

			for(BasicAsset s: enemies){
				 {
					Rectangle r = new Rectangle(s.getX(),s.getY(),s.getWidth(),s.getHeight());
					if (r.contains(newX, newY))
						fire = true;
				}
			}
			if(fire)
				fire();
			else {
				destX = newX - ship.getWidth()/2;
				destY = newY;
				setCourse();
				setAngle();
			}
			//ship.setDestAngle(angle);



		}


		/*if(x != destX)
		{
			x += SPEED * Math.sin(Math.atan2(destY - y,destX- x));
		}

		if(y != destY)
			y += SPEED * Math.cos(Math.atan2(destY - y,destX- x));*/

		if(destX != x)
		{
			if (Math.abs(destX - x) < SPEED)
				x = destX;
			else
				x += stepX;
		}

		if(destY != y )
		{
			if (Math.abs(destY - y) < SPEED)
				y = destY;
			else
				y += stepY;
		}

		//keep ship on screen
		if(x < 0)
			x = 0;
		if(x > Window.getWidth() - ship.getWidth())
			x = Window.getWidth() - ship.getWidth();
		if(y < 0)
			y  = 0;
		if(y > Window.getHeight() - ship.getHeight())
			y = Window.getHeight() - ship.getHeight();

		ship.setX((int)x);
		ship.setY((int)y);

		ship.update();
	}

	private void setAngle()
	{
		//angle =);
		angle = Math.toDegrees(Math.atan2(relX, relY));
		//if (angle>360){ angle=angle-360; }
		//ship.setDestAngle(Math.toDegrees(Math.atan2(MouseManager.getMouseX() - y,MouseManager.getMouseY()- x))+180);
		System.out.println("OG x = " + x);
		System.out.println("OG y = " + y);
		System.out.println("Dest x = " + destX);
		System.out.println("Dest y = " + destY);
		System.out.println("Rel x = " + relX);
		System.out.println("Rel y = " + relY);
		System.out.println("angle is :" + angle);

		//find shortest route
		if(angle > 180){
			angle = 360 - angle;
			System.out.println("angle adjusted to " + angle);
		}
		if(angle < -180){
			angle = angle + 360;
			System.out.println("angle adjusted to " + angle);
		}

		ship.setDestAngle(angle);
	}
	private void setCourse()
	{
		/*double slope = (destY - y) / (destX - x);
		if(slope > 0)
		{
			stepX = 1;
			stepY = slope;
		}
		else
		{
			stepX = slope;
			stepY = 1;
		}*/

		if(destX > x)
			stepX = 4;
		else
			stepX = -4;
		if(destY > y)
			stepY = 4;
		else
			stepY = -4;
		relX = destX - x;
		relY = y - destY;
	}
	
	/**
	 * <h2>render() method</h2>
	 * 
	 * <p>This method draws the ship to the screen with the given
	 * graphics object.</p>
	 * 
	 * @param g graphics for location to draw to
	 */
	public void render(Graphics2D g)
	{
		ship.render(g);
	}

	/**
	 * <h2>getCollisionBox() method</h2>
	 * 
	 * <p>This method returns where the collision box is 
	 * for the trash in a rectangle object.</p>
	 * 
	 * @return collision for trash
	 */
	public Rectangle getCollisionBox()
	{
		return new Rectangle((int)x,(int)y,ship.getWidth(),ship.getHeight());
	}

	public void fire()
	{
		int xFire = MouseManager.getMouseX();
		int yFire = MouseManager.getMouseY();

		System.out.println("ship");

		ship.fire();
	}
}
