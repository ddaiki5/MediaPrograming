import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class TitleView extends JPanel implements KeyListener{
    //View view;
    SceneManager sceneManager;
    //TitleController c;
    TitleView(SceneManager sceneManager){
        this.sceneManager = sceneManager;
        //c = new TitleController(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        //this.requestFocusInWindow();
    }
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0,0,400,300);
		g.setColor(Color.BLACK);
        g.drawString("TITLE", 170, 50);
        //timer = new javax.swing.Timer(10, this);
    }
    public void changeScene(){
        this.setFocusable(false);
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
