import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/*クリボーみたいな敵(強化版)*/

public class Enemy2 extends Character{
    protected int wayx = -2;
    protected int end = 0, through = 0;
    private ImageIcon icon1, icon2;
    public Enemy2(int x, int y){
        super(x, y, 32, 32, 2, 1);
        gw = gh = 32;
    }
    //アップデート
    public void update(Field field){
        super.update(field);
        if(isCollisionX){
          wayx = -wayx;
          isCollisionX = false;
        }
        moveX(wayx);
    }
    //移動
    public void moveX(int d){
      super.moveX(d);
      vx = d;
    }
    //描画処理
    public void draw(Graphics g, int offsetX, int offsetY){
        icon1 = new ImageIcon(getClass().getResource("pictures/enemy2-1.png"));
        icon2 = new ImageIcon(getClass().getResource("pictures/enemy2-2.png"));
        if(dir == 1){
          image = icon1.getImage();
        }
        else{
          image = icon2.getImage();
        }
        g.drawImage(image, (int)x +offsetX, (int)y+offsetY, gw, gh,null);
    }
}

