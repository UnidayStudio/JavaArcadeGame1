package main.com.game.app;

import main.com.game.object.PlayerObject;
import main.com.game.assets.ImageTexture;
import main.com.game.assets.Map2D;
import main.com.game.math.Camera2D;
import main.com.game.math.Vector2D;
import main.com.game.object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


public class GameApp extends JPanel implements Runnable, KeyListener {
    private static final int WIDTH = 900;
    private static final int HEIGHT = 550;
    private static final int FPS = 60;
    private static final double GRAVITY = -0.98;
    private static final double CAMERA_ZOOM = 0.8;

    private Thread thread;

    private long targetTime = 1000 / FPS;
    private boolean isRunning = false;

    private boolean lockGameObjects = false;
    private ArrayList<GameObject> gameObjects;
    private ArrayList<GameObject> gameObjectsToAdd;
    private ArrayList<GameObject> gameObjectsToRemove;
    private ArrayList<String> activeKeyList;
    private ImageTexture background;

    private Camera2D camera;
    private Map2D map;

    private static GameApp gameAppSingleton = null;

    private GameApp() {
        activeKeyList = new ArrayList<String>();
        gameObjects = new ArrayList<GameObject>();
        gameObjectsToAdd = new ArrayList<GameObject>();
        gameObjectsToRemove = new ArrayList<GameObject>();
        camera = new Camera2D(WIDTH, HEIGHT, CAMERA_ZOOM);
        map = new Map2D(this);

        background = new ImageTexture("res/bg7.png");

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        setFocusable(true);
        requestFocus();

        start();
    }

    public static GameApp getInstance(){
        if (gameAppSingleton == null){
            gameAppSingleton = new GameApp();
        }
        return gameAppSingleton;
    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();

        // Adding the game objects.
        map.load("res/map1.map");

        PlayerObject obj1 = new PlayerObject();
        obj1.getTransform().setPosition(new Vector2D(50.0,50.0));
        addObject(obj1);
    }

    public void run(){
        long start, elapsed, wait;

        // Gameloop
        while (isRunning){
            start = System.nanoTime();

            tick();
            updatePhysics();
            repaint();

            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;

            if (wait <= 0){
                wait = 5;
            }
            try{
                Thread.sleep(wait);
            } catch (Exception e){
                e.printStackTrace();
            }

            posGameUpdate();
        }
    }

    private void tick(){
        // Updates the logic
        for (GameObject object: gameObjects) {
            object.update(activeKeyList);
        }
    }

    private void updatePhysics(){
        // Gravity and linear velocity
        for (GameObject object: gameObjects) {
            if (object.getCollisionType() == GameObject.CollisionType.COLLISION_DYNAMIC){
                Vector2D linV = object.getLinearVelocity();
                linV.multiply(0.9); // air resistance
                linV.subtract(0.0, GRAVITY);
                object.setLinearVelocity(linV);
                object.applyLinearVelocity();
            }
        }
        // Collisions
        for (GameObject objectA: gameObjects) {
            for (GameObject objectB: gameObjects) {
                if (objectA != objectB) {
                    if (objectA.collided(objectB, true)) {
                        objectA.collisionCallback(objectB);
                        objectB.collisionCallback(objectA);
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        // Draw the background
        g.drawImage(background.get(), 0, 0, WIDTH+10, HEIGHT+10, null);

        lockGameObjects = true;
        for (GameObject object: gameObjects) {
            object.draw(g, camera);
        }
        lockGameObjects = false;
    }

    private void posGameUpdate(){
        if (lockGameObjects){
            return;
        }
        gameObjects.addAll(gameObjectsToAdd);
        gameObjectsToAdd.clear();

        for (GameObject object : gameObjectsToRemove){
            gameObjects.remove(object);
        }
        gameObjectsToRemove.clear();
    }

    public void keyTyped(KeyEvent e) {

    }

    //@Override
    public void keyPressed(KeyEvent e) {
        String key = Character.toString(e.getKeyChar());

        if (!activeKeyList.contains(key)) {
            activeKeyList.add(key);
        }
    }

    public void keyReleased(KeyEvent e) {
        String key = Character.toString(e.getKeyChar());

        if (activeKeyList.contains(key)) {
            activeKeyList.remove(Character.toString(e.getKeyChar()));
        }
    }
    public void addObject(GameObject object){
        gameObjectsToAdd.add(object);
    }

    public void removeObject(GameObject object){
        //gameObjects.remove(object);
        gameObjectsToRemove.add(object);
    }

    public Camera2D getCamera() {
        return camera;
    }

    public void setCamera(Camera2D camera) {
        this.camera = camera;
    }
}
