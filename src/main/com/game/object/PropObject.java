package main.com.game.object;

import main.com.game.assets.ImageTexture;
import main.com.game.math.Camera2D;
import main.com.game.math.Transform2D;
import main.com.game.math.Vector2D;

import java.awt.*;

public class PropObject extends GameObject{
    protected ImageTexture sprite;

    public PropObject(ImageTexture imageTexture){
        super();

        sprite = imageTexture;
        setCollisionType(GameObject.CollisionType.COLLISION_STATIC);
    }

    public void draw(Graphics g, Camera2D camera) {
        Transform2D modelView = camera.getModelViewTransform(this);
        Vector2D position = modelView.getPosition();
        Vector2D scale = modelView.getScale();

        g.drawImage(sprite.get(), (int) position.x, (int) position.y, (int) scale.x, (int) scale.y, null);
    }
}
