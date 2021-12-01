package uet.oop.bomberman;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
//import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.control.Move;
import uet.oop.bomberman.control.collision.Collision;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.lifesingame.Ballom;
import uet.oop.bomberman.entities.lifesingame.Doll;
import uet.oop.bomberman.entities.lifesingame.LifesInGame;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import uet.oop.bomberman.entities.lifesingame.Oneal;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static int _level = 1;
    public static int _width = 0;
    public static int _height = 0;
    public static char[][] _map;

    private GraphicsContext gc;
    private Canvas canvas;
    public static final List<Entity> block = new ArrayList<>();
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<LifesInGame> Animal = new ArrayList<>();
    public static int[][] listKill;
    public static LifesInGame player;
    private Move move;
    public static Sprite _sprite;

    public static int speed = 1;


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }


    @Override
    public void start(Stage stage) throws IOException {

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);
            player = new Bomber(1, 1, Sprite.player_right_2.getFxImage());
            //Entity bomberman = new Bomber(1, 1, Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate(_animate), 20).getFxImage());
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.LEFT ) {
                    //bomberman.setX((bomberman.getX() - 1 * speed));
                    Move.Left(player);
                }
                if (event.getCode() == KeyCode.RIGHT ) {
                    //bomberman.setX((bomberman.getX() + 1 * speed));
                    Move.Right(player);
                }
                if (event.getCode() == KeyCode.DOWN ) {
                    //bomberman.setY((bomberman.getY() + 1 * speed));
                    Move.Down(player);
                }
                if (event.getCode() == KeyCode.UP ) {
                    //bomberman.setY((bomberman.getY() - 1 * speed));
                    Move.Up(player);
                }
            });
            //entities.add(bomberman);
            entities.add(player);
            player.setLife(false);
        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle("Bomberman");
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

    }
    /*
        public void createMap() {
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    Entity object;
                    if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                    }
                    else {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                    }
                    stillObjects.add(object);
                }
            }
        }
    */
    public void update() {
        block.forEach(Entity::update);
        //entities.forEach(Entity::update);
        player.update();
        player.setCountToRun(player.getCountToRun() + 1);
        Animal.forEach(LifesInGame::update);
        if (player.getCountToRun() == 4) {
            Move.checkMove(player);
            player.setCountToRun(0);
        }
        
        for (LifesInGame a : Animal) {
            a.setCountToRun(a.getCountToRun() + 1);
            if (a.getCountToRun() == 8) {
                Move.checkMove(a);
                a.setCountToRun(0);
            }
        }
        
        
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        //entities.forEach(g -> g.render(gc));
        Animal.forEach(g -> g.render(gc));
        player.render(gc);
    }

    public void createMap() throws IOException {
        String path = "res/levels/Level" + _level + ".txt";
        try {
            File mapFile = new File(path);
            Scanner sc = new Scanner(mapFile);
            String[] info = sc.nextLine().split(" ");
            _level = Integer.parseInt(info[0]);
            _height = Integer.parseInt(info[1]);
            _width = Integer.parseInt(info[2]);

            _map = new char[_height][_width];
            
            LifesInGame Ballom1 = new Ballom(13, 1, Sprite.balloom_right1.getFxImage());
            LifesInGame Ballom2 = new Ballom(18, 5, Sprite.balloom_right1.getFxImage());
            LifesInGame Ballom3 = new Ballom(24, 5, Sprite.balloom_right1.getFxImage());
            Animal.add(Ballom1);
            Animal.add(Ballom2);
            Animal.add(Ballom3);
            entities.add(Ballom1);
            entities.add(Ballom2);
            entities.add(Ballom3);
            
            LifesInGame Doll1 = new Doll(5, 5,Sprite.doll_left1.getFxImage());
            LifesInGame Doll2 = new Doll(25, 10,Sprite.doll_left1.getFxImage());
            LifesInGame Doll3 = new Doll(7, 11,Sprite.doll_left1.getFxImage());
            Animal.add(Doll1);
            Animal.add(Doll3);
            Animal.add(Doll2);
            entities.add(Doll3);
            entities.add(Doll1);
            entities.add(Doll2);
            
            LifesInGame Oneal1 = new Oneal(9, 11, Sprite.oneal_left1.getFxImage());
            LifesInGame Oneal2 = new Oneal(25, 8, Sprite.oneal_left1.getFxImage());
            LifesInGame Oneal3 = new Oneal(27, 10, Sprite.oneal_left1.getFxImage());
            LifesInGame Oneal4 = new Oneal(9, 5, Sprite.oneal_left1.getFxImage());
            Animal.add(Oneal1);
            Animal.add(Oneal2);
            Animal.add(Oneal3);
            Animal.add(Oneal4);
            entities.add(Oneal1);
            entities.add(Oneal2);
            entities.add(Oneal3);
            entities.add(Oneal4);
            
            for (int i = 0; i < _height; i++) {
                String mapRow = sc.nextLine();
                for (int j = 0; j < _width; j++) {
                    _map[i][j] = mapRow.charAt(j);
                    switch (_map[i][j]) {
                        case '#':
                            Wall wall = new Wall(j, i, Sprite.wall.getFxImage());
                            stillObjects.add(j + i * _width, wall);
                            entities.add(wall);
                            break;
                        case '*':
                            Brick brick= new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(j + i * _width, brick);
                            entities.add(brick);
                            break;
                        //case 'p' :
                            //System.out.println(i + " " + j);
//   stillObjects.add(j + i * _width, new Bomber(j, i, Sprite.player_right.getFxImage()));
                        //case '1' :
                          //  Animal.add(j + i * _width,new Ballom(j, i, Sprite.balloom_right1.getFxImage()));
                            //stillObjects.add(j + i * _width, new Ballom(j, i, Sprite.balloom_right1.getFxImage()));
                            //System.out.println(i + " " + j);
                          //  break;
                        default:
                            stillObjects.add(j + i * _width, new Grass(j, i, Sprite.grass.getFxImage()));
                    }
                }
            }
        } catch (NullPointerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}