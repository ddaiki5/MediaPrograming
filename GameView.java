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
  private int viewWidth, viewHeight;
  private Dimension size;
  public GameView(Model m, CharaController c,SceneManager sceneManager) {
    // model = new Model();
    // c = new CharaController(model);
    model = m;
    this.setBackground(Color.white);
    //this.requestFocusInWindow();
    setFocusable(true);
    this.addMouseListener(c);
    this.addMouseMotionListener(c);
    //this.requestFocus();
    //this.setFocusable(true);
    addKeyListener(c);
    timer = new javax.swing.Timer(10, this);
    timer.start();
    //model = m;
    field = model.field;
    this.sceneManager = sceneManager; 
    model.addObserver(this);
    System.out.println("a");
    size= getSize();
  }
  //描画update処理
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    for(Character f:model.getCharactors()){
      f.draw(g, field.getOffsetX(), field.getOffsetY());
    }
    field.draw(g);
  }
  public void update(Observable o,Object arg){
    repaint();
  }
  //timerのupdate処理
  public void actionPerformed(ActionEvent e){
      size = getSize();
      model.update();
      field.update(size);
      this.repaint();
      if(model.goal || model.gameOver){
        model.goal = false;
        model.gameOver = false;
        timer.stop();
        System.out.println("goal");
        //this.setFocusable(false);
        sceneManager.setSceneNum(0);
        sceneManager.changeScene();
      }
      //System.out.println("t");
  }

  //get,set関数
  public int getViewWidth(){
    return viewWidth;
  }
  public int getViewHeight(){
    return viewHeight;
  }
}

