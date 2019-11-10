package main.com.game.object;

import main.com.game.math.Transform2D;
import main.com.game.math.Vector2D;
import main.com.game.math.Camera2D;

import java.awt.*;
import java.util.ArrayList;

public class GameObject {
    public enum CollisionType{
        COLLISION_GHOST,
        COLLISION_STATIC,
        COLLISION_DYNAMIC
    }

    private Transform2D transform;
    private Vector2D linearVelocity;
    private CollisionType collisionType;

    public GameObject(){
        transform = new Transform2D();
        linearVelocity = new Vector2D();
        setCollisionType(CollisionType.COLLISION_GHOST);
    }

    public void update(ArrayList<String> activeKeyList){

    }

    public void draw(Graphics g, Camera2D camera){

    }

    public void resolveCollision(GameObject other){
        Transform2D otherTransform = other.getTransform();
        Vector2D center = transform.getCenterPosition();
        Vector2D position = transform.getPosition();

        if (center.y < otherTransform.getPosition().y){
            linearVelocity.y = 0.0;
            position.y = otherTransform.getPosition().y - transform.getScale().y;
        }
        else if (center.x < otherTransform.getPosition().x){
            linearVelocity.x = 0.0;
            position.x = otherTransform.getPosition().x - transform.getScale().x;
        }
        else if (center.x > otherTransform.getEndPosition().x){
            linearVelocity.x = 0.0;
            position.x = otherTransform.getEndPosition().x;
        }
        else if (center.y > otherTransform.getEndPosition().y){
            linearVelocity.y = 0.0;
            position.y = otherTransform.getEndPosition().y;
        }
        transform.setPosition(position);
    }

    public boolean collided(GameObject other, boolean resolveCollision){
        Transform2D otherTransform = other.getTransform();

        Vector2D endPosA = transform.getEndPosition();
        Vector2D endPosB = otherTransform.getEndPosition();

        // AABB Collision checking
        if (transform.getPosition().x < endPosB.x && endPosA.x > otherTransform.getPosition().x){
            if (transform.getPosition().y < endPosB.y && endPosA.y > otherTransform.getPosition().y){
                if (resolveCollision && collisionType == CollisionType.COLLISION_DYNAMIC && other.getCollisionType() != CollisionType.COLLISION_GHOST){
                    resolveCollision(other);
                }
                return true;
            }
        }
        return  false;
    }

    public void collisionCallback(GameObject other){

    }

    public void applyLinearVelocity(){
        Vector2D pos = new Vector2D(transform.getPosition());
        pos.add(linearVelocity);
        transform.setPosition(pos);
    }

    public CollisionType getCollisionType() {
        return collisionType;
    }

    public void setCollisionType(CollisionType collisionType) {
        this.collisionType = collisionType;
    }

    public Vector2D getLinearVelocity() {
        return linearVelocity;
    }

    public void setLinearVelocity(Vector2D linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Transform2D getTransform() {
        return transform;
    }

    public void setTransform(Transform2D transform) {
        this.transform = transform;
    }
}
