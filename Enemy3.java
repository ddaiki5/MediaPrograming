import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/*はねるパタパタみたいな敵*/

//↓なかったことに。
/*Model.java内に 以下の追加で動作   
　protected Enemy3 enemy3;

  public void createEnemy3(int x, int y){
    enemy3 = new Enemy3(x, y);
    chara.add(enemy3);
    //setChanged();
    //notifyObservers();
  }

  ・public void update(){の中に以下を追加
    enemy3.update(enemy3);

  ///////////
  DrawFrame.javaのpublic DrawFrame(){　の中に以下を追加
    model.createEnemy3(750, 300);
*/


public class Enemy3 extends Character{
    protected int wayx = -1;
    protected int end = 0, through = 0;
    public Enemy3(int x, int y){
        super(x, y, 32, 32, 1, 1);
        gw = gh = 32;
    }

    public void jum(){
        //System.out.println(isGround);
        if(isGround){
            vy = -2f;
            //System.out.println(vy);
            isGround = false;
        }
    }

    public void update(Field field){
        super.update(field);
        if(isCollisionX){
          wayx = -wayx;
          isCollisionX = false;
        }
        moveX(wayx);

        if(isGround == true){
          this.jum();
        }

        if(this.hp <= 0 && this.end == 0){
            this.through = 1;
            this.x = -10;
            this.y = -10;
        }
    }

    public void draw(Graphics g, int offsetX, int offsetY){
        //System.out.println((int)x +offsetX);
        if(this.end != 1){
            if(this.through == 1){this.end = 1;}
            g.setColor(Color.RED);
            g.fillRect((int)x +offsetX, (int)y+offsetY, width, height);
        }
    }
}
