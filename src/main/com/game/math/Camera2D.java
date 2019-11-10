package main.com.game.math;

import main.com.game.object.GameObject;

public class Camera2D extends Transform2D {
    private final int WIDTH;
    private final int HEIGHT;
    private double cameraZoom;

    public Camera2D(int width, int height, double zoom){
        super();
        WIDTH = width;
        HEIGHT = height;
        cameraZoom = zoom;

        updateScale();
    }

    public void setCenter(Vector2D position){
        Vector2D newPosition = new Vector2D(position);
        double x = 1.0 / getScale().x;
        double y = 1.0 / getScale().y;
        newPosition.subtract((WIDTH/2.0) * x, (HEIGHT/2.0) * y);

        setPosition(newPosition);
    }

    public double getCameraZoom() {
        return cameraZoom;
    }

    public void setCameraZoom(double cameraZoom) {
        this.cameraZoom = cameraZoom;
        updateScale();
    }

    public void updateScale(){
        setScale(new Vector2D((WIDTH / HEIGHT) * cameraZoom, cameraZoom));
    }

    public Transform2D getModelViewTransform(GameObject object){
        Transform2D transform = new Transform2D();
        Vector2D screenScale = getScale();

        Vector2D position = new Vector2D(object.getTransform().getPosition());
        position.subtract(getPosition());
        position.x *= screenScale.x;
        position.y *= screenScale.y;

        Vector2D scale = new Vector2D(object.getTransform().getScale());
        scale.x *= screenScale.x;
        scale.y *= screenScale.y;

        transform.setPosition(position);
        transform.setScale(scale);

        return transform;
    }
}
