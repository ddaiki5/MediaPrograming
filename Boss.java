import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Boss extends Character{
    private int moveCounter, i, j;
    private ImageIcon icon;
    private Image move0, move1, move2,move3,move4,move5,move0r, move1r, move2r,move3r,move4r,move5r;
    private boolean isFly;
    private float ppx, ppy;
    private Random random;
    public Boss(int x,int y){
        super(x, y, 32, 32, 25, 10);
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
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動000r.png"));
        move0r = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動001r.png"));
        move1r = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動002r.png"));
        move2r = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動003r.png"));
        move3r = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動004r.png"));
        move4r = icon.getImage();
        icon = new ImageIcon(getClass().getResource("pictures/ドラゴンA_移動005r.png"));
        move5r = icon.getImage();
        attackFlag = false;
        setPlayer();
        g = 0.15f;
        i = 0;
        j =0 ;
        random = new Random();
    }

    @Override
    public void draw(Graphics g, int offsetX, int offsetY){
        if(dir==LEFT){
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
        }else if(dir==RIGHT){
            if(animationCount<10){
                g.drawImage(move0r, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
            }else if(animationCount<20){
                g.drawImage(move1r, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
            }else if(animationCount<30){
                g.drawImage(move2r, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
            }else if(animationCount<40){
                g.drawImage(move3r, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
            }else if(animationCount<50){
                g.drawImage(move4r, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
            }else if(animationCount<60){
                g.drawImage(move5r, (int)x +offsetX-60, (int)y+offsetY-85, gw+34, gh,null);
            }
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
            changeDir();
            i = random.nextInt(5);
            if(i==4){
                i=0;
            }else if(i==5){
                i = 1;
            }
        }
        if(moveCounter==0){
            //System.out.println(vx+" "+ vy);
            if(i==0){
                System.out.println("attack!");
            }else if(i==1){
                tackle();
            }else if(i==2){
                moveHorizon();
            }
            j = random.nextInt(200);
        }
        if(moveCounter<j+400){
            if(i==0){
                attack();
            }
        }
        if(moveCounter==300){
            if(isCollisionX){
                moveCounter=0;
            }
        }
        if(moveCounter==j+400){
            changeDir();
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
        if(moveCounter>j+400+100){
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
            soundManager.stop("fire");
            soundManager.play("fire");
        }
    }

    private void setPlayer(){
        ppx = playerX;
        ppy = playerY;
    }

    private void tackle(){
        if(ppx>x){
            vx = 2.5f;
            
        }else{
            vx = -2.5f;
            
        }
    }

    private void moveHorizon(){
        if(ppx>x){
            vx = 1.5f;
        }else{
            vx = -1.5f;
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
