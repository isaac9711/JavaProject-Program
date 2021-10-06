import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GamblingGame extends JFrame {
	JPanel GamePanel = new JPanel();		//JPanel ��ӹ��� ���ο� GamePanel ����Ʈ������ ���
	JPanel squar = new JPanel();			//JPanel ��ӹ��� ���ο� squar ����Ʈ������ ���
	JLabel[] slot = new JLabel[3];			//3������ slot �迭 ����
	JLabel la = new JLabel("�����մϴ�."); //"�����մϴ�." ���ڿ��� ����ϱ� ���� ���̺� ������Ʈ
	
	GamblingGame() {
		setTitle("Open Challenge 9"); // Ÿ��Ʋ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//������ ������ ������ ���α׷� ����
		Container GamePanel = getContentPane();		// ����Ʈ�� ����
		GamePanel.setLayout(null);		//GamePanel��ġ������ null�� ����
		GamePanel.addKeyListener(new MyKeyAdapter());	//Ű ������ ����
		la.setLocation(90,120);		//���ڿ� ��ġ����
		la.setSize(200,20);				//���ڿ� ũ������
		squar.setLayout(new FlowLayout(FlowLayout.CENTER,30,50));		//squar ��ġ����
		squar.setSize(250,100);											//squar ũ������
		for(int i=0;i<slot.length;i++) {
			slot[i]= new JLabel();					//���� ����
			slot[i].setBackground(Color.MAGENTA);	//��������
			slot[i].setOpaque(true);				//��������
			slot[i].setForeground(Color.yellow);	//���ڻ�����
			slot[i].setText("0");					//�ؽ�Ʈ����
			slot[i].setFont(new Font("�ü�", Font.BOLD, 25));	//��Ʈ����
			slot[i].setHorizontalAlignment(SwingConstants.CENTER);	//�������
			squar.add(slot[i]);							//slot �ޱ�
		}
		GamePanel.add(squar,BorderLayout.CENTER);	//squar�� GamePanel �� ��ġ �߾Ӽ���
		GamePanel.add(la,BorderLayout.SOUTH);		//la�� �Ʒ��� �߾���ġ ����
		setSize(250,250);							//âũ�� ����
		setVisible(true);							//â ��Ÿ����
		GamePanel.setFocusable(true);		//����Ʈ���� ��Ŀ���� ���� �� �ֵ��� ����
		GamePanel.requestFocus();			//����Ʈ�ҿ� ��Ŀ�� ����
	}
	class MyKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyChar() == '\n') {			//���� ���� ���� ���ǹ�
				int num1 = (int) (Math.random() * 4);			//0~3������ ������ ��
				int num2 = (int) (Math.random() * 4);			//0~3������ ������ ��
				int num3 = (int) (Math.random() * 4);			//0~3������ ������ ��
				slot[0].setText(Integer.toString(num1));		//num1�� ù��°ĭ�� �Է�
				slot[1].setText(Integer.toString(num2));		//num2�� �ι�°ĭ�� �Է�
				slot[2].setText(Integer.toString(num3));		//num3�� ����°ĭ�� �Է�
				if(slot[0].getText().equals(slot[1].getText()) && slot[0].getText().equals(slot[2].getText())) {		//������ ���Ǽ� ��
					la.setText("�����մϴ�!!");		//���� ��¹�
				}
				else {
					la.setText("�ƽ�����");		//���� ��¹�
				}
			}
		}
	}
	public static void main(String[] args) {
		new GamblingGame();
	}
}