package assets;

import java.awt.*;
import java.util.ArrayList;
import main.Window;

public class Turret extends RotatingAsset
{
    private String projectilepath;
    private ArrayList<Projectile> projectiles;
    private char dir;

    public Turret(String path,int x, int y, String classification, char dir, String projectilepath,long shipID)
    {
        super(path,x,y,classification,dir);
        this.projectilepath = projectilepath;
        this.dir = dir;
        projectiles = new ArrayList<Projectile>();
        projectiles.add(new Projectile(projectilepath,x,y, "projectile",dir));
    }

    public void update()
    {
        super.update();
        ArrayList<Projectile> remove  = new ArrayList<Projectile>();
        for(Projectile p : projectiles)
        {
            p.update();
            if(p.getX() < 0 || p.getX()> Window.getWidth() || p.getY() < 0 || p.getY() > Window.getHeight())
                remove.add(p);
        }

        for(Projectile p: remove)
            projectiles.remove(p);

    }

    public void render(Graphics2D g)
    {
        super.render(g);
        for(Projectile p: projectiles)
            p.render(g);
    }
    public void fire()
    {
        System.out.println("turret");
        projectiles.add(new Projectile(projectilepath,x,y,"projectile",dir));
    }
}
