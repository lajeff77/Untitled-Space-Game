package game;

import assets.GameAsset;
import org.newdawn.slick.*;

public class Ship extends GameAsset {

//    private static final int VELOCITY = 2;
    private Image shipImg;

    public Ship(String imageRef, float x, float y) throws SlickException {
        initialized = false;
        classification = "ship";
        shipImg = new Image(imageRef);
        this.x = x;
        this.y = y;
    }

    public void update() { }

    public void render(Graphics graphics)
    {
        //draw here
        shipImg.draw(x,y);
    }
}
