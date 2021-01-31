import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/*弾飛ばす敵*/
//CharacterNumは2
public class Enemy4 extends Character{
    protected int end = 0, through = 0;
    private ImageIcon icon1, icon2;
    protected int cooltime = 0;    //攻撃までのクールタイム(この敵のジャンプもクールタイムで管理)
    public Enemy4(int x, int y){
        super(x, y, 32, 32, 2, 2);
        gw = gh = 32;
        this.dir = 1;
        this.attackFlag = false;
    }
    public void jum(){
        //System.out.println(isGround);
        if(isGround){
            vy = -4f;
            //System.out.println(vy);
            isGround = false;
        }
    }
    public void update(Field field){
        super.update(field);
        if(isGround == true && cooltime == 300){
          this.jum();
        }

        if(this.cooltime == 350){
          this.cooltime = 0;
          this.attackFlag = true;
        }

        this.cooltime += 1;

        if(this.hp <= 0 && this.end == 0){
            this.through = 1;
            this.x = -100;
            this.y = -100;
        }
    }
    public void dir(int d){ //dirを変える
        if(this.dir != d)
          this.dir = d;
    }
    //描画処理
    public void draw(Graphics g, int offsetX, int offsetY){
        //System.out.println((int)x +offsetX);
        icon1 = new ImageIcon(getClass().getResource("pictures/enemy4-1.png"));
        icon2 = new ImageIcon(getClass().getResource("pictures/enemy4-2.png"));
        if(dir == 1){
          image = icon1.getImage();
        }
        else{
          image = icon2.getImage();
        }
        g.drawImage(image, (int)x +offsetX, (int)y+offsetY-10, gw+10, gh+10,null);
    }
}
