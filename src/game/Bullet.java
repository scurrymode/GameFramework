package game;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject{
	
	public Bullet(ObjectManager objectManager,ObjectId id,int x, int y, int width, int height) {
		super(objectManager,id,x,y,width,height);
		
		velX=4;
	}
	
	public void tick(){
		x+=velX;
		rect.setBounds(x, y, width, height);
		
		//적군과 내가 교차하면, 둘다 죽기! 죽기=ObjectManager 명단에서 빠져나가면 더이상 안그림
		for(int i=0; i<objectManager.list.size();i++){
			GameObject obj=objectManager.list.get(i);
			if(rect.intersects(obj.rect)){
				if(obj.id==ObjectId.Enemy){
					objectManager.list.remove(obj);
					objectManager.list.remove(this);
				}
			}
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.YELLOW);
		//Graphics2D g2 = (Graphics2D)g;
		//g2.draw(rect);
		g.fillOval(x, y, width, height);
		
	}

}
