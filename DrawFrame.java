import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class DrawFrame{
    Model model;
    CharaController controller;
    GameView view;
    public DrawFrame(){
        model = new Model();
        controller = new CharaController(model);
        // view = new GameView(model, controller);
        // model.createPlayer(100, 250);
        // this.setBackground(Color.black);
        // this.setSize(500, 500);
        // this.add(view);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        View view = new View();
        view.setVisible(true);
        //view.change(new GameView(model, controller, view));
    }
    public static void main(String argv[]){
        new DrawFrame();
    }
}
