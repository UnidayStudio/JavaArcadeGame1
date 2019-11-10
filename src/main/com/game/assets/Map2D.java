package main.com.game.assets;

import main.com.game.app.GameApp;
import main.com.game.math.Vector2D;
import main.com.game.object.CoinObject;
import main.com.game.object.EnemyObject;
import main.com.game.object.GameObject;
import main.com.game.object.PropObject;
import main.com.game.object.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Map2D {
    private static final int RESOLUTION = 100;
    private ImageTexture[] sprites;
    private GameApp app;

    public Map2D(GameApp gameApp) {
        app = gameApp;

        sprites = new ImageTexture[8];
        sprites[0] = new ImageTexture("res/map/tl.png");
        sprites[1] = new ImageTexture("res/map/t.png");
        sprites[2] = new ImageTexture("res/map/tr.png");
        sprites[3] = new ImageTexture("res/map/l.png");
        sprites[4] = new ImageTexture("res/map/r.png");
        sprites[5] = new ImageTexture("res/map/center.png");
        sprites[6] = new ImageTexture("res/map/c.png");
        sprites[7] = new ImageTexture("res/map/ct.png");
    }

    public void load(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = reader.readLine();
            int y=0;
            while (line != null){
                String[] objects = line.split(" ");

                int x = 0;
                for (String object : objects){
                    if (!object.equals(".")) {

                        GameObject prop;
                        if (object.equals("@")) {
                            // Coin!
                            prop = new CoinObject();
                        }
                        else if (object.equals("#")){
                            // Enemy!
                            prop = new EnemyObject();
                            //prop = new CoinObject();
                        }
                        else {
                            ImageTexture texture = sprites[Integer.parseInt(object)-1];
                            prop = new PropObject(texture);
                        }
                        prop.getTransform().setScale(new Vector2D(RESOLUTION, RESOLUTION));
                        prop.getTransform().setPosition(new Vector2D(x * RESOLUTION, y * RESOLUTION));
                        app.addObject(prop);
                    }
                    x++;
                }
                line = reader.readLine();
                y++;
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
