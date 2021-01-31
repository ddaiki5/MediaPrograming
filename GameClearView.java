import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class GameClearView extends JPanel  implements KeyListener{
    
    
//View view;
    SceneManager sceneManager;
    private Model model;
    //TitleController c;
    GameClearView(SceneManager sceneManager,Model model){
        this.sceneManager = sceneManager;
        this.model = model;
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
                 
                 repaint();
                }
            }
          };
          thread.start();

          
        addKeyListener(this);
        //setFocusable(true);
        //this.requestFocusInWindow();
    }
    
    
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,500,700);
        Font font = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,100);
        g.setFont(font);
		g.setColor(
            new Color(
        (int)(Math.random() * 256), 0, 0)); 
        g.drawString("STAGE", 30, 130);
        Font font2 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,100);
        g.setFont(font2);
		g.setColor(
            new Color(
        (int)(Math.random() * 256), 0, 0)); 
        g.drawString("CLEAR!", 120, 230);
        Font font3 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,50);
        g.setFont(font3);
		g.setColor(Color.BLUE); 
        int score = model.getScore();
        g.drawString("Score:"+score,110,310);
        
        Font font4 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,20);
        g.setFont(font4);
		g.setColor(
            new Color(
                (int)(Math.random() * 256), 
                (int)(Math.random() * 256), 
                (int)(Math.random() * 256))); 
        g.drawString("SPACEを押してタイトルへ", 130, 350);  
        //timer = new javax.swing.Timer(10, this);
        //g.fillRect(x,y,50,50);  
        
    }
    public void changeScene(){
        //this.setFocusable(false);
        sceneManager.setSceneNum(0);
        sceneManager.changeScene();
    }
    public void keyTyped(KeyEvent e){
        System.out.println("a");
    }
    
    
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
        
        }
    }
    public void keyPressed(KeyEvent e){
      switch(e.getKeyCode()){
        case KeyEvent.VK_D:
            break;
        case KeyEvent.VK_A:
            break;
            
        }
    }
}


