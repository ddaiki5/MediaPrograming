import javax.sound.sampled.*;
import javax.swing.*;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.File;


public class Model extends Observable{
    private ArrayList<Character> chara;
    public Player player;
    private boolean canMove, canJump;//使われていない
    public Field field;
    private boolean pressedKeyRight, pressedKeyLeft;
    private SceneManager sceneManager;
    public boolean goal,gameOver, bossFlag, stageClear;
    private int score;
    private SoundManager soundManager;
    private static final String[] soundNames = {"coin","jump","block","enter","shoot","stomp"};
    
    public Model(int i){
        init(i);
    }
    //初期化用
    public void init(int i){
        chara = new ArrayList<Character>();
        field = new Field(this, i);
        soundManager = new SoundManager();
        pressedKeyRight= false;
        pressedKeyLeft = false;
        goal = false;
        gameOver = false;
        bossFlag = false;
        stageClear = false;
        if(i==0){
            score = 0;
            
        }
        
        loadSound();
    }
    
    public void createPlayer(int x, int y){//Player作成用
        player = new Player(x, y);
        player.setSoundManager(soundManager);
        chara.add(0,player);//playerは０番目
        //setChanged();
        //notifyObservers();
    }
    //viewで毎フレーム更新
    public void update(){
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
            if(i==0){//player
                chara.get(i).update(field);
            }else{//player以外のupdateはplayerに一定の距離近づいてから
                if(Math.sqrt(Math.pow(player.getX()-chara.get(i).getX(),2)+Math.pow(player.getY()-chara.get(i).getY(),2))<500){
                    chara.get(i).update(field);
                }
            }
            //playerの位置を伝える
            chara.get(i).setPlayerLocate(player.getX(), player.getY());
            //attackFlagのチェック
            enemyAttackFlagCheck(chara.get(i));
            //hpが0以下でリストから外す
            endCheck(chara.get(i));
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
            //shoot();
        }
    }
    //player ball攻撃 controllerで呼び出し
    public void shoot(){
        Ball ball;
        soundManager.play("shoot");
        if(player.dir==0){
            ball = new Ball((int)player.getX()+player.getWidth()+6, (int)player.getY()+player.getHeight()/4);
            ball.dir = 0;
        }else{
            ball = new Ball((int)player.getX()-10, (int)player.getY());
            ball.vx = -1.5f;
            ball.dir = 1;
        }
        chara.add(ball);
    }

    private void enemyAttackFlagCheck(Character c){
        //Boss
        if(c.getCharacterNum()==10){
            if(c.getAttackFlag()){
                BossFire fire = new BossFire((int)c.getX()-6, (int)c.getY());
                c.attacked();
                chara.add(fire);
            }
        }

        //(1/28追加)
        //Enemy4
        if(c.getCharacterNum()==2){
            if(c.getAttackFlag()){
                EnemyBall eball = new EnemyBall((int)c.getX(), (int)c.getY());
                if(player.getX() > c.getX()){
                    c.dir = 0;
                    eball.EBdir(0);
                }
                else{
                    c.dir = 1;
                    eball.EBdir(1);
                }
                c.attacked();
                chara.add(eball);
            }
        }
    }
    //ballを消す
    private void endCheck(Character c){
        if(c.getCharacterNum()==98 || c.getCharacterNum()==1 || c.getCharacterNum()==2 ){
            if(c.hp<=0){
                chara.remove(chara.indexOf(c));
                score += 100;
            }
        }else if(c.getCharacterNum()==97 || c.getCharacterNum()==99){
            if(c.hp<=0){
                chara.remove(chara.indexOf(c));
            }
        }else if(c.getCharacterNum()==10){
            if(c.hp<=0){
                chara.remove(chara.indexOf(c));
                score += 10000;
                stageClear = true;
            }
        }else if(c.getCharacterNum()==6){
            if(c.hp<=0){
                chara.remove(chara.indexOf(c));
                score += 200;
                System.out.println("coin");
            }
        }
        
    }
    //キャラ同市のあたり判定
    private void collisionCheack(Character c1, Character c2){
        //矩形あたり判定
        if(Math.abs(c1.getX()-c2.getX())<(c1.gw+c2.gw)/2){
            if(Math.abs(c1.getY()-c2.getY())<(c1.gh+c2.gh)/2){
                if(c1.getCharacterNum()==0){//playerとenemyのあたり判定
                    if(c1.getDamageCount()==0){//無敵でないなら
                        if(c2.getCharacterNum()==99){//ballとの判定はない
                            return;
                        }
                        if(c2.getCharacterNum()==6){//coinとの判定
                            c2.damaged(1);
                            soundManager.play("coin");
                            return;
                        }
                        if(c1.pY+c1.getHeight()<c2.getY()){//上から
                            //player.jump();
                            soundManager.play("stomp");
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
                }else if(c1.getCharacterNum()==6 || c2.getCharacterNum()==6){
                    //return;
                
                }else if(c1.getCharacterNum()==99 || c2.getCharacterNum()==99){
                    c1.damaged(1);
                    c2.damaged(1);
                }else if((c1.getCharacterNum()==98 || c2.getCharacterNum()==99) ||(c1.getCharacterNum()==99 || c2.getCharacterNum()==98)){
                    if(c1.getCharacterNum()==99){
                        c1.damaged(1);
                    }else if(c2.getCharacterNum()==99){
                        c2.damaged(1);
                    }
                }else if((c1.getCharacterNum()==99 && c2.getCharacterNum()==97) || (c1.getCharacterNum()==97 && c2.getCharacterNum()==99) ){
                    //(1/28)EnemyBall用のNum97を追加
                    c1.damaged(1);
                    c2.damaged(1);
                }
            }
        }
    }
    
    
    //playerのhpがゼロになったかをチェックする
    private void gameOverCheck(){
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
    //音ファイル読み込み
    private void loadSound(){
        for(int i=0;i<soundNames.length;i++){
            soundManager.load(soundNames[i], "sounds/"+soundNames[i] + ".wav");
        }
    }

    
    //get.set関数
    public ArrayList<Character> getCharactors(){
        return chara;
    }
    public void setCharacter(Character c){
        c.setSoundManager(soundManager);
        chara.add(c);
    }
    public Character getCharactor(int idx){
        return chara.get(idx);
    }

    public int getScore(){
        return score;
    }

    public int getPlayerHp(){
        return player.getHp();
    }

    public void soundPlay(String name){
        soundManager.play(name);
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

