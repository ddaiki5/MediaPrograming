import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class TitleView1 extends JPanel  implements KeyListener{
    
    
//View view;
    SceneManager sceneManager;
    private SoundManager soundManager;
    //TitleController c;
    TitleView1(SoundManager soundManager, SceneManager sceneManager){
        this.sceneManager = sceneManager;
        this.soundManager = soundManager;
        soundManager.loop("title1");
        //c = new TitleController(this);
        
        Thread thread = new Thread(){
            @Override
            public void run(){
               while(true){
                 try{
                   sleep(30);
                 }catch(InterruptedException e){
                    e.printStackTrace();
                 }
                 if(down){
                   y+=4;
                 }
                 if(up){
                  y-=4;
                 }
                 if(left){
                  x-=4;
                 }
                 if(right){
                  x+=4;
                 }
                 repaint();
                }
            }
          };
          thread.start();

          
        addKeyListener(this);
        //setFocusable(true);
        //this.requestFocusInWindow();
    }
    
    int x;
    int y;
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,500,700);
        Font font = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,70);
        g.setFont(font);
		g.setColor(
            new Color(
        (int)(Math.random() * 256), 
        (int)(Math.random() * 256), 
        (int)(Math.random() * 256))); 
        g.drawString("横スクロール", 30, 100);
        Font font2 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,70);
        g.setFont(font2);
		g.setColor(
            new Color(
                (int)(Math.random() * 256), 
                (int)(Math.random() * 256), 
                (int)(Math.random() * 256))); 
        g.drawString("アクション", 150, 170);
        Font font3 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,20);
        g.setFont(font3);
		g.setColor(
            new Color(
                (int)(Math.random() * 256), 
                (int)(Math.random() * 256), 
                (int)(Math.random() * 256))); 
        g.drawString("SPACEを押してスタート", 150, 350);  
        //timer = new javax.swing.Timer(10, this);
        //g.fillRect(x,y,50,50);  
        Image hart;
        hart = Toolkit.getDefaultToolkit().getImage("pictures/hart.png");  
        
        g.drawImage(hart,x,y,this);
    }
    public void changeScene(){
        //this.setFocusable(false);
        soundManager.play("decide");
        soundManager.stop("title1");
        sceneManager.setSceneNum(3);
        sceneManager.changeScene();
    }
    public void keyTyped(KeyEvent e){
        System.out.println("a");
    }
    boolean up;
    boolean down;
    boolean right;
    boolean left;
    
    public void keyReleased(KeyEvent e){
        System.out.println("a");
      int n = e.getKeyCode();
      switch(e.getKeyCode()){
        case KeyEvent.VK_D:
            break;
        case KeyEvent.VK_A:
            break;
        case KeyEvent.VK_SPACE:
            changeScene();
            System.out.println("space");
            break;
        case 37:
            left = false;
            break;
        case 38:
            up = false;
            break;
        case 39:
            right = false;
            break;
        case 40:
            down = false;
            break;
        }
    }
    public void keyPressed(KeyEvent e){
      switch(e.getKeyCode()){
        case KeyEvent.VK_D:
            break;
        case KeyEvent.VK_A:
            break;
            case 37:
            left = true;
            break;
           case 38:
            up = true;
            break;
           case 39:
            right = true;
            break;
           case 40:
            down = true;
            break;
        }
    }
}


