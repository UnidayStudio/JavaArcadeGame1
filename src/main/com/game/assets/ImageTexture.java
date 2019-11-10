package main.com.game.assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageTexture {
    private BufferedImage block;

    public ImageTexture(String fileName) {
        try {
            // System.getProperty("user.dir") +
            block = ImageIO.read(new FileInputStream(fileName));
        } catch (IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public BufferedImage get() {
        return block;
    }

    public void set(BufferedImage block) {
        this.block = block;
    }
}
