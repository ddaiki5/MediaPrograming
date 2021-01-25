import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Boss extends Character{
    private int moveCounter;
    private ImageIcon icon;
    private Image move0, move1, move2,move3,move4,move5;
    public Boss(int x,int y){
        super(x, y, 32, 32, 15, 10);
        gw = 150;
        gh = 120; 
        moveCounter = 0;
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動000.png"));
        move0 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動001.png"));
        move1 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動002.png"));
        move2 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動003.png"));
        move3 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動004.png"));
        move4 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動005.png"));
        move5 = icon.getImage();
        attackFlag = false;
    }

    @Override
    public void draw(Graphics g, int offsetX, int offsetY){
        if(animationCount<10){
            g.drawImage(move0, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }else if(animationCount<20){
            g.drawImage(move1, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }else if(animationCount<30){
            g.drawImage(move2, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }else if(animationCount<40){
            g.drawImage(move3, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }else if(animationCount<50){
            g.drawImage(move4, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }else if(animationCount<60){
            g.drawImage(move5, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }
        if(Math.abs(vx)>=0.5f){
            animationCount++;
        }
        if(animationCount>=60){
            animationCount = 0;
        }
    }
    @Override
    public void update(Field field){
        super.update(field);
        bossMove();
    }

    private void bossMove(){
        if(moveCounter%800==799){
            vy += -7;
        }
        if(moveCounter%2000<500){
            vx = -1;
        }else if(moveCounter%2000>=1000&&moveCounter<1500){
            vx = 1;
        }else{
            vx = 0;
        }
        if(moveCounter%1000==600 || moveCounter%1000==650 || moveCounter%1000==700 ||moveCounter%1000==700 ||moveCounter%1000==750){
            attackFlag = true;
        }
        moveCounter++;
        if(moveCounter>2000){
            moveCounter = 0;
        }
    }

}
