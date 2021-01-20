import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/*クリボーみたいな敵*/

/*Model.java内に 以下の追加で動作   
　protected Enemy1 enemy1;

  public void createEnemy1(int x, int y){
    enemy1 = new Enemy1(x, y);
    chara.add(enemy1);
    //setChanged();
    //notifyObservers();
  }

  ・public void update(){の中に以下を追加
    enemy1.update(enemy1);

  ///////////
  DrawFrame.javaのpublic DrawFrame(){　の中に以下を追加
    model.createEnemy2(300, 210);


*/



public class Enemy1 extends Character{
    protected int wayx = -1;
    protected int end = 0, through = 0;
    public Enemy1(int x, int y){
        super(x, y, 32, 32, 1, 1);
        gw = gh = 32;
    }

    public void update(Field field){
        super.update(field);
        if(isCollisionX){
          wayx = -wayx;
          isCollisionX = false;
        }
        //e.moveX(wayx);

    //    this.hp = this.hp - 1;

        
        if(this.hp <= 0 && this.end == 0){
            //System.out.println("shinda!");
            this.through = 1;
            this.x = -10;
            this.y = -10;

        }
    }

    public void draw(Graphics g, int offsetX, int offsetY){
        //System.out.println((int)x +offsetX);

        //追加
        if(this.end != 1){
          if(this.through == 1){this.end = 1;}
          g.drawRect((int)x +offsetX, (int)y+offsetY, width, height);
        }
    }
}

