import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class SceneManager {
    private int sceneNum;
    private Scene scene;
    private Model model;
    private CharaController c;
    private View view;
    SceneManager(){
        model = new Model(0);
        c = new CharaController(model);
        sceneNum = 0;
        view = new View();
        view.setVisible(true);
    }
    public void setSceneNum(int n){
        sceneNum = n;
    }
    public void changeScene(){
        switch(sceneNum){
            case 0:
                view.change(new TitleView1(this));
                break;
            case 1:
                model.init(0);
                view.change(new GameView(model, c, this));
                break;
            case 2:
                model.init(1);
                view.change(new GameView(model, c, this));
                break;
        }
    }
}
