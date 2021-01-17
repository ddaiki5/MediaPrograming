import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TitleView1 extends JPanel  implements KeyListener{
    
    
//View view;
    SceneManager sceneManager;
    //TitleController c;
    TitleView1(SceneManager sceneManager){
        this.sceneManager = sceneManager;
        //c = new TitleController(this);
        addKeyListener(this);
        //setFocusable(true);
        //this.requestFocusInWindow();
    }
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,500,700);
        Font font = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,70);
        g.setFont(font);
		g.setColor(Color.BLUE); 
        g.drawString("横スクロール", 30, 100);
        Font font2 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,70);
        g.setFont(font2);
		g.setColor(Color.BLUE);
        g.drawString("アクション", 150, 170);
        Font font3 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,20);
        g.setFont(font3);
		g.setColor(Color.WHITE);
        g.drawString("SPACEを押してスタート", 150, 350);  
        //timer = new javax.swing.Timer(10, this);
    }
    public void changeScene(){
        //this.setFocusable(false);
        sceneManager.setSceneNum(1);
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


