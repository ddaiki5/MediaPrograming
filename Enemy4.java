import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/*弾飛ばす敵*/

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

//CharacterNumは2
public class Enemy4 extends Character{
    protected int wayx = 0;
    protected int end = 0, through = 0;
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
        if(isCollisionX){
          wayx = -wayx;
          isCollisionX = false;
        }
        moveX(wayx);

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

    /*
    public void enemyshoot(Character chara){
        EnemyBall eball;
        if(this.dir==0){
            eball = new EnemyBall((int)this.getX()+this.getWidth()+30, (int)this.getY()+this.getHeight()/2);
            eball.dir = 0;
        }else{
            eball = new EnemyBall((int)this.getX()-30, (int)this.getY()+this.getHeight()/2);
            eball.vx = -1.5f;
            eball.dir = 1;
        }
        chara.add(eball);
    }*/





    /*
    public int get_cooltime(){ //クールタイムを返す
        return this.cooltime;
    }


    public void EBdir(int d){ //dirを変える
        if(this.dir != d)
          this.dir = d;
    }

    public void resetcooltime(){ //クールタイムを0にする
        this.cooltime = 0;
    }
    */



    public void draw(Graphics g, int offsetX, int offsetY){
        //System.out.println((int)x +offsetX);
        if(this.end != 1){
            if(this.through == 1){this.end = 1;}
            g.setColor(Color.ORANGE);
            g.fillRect((int)x +offsetX, (int)y+offsetY, width, height);
        }
    }
}
