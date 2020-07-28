package Computer_master_Manager;

import Computer_Master_Bullets.Bullet;
import Computer_Master_Enemy.Enemy;
import Computer_Master_Setup.gameSetUp;
import computer_Master_Entity.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Manager {

    private Player player;
    public static ArrayList<Bullet> bullet;
    private ArrayList<Enemy> enemies;

    private long current, delay;

    public Manager(){

    }

    public void init(){
        player = new Player(gameSetUp.gameWidth/2+35, gameSetUp.gameHeight+20);
        player.init();
        bullet = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();

        current = System.nanoTime();
        delay = 800;

    }

    public void tick(){
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

                enemies.add(new Enemy(randX, -randY));
            }
            current = System.nanoTime();
        }

        //enemy
        for (int i=0; i<enemies.size(); i++){
            enemies.get(i).tick();
        }
    }

    public void render(Graphics g){
        player.render(g);

//        int px = player.getX();
//        int py = player.getY();
//
//        if (px<)


        for (int i =0; i<bullet.size(); i++){
            bullet.get(i).render(g);
        }

        for (int i=0; i<bullet.size(); i++){
            if(bullet.get(i).getY()<=50){
                bullet.remove(i);
                i--;
            }
        }


        //enemies

        for (int i=0; i<enemies.size(); i++){
            if (!(enemies.get(i).getX()<=50 || enemies.get(i).getX()>=425
                    || enemies.get(i).getY() >= 425))
                if (enemies.get(i).getY()>=50)
                    enemies.get(i).render(g);
        }

        for(int i=0; i<enemies.size(); i++){

            int ex = enemies.get(i).getX();
            int ey = enemies.get(i).getY();

            for(int j=0; j<bullet.size(); j++){

                int bx = bullet.get(j).getX();

                int by = bullet.get(j).getY();


                if(ex < bx+6 && ex +25 > bx && ey<by+6 &&
                    ey + 25 > by){
                    enemies.remove(i);

                    i--;

                    bullet.remove(j);

                    j--;
                }
            }
        }


    }
}
