package Computer_master_Manager;

import Computer_Master_Bullets.Bullet;
import Computer_Master_Setup.gameSetUp;
import computer_Master_Entity.Player;

import java.awt.*;
import java.util.ArrayList;

public class Manager {

    private Player player;
    public static ArrayList<Bullet> bullet;

    public Manager(){

    }

    public void init(){
        player = new Player(gameSetUp.gameWidth/2+35, gameSetUp.gameHeight+20);
        player.init();
        bullet = new ArrayList<Bullet>();

    }

    public void tick(){
        player.tick();
        for (int i =0; i<bullet.size(); i++){
            bullet.get(i).tick();
        }
    }

    public void render(Graphics g){
        player.render(g);

        for (int i =0; i<bullet.size(); i++){
            bullet.get(i).render(g);
        }

        for (int i=0; i<bullet.size(); i++){
            if(bullet.get(i).getY()<=50){
                bullet.remove(i);
                i--;
            }
        }
    }
}
