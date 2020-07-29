package Computer_master_Manager;

import Computer_Master_Bullets.Bullet;
import Computer_Master_Display.Display;
import Computer_Master_Enemy.Enemy;
import Computer_Master_Setup.gameSetUp;
import computer_Master_Entity.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Manager implements KeyListener {

    private Player player;
    public static ArrayList<Bullet> bullet;
    private ArrayList<Enemy> enemies;

    private long current, delay;
    private int health, score;

    boolean start;

    public Manager(){

    }

    public void init(){

        Display.frame.addKeyListener(this);

        player = new Player(gameSetUp.gameWidth/2+50, gameSetUp.gameHeight+20);
        player.init();
        bullet = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();

        current = System.nanoTime();
        delay = 800;
        score = 0;

        health= player.getHealth();
//        start = true;

    }

    public void tick(){
        if(start){

            player.tick();
            for (int i =0; i<bullet.size(); i++){
                bullet.get(i).tick();
            }

            long breaks = (System.nanoTime()-current)/1000000;

            if(breaks >delay) {
                for (int i = 0; i < 2; i++) {
                    Random random = new Random();

                    int randX = random.nextInt(450);
                    int randY = random.nextInt(450);

                    if (health>0)
                        enemies.add(new Enemy(randX, -randY));
                }
                current = System.nanoTime();
            }

            //enemy
            for (int i=0; i<enemies.size(); i++){
                enemies.get(i).tick();
            }
        }

    }

    public void render(Graphics g) {

        if (start) {
            player.render(g);


            for (int i = 0; i < bullet.size(); i++) {
                bullet.get(i).render(g);
            }

            for (int i = 0; i < bullet.size(); i++) {
                if (bullet.get(i).getY() <= 50) {
                    bullet.remove(i);
                    i--;
                }
            }


            //enemies

            for (int i = 0; i < enemies.size(); i++) {
                if (!(enemies.get(i).getX() <= 50 || enemies.get(i).getX() >= 425
                        || enemies.get(i).getY() >= 425))
                    if (enemies.get(i).getY() >= 50)
                        enemies.get(i).render(g);
            }

            //enemies and bullet collision detected

            for (int i = 0; i < enemies.size(); i++) {

                int ex = enemies.get(i).getX();
                int ey = enemies.get(i).getY();

                for (int j = 0; j < bullet.size(); j++) {

                    int bx = bullet.get(j).getX();

                    int by = bullet.get(j).getY();


                    if (ex < bx + 6 && ex + 25 > bx && ey < by + 6 &&
                            ey + 25 > by) {
                        enemies.remove(i);

                        i--;

                        bullet.remove(j);

                        j--;

                        score += 5;
                    }
                }
            }

            //enemies and player collision detected

            for (int i = 0; i < enemies.size(); i++) {

                int ex = enemies.get(i).getX();
                int ey = enemies.get(i).getY();


                int px = player.getX();
                int py = player.getY();


                if (ex < px + 30 && ex + 25 > px && ey < py + 30 &&
                        ey + 25 > py) {
                    enemies.remove(i);
                    i--;

                    health--;
                    System.out.println(health);

                    if (health <= 0) {

                        enemies.removeAll(enemies);
                        player.setHealth(0);

                        start = false;


                    }


                }


            }

            g.setColor(Color.blue);
            g.setFont(new Font("arial", Font.BOLD, 40));
            g.drawString("Score : " + score, 70, 500);


        }

        else {
            g.setColor(Color.blue);
            g.setFont(new Font("arial", Font.PLAIN, 33));
            g.drawString("Press enter to start", 100, (gameSetUp.gameHeight/2)+50);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int source = keyEvent.getKeyCode();

        if(source == keyEvent.VK_ENTER){
            start = true;
            init();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
