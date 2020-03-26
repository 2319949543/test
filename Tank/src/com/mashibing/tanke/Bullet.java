package com.mashibing.tanke;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 5;
    public static int WIDTh=ResourceMgr.bulletD.getWidth();
    public static int HEIGHT=ResourceMgr.bulletD.getHeight();
    private int x,y;
    private Dir dir;
    private  boolean living =true;
    private  TankFrame tankFrame=null;
    private Group group;
    
    Rectangle rectangle = new Rectangle();
    
    public Bullet(int x, int y, Dir dir,Group group,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tankFrame=tankFrame;
        this.rectangle.x=this.x;
        this.rectangle.y=this.y;
        this.rectangle.width=this.WIDTh;
        this.rectangle.height=this.HEIGHT;
    }
    public void  paint(Graphics g){
        //Color c=g.getColor();
    	//g.setColor(Color.RED);
    	//g.fillOval(x,y,WIDTh,HEIGHT);
    	//g.setColor(c);
    	if(!this.living) {
    		this.tankFrame.bullets.remove(this);
    	}
    	switch (dir) {
			case LEFT:
				g.drawImage(ResourceMgr.bulletL, x,y,null);
				break;
			case UP:
				g.drawImage(ResourceMgr.bulletU, x,y,null);
				break;
			case RIGHT:
				g.drawImage(ResourceMgr.bulletR, x,y,null);
				break;
			case DOWN:
				g.drawImage(ResourceMgr.bulletD, x,y,null);
				break;
	
			default:
				break;
		}
        move();
    }


    private void move() {
        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }
        if(x<0||x>TankFrame.GAME_WIDTH||y<0||y>TankFrame.Game_HEIGHT){
            this.living=false;
        }
        
        //update react
        this.rectangle.x=this.x;
        this.rectangle.y=this.y;
    }
	public void collidWith(Tank tank) {
		if(this.group==tank.getGroup()) {
			return;
		}
		Rectangle r1=this.rectangle;
		Rectangle r2=tank.rectangle;
		if(r1.intersects(r2)) {
			this.die();
			tank.die();
		}
		
	}
	private void die() {
		this.living=false;
	}

}
