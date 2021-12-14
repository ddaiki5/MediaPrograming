import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/*はねるパタパタみたいな敵*/

public class Enemy3 extends Character{
    protected int wayx = -1;
    protected int end = 0, through = 0, jumpcount = 0;
    private ImageIcon icon1, icon2;
    public Enemy3(int x, int y){
        super(x, y, 32, 32, 1, 1);
        gw = gh = 32;
    }
    //ジャンプ機能
    public void jum(){
        //System.out.println(isGround);
        if(isGround){
            if(jumpcount < 2){
              vy = -2f;
              isGround = false;
              jumpcount += 1;
            }
            else{
              vy = -3f;
              isGround = false;
              jumpcount = 0;
            }
        }
    }
    //アップデート
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
    }
    //移動
    public void moveX(int d){
      super.moveX(d);
      vx = d;
    }
    //描画処理
    public void draw(Graphics g, int offsetX, int offsetY){
      icon1 = new ImageIcon(getClass().getResource("pictures/enemy3-1.png"));
      icon2 = new ImageIcon(getClass().getResource("pictures/enemy3-2.png"));
      if(dir == 1){
        image = icon1.getImage();
      }
      else{
        image = icon2.getImage();
      }
      g.drawImage(image, (int)x +offsetX, (int)y+offsetY-10, gw+10, gh+10,null);
    }
}
