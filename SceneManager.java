import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class SceneManager {
    private int sceneNum;
    //private Scene scene;
    private Model model;
    //private CharaController c;
    private MainFrame view;
    private SoundManager soundManager;
    SceneManager(){
        soundManager = new SoundManager();
        model = new Model(0, soundManager);
        //c = new CharaController(model);
        sceneNum = 0;
        view = new MainFrame();
        view.setVisible(true);
        
    }
    public void setSceneNum(int n){
        sceneNum = n;
    }
    public int getSceneNum(){
        return sceneNum;
    }
    public void changeScene(){
        switch(sceneNum){
            case 0:
                view.change(new TitleView1(soundManager,this));
                break;
            case 1:
                model.init(0, soundManager);
                view.change(new GameView(model, soundManager,this));
                break;
            case 2:
                model.init(1, soundManager);
                view.change(new GameView(model, soundManager,this));
                break;
            case 3:
                view.change(new TitleView(soundManager,this));
                break;
            case 4:
                view.change(new GameOverView(soundManager,this, model));
                break;
            case 5:
                view.change(new GameClearView(soundManager,this, model));
        }
    }
}
