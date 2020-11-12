import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

class FishMove extends Thread implements Runnable{		//랜덤으로 움직이는 fish를 위한 스레드
	JLabel fish;		//GameObject의 fish를 참조

	public FishMove(JLabel f){
		this.fish = f;
	}
	public void run() {		//주기적인 동작을 실행시키기 위해 run()함수 사용
		Random r= new Random();		//좌표값을 랜덤으로 변경시킬 r 랜덤변수 선언
		Random rp= new Random();	//좌표의 상하좌우를 결정시키기 위한 rp 랜덤변수 선언
		int x=fish.getX(), y=fish.getY();	//fish의 현재 좌표 수령
		while(true) {
			try {
				Thread.sleep(250);			//250ms마다 실행
			}
			catch(InterruptedException e) {		//스레드 종료 선언구
				fish.setText("");					//fish 공백으로 변경
				return;
			}
			int point = rp.nextInt(2)+1;		//상하좌우의 선언을위해 랜덤변수 범위지정
			if(point == 1) {					//조건문으로 상하좌우 결정
			x = x + r.nextInt()%2*20;			//좌우 결정 시 20픽셀 가로 이동할 변수값 저장
			}
			else {
			y = y + r.nextInt()%2*20;			//상하 결정 시 20픽셀 세로 이동할 변수값 저장
			}
			if( x>0 && y>0 && x<360 && y<360)	//fish가 창 벗어나지 않을 때만 이동
				fish.setLocation(x,y);				//결정된 좌표로 이동
		}
	}
}

public class GameObject extends JFrame{			//메인 스레드
	private JLabel bear = new JLabel("B");		//사용자가 움직일 Bear JLabel 생성
	public JLabel fish = new JLabel("@");		//컴퓨터가 움직일 fish JLabel 생성
	private JLabel finish = new JLabel("Bear가 Fish를 먹었습니다!");	//bear가 fish 잡았을경우 안내문 생성
	Thread th;
	public GameObject(){
		setTitle("Bear의 Flash 먹기 게임");			//창 타이틀 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//종료문구
		Container c= getContentPane();			//컨텐트팬 생성
		c.setLayout(null);						//레이아웃 
		c.addKeyListener(new MyKeyListener());	//사용자의 키 입력을 받을 키리스너 생성
		th = new Thread(new FishMove(fish));
		th.start();
		bear.setLocation(0,0);					//bear의 초기 위치 설정
		bear.setSize(20,20);					//bear의 크기 설정
		fish.setLocation(200,200);				//fish의 초기 위치 설정
		fish.setSize(20,20);					//fish의 크기 설정
		finish.setLocation(80,40);				//안내문의 위치 설정
		finish.setSize(200,20);					//안내문의 크기 설정
		finish.setVisible(false);				//처음 안내문 안보이게 설정
		c.add(bear);							//컨텐트팬에 bear 삽입
		c.add(finish);							//컨텐트팬에 안내문 삽입
		c.add(fish);							//컨탠트팬에 fish 삽입
		setSize(400,400);						//인터페이스 크기 설정
		setVisible(true);						//인터페이스 보이게 설정
		c.setFocusable(true);					//키이벤트 받기위해 설정
		c.requestFocus();						//키이벤트 받을 컴포넌트 강제 설정
	}
	
	class MyKeyListener extends KeyAdapter{		//키 어댑터를 사용할 클래스
		public void keyPressed(KeyEvent e) {	//키 이벤트 사용
			int keyCode = e.getKeyCode();		//키 이벤트를 위한 변수 선언
			int Eating=0;						//bear가 fish를 잡을 때의 카운트 변수 선언
			if(bear.getX()==fish.getX())		//bear와 fish의 x좌표가 동일하고
				if(bear.getY()==fish.getY())	//bear와 fish의 y좌표가 동일할때
					Eating = 1;					//카운트 1로 설정
			if(Eating==1)						//카운트 1일때
			{
				finish.setVisible(true);			//준비되었던 안내문 보이게 설정
				th.interrupt();						//스레드 종료
				return;
			}
			switch(keyCode) {					//사용자 키 다운 시 각각 키 마다 조건문 실행(해당 위치만큼 20픽셀씩 이동)
			case KeyEvent.VK_UP:				//위 키 입력했을때
				if(bear.getY()!=0) bear.setLocation(bear.getX(), bear.getY() - 20); break;
			case KeyEvent.VK_DOWN:				//아래 키 입력했을때
				if(bear.getY()!=340)bear.setLocation(bear.getX(), bear.getY() + 20); break;
			case KeyEvent.VK_LEFT:				//왼쪽 키 입력했을때
				if(bear.getX()!=0)bear.setLocation(bear.getX() - 20, bear.getY()); break;
			case KeyEvent.VK_RIGHT:				//오른쪽 키 입력했을때
				if(bear.getX()!=380)bear.setLocation(bear.getX() + 20, bear.getY()); break;
			}									//각각 키 입력시마다 창을 벗어나지 않게 범위 설정
		}
	}
	public static void main(String [] args) {
		new GameObject();				//게임 실행
	}
}
