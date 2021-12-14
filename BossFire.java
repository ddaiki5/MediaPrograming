import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class BossFire extends Character{
    private int count;
    private ImageIcon icon;
    private Image image;
    BossFire(int x, int y, float vx, float vy){
        super(x, y, 32, 32,3,98);
        characterNum = 98;
        this.vx = vx*2;
        this.vy = vy*2;
        gw = 24;
        gh = 24;
        count = 0;
        g=0;
        icon = new ImageIcon(getClass().getResource("pictures/bossfire.png"));
        image = icon.getImage();
    }
    @Override
    public void update(Field field){
        super.update(field);
        if(isCollisionX || count>=800 || isCollisionY){
            hp = 0;
        }
        count++;
    }
    
    @Override
    public void draw(Graphics g, int offsetX, int offsetY){
        //System.out.println("ok");
        //g.setColor(Color.RED);
        //g.fillOval((int)x+offsetX+width/4, (int)y+offsetY, gw, gh);
        g.drawImage(image, (int)x +offsetX, (int)y+offsetY, (int)x +offsetX+(int)width, (int)y+offsetY+(int)height,0, 0, 1500,1500, null);
    }
}
