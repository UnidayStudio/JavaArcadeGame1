package main.com.game.object;

import main.com.game.math.Transform2D;
import main.com.game.math.Vector2D;
import main.com.game.math.Camera2D;

import java.awt.*;
import java.util.ArrayList;

public class StaticObject extends GameObject {
    private Color objectColor;

    public StaticObject(){
        super();
        objectColor = new Color(63,63,63);

        setCollisionType(GameObject.CollisionType.COLLISION_STATIC);
    }

    public void update(ArrayList<String> activeKeyList) {

    }

    public void draw(Graphics g, Camera2D camera) {
        Transform2D modelView = camera.getModelViewTransform(this);
        Vector2D position = modelView.getPosition();
        Vector2D scale = modelView.getScale();

        g.setColor(objectColor);
        g.fillRect((int) position.x, (int) position.y, (int) scale.x, (int) scale.y);
    }

    public Color getObjectColor() {
        return objectColor;
    }

    public void setObjectColor(Color objectColor) {
        this.objectColor = objectColor;
    }
}
