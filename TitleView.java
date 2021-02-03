import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Font;
public class TitleView extends JPanel implements KeyListener{
    //View view;
    SceneManager sceneManager;
    private SoundManager soundManager;
    //TitleController c;
    TitleView(SoundManager soundManager,SceneManager sceneManager){
        this.sceneManager = sceneManager;
        this.soundManager = soundManager;
        //c = new TitleController(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        //this.requestFocusInWindow();
    }
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,500,700);
        Font font = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,30);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("Dキーで進む", 50, 100);
        Font font2 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,30);
        g.setFont(font2);
        g.setColor(Color.WHITE);
        g.drawString("Aキーで戻る", 50, 130);
        Font font3 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,30);
        g.setFont(font3);
        g.setColor(Color.WHITE);
        g.drawString("Sキーで戻る", 50, 160);
        Font font4 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,30);
        g.setFont(font4);
        g.setColor(Color.WHITE);
        g.drawString("SPACEでジャンプ・攻撃", 50, 190);
        Font font5 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,30);
        g.setFont(font5);
        g.setColor(Color.WHITE);
        g.drawString("敵を倒してゴールを目指せ！", 50, 220);
        Font font6 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,30);
        g.setFont(font6);
        g.setColor(Color.WHITE);
        g.drawString("SPACEで次へ", 150, 350);
        
        //timer = new javax.swing.Timer(10, this);
    }
    public void changeScene(){
        //this.setFocusable(false);
        soundManager.stop("title");
        soundManager.play("decide");
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
class TitleController implements KeyListener{
    TitleView v;
    TitleController(TitleView v){
        this.v = v;
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){
      int n = e.getKeyCode();
      switch(e.getKeyCode()){
        case KeyEvent.VK_D:
            break;
        case KeyEvent.VK_A:
            break;
        case KeyEvent.VK_SPACE:

            v.changeScene();
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
