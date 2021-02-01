import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Boss extends Character{
    private int moveCounter, i;
    private ImageIcon icon;
    private Image move0, move1, move2,move3,move4,move5;
    private boolean isFly;
    private float ppx, ppy;
    private Random random;
    public Boss(int x,int y){
        super(x, y, 32, 32, 15, 10);
        gw = 150;
        gh = 120; 
        moveCounter = 0;
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動000.png"));
        move0 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動001.png"));
        move1 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動002.png"));
        move2 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動003.png"));
        move3 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動004.png"));
        move4 = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動005.png"));
        move5 = icon.getImage();
        attackFlag = false;
        setPlayer();
        g = 0.15f;
        i = 0;
        random = new Random();
    }

    @Override
    public void draw(Graphics g, int offsetX, int offsetY){
        if(animationCount<10){
            g.drawImage(move0, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }else if(animationCount<20){
            g.drawImage(move1, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }else if(animationCount<30){
            g.drawImage(move2, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }else if(animationCount<40){
            g.drawImage(move3, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }else if(animationCount<50){
            g.drawImage(move4, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }else if(animationCount<60){
            g.drawImage(move5, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
        }
        if(Math.abs(vx)>=0.5f){
            animationCount++;
        }
        if(animationCount>=60){
            animationCount = 0;
        }
    }
    @Override
    public void update(Field field){
        super.update(field);
        bossMove();
    }

    private void bossMove(){
        fly();
        if(moveCounter==0){
            setPlayer();
            i = random.nextInt(3);
        }
        if(moveCounter<500){
            if(i==0){
                attack();
            }else if(i==1){
                tackle();
            }else if(i==2){
                moveHorizon();
            }
        }
        if(moveCounter==500){
            vx = 0;
            i = random.nextInt(3);
            if(i==0){
                if(!isFly){
                    System.out.println("Bossfly!");
                    jump();
                    //isFly = true;
                }else{isFly = false;}
            }else{
                if(i==1){
                    System.out.println("Bossjump");
                    if(!isFly){
                        jump();
                    }
                    
                }
                isFly = false;
            }
        }
        if(moveCounter==550){
            if(i==0){
                isFly = true;
            }
        }
        moveCounter++;
        if(moveCounter>700){
            moveCounter = 0;
        }
    }
    private void jump(){
        vy -= 8;
    }

    private void fly(){
        if(isFly){
            g = 0f;
            vy = 0;
        }else{
            g = 0.15f;
        }
    }

    private void attack(){
        if(moveCounter%500==100 || moveCounter%500==150 || moveCounter%500==200 ||moveCounter%500==250 ||moveCounter%500==300){
            attackFlag = true;
        }
    }

    private void setPlayer(){
        ppx = playerX;
        ppy = playerY;
    }

    private void tackle(){
        if(ppx>x){
            vx = 2f;
            
        }else{
            vx = -2f;
            
        }
    }

    private void moveHorizon(){
        if(ppx>x){
            vx = 1f;
        }else{
            vx = -1f;
        }
    }

    private void changeDir(){
        if(ppx>x){
            dir = RIGHT;
        }else{
            dir = LEFT;
        }
    }

}
