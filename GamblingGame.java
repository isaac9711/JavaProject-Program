import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GamblingGame extends JFrame {
	JPanel GamePanel = new JPanel();		//JPanel 상속받은 새로운 GamePanel 컨텐트팬으로 등록
	JPanel squar = new JPanel();			//JPanel 상속받은 새로운 squar 컨텐트팬으로 등록
	JLabel[] slot = new JLabel[3];			//3사이즈 slot 배열 생성
	JLabel la = new JLabel("시작합니다."); //"시작합니다." 문자열을 출력하기 위한 레이블 컴포넌트
	
	GamblingGame() {
		setTitle("Open Challenge 9"); // 타이틀 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//프레임 윈도우 닫으면 프로그램 종료
		Container GamePanel = getContentPane();		// 컨텐트팬 설정
		GamePanel.setLayout(null);		//GamePanel배치관리자 null로 설정
		GamePanel.addKeyListener(new MyKeyAdapter());	//키 리스너 설정
		la.setLocation(90,120);		//문자열 위치지정
		la.setSize(200,20);				//문자열 크기지정
		squar.setLayout(new FlowLayout(FlowLayout.CENTER,30,50));		//squar 위치지정
		squar.setSize(250,100);											//squar 크기지정
		for(int i=0;i<slot.length;i++) {
			slot[i]= new JLabel();					//슬롯 선택
			slot[i].setBackground(Color.MAGENTA);	//배경색설정
			slot[i].setOpaque(true);				//투명도설정
			slot[i].setForeground(Color.yellow);	//글자색설정
			slot[i].setText("0");					//텍스트변경
			slot[i].setFont(new Font("궁서", Font.BOLD, 25));	//폰트설정
			slot[i].setHorizontalAlignment(SwingConstants.CENTER);	//가운데정렬
			squar.add(slot[i]);							//slot 달기
		}
		GamePanel.add(squar,BorderLayout.CENTER);	//squar을 GamePanel 안 위치 중앙설정
		GamePanel.add(la,BorderLayout.SOUTH);		//la를 아래쪽 중앙위치 설정
		setSize(250,250);							//창크기 설정
		setVisible(true);							//창 나타내기
		GamePanel.setFocusable(true);		//컨텐트팬이 포커스를 받을 수 있도록 설정
		GamePanel.requestFocus();			//컨텐트팬에 포커스 설정
	}
	class MyKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar() == '\n') {			//엔터 받을 시의 조건문
				int num1 = (int) (Math.random() * 4);			//0~3까지의 임의의 값
				int num2 = (int) (Math.random() * 4);			//0~3까지의 임의의 값
				int num3 = (int) (Math.random() * 4);			//0~3까지의 임의의 값
				slot[0].setText(Integer.toString(num1));		//num1값 첫번째칸에 입력
				slot[1].setText(Integer.toString(num2));		//num2값 두번째칸에 입력
				slot[2].setText(Integer.toString(num3));		//num3값 세번째칸에 입력
				if(slot[0].getText().equals(slot[1].getText()) && slot[0].getText().equals(slot[2].getText())) {		//세개의 임의수 비교
					la.setText("축하합니다!!");		//성공 출력문
				}
				else {
					la.setText("아쉽군요");		//실패 출력문
				}
			}
		}
	}
	public static void main(String[] args) {
		new GamblingGame();
	}
}