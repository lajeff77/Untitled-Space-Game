package game;

import main.Window;

import java.awt.*;

public class Line {

    public static final int LINE_WIDTH = 10;
    private final int MAX_RATE = 100;
    private final int MIN_RATE = 50;

    private int x, y;
    private double rate;


    public Line(int x)
    {
        this.x = x;
        this.y = 0;
        this.rate = generateRate();
    }

    public void update()
    {
       if(y < Window.getHeight())
           y += Window.getHeight()* rate;
    }

    public void render(Graphics2D g)
    {
        g.setColor(Color.WHITE);
        g.fillRect(x,y,LINE_WIDTH,Window.getHeight());
    }

    private double generateRate()
    {
        double r = Math.random() *(MAX_RATE - MIN_RATE);
        r += MIN_RATE;
        return 1/r;
    }
}
