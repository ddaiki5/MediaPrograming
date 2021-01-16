import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CharaController implements MouseListener,MouseMotionListener,KeyListener {
    protected Model model;
    protected int dragStartX,dragStartY;
    public CharaController(Model a) {
      model = a;
    }
    public void mouseClicked(MouseEvent e) { }
    public void mousePressed(MouseEvent e) {
      //System.out.println("c");
    }
    public void mouseDragged(MouseEvent e) {
      
    }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseMoved(MouseEvent e) { }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e){
      int n = e.getKeyCode();
      switch(e.getKeyCode()){
        case KeyEvent.VK_D:
            model.move(1, false);
            break;
        case KeyEvent.VK_A:
            model.move(-1, false);
            break;
        case KeyEvent.VK_SPACE:
            System.out.println("jump!");
            model.jump();
            break;
        }
    }
    public void keyPressed(KeyEvent e){
      switch(e.getKeyCode()){
        case KeyEvent.VK_D:
            model.move(1, true);
            break;
        case KeyEvent.VK_A:
            model.move(-1, true);
            break;
        }
    }
}
  