# MediaPrograming
java Mainで実行

![クラス図](/pictures/game.PNG)

## 概要
作成した横スクロールアクションゲームはキャラを移動させてスコアを獲得しながら，Boss を倒す又はゴール地点にたどり着くことを目指すゲームである．スコアは敵を倒す又はコインを取得することで獲得することができる．敵を倒す手段は火の玉を出して攻撃する又は敵をジャンプで踏みつける(Boss には無効)の２つである．敵にぶつかる又は敵の攻撃を受けることで HP が１つ減少する．HP が０になる又は崖から落ちることでゲームオーバーとなる．

## クラス図
MVCモデルに基づいて設計を行った。以下にクラス図を添付する。
![クラス図](/pictures/uml2.png)

## クラス説明
- Model：キャラクターやフィールドといったデータの管理、処理
- View：画面への描画処理
- Controller：キーボードからの入力処理
- Character：Model で管理するプレイヤーや敵などのオブジェクト
- Field：キャラクターが動くフィールドデータの管理
- SceneManager：タイトル、ゲーム、クリア、ゲームオーバーといったシーン遷移の管理

## 操作方法
- W: 左へ移動
- D: 右へ移動
- S: 火の玉を出して攻撃
- スペース: ジャンプ

## デモビデオ
[![](https://img.youtube.com/vi/0Cl7cDDq9TE/0.jpg)](https://www.youtube.com/watch?v=0Cl7cDDq9TE)

https://www.youtube.com/watch?v=0Cl7cDDq9TE
