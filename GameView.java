import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Font;
import java.awt.Image;
public class GameView extends JPanel implements Observer, ActionListener{
  protected Model model;
  protected CharaController c;
  protected Field field;
  private javax.swing.Timer timer;
  private SceneManager sceneManager;
  private int viewWidth, viewHeight;//使っていない
  private Dimension size;
  private SoundManager soundManager;
  public GameView(Model m,SoundManager soundManager,SceneManager sceneManager) {
    // model = new Model();
    // c = new CharaController(model);
    model = m;
    this.setBackground(Color.DARK_GRAY);
    this.soundManager = soundManager;
    //this.requestFocusInWindow();
    setFocusable(true);
    //this.requestFocus();
    //this.setFocusable(true);
    this.c = new CharaController(model);
    addKeyListener(c);
    timer = new javax.swing.Timer(10, this);
    timer.start();
    //model = m;
    field = model.field;
    this.sceneManager = sceneManager; 
    model.addObserver(this);
    //System.out.println("a");
    size= getSize();
    if(model.getStageNum()==0){
      soundManager.stop("field2");
      soundManager.loop("field2");
    }else if(model.getStageNum()==1){
      soundManager.stop("boss");
      soundManager.play("boss");
    }
  }
  //描画update処理
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    //スコア表示
    Font font = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,20);
        g.setFont(font);
        g.setColor(Color.WHITE);
    int score = model.getScore();
    g.drawString("Score:"+score,30,70);

    //HP表示

    Font font2 = new Font("HGP創英角ﾎﾟｯﾌﾟ体",Font.ITALIC,20);
        g.setFont(font2);
        g.setColor(Color.RED);
    int hp = model.getPlayerHp();
    Image hart;
        hart = Toolkit.getDefaultToolkit().getImage("pictures/hart.png");  
        
        g.drawImage(hart,30,0,this);
    g.drawString(":"+hp,80,30);




    field.draw(g);
    for(Character f:model.getCharactors()){
      f.draw(g, field.getOffsetX(), field.getOffsetY());
    }
  }
  public void update(Observable o,Object arg){//使っていない
    repaint();
  }
  //timerのupdate処理
  public void actionPerformed(ActionEvent e){
      size = getSize();
      model.update();
      field.update(size);
      this.repaint();
      if(model.gameOver){
        soundManager.stop("field2");
        soundManager.stop("boss");
        timer.stop();
        System.out.println("goal");
        //this.setFocusable(false);
        sceneManager.setSceneNum(4);
        sceneManager.changeScene();
      }else if(model.goal){
        soundManager.stop("field2");
        soundManager.stop("boss");
        timer.stop();
        System.out.println("goal");
        //this.setFocusable(false);
        if(sceneManager.getSceneNum()==1){
          sceneManager.setSceneNum(2);
        }else if(sceneManager.getSceneNum()==2){
          soundManager.stop("field2");
        soundManager.stop("boss");
          sceneManager.setSceneNum(4);
        }
        
        sceneManager.changeScene();
      }else if(model.stageClear){
        soundManager.stop("field2");
        soundManager.stop("boss");
        timer.stop();
        sceneManager.setSceneNum(5);
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

