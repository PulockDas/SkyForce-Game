package Computer_Master_Display;

import javax.swing.*;
import java.awt.*;

public class Display {

    String title;
    int width;
    int height;

    public static JFrame frame;
    private Canvas canvas;

    public Display(String title, int width, int height){
        this.height=height;
        this.title=title;
        this.width=width;

        CreateDisplay();
    }

    public void CreateDisplay(){
        frame = new JFrame(title);
        frame.setSize(width, height);

        frame.setVisible(true);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setBackground(new Color(212, 204, 161));
//        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();

    }

    public Canvas getCanvas(){
        return canvas;
    }
}
