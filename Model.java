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
    private boolean pressedKeyRight, pressedKeyLeft;//コントローラーからの入力
    private boolean isSoundLoad = false;//sounManagerが呼び出されているか
    public boolean goal,gameOver, bossFlag, stageClear;//シーン遷移用フラグ
    private int score,stageNum;//scoreと現在のステージの番号
    private SoundManager soundManager;
    //読み込む音データ指定
    private static final String[] soundNames = {"coin","jump","block","enter","shoot","stomp", "decide", "title","boss","field1","gameclear","gameover","fire","field2","title1"};
    private int ccc;//debug
    public Model(int i, SoundManager soundManager){
        init(i, soundManager);
    }
    //初期化用
    public void init(int i, SoundManager soundManager){
        chara = new ArrayList<Character>();
        field = new Field(this, i);
        this.soundManager = soundManager;
        pressedKeyRight= false;
        pressedKeyLeft = false;
        goal = false;
        gameOver = false;
        bossFlag = false;
        stageClear = false;
        stageNum = i;
        if(i==0){
            score = 0;
            
        }
        ccc = 0;
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
        //前フレームの座標を保存　使っていない
        for(Character f:chara){
            f.pX = f.getX();
            f.pY = f.getY();
        }

        // if(ccc%100==0){
        //     soundManager.stop("shoot");
        //     soundManager.play("shoot");
        // }
        // ccc++;
        
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
        soundManager.stop("shoot");
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
                float vx = (float)((player.getX()-c.getX())/(Math.sqrt(Math.pow(player.getX()-c.getX(),2)+Math.pow(player.getY()-c.getY(),2))));
                float vy = (float)((player.getY()-c.getY())/(Math.sqrt(Math.pow(player.getX()-c.getX(),2)+Math.pow(player.getY()-c.getY(),2))));
                if(c.dir==1){
                    BossFire fire = new BossFire((int)c.getX()-6, (int)c.getY()-20,vx,vy);
                    c.attacked();
                    chara.add(fire);
                }else{
                    BossFire fire = new BossFire((int)c.getX()+c.gw-48, (int)c.getY()-20,vx,vy);
                    c.attacked();
                    chara.add(fire);
                }
                
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
        if( c.getCharacterNum()==1 || c.getCharacterNum()==2 ){
            if(c.hp<=0){
                chara.remove(chara.indexOf(c));
                score += 100;
            }
        }else if(c.getCharacterNum()==97 || c.getCharacterNum()==98 ||c.getCharacterNum()==99){
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
                            soundManager.stop("coin");
                            soundManager.play("coin");
                            return;
                        }
                        if(c1.pY+c1.getHeight()<c2.getY()){//上から
                            //player.jump();
                            soundManager.stop("stomp");
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
                }else if((c1.getCharacterNum()==98 && c2.getCharacterNum()==10) ||(c1.getCharacterNum()==10 && c2.getCharacterNum()==98)){
                    return;
                }else if(c1.getCharacterNum()==6 || c2.getCharacterNum()==6){
                    //return;
                
                }else if(c1.getCharacterNum()==99 || c2.getCharacterNum()==99){
                    c1.damaged(1);
                    c2.damaged(1);
                }else if((c1.getCharacterNum()==98 && c2.getCharacterNum()==99) ||(c1.getCharacterNum()==99 && c2.getCharacterNum()==98)){
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
        if(!isSoundLoad){
            for(int i=0;i<soundNames.length;i++){
                soundManager.load(soundNames[i], "sounds/"+soundNames[i] + ".wav");
            }
        }
        isSoundLoad = true;
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
        soundManager.stop(name);
        soundManager.play(name);
    }

    public int getStageNum(){
        return stageNum;
    }

}

