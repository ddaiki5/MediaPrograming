import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Model extends Observable{
    protected ArrayList<Character> chara,balls;
    protected Player player;
    protected boolean canMove, canJump;//使われていない
    protected Field field;
    private boolean pressedKeyRight, pressedKeyLeft;
    private SceneManager sceneManager;
    public boolean goal,gameOver;
    //Enemy1 e1;
    public Model(){
        init();
    }
    //初期化用
    public void init(){
        chara = new ArrayList<Character>();
        //balls = new ArrayList<Ball>();
        //player = null;
        field = new Field(this);
        pressedKeyRight= false;
        pressedKeyLeft = false;
        goal = false;
        gameOver = false;
    }
    
    public void createPlayer(int x, int y){//Player作成用
        player = new Player(x, y);
        chara.add(player);
        //e1 = new Enemy1(200, 50);
        chara.add(new Enemy1(200, 50));
        chara.add(new Boss(250, 100));
        //setChanged();
        //notifyObservers();
    }
    //viewで毎フレーム更新
    public void update(){
        //System.out.println(player==null);
        //Controllerの入力に対する移動
        if(pressedKeyRight){
            player.moveX(1);
            pressedKeyLeft = false;
            //System.out.println("right");
        }else if(pressedKeyLeft){
            player.moveX(-1);
            pressedKeyRight = false;
            //System.out.println("left");
        }else if(!pressedKeyRight && !pressedKeyLeft){
            player.moveX(0);
        }
        //落下と移動処理
        for(int i=0;i<chara.size();i++){
            chara.get(i).update(field);
            ballEndCheck(chara.get(i));
        }
        
        //character同市のあたり判定
        for(int i=0;i<chara.size();i++){
            for(int j=i+1;j<chara.size();j++){
                collisionCheack(chara.get(i), chara.get(j));
            }
        }
        //前フレームの座標を保存
        for(Character f:chara){
            f.pX = f.getX();
            f.pY = f.getY();
        }
        
        //e1.update(field);
        gameOverCheck();
    }
    //controllerで呼び出し
    public void move(int d, boolean f){
        if(d==-1){
            pressedKeyLeft = f;
        }
        if(d==1){
            pressedKeyRight = f;
        }
    }
    //controllerで呼び出し
    public void jump(){
        if(player!=null){
            player.jump();
            shoot();
        }
    }
    //ball攻撃
    public void shoot(){
        Ball ball;
        if(player.dir==0){
            ball = new Ball((int)player.getX()+player.getWidth()+6, (int)player.getY()+player.getHeight()/2);
            ball.dir = 0;
        }else{
            ball = new Ball((int)player.getX()-6, (int)player.getY()+player.getHeight()/2);
            ball.vx = -1.5f;
            ball.dir = 1;
        }
        chara.add(ball);
    }
    //ballを消す
    public void ballEndCheck(Character c){
        if(c.getCharacterNum()==99){
            if(c.hp<=0){
                chara.remove(chara.indexOf(c));
            }
        }
    }
    //キャラ同市のあたり判定
    public void collisionCheack(Character c1, Character c2){
        //矩形あたり判定
        if(Math.abs(c1.getX()-c2.getX())<(c1.gw+c2.gw)/2){
            if(Math.abs(c1.getY()-c2.getY())<(c1.gh+c2.gh)/2){
                if(c1.getCharacterNum()==0){//playerとenemyのあたり判定
                    if(c1.getDamageCount()==0){//無敵でないなら
                        if(c2.getCharacterNum()==99){//ballとの判定はない
                            return;
                        }
                        if(c1.pY+c1.getHeight()<c2.getY()){//上から
                            //player.jump();
                            c2.damaged(1);//enemyにダメージ
                            c1.setVy(-3);
                        }else{
                            c1.damaged(1);//playerにダメージ
                        }
                        // if(Math.abs(c1.pX-c2.pX)>(c1.getWidth()+c2.getWidth())/2){//横から
                        //     c1.damaged(1);//playerにダメージ 
                        // }else if(c1.getY()-c2.getY()<(c1.getHeight()+c2.getHeight())/2){//下から
                        //     c1.damaged(1);
                        // }
                    }
                }else if(c1.getCharacterNum()==99 || c2.getCharacterNum()==99){
                    c1.damaged(1);
                    c2.damaged(1);
                }
            }
        }
    }
    
    
    //playerのhpがゼロになったかをチェックする
    public void gameOverCheck(){
        if(player.getHp()<=0){
            gameOver = true;
        }
        if(player.getX()<0||player.getX()>field.WIDTH){
            gameOver = true;
        }
        if(player.getY()<0||player.getY()>field.HEIGHT){
            gameOver = true;
        }
        
    }
    //get.set関数
    public ArrayList<Character> getCharactors(){
        return chara;
    }
    public void setCharacter(Character c){
        chara.add(c);
    }
    public Character getCharactor(int idx){
        return chara.get(idx);
    }

    // public void collisionCheack(Character c){
    //     for(int i=0;i<field.getRow();i++){
    //         for(int j=0;j<field.getCol();j++){
    //             if((c.getX()+c.getVx()>=field.getCs()*j&&c.getX()+c.getVx()<=field.getCs()*(j+1))||(c.getX()+c.getVx()+c.getWidth()>field.getCs()*j&&c.getX()+c.getVx()+c.getWidth()<field.getCs()*(j+1))){
    //                 if(c.getY()+c.getVy()>=field.getCs()*i&&c.getY()+c.getVy()<=field.getCs()*(i+1)){
    //                     if(field.isHit(i, j)){
    //                         System.out.println("1");
    //                         if(!((c.getX()>=field.getCs()*j&&c.getX()<=field.getCs()*(j+1)))){
    //                             c.checkIsCollisionX(true);
    //                             //c.checkIsCollisionY(false);
    //                             System.out.println("a");
    //                             //c.setLocation((int)c.pX, (int)c.getY());
    //                             //return;
    //                         }
    //                         if(!(c.getX()+c.getWidth()>field.getCs()*j&&c.getX()+c.getWidth()<field.getCs()*(j+1))){
    //                             c.checkIsCollisionX(true);
    //                             //c.checkIsCollisionY(false);
    //                             System.out.println("b");
    //                             //c.setLocation((int)c.pX, (int)c.getY());
    //                             //return;
    //                         }
    //                         if(!(c.getY()>=field.getCs()*i&&c.getY()<=field.getCs()*(i+1))){
    //                             //c.checkIsCollisionX(false);
    //                             c.checkIsCollisionY(true);
    //                             //c.setLocation((int)c.getX(), (int)field.getCs()*(i+1));
    //                             System.out.println("ue");
    //                             if(field.getNum(i, j)==2){
    //                                 field.setNum(i, j, 0);
    //                             }
    //                             //return;
    //                         }
    //                         return;
    //                     }
    //                 c.checkIsGround(false);
    //                 }else if(c.getY()+c.getVy()+c.getHeight()>field.getCs()*i&&c.getY()+c.getVy()+c.getHeight()<field.getCs()*(i+1)){
    //                     if(field.isHit(i, j)){
    //                         c.checkIsGround(true);
    //                         c.checkIsCollisionX(false);
    //                         c.checkIsCollisionY(false);

    //                         return;
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     c.checkIsCollisionX(false);
    //     c.checkIsCollisionY(false);
    // }
    
}

