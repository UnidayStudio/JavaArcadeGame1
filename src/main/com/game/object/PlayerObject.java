package main.com.game.object;

import main.com.game.app.GameApp;
import main.com.game.assets.ImageTexture;
import main.com.game.math.Camera2D;
import main.com.game.math.Transform2D;
import main.com.game.math.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PlayerObject extends GameObject {
    private static final double SPEED = 1.2;
    private static final double JUMP_FORCE = -40.0;

    private boolean jumpKeyFlag = false;
    private boolean isWalking = false;
    private boolean flipSprite = false;

    private ImageTexture[] sprites;

    public PlayerObject() {
        super();
        setCollisionType(GameObject.CollisionType.COLLISION_DYNAMIC);

        sprites = new ImageTexture[6];
        sprites[0] = new ImageTexture("res/GuyIdle1.png");
        sprites[1] = new ImageTexture("res/GuyIdle2.png");

        sprites[2] = new ImageTexture("res/GuyWalk1.png");
        sprites[3] = new ImageTexture("res/GuyWalk2.png");
        sprites[4] = new ImageTexture("res/GuyWalk3.png");
        sprites[5] = new ImageTexture("res/GuyWalk4.png");
    }

    public void update(ArrayList<String> activeKeyList) {
        double x = 0.0;
        double y = 0.0;

        isWalking = false;
        if (activeKeyList.contains("d")){
            x = SPEED;
            isWalking = true;
            flipSprite = false;
        }
        else if (activeKeyList.contains("a")){
            x = -SPEED;
            isWalking = true;
            flipSprite = true;
        }

        if (activeKeyList.contains("w")){
            if (!jumpKeyFlag){
                y = JUMP_FORCE;
                jumpKeyFlag = true;
            }
        } else {
            jumpKeyFlag = false;
        }

        Vector2D linV = getLinearVelocity();
        linV.add(x, y);
        setLinearVelocity(linV);

        GameApp app = GameApp.getInstance();
        app.getCamera().setCenter(getTransform().getCenterPosition());
    }

    public void draw(Graphics g, Camera2D camera) {
        Transform2D modelView = camera.getModelViewTransform(this);
        Vector2D position = modelView.getPosition();
        Vector2D scale = modelView.getScale();

        if (flipSprite){
            position.x += scale.x;
            scale.x = -scale.x;
        }

        BufferedImage image;
        int frame = (int)System.currentTimeMillis()/300;
        if (isWalking){
            image = sprites[2 + (frame % 4)].get();
        }
        else{
            image = sprites[frame % 2].get();
        }
        g.drawImage(image, (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
    }
}
