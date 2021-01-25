import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class BossFire extends Ball{
    private int count;
    BossFire(int x, int y){
        super(x, y);
        characterNum = 98;
        gw = 24;
        gh = 24;
        count = 0;
    }
    @Override
    public void update(Field field){
        super.update(field);
        vx = -2;
        if(isCollisionX || count>=800){
            hp = 0;
        }
        count++;
    }
    
    @Override
    public void fall(){}
    @Override
    public void draw(Graphics g, int offsetX, int offsetY){
        //System.out.println("ok");
        g.setColor(Color.RED);
        g.fillOval((int)x+offsetX+width/4, (int)y+offsetY, gw, gh);
    }
}
