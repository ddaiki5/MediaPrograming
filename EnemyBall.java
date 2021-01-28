import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/*Ballクラスと異なるのは、軌道とキャラナンバーのみ*/ 


public class EnemyBall extends Ball{
    EnemyBall(int x, int y){
        super(x, y);
        changeCharaNum();
        fallcount = 0;
        this.dir = 1;
        this.gw = 16;
        this.gh = 16;
    };

    public void changeCharaNum(){
        this.characterNum = 97;
    }

    private int fallcount;
    @Override
    public void update(Field field){
        super.update(field);
        if(dir==0){
            vx = 2f;
        }else{
            vx = -2f;
        }
        if(isGround){
            System.out.println(hp);
            vy += -2;
            fallcount+=1;
            if(fallcount>5){
                hp = 0;
            }
        }
    }

    public void EBdir(int i){
        this.dir = i;
    }


    @Override
    public void draw(Graphics g, int offsetX, int offsetY){
        super.draw(g, offsetX, offsetY);
        /*System.out.println("ok");
        g.setColor(Color.blue);
        g.fillOval((int)x+offsetX+width/4, (int)y+offsetY+width/4, width/2, height/2);*/
    }
}