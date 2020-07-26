package Computer_Master_Bullets;

import java.awt.*;

public class Bullet {

    private int x, y;
    private int speed;

    public Bullet(int x, int y){
        this.x = x;
        this.y= y;

        speed = 10;
    }

    public int getY(){
        return y;
    }

    public void init(){

    }

    public void tick(){
        y-=speed;
    }

    public void render(Graphics g){
        g.setColor(Color.blue);
        g.fillRect(x, y, 6, 10);
    }
}
