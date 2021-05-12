package game;

import org.newdawn.slick.*;
import org.newdawn.slick.util.pathfinding.navmesh.Link;

import java.awt.geom.AffineTransform;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Ship2 {

    private static final int VELOCITY = 2;
    private Image shipImg;
    private Queue<Course> courseQueue;

    private float x, y;
    private Point[] course;
    private float currAngle;

   // private AffineTransform originalOrientataion;


    public Ship2(String imageRef, float x, float y) throws SlickException {
        shipImg = new Image(imageRef);
        courseQueue = new LinkedList<>();
        course = new Point[4];
        course[0] = new Point(100,500);
        course[1] = new Point(200,200);
        course[2] = new Point(300,200);
        course[3] = new Point(400,500);

        float diffX = course[0].getX() - course[1].getX();
        float diffY = course[0].getY() - course[1].getY();
        currAngle = (float) Math.atan2(diffX, diffY);

        this.x = x;
        this.y = y;

        // originalOrientataion = new AffineTransform();
    }

    public void update(GameContainer gameContainer) {
        Input input = gameContainer.getInput();
        if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            courseQueue.add(new Course(course, currAngle));
        }

        if (!courseQueue.isEmpty()) {
            Course currentCourse = courseQueue.peek();
            currentCourse.update();
            x = currentCourse.getCurrX();
            y = currentCourse.getCurrY();
            if(currentCourse.isCourseCompleted())
                courseQueue.remove();
        }

    }

    public void render(Graphics graphics)
    {

        //draw here
        //shipImg.setCenterOfRotation(x+ shipImg.getWidth()/2,y);
        shipImg.setRotation((float)Math.toDegrees(currAngle));
        shipImg.draw(x,y);
        if(!courseQueue.isEmpty())
        {
            courseQueue.peek().drawPath(graphics, (shipImg.getWidth()/2));
        }
    }
}
