import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Boss extends Character{
    private int moveCounter;
    public Boss(int x,int y){
        super(x, y, 32, 32, 15, 10);
        gw = 96;
        gh = 96; 
        moveCounter = 0;
    }

    @Override
    public void draw(Graphics g, int offsetX, int offsetY){
        g.drawRect((int)x +offsetX, (int)y+offsetY-64, gw, gh);
    }
    @Override
    public void update(Field field){
        super.update(field);
        bossMove();

    }

    private void bossMove(){
        if(moveCounter%1000==999){
            vy += -5;
        }
        moveCounter++;
    }
}
