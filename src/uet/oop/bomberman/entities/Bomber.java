package uet.oop.bomberman.entities;

//import javafx.scene.SnapshotParameters;
//import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.entities.lifesingame.LifesInGame;

//import static uet.oop.bomberman.BombermanGame._map;
//import static uet.oop.bomberman.BombermanGame._sprite;

public class Bomber extends LifesInGame {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public Bomber() {}

    public Bomber(int isMove, int swap, String direction, int countStep, int countToRun) {
        super(4, 1, "down", 0, 0);
    }


    @Override
    public void update() {

    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber) {
            return true;
        }
        return e.collide(this);
    }
   

}
