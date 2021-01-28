import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Coin extends Character{
    private Image image[];
    private ImageIcon icon;
    Coin(int x, int y){
        super(x, y, 32, 32, 1, 6);
        gh = 32;
        gw = 32;
        g = 0;
        image = new Image[9];
        for(int i=0;i<9;i++){
            icon = new ImageIcon(getClass().getResource("pictures/coin"+(i+1)+".png"));
            image[i] = icon.getImage();
        }
        //System.out.println("coin");
    }


    @Override
    public void draw(Graphics g, int offsetX, int offsetY){
        count = (animationCount/10)%9;
        //g.fillRect((int)x +offsetX, (int)y+offsetY, width, height);
        g.drawImage(image[count], (int)x +offsetX, (int)y+offsetY, gw, gh,null);
        animationCount++;
    }


}
