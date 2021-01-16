import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class GameView extends JPanel implements Observer, ActionListener{
  protected Model model;
  protected CharaController c;
  protected Field field;
  private javax.swing.Timer timer;
  private SceneManager sceneManager;
  public GameView(Model m, CharaController c,SceneManager sceneManager) {
    // model = new Model();
    // c = new CharaController(model);
    model = m;
    this.setBackground(Color.white);
    //this.requestFocusInWindow();
    this.setFocusable(true);
    this.addMouseListener(c);
    this.addMouseMotionListener(c);
    //this.requestFocus();
    //this.setFocusable(true);
    this.addKeyListener(c);
    timer = new javax.swing.Timer(10, this);
    timer.start();
    //model = m;
    field = model.field;
    this.sceneManager = sceneManager; 
    model.createPlayer(100, 250);
    model.addObserver(this);
    System.out.println("a");
    
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Dimension size= getSize();
    int offsetX = size.width/2-(int)model.player.getX();
    int offsetY = size.height/2-(int)model.player.getY();
    //System.out.println(String.valueOf(offsetX)+" "+String.valueOf(model.player.getX()));
    //int offsetY = size.height/2-(int)model.player.getY();
    offsetX = Math.min(offsetX, 0);
    offsetX = Math.max(offsetX, size.width - field.WIDTH);
    offsetY = Math.min(offsetY, 0);
    offsetY = Math.max(offsetY, size.height - field.HEIGHT);
    //offsetX = Math.max(offsetX, size.width);
    //model.player.draw(g, offsetX, 0);
    
    for(Character f:model.getCharactors()){
      f.draw(g, offsetX, offsetY);
    }
    field.draw(g, offsetX, offsetY);
  }
  public void update(Observable o,Object arg){
    repaint();
  }
  public void actionPerformed(ActionEvent e){
      this.repaint();
      model.update();
      if(field.goal){
        System.out.println("goal");
        this.setFocusable(false);
        sceneManager.setSceneNum(0);
        sceneManager.changeScene();
      }
      //System.out.println("t");
  }
}

