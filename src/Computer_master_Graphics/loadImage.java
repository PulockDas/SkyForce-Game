package Computer_master_Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class loadImage {

    public static BufferedImage bufferedImage, entities, player, enemy;

    public static void init(){
        bufferedImage = imageLoader("/res/night_back.jpg");
        entities = imageLoader("/res/entities.png");

        crop();
    }

    public static BufferedImage imageLoader(String path){

//        URL imgURL = loadImage.class.getResource(path);

        try {
            return ImageIO.read(loadImage.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

    public static void crop(){
        enemy = entities.getSubimage(0, 0, 115, 95);
        player = entities.getSubimage(115, 0, 115, 95);


    }
}
