package com.mashibing.tanke;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    static final int GAME_WIDTH=1280,Game_HEIGHT=960;
    static List<Tank> tanks = new ArrayList<>();

    Tank myTank = new Tank(200,200,Dir.DOWN,Group.GOOD,this);
    List<Bullet> bullets = new ArrayList<>();
    List<Explode> explods = new ArrayList<>();
    public  TankFrame(){
        setSize(GAME_WIDTH,Game_HEIGHT);//设置大小
        setResizable(false);//设置大小可否改变
        setTitle("Tank war");//设置标题

        setVisible(true);//设置可视

        this.addKeyListener(new MyKeyListener());//键盘监听
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });//设置可关闭
    }

    //双缓冲解决闪烁
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage==null){
            offScreenImage=this.createImage(GAME_WIDTH,Game_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c=gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,Game_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);

    }

    @Override
    public void paint(Graphics g){
    	
    	Color c = g.getColor();
    	g.setColor(Color.white);
    	g.drawString("子弹的数量："+bullets.size()+"", 10, 60);
    	g.drawString("敌人的数量："+tanks.size()+"", 10, 80);
    	g.drawString("爆炸的数量："+explods.size()+"", 10, 80);
    	g.setColor(c);
        myTank.paint(g);
        for (int i=0;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }
        for(int i=0;i<tanks.size();i++) {
        	tanks.get(i).paint(g);
        }
        for(int i=0;i<explods.size();i++) {
        	explods.get(i).paint(g);
        }
        for (int i=0;i<bullets.size();i++){
        	for(int j=0;j<tanks.size();j++) {
        		bullets.get(i).collidWith(tanks.get(j));
            }
            
        }
    }

    class MyKeyListener extends KeyAdapter{
        boolean bL =false;
        boolean bU =false;
        boolean bR =false;
        boolean bD =false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL=true;
                    break;
                case KeyEvent.VK_UP:
                    bU=true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=true;
                    break;
                default:
                    break;
            }
            setMainTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL=false;
                    break;
                case KeyEvent.VK_UP:
                    bU=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR=false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD=false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        private void setMainTankDir() {
            if(bD||bL||bR||bU){
                myTank.setMoving(true);

                if(bL)myTank.setDir(Dir.LEFT);
                if(bU)myTank.setDir(Dir.UP);
                if(bR)myTank.setDir(Dir.RIGHT);
                if(bD)myTank.setDir(Dir.DOWN);
            }else{
                myTank.setMoving(false);
            }

        }
    }


}
