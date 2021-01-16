
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class View extends JFrame{
    Model model;
    CharaController controller;
    GameView view;
    public View(){
        super("test");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(false);
    }
    public void change(JPanel panel){
        getContentPane().removeAll();
        super.add(panel);
        validate();
        repaint();
    }
}


