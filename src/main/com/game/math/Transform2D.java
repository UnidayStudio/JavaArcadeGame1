package main.com.game.math;

public class Transform2D {
    private Vector2D position;
    private Vector2D scale;

    public Transform2D(){
        position = new Vector2D(0,0);
        scale = new Vector2D(100,100);
    }

    public Vector2D getEndPosition(){
        Vector2D endPosition = new Vector2D(position);
        endPosition.add(scale);

        return endPosition;
    }

    public Vector2D getCenterPosition(){
        Vector2D centerPosition = new Vector2D(position);
        Vector2D halfScale = new Vector2D(scale);
        halfScale.multiply(0.5);
        centerPosition.add(halfScale);

        return centerPosition;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Vector2D getScale() {
        return scale;
    }

    public void setScale(Vector2D scale) {
        this.scale = scale;
    }
}
