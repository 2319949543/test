package com.mashibing.tanke;


public class Main {
    public static void main(String[] args) throws InterruptedException {
    	TankFrame f = new TankFrame();
    	int intTankCount = Integer.parseInt((String) PropertyMgr.get("intTankCount"));
        for(int i=0;i<intTankCount;i++) {
        	f.tanks.add(new Tank(80+i*80, 100, Dir.DOWN,Group.BAD, f));
        }
        //new Thread(()->new Audio("audio/war1.wav").loop()).start();
        while (true){
            Thread.sleep(50);
            f.repaint();
        }



    }
}
