package main.com.game.object;

import main.com.game.app.GameApp;
import main.com.game.assets.ImageTexture;

public class CoinObject extends PropObject {
    private static ImageTexture coin = null;

    public CoinObject(){
        super(coin);
        if (coin == null){
            coin = new ImageTexture("res/coin.png");
            sprite = coin;
        }

        setCollisionType(CollisionType.COLLISION_GHOST);
    }
    public void collisionCallback(GameObject other) {
        if (other.getCollisionType() == CollisionType.COLLISION_DYNAMIC){
            GameApp.getInstance().removeObject(this);
        }
    }
}
