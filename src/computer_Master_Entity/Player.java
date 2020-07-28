package computer_Master_Entity;

import Computer_Master_Bullets.Bullet;
import Computer_Master_Display.Display;
import Computer_master_Manager.Manager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {

    private int x,y;
    private boolean left, right, fire;
    private long current, delay;

    public Player(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void init(){
        Display.frame.addKeyListener(this);

        current = System.nanoTime();
        delay = 100;
    }

    public void tick(){
        if(left){
            if(x>=50)
                x -= 4;
        }

        if(right){
            if(x<=420)
                x += 4;
        }

        if(fire){
            long breaks = (System.nanoTime()-current)/1000000;
            if(breaks > delay) {
                Manager.bullet.add(new Bullet(x + 13, y));
            }

            current = System.nanoTime();
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 30, 30);
    }

    public void keyPressed(KeyEvent e){
        int source = e.getKeyCode();

        if (source == KeyEvent.VK_LEFT){
            left = true;
        }

        if (source == KeyEvent.VK_RIGHT){
            right = true;
        }

        if(source == KeyEvent.VK_B)
            fire = true;
    }

    public void keyReleased(KeyEvent e){
        int source = e.getKeyCode();

        if(source==KeyEvent.VK_LEFT){
            left = false;
        }

        if(source==KeyEvent.VK_RIGHT){
            right = false;
        }

        if(source == KeyEvent.VK_B)
            fire = false;
    }

    public void keyTyped(KeyEvent e){

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
