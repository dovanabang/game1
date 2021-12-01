package uet.oop.bomberman.control.collision;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame._map;
import static uet.oop.bomberman.BombermanGame._sprite;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;


public class Collision {

    public static boolean block_down(Entity entity) {
        return (_map[entity.getX() ][entity.getY()  + 1] == '#');
    }

    public static boolean block_up(Entity entity) {
        return (_map[entity.getX() ][entity.getY()  - 1] == '#');
    }

    public static boolean block_left(Entity entity) {
        return (_map[entity.getX()  - 1][entity.getY() ] == '#');
    }

    public static boolean block_right(Entity entity) {
        return  (_map[entity.getX()  + 1][entity.getY() ] == '#');
    }

    public static boolean canMove(int nextX, int nextY) {
        int size = SCALED_SIZE;

        int nextX_1 = nextX / size;
        int nextY_1 = nextY / size;

        int nextX_2 = (nextX + size - 1) / size;
        int nextY_2 = nextY / size;

        int nextX_3 = nextX / size;
        int nextY_3 = (nextY + size - 1) / size;

        int nextX_4 = (nextX + size - 1) / size;
        int nextY_4 = (nextY + size - 1) / size;
           
        
       
        return !((_map[nextY_1][nextX_1] == '#' || _map[nextY_1][nextX_1] == '*') ||
                (_map[nextY_2][nextX_2] == '#' || _map[nextY_2][nextX_2] == '*') ||
                (_map[nextY_3][nextX_3] == '#' || _map[nextY_3][nextX_3] == '*') ||
                (_map[nextY_4][nextX_4] == '#' || _map[nextY_4][nextX_4] == '*'));
    }

}
