package Computer_Master_Setup;

import Computer_Master_Display.Display;
import Computer_master_Graphics.loadImage;
import Computer_master_Manager.Manager;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class gameSetUp implements Runnable{
    String title;
    int width;
    int height;

    public static final int gameWidth = 400;
    public static final int gameHeight = 400;

    private Manager manager;

    private Thread thread;

    private boolean running;

    private Display display;

    private BufferStrategy buffer;

    private Graphics g;

    private int y;

    public gameSetUp(String title, int width, int height) {   //constructer, this call came from Main Class
        this.height = height;
        this.title = title;
        this.width = width;
    }

    public void init() {

        display = new Display(title, width, height);
        loadImage.init();
        manager = new Manager();
        manager.init();
    }

    public synchronized void start() {  // This function is accessed by Main Class

        if (running)
            return;

        running = true;

        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }

    }

    public synchronized void stop() {

        if (!(running))
            return;

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void tick(){
        manager.tick();
    }

    public void render(){

        buffer = display.getCanvas().getBufferStrategy();

        if(buffer == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = buffer.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        //draw

//        g.setColor(Color.WHITE);
//        g.fillRect(50, 50, gameWidth, gameHeight);

        g.drawImage(loadImage.bufferedImage, 50, 50, gameWidth, gameHeight, null);


        manager.render(g);

        //end of draw

        buffer.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();      //This call goes to Display Class

        int fps = 50;
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long current = System.nanoTime();


        while (running) {

            delta = delta + (System.nanoTime()-current)/timePerTick;

            current = System.nanoTime();

            if(delta>=1) {
                tick();
                render();
                delta--;
            }
        }
    }
}
