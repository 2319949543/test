package com.mashibing.tanke;

import java.awt.*;
import java.util.Random;

public class Tank {
    public final static int WIDTH=ResourceMgr.goodTankD.getWidth();
    public final static int HEIGHT=ResourceMgr.goodTankD.getHeight();
	private final static  int SPEED=2;
    private  int x,y;
    private Dir dir = Dir.DOWN;

    private Group group=Group.BAD;
    
    private boolean moving = true;
    private  TankFrame tankFrame;
    private boolean living=true;
    
    private Random random = new Random();
    
    Rectangle rectangle = new Rectangle();
    
    public Tank(int x, int y, Dir dir,Group group,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
        this.tankFrame=tankFrame;
        this.rectangle.x=this.x;
        this.rectangle.y=this.y;
        this.rectangle.width=this.WIDTH;
        this.rectangle.height=this.HEIGHT;
    }

    public void fire() {
    	switch (dir) {
		case LEFT:
			tankFrame.bullets.add( new Bullet(this.x,this.y+Tank.WIDTH/2-Bullet.WIDTh/2,this.dir,this.group,this.tankFrame));
			break;
		case RIGHT:
			tankFrame.bullets.add( new Bullet(this.x+Tank.WIDTH,this.y+Tank.WIDTH/2-Bullet.WIDTh/2,this.dir,this.group,this.tankFrame));
			break;
		case UP:
			tankFrame.bullets.add( new Bullet(this.x+Tank.WIDTH/2-Bullet.WIDTh/2,this.y,this.dir,this.group,this.tankFrame));
			break;
		case DOWN:
			tankFrame.bullets.add( new Bullet(this.x+Tank.WIDTH/2-Bullet.WIDTh/2,this.y+Tank.WIDTH/2,this.dir,this.group,this.tankFrame));
			break;

		default:
			break;
		}
    }


    public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Dir getDir() {
        return dir;
    }

    public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

    public boolean isMoving() {
        return moving;
    }

    private void move() {
       if(!moving)return;
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
       
       if(this.group==Group.BAD&&random.nextInt(100)>95)this.fire();
       if(this.group==Group.BAD&&random.nextInt(200)>195)
       randomDir();
       
       boundsCheck();
       //update rect
       this.rectangle.x=this.x;
       this.rectangle.y=this.y;
    }

    private void boundsCheck() {
    	if(x<0)x=0;
    	else if(y<30)y=30;
    	else if(x>(tankFrame.GAME_WIDTH-this.WIDTH))x=tankFrame.GAME_WIDTH-this.WIDTH;
    	else if(y>(tankFrame.Game_HEIGHT-this.HEIGHT))y=tankFrame.Game_HEIGHT-this.HEIGHT;
		
	}

	private void randomDir() {
    	this.dir=Dir.values()[random.nextInt(4)];
	}

	public void paint(Graphics g) {
        //Color c=g.getColor();
        //g.setColor(Color.YELLOW);
        //g.fillRect(x,y,WIDTH,HEIGHT);
        //g.setColor(c);
    	if(!this.living) {
    		this.tankFrame.tanks.remove(this);
    		new Explode(x, y, tankFrame).paint(g);;
    	}
        switch (dir) {
			case LEFT:
				g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL, x,y,null);
				break;
			case UP:
				g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU, x,y,null);
				break;
			case RIGHT:
				g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR, x,y,null);
				break;
			case DOWN:
				g.drawImage(this.group==Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD, x,y,null);
				break;
	
			default:
				break;
		}
        move();
    }

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setMoving(boolean moving) {
        this.moving = moving;
    }

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void die() {
		this.living=false;
		tankFrame.explods.add(new Explode(x, y, tankFrame));
	}

}
