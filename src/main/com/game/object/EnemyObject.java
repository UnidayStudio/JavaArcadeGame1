package main.com.game.object;

import main.com.game.math.Vector2D;
import main.com.game.assets.ImageTexture;
import main.com.game.math.Camera2D;
import main.com.game.math.Transform2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class EnemyObject extends GameObject {
    private static final double SPEED = 0.5;
    private boolean flipSprite = false;
    private static ImageTexture[] sprites = null;

    private int randomOffset;

    public EnemyObject(){
        super();
        setCollisionType(GameObject.CollisionType.COLLISION_DYNAMIC);

        if (sprites == null) {
            sprites = new ImageTexture[4];
            sprites[0] = new ImageTexture("res/CopWalk1.png");
            sprites[1] = new ImageTexture("res/CopWalk2.png");
            sprites[2] = new ImageTexture("res/CopWalk3.png");
            sprites[3] = new ImageTexture("res/CopWalk4.png");
        }

        Random r = new Random();
        randomOffset = r.nextInt(90870) * 100;
    }

    public void update(ArrayList<String> activeKeyList) {
        int frame = (int)(System.currentTimeMillis() + randomOffset)/1500;

        double x = 0.0;
        if (frame % 2 == 0){
            x = SPEED;
            flipSprite = false;
        }
        else{
            x = -SPEED;
            flipSprite = true;
        }

        Vector2D linV = getLinearVelocity();
        linV.add(x, 0.0);
        setLinearVelocity(linV);
    }

    public void draw(Graphics g, Camera2D camera) {
        Transform2D modelView = camera.getModelViewTransform(this);
        Vector2D position = modelView.getPosition();
        Vector2D scale = modelView.getScale();

        if (flipSprite){
            position.x += scale.x;
            scale.x = -scale.x;
        }

        int frame = (int)(System.currentTimeMillis() + randomOffset)/300;
        BufferedImage image = sprites[frame % 4].get();
        g.drawImage(image, (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
    }

}
