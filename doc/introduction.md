# CPSLAB Android Tutorial
この資料はCPSLABのゼミ生に向けたものです。  
[基礎編はこちら](./document.md)

# Create New Project
`Start a new Android Studio project`をクリック。  
![Welcome.png](./image/Welcome.png)

`Application name`と`Company Domain`を入力してNextをクリック。  
`Company Domain`はなんでも良いけど`Package name`になるので自分でわかりやすい名前を付ける。  
![new1.png](./image/Create_New_Project1.png)

`Phone and Tablet`にチェックをいれて`Minimum SDK`を選択します。  
`Minimum SDK`は動作する端末のAPIレベルの指定。指定したAPIレベル以上のAndroid端末でしか動きません。
![new2.png](./image/Create_New_Project2.png)

最初にAndroidStudioが初期化してくれる画面の選択。  
今回は`Empty Activity`を選択。  
![new3.png](./image/Create_New_Project3.png)

いろいろ初期化が終わると下図のような画面が出てくるはずなのでとりあえず端末にインストールできるかのチェック。  
上部の緑色の三角のボタンを押す。  
このときにPCにインストールするAndroid端末を接続しておく。
![MainActivity.png](./image/MainActivity.png)

そうすると下図のような画面が出てくるのでデバイスを選んでOKをクリック。  
![select_device.png](./image/select_device.png)

上でデバイスが表示されない場合はAndroid端末をPCにUSBケーブルで接続し設定から以下の手順でUSBデバッグを有効化。  
![usb_debug_tejun.png](./image/usb_debug_tejun.png)

下図のような画面がAndroid端末に表示されたら成功。  
![screenshot1.jpg](./image/screenshot1.jpg)
