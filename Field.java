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
    private boolean is83;//使っていない
    public int WIDTH,HEIGHT;
    private ImageIcon icon;
    private Image i13,i40,i117,i194,i195;
    private int IMAGESIZE = 18,bi,bj;//bi、bjはクリア演出用　今は使っていない
    private int offsetX,offsetY, count;//countはクリア演出用　今は使っていない
    private Model model;
    public Field(Model model,int i){
        init(model, i);
    }
    //初期化用
    public void init(Model model, int i){
        if(i==0){
            loadField("map1.csv");
        }else if(i==1){
            loadField("map3.csv");
        }
        icon = new ImageIcon(getClass().getResource("map/13.png"));
        i13 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("map/40.png"));
        i40 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("map/194.png"));
        i194 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("map/117.png"));
        i117 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("map/195.png"));
        i195 = icon.getImage();
        offsetX = 0;
        offsetY = 0;
        is83 = false;
        this.model = model;
        count = 0;
        bi=29;
        bj=37;
        //goal = false;
        //model.createPlayer(100, 250);
        charaSet();
    }
    //viewで毎フレーム呼ばれる
    public void update(Dimension size){
        updateOffset(size);
        //bossFinish();
    }
    //offsetのアップデート
    private void updateOffset(Dimension size){
        offsetX = size.width/2-(int)model.player.getX();
        offsetY = size.height/2-(int)model.player.getY();
        offsetX = Math.min(offsetX, 0);
        offsetX = Math.max(offsetX, size.width - WIDTH);
        offsetY = Math.min(offsetY, 0);
        offsetY = Math.max(offsetY, size.height - HEIGHT);
    }
    //描画処理
    public void draw(Graphics g){
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
                    case 195:
                        g.drawImage(i195, j*(int)cs+offsetX, i*(int)cs+offsetY, j*(int)cs+offsetX+(int)cs, i*(int)cs+offsetY+(int)cs,0, 0, IMAGESIZE,IMAGESIZE, null);
                        break;
                    case 90:
                        g.drawImage(i117, j*(int)cs+offsetX, i*(int)cs+offsetY, j*(int)cs+offsetX+(int)cs, i*(int)cs+offsetY+(int)cs,0, 0, IMAGESIZE,IMAGESIZE, null);
                        break;
                    
                }
            }
        }
    }
    //mapとキャラのあたり判定用
    public boolean collisionCheck(int i, int j, Character c){
        float bx=j*cs;
        float by=i*cs;
        if(Math.abs(c.getX()-bx)<(cs+c.getWidth())/2f){
            if(Math.abs(c.getY()-by)<(cs+c.getHeight())/2f){
                if(map[i][j]>0){
                    if(map[i][j]==117){
                        if(c.getCharacterNum()==0){
                            model.goal = true;
                            model.soundPlay("enter");
                        }
                    }
                    if(map[i][j]==90){//使っていない
                        if(c.getCharacterNum()==0){
                            model.bossFlag = true;
                            return false;
                        }
                    }
                    
                    return true;
                }
                
            }
        }

        return false;
    }
    //csvファイル読み込み
    private void loadField(String fileName){
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
    //mapを元にキャラを設置
    private void charaSet(){
        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++){
                if(map[i][j]==0){
                    model.createPlayer((int)getBlockX(i, j), (int)getBlockY(i, j));
                }else if(map[i][j]==-2){
                    model.setCharacter(new Enemy1((int)getBlockX(i, j), (int)getBlockY(i, j)));
                }else if(map[i][j]==-9){
                    model.setCharacter(new Boss((int)getBlockX(i, j), (int)getBlockY(i, j)));
                }else if(map[i][j]==-3){
                    model.setCharacter(new Enemy3((int)getBlockX(i, j), (int)getBlockY(i, j)));
                }else if(map[i][j]==-4){
                    model.setCharacter(new Enemy4((int)getBlockX(i, j), (int)getBlockY(i, j)));
                }else if(map[i][j]==-6){
                    model.setCharacter(new Coin((int)getBlockX(i, j), (int)getBlockY(i, j)));
                }
            }
        }
    }

    private void bossFinish(){//使っていない
        if(model.bossFlag){
            count++;
            if(count%10==0){
                map[bi][bj] = -1;
                model.soundPlay("block");
                bj--;
            }
            
        }
        if(count>300){
            model.stageClear = true;
        }
    }
    
    //以下get,set関数
    public int getRow(){return ROW;}
    public int getCol(){return COL;}
    public float getCs(){return cs;}
    public int getOffsetX(){
        return offsetX;
    }
    public int getOffsetY(){
        return offsetY;
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
    // public boolean isHit(int i, int j){
    //     if(map[i][j]>0){
    //         return true;
    //     }else{
    //         return false;
    //     }
    // }
    
}

