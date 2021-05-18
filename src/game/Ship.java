package game;

import org.newdawn.slick.*;

public class Ship {

    private static final int VELOCITY = 2;
    private Image shipImg;
    private float x, y;

    public Ship(String imageRef, float x, float y) throws SlickException {
        shipImg = new Image(imageRef);
        this.x = x;
        this.y = y;
    }

    public void update(GameContainer gameContainer) {
//        Input input = gameContainer.getInput();
//        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
//            x = input.getMouseX();
//            y = input.getMouseY();
//        }
    }

    public void render(Graphics graphics)
    {
        //draw here
        shipImg.draw(x,y);
    }

    public float getX()
    {
        return  x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }
}
