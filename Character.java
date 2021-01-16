import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Character {
    protected int width, height, hp, LimitY, mutekiTime,characterNum;//characterNum:種類判別用のナンバー
    protected float x, y, vx, vy, g;
    protected Image image;
    protected boolean isGround, isCollisionX, isCollisionY, isDamaged;
    //animation用の向き
    protected static final int RIGHT = 0;
    protected static final int LEFT = 1;
    protected int dir;
    //animationCount,count:アニメーション用,damageCount:damage時の一定時間無敵用
    protected int animationCount, count, damageCount;
    //一フレーム前の座標保存用
    public float pX, pY;
    
    public Character(int x, int y, int w, int h, int hp, int characterNum){
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        this.hp = hp;
        vx = 0;
        vy = 0;
        g = 0.08f;
        isCollisionX = false;//使ってない
        isCollisionY = false;//使ってない
        isGround = false;
        animationCount = 0;
        count = 0;
        isDamaged = false;
        damageCount = 0;
        this.characterNum = characterNum;
    }
    
    
    //modelで毎フレーム呼び出し
    public void update(Field field){
        fall();
        move(field);
        if(damageCount>0){
            damageCount--;
        }
    }
    //fieldとのあたり判定を考慮した移動
    public void move(Field field){
        //System.out.println(vx);
        collisionX(field);
        collisionY(field);
        //System.out.println(y);
        //System.out.println(vy);
    }
    //キーボードの入力に対する速度と向き
    public void moveX(int d){
        if(d>0){
            dir = RIGHT;
        }else if(d<0){
            dir = LEFT;
        }
        vx = d*2;
    }

    // public void fall(){
    //     if(isGround!=true){
    //         vy+=g;
    //         y+=vy;
    //     }else{
    //         //vy=0;
    //     }
    // }
    public void fall(){//重力
        // if(!isGround){
        //     vy+=g;
        // }
        vy+=g;
    }

    //ダメージを受けたときに呼び出し
    public void damaged(int i){
        if(damageCount==0){
            hp -= i;
            damageCount = 100;
            System.out.println("damage!"+hp);
        }
    }
    //描画処理
    public void draw(Graphics g, int offsetX, int offsetY){};
    //xのあたり判定
    public void collisionX(Field field){
        float newx = x + vx;
        for(int i=0;i<field.getRow();i++){
            for(int j=0;j<field.getCol();j++){
                if(field.collisionCheck(i, j, this)){      
                    if(vx>0){
                        isCollisionX = true;
                        x = field.getBlockX(i, j)-width;
                        vx = 0;
                    }else if(vx<0){
                        isCollisionX = true;
                        x = field.getBlockX(i, j)+field.getCs();
                        vx = 0;
                    }
                    return;  
                }else{
                    x = newx;
                    isCollisionX = false;
                }
            }
        }
        //x = newx;
    }
    
    //protected void loadImage(){}
    //yのあたり判定
    public void collisionY(Field field){
        float newy = y + vy;
        //boolean f=false;
        for(int i=0;i<field.getRow();i++){
            for(int j=0;j<field.getCol();j++){
                if(field.collisionCheck(i, j, this)){
                    //if(field.getNum(i, j)>0){
                        if(vy>0){
                            y = field.getBlockY(i, j)-height;
                            vy = 0;
                            isGround = true;
                            //System.out.println("true");
                        }else if(vy<0){
                            y = field.getBlockY(i, j)+field.getCs();
                            vy = 0;
                        }
                        //f=true;
                        return;
                    //}else{
                        //y = newy;
                        //isGround = false;
                    //}
                }else{
                    y = newy;
                    isGround = false;
                }
            }
        }
        //y = newy;
        //isGround = false;
    }
    //以下getset関数など
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public boolean getIsDamaged(){
        return isDamaged;
    }
    public int getHp(){
        return hp;
    }
    public float getVx(){
        return vx;
    }
    public float getVy(){
        return vy;
    }
    public void setVy(float f){
        vy = f;
    }
    public int getCharacterNum(){
        return characterNum;
    }

    //今使ってないやつ
    public int getDamageCount(){
        return damageCount;
    }
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void cheackIsGround(int limitY){
        LimitY = limitY;
        if(y>=limitY){
            isGround = true;
        }else{
            isGround = false;
        }
    }
    public void checkIsGround(boolean f){
        isGround = f;
    }
    public void checkIsCollisionX(boolean f){
        isCollisionX = f;
        if(f){
            vx=0;
        }
    }
    public void checkIsCollisionY(boolean f){
        isCollisionY = f;
        if(f){
            vy=0;
        }
    }
}
