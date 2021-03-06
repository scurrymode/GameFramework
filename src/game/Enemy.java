package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Enemy extends GameObject{
	Random r = new Random();
	int max;
	int min;

	public Enemy(ObjectManager objectManager, ObjectId id, int x, int y, int width, int height) {
		super(objectManager,id,x, y, width, height);
		velX=-1;
		max=GamePanel.HEIGHT*GamePanel.SCALE-50;
		min=50;
	}

	
	public void tick() {
		x+=velX;
		//화면 좌측끝에 도달하면 다시 우측부터 시작하기(총알 맞지않으면 재사용)
		if(x<0){
			x=GamePanel.WIDTH*GamePanel.SCALE;
			y=r.nextInt(max-min+1)+min;
		}
		rect.setBounds(x, y, width, height);
	}

	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		Graphics2D g2=(Graphics2D)g;
		g2.draw(rect);
	}

}
