import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Player extends Character{
    private static  final int IMAGESIZE = 32;
    private ImageIcon icon;
    public Player(int x, int y){
        super(x, y, 32, 32, 3, 0);
    }
    //modelで呼び出し
    public void jump(){
        System.out.println(isGround);
        if(isGround){
            vy = -5f;
            System.out.println(vy);
            isGround = false;
        }
    }
    //描画処理
    public void draw(Graphics g, int offsetX, int offsetY){
        icon = new ImageIcon(getClass().getResource("pictures/1player.png"));
        image = icon.getImage();
        //g.drawRect((int)x +offsetX, (int)y+offsetY, width, height);
        g.drawImage(image, (int)x +offsetX, (int)y+offsetY, (int)x +offsetX+(int)width+3, (int)y+offsetY+(int)height+3,IMAGESIZE+animationCount*(int)width+dir*IMAGESIZE*2+2, 0, IMAGESIZE+animationCount*(int)width+(int)width+dir*IMAGESIZE*2-2,IMAGESIZE, null);
        count++;
        if(count>=100){
            count = 0;
        }
        if(count<50){
            animationCount = 0;
        }else if(count<100){
            animationCount = 1;
        }

    }
    // public void damaged(){
    //     if(mutekiTime==0){
    //         mutekiTime = 100;
    //         if(hp>0)hp--;
    //     }
        
    // }
    
}

