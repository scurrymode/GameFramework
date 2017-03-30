/*
 * 이 클래스는 sun에서 자체 제작한 UI 컴포넌트가 아니기 때문에 화면에 그려질 수 없다.
 * 따라서 JPanel에 그려지려면 JPanel의 Graphics객체의 레퍼런스를 보유해야한다.
 * */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player extends GameObject{
	
	public Player(ObjectManager objectManager, ObjectId id, int x, int y, int width, int height) {
		super(objectManager,id,x,y,width, height); //이렇게하면 계속 this, this 안거려도된다. 슈퍼에서 다해줬으니깐!
	}
	
	//총알 발사 행위를 정의한다.
	public void fire(){
		Bullet bullet = new Bullet(objectManager,ObjectId.Bullet,x, y, 10, 10);
		objectManager.addObject(bullet);
		
	}
	
	//x,y,width,height 등의 물리량 등의 변화를 제어하기 위한 메서드!! (사람 비유- 운동량 변화)
	public void tick(){
		x+=velX;
		y+=velY;
		//사각형이 나를 따라다니게 값의 동기화!!
		rect.setBounds(x, y, width, height);
		
	}
	
	//변화값을 반영해서 그리기 위한 메서드!
	
	public void render(Graphics g){
		g.setColor(Color.WHITE); //페인트 색 바꾸기
		//g.drawRect(x, y, width, height);
		Graphics2D g2=(Graphics2D)g;
		g2.draw(rect);//렉트가 있다는걸 인식하려고~
	}
	
	
}
