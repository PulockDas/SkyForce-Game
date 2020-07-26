package Computer_Master_Setup;

import Computer_Master_Display.Display;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class gameSetUp implements Runnable {
    String title;
    int width;
    int height;

    private Thread thread;

    private boolean running;

    private Display display;

    private BufferStrategy buffer;

    private Graphics g;

    private int y;

    public gameSetUp(String title, int width, int height) {   //constructer
        this.height = height;
        this.title = title;
        this.width = width;
    }

    public void init() {
        display = new Display(title, width, height);

    }

    public synchronized void start() {

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
        y += 1;
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

        g.fillRect(33, y , 44, 44);


        //end of draw

        buffer.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();

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
