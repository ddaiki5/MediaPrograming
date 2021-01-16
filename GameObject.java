import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class GameObject {
    protected int x, y, w, h;
    public GameObject(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(Graphics g){};
}
