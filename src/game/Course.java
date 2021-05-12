package game;

import org.newdawn.slick.Graphics;

public class Course {

    private static final float VELOCITY = 0.01f;
    private static final float OTHER_VELOCITY = 2;
    private Point[] points;
    private float arc;
    private float stepX, stepY;
    private float currX;
    private float currY;
    private boolean phase1Over, phase2Over, phase3Over;

    private float angle;

    public Course(Point[] points, float angle)
    {
        this.points = points;
        arc = 0;
        phase1Over = phase2Over = phase3Over = false;
        currX = points[0].getX();
        currY = points[0].getY();
        setPhase(points[0], points[1]);
        this.angle = angle;
    }

    public void drawPath(Graphics graphics, int xOffset)
    {
        graphics.drawLine(points[0].getX() + xOffset,points[0].getY(),points[1].getX()+xOffset,points[1].getY());
        for(float i = 0.0f; i <= 1.0f; i += 0.01)
        {
            Point p = getSplinePoint(i);
            graphics.drawRect(p.getX()+ xOffset, p.getY(),1,1);
        }
        graphics.drawLine(points[2].getX()+ xOffset,points[2].getY(),points[3].getX()+xOffset,points[3].getY());
    }

    public void update()
    {
        if(!phase1Over)
            phase1Update();
        if(phase1Over &&!phase2Over)
            phase2Update();
        if(phase1Over && phase2Over && !phase3Over)
            phase3Update();
    }

    private void phase1Update()
    {
        if((Math.abs(stepX) == 1 && Math.abs(currX-points[1].getX()) > OTHER_VELOCITY ) || (Math.abs(stepY) == 1 && Math.abs(currY-points[1].getY()) > OTHER_VELOCITY )){
            currX += stepX * OTHER_VELOCITY;
            currY += stepY * OTHER_VELOCITY;
        }
        else
        {
            currX = points[1].getX();
            currY = points[1].getY();
            phase1Over= true;
        }
    }

    private void phase2Update()
    {
        arc += VELOCITY;
        Point p = getSplinePoint(arc);
        currX = p.getX();
        currY = p.getY();
        if (arc >= 1) {
            phase2Over = true;
            setPhase(points[2], points[3]);
        }
    }

    private void phase3Update()
    {
        if((Math.abs(stepX) == 1 && Math.abs(currX-points[3].getX()) > OTHER_VELOCITY ) || (Math.abs(stepY) == 1 && Math.abs(currY-points[3].getY()) > OTHER_VELOCITY )){
            currX += stepX * OTHER_VELOCITY;
            currY += stepY * OTHER_VELOCITY;
        }
        else
        {
            currX = points[3].getX();
            currY = points[3].getY();
            phase3Over= true;
        }
    }

    private void setPhase(Point p1, Point p2)
    {
        float diffX = p1.getX() - p2.getX();
        float diffY = p1.getY() - p2.getY();

        //angle = Math.atan2(diffX, diffY);
        //System.out.println("angle is " + angle + " radians and " + Math.toDegrees(angle) + " degrees.");

        if(Math.abs(diffX) > Math.abs(diffY)) {
            stepX = 1;
            stepY = Math.abs(diffY/diffX);
        }
        else
        {
            stepX = Math.abs(diffX/diffY);
            stepY = 1;
        }

        if(diffX > 0)
            stepX = -(stepX);

        if(diffY > 0)
            stepY = -(stepY);
    }

    private Point getSplinePoint(float t)
    {
        //calculate index of nodes
        int p1 = 1;
        int p2 = 2;
        int p3 = 3;
        int p0 = 0;

        float t2 = t*t;
        float t3 = t2*t;

        //calculate the influence of each node
        float q0 = -t3 + 2.0f*t2 - t;
        float q1 = 3.0f*t3 - 5.0f*t2 + 2.0f;
        float q2 = -3.0f*t3 + 4.0f*t2 + t;
        float q3 = t3 - t2;

        //factor in the influence with the position of each point
        float tx = 0.5f * (points[p0].getX() * q0 + points[p1].getX() * q1 + points[p2].getX() * q2 + points[p3].getX() * q3);
        float ty = 0.5f * (points[p0].getY() * q0 + points[p1].getY() * q1 + points[p2].getY() * q2 + points[p3].getY() * q3);

        return new Point(tx,ty);
    }

    public float getCurrX() {
        return currX;
    }

    public float getCurrY() {
        return currY;
    }

    public boolean isCourseCompleted() {
        return phase1Over && phase2Over && phase3Over;
    }
}
