
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class MainFrame extends JFrame{
    public MainFrame(){
        super("test");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
        setResizable(false);
        //pack();
    }
    
    public void change(JPanel panel){
        getContentPane().removeAll();
        super.add(panel);
        validate();
        repaint();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    public static void main(String argv[]){
        SceneManager sceneManager = new SceneManager();
        sceneManager.changeScene();
    }
}


