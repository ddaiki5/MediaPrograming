import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Field {
//     private int[][] map= {
//         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
//         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
//         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
//         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
//         {1,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,1,1,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
//         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
//         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
//         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
//         {1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,2,2,2,1,1,2,2,2,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
//         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
//         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
//         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
//         {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,1},
//         {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
// };
    private int[][] map;
    private float cs = 32;//cell_size
    private int ROW, COL;
    public boolean goal = false;
    public int WIDTH,HEIGHT;
    private ImageIcon icon;
    private Image i13,i40,i117,i194;
    private int IMAGESIZE = 18;
    public Field(){
        loadField("map1.csv");
        icon = new ImageIcon(getClass().getResource("map/13.png"));
        i13 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("map/40.png"));
        i40 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("map/194.png"));
        i194 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("map/117.png"));
        i117 = icon.getImage();
    }
    public int getRow(){return ROW;}
    public int getCol(){return COL;}
    public float getCs(){return cs;}
    public void draw(Graphics g, int offsetX, int offsetY){
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++){
                switch(map[i][j]){
                    case 1://block
                        g.setColor(Color.BLACK);
                        g.fillRect(j*(int)cs+offsetX, i*(int)cs+offsetY, (int)cs, (int)cs);
                        break;
                    case 2:
                        g.setColor(Color.ORANGE);
                        g.fillRect(j*(int)cs+offsetX, i*(int)cs+offsetY, (int)cs, (int)cs);
                        break;
                    case 13:
                    g.setColor(Color.ORANGE);
                    g.fillRect(j*(int)cs+offsetX, i*(int)cs+offsetY, (int)cs, (int)cs);
                        g.drawImage(i13, j*(int)cs+offsetX, i*(int)cs+offsetY, j*(int)cs+offsetX+(int)cs, i*(int)cs+offsetY+(int)cs,0, 0, IMAGESIZE,IMAGESIZE, null);
                        break;
                    case 40:
                        g.drawImage(i40, j*(int)cs+offsetX, i*(int)cs+offsetY, j*(int)cs+offsetX+(int)cs, i*(int)cs+offsetY+(int)cs,0, 0, IMAGESIZE,IMAGESIZE, null);
                        break;
                    case 117:
                        g.drawImage(i117, j*(int)cs+offsetX, i*(int)cs+offsetY, j*(int)cs+offsetX+(int)cs, i*(int)cs+offsetY+(int)cs,0, 0, IMAGESIZE,IMAGESIZE, null);
                        break;
                    case 194:
                        g.drawImage(i194, j*(int)cs+offsetX, i*(int)cs+offsetY, j*(int)cs+offsetX+(int)cs, i*(int)cs+offsetY+(int)cs,0, 0, IMAGESIZE,IMAGESIZE, null);
                        break;
                    
                }
            }
        }
    }
    public boolean isHit(int i, int j){
        if(map[i][j]>0){
            return true;
        }else{
            return false;
        }
    }
    public void setNum(int i, int j, int n){
        map[i][j] = n;
    }
    public int getNum(int i, int j){
        return map[i][j];
    }
    public float getBlockX(int i, int j){
        return j*cs;
    }
    public float getBlockY(int i, int j){
        return i*cs;
    }
    public boolean collisionCheck(int i, int j, Character c){
        float bx=j*cs;
        float by=i*cs;
        if(Math.abs(c.getX()-bx)<(cs+c.getWidth())/2f){
            if(Math.abs(c.getY()-by)<(cs+c.getHeight())/2f){
                if(map[i][j]>0){
                    if(map[i][j]==117){
                        goal = true;
                    }
                    return true;
                }
                
            }
        }
        return false;
    }

    public void loadField(String fileName){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("map/"+fileName)));
            String line = br.readLine();
            ROW = Integer.parseInt(line);
            line = br.readLine();
            COL = Integer.parseInt(line);
            System.out.println(ROW+", "+COL);
            map = new int[ROW][COL];
            WIDTH = (int)cs*COL;
            HEIGHT = (int)cs*ROW;
            
            String col[];
            for(int i = 0;i<ROW;i++){
                line = br.readLine();
                col = line.split(",");
                for(int j=0;j<COL;j++){
                    map[i][j] = Integer.parseInt(col[j]);
                }
            }
            // for(int i = 0;i<ROW;i++){
            //     for(int j=0;j<COL;j++){
            //         System.out.print(map[i][j]+",");
            //     }
            //     System.out.println();
            // }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}
