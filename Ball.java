import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Ball extends Character{
    Ball(int x, int y){
        super(x, y, 32, 32, 1,99);
        fallcount = 0;
        g = 0.25f;
        gw = 16;
        gh = 16;
    };
    private int fallcount;
    @Override
    public void update(Field field){
        super.update(field);
        if(dir==0){
            vx = 4f;
        }else{
            vx = -4f;
        }
        if(isGround){
            System.out.println(hp);
            vy += -3;
            fallcount+=1;
            if(fallcount>7){
                hp = 0;
            }
        }
        if(isCollisionX){
            hp = 0;
        }
    }

    @Override
    public void draw(Graphics g, int offsetX, int offsetY){
        //System.out.println("ok");
        g.setColor(Color.RED);
        g.fillOval((int)x+offsetX+width/4, (int)y+offsetY+width/4, width/2, height/2);
    }


}