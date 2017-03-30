/*
 * 모든 게임은 이 패널안에서 그려질 예정임
 * 아무리 게임의 장면이 다양하더라도, 패널은 오직 1개만 사용된다!!
 * 
 * 모든 오브젝트는 결국 이 패널에 그려져야 하므로, 이 패널의 paint 메서드의
 * 인수로 전달되는 Graphics 객체를 게임에 등장할 모든 오브젝트가 전달받아야한다.
 * */

package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	public static final int WIDTH=400;
	public static final int HEIGHT=300;
	public static final int SCALE=2;
	boolean flag= true; //게임 가동여부를 결정하는 변수
	Thread thread; //게임을 운영할 심장 선언
	Player player; //플레이어는 한명이니깐 멤버변수로 뺀다.
	ObjectManager objectManager; //객체 명단관리자
	
	
	public GamePanel() {
		//패널 생성과 동시에 심장 부여
		thread = new Thread(this);
		thread.start();
		
		//게임 초기화 
		init();
		
		//유지보수 좋게 크기 지정
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		
	}
	
	public void init(){
		//명단 관리자 생성
		objectManager = new ObjectManager();
		
		//주인공 등장 시키기
		player = new Player(objectManager,ObjectId.Player,100, 200, 50, 50);	
		objectManager.addObject(player);//1명추가
		
		//적군등장
		Random r = new Random();
		for(int i=0;i<10;i++){
			int x=r.nextInt((WIDTH*SCALE-50)-(500)+1)+500;
			int y=r.nextInt((HEIGHT*SCALE-50)-(50)+1)+50;
			
			Enemy enemy = new Enemy(objectManager,ObjectId.Enemy,x, y, 30, 30);
			objectManager.addObject(enemy);
			
		}
		
		//패널과 키보드 리스너 연결
		this.addKeyListener(new KeyBoard(player));
	}
	
	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0,WIDTH*SCALE,HEIGHT*SCALE);
		
		//모든 객체 render()호출
		for(int i=0;i<objectManager.list.size();i++){
			objectManager.list.get(i).render(g); //모든 객체가 게임오브젝트를 하나의 객체로 is a해둬서 가능
		}//위치중요 백그라운드 그려지고 내가 해야징~!
	}
	
	
	public void run() {
		while(flag){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 오브젝트 매니져에 등록된 모든 객체를 대상으로 tick()을 호출해보자
			for(int i=0;i<objectManager.list.size();i++){
				objectManager.list.get(i).tick(); //모든 객체가 게임오브젝트를 하나의 객체로 is a해둬서 가능
			}
			
			//player.tick();
			repaint(); //paintComponent를 간접호출
		}
	}
	

	

}
