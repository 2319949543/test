package com.mashibing.tanke;

import java.awt.*;
import java.util.Random;

public class Explode {
    public final static int WIDTH=ResourceMgr.explodes[0].getWidth();
    public final static int HEIGHT=ResourceMgr.explodes[0].getHeight();
    
    private  int x,y;
    
    private  TankFrame tankFrame=null;
    
    private int step=0;
    
    public Explode(int x, int y,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame=tankFrame;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    

    public void paint(Graphics g) {
       g.drawImage(ResourceMgr.explodes[step++],x,y, null);
       if(step>=ResourceMgr.explodes.length)
    	   tankFrame.explods.remove(this);
    }



}
