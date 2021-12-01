package uet.oop.bomberman.entities.lifesingame;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import java.util.Random;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.BombermanGame.Animal;
import static uet.oop.bomberman.BombermanGame.listKill;
import static uet.oop.bomberman.control.Move.*;
import static uet.oop.bomberman.BombermanGame.player;
import static uet.oop.bomberman.BombermanGame.entities;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Oneal extends LifesInGame{
    private static int swapKill = 1;
    private static int countKill = 0;
    
    public Oneal(int isMove, int swap, String direction, int count, int countToRun) {
        super(4, 1, "up", 0, 0);
    }

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }
    
    private void killBallom(LifesInGame animal) {
        if (countKill % 16 == 0) {
            if (swapKill == 1) {
                animal.setImg(Sprite.mob_dead1.getFxImage());
                
                swapKill = 2;
            } else if (swapKill == 2) {
                animal.setImg(Sprite.mob_dead2.getFxImage());
                swapKill = 3;
            } else if (swapKill == 3) {
                animal.setImg(Sprite.mob_dead3.getFxImage());
                swapKill = 4;
            } else {
                animal.setLife(false);
                Animal.remove(animal);
                swapKill = 1;
            }
        }
    }

    private void kill() {
        for (LifesInGame animal : Animal) {
            if (listKill[animal.getX() / 32][animal.getY() / 32] == 4) {
                animal.setLife(false);
            }
        }
    }
    
    

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Ballom) {
            return true;
        }
        return e.collide(this);
    }
    
    @Override
    public void update() {
    /*    kill();
        countKill++;
        for (LifesInGame animal : Animal) {
            if (animal instanceof Ballom && !animal.life)
                killBallom(animal);
        }

    */    
        if(this.x == player.getX() 
                && this.y/SCALED_SIZE - player.getY()/SCALED_SIZE <= 2
                && this.y/SCALED_SIZE - player.getY()/SCALED_SIZE >= 0) {
            
            boolean a = true;
            for(int i = player.getY() + 32; i < this.y; i+=32) {
                if(this.getAt(this.x, i) != null) {
                    a = false;
                    break;
                }                
            }
            if(a) Up(this);
            
            
        } else if(this.x == player.getX() 
                && player.getY()/SCALED_SIZE - this.y/SCALED_SIZE <= 2
                && player.getY()/SCALED_SIZE - this.y/SCALED_SIZE >= 0) {
            
            boolean a = true;
            for(int i = this.y + 32;i <  player.getY(); i+= 32) {
                if(this.getAt(this.x, i) != null) {
                    a = false;
                    break;
                }                
            }
            if(a) Down(this);
            
        } else if(this.y == player.getY() 
                && player.getX()/SCALED_SIZE - this.x/SCALED_SIZE <= 2 
                && player.getX()/SCALED_SIZE - this.x/SCALED_SIZE >=0) {
            
            boolean a = true;
            for(int i = this.x+ 32;i <  player.getX(); i+=32) {
                if(this.getAt(i, this.y) != null) {
                    a = false;
                    break;
                }                
            }
            if(a) Right(this);
        } else if(this.y == player.getY() 
                && this.x/SCALED_SIZE - player.getX()/SCALED_SIZE <= 2
                && this.x/SCALED_SIZE - player.getX()/SCALED_SIZE >= 0) {
            
            boolean a = true;
            for(int i = player.getX() + 32;i <  this.x; i+=32) {
                if(this.getAt(i, this.y) != null) {
                    a = false;
                    break;
                }                
            }
            if(a) Left(this);
        } else {
            Random random = new Random();
            int dir = random.nextInt(4);
            
            switch (dir) {
                case 0:
                    Down(this);
                    break;
                case 1:
                    Up(this);
                    break;
                case 2:
                    Left(this);
                    break;
                case 3:
                    Right(this);
                    break;
            }
        } 
    }
}
