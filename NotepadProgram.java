import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class NotepadProgram extends JFrame {		//������ �޸��� ���α׷�
	private JTextField notefilename = new JTextField(30);		//�����̸��� �ۼ��� �ؽ�Ʈ�ʵ� ����
	private JTextArea notefileedit = new JTextArea(15, 55);		//���ϳ����� �ۼ��� �ؽ�Ʈ�Ƹ��� ����
	private JButton notefileopen = new JButton("����");			//���Ͽ��⸦ ���� ��ư ����
	private JButton notefilesave = new JButton("����");			//���������� ���� ��ư ����
	private JButton newnote = new JButton("���� �����");			//ȭ�� ����� ���� ��ư ����
	private NotepadProgram() {
		setTitle("�޸��� ����");									//â �̸� �ۼ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//���Ṯ �ۼ�
		Container c = getContentPane();							//�����̳� ����
		c.setLayout(new FlowLayout());							//���̾ƿ� �ڵ�����
		notefileopen.setSize(40,40);							//���Ͽ��� ��ư ũ�⼳��
		notefilesave.setSize(40,40);							//�������� ��ư ũ�⼳��
		newnote.setSize(80,40);									//ȭ������� ��ư ũ�⼳��
		c.add(new JScrollPane(notefileedit));					//��ũ�ѱ���� �����ϵ��� ���ϳ��� �����̳ʿ� ����
		c.add(notefilename);									//�����̸� �����̳ʿ� ����
		c.add(notefileopen);									//���Ͽ��� ��ư �����̳ʿ� ����
		c.add(notefilesave);									//�������� ��ư �����̳ʿ� ����
		c.add(newnote);											//ȭ������� ��ư �����̳ʿ� ����
		
		notefileopen.addActionListener(new EventHandler());		//���Ͽ��� ��ư�� ������Ű������ �׼Ǹ����� ��� �߰�
		notefilesave.addActionListener(new EventHandler());		//�������� ��ư�� ������Ű������ �׼Ǹ����� ��� �߰�
		newnote.addActionListener(new EventHandler());			//ȭ������� ��ư�� ������Ű������ �׼Ǹ����� ��� �߰�
		
		setSize(700,450);						//âũ�� ����
		setVisible(true);						//â ���̰� ����
	}
	class EventHandler implements ActionListener {		//�� ��ư�� ��� �̺�Ʈ�ڵ鷯�� �ۼ�
		@Override
		public void actionPerformed(ActionEvent e) {
		if(e.getSource()==notefileopen) {				//������ ������ ��
			String tmpfilename = notefilename.getText();	//���� ������ ���߱� ���� �ؽ�Ʈ�ʵ� ���� ��Ʈ������ ��ȯ
			try {
				BufferedReader reader = new BufferedReader(new FileReader(tmpfilename));		//���۸����� ���Ͽ��� �о����
				notefileedit.setText("");		//���ϳ����� ��ġ�� �ʵ��� ���ϳ����ۼ��� ��ĭ���� �ʱ�ȭ
				String line;					//���ϳ����� ���ٽ� �о���� ���� ��Ʈ�� ���� ����
				while((line=reader.readLine())!=null) {	//�о�� ���Ͽ��� ���� ��������
					notefileedit.append(line+"\n");		//\n�߰�
				}
				reader.close();						//���� �Ϸ� �� ���ϸ����� ����
				JOptionPane.showMessageDialog(null, "���� ���� �Ϸ�");		//���� ������ �� ��µǸ� �˾����� Ȯ��
			}catch(Exception e2) {				//���� �߻���
				JOptionPane.showMessageDialog(null, "����� ����");		//�ȳ��� �˾����� Ȯ��
			}
		}
		else if(e.getSource()==notefilesave) {		//���� ������ ��
			String tmpfilename = notefilename.getText();		//���� ������ ���߱� ���� �ؽ�Ʈ�ʵ� ���� ��Ʈ������ ��ȯ
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfilename));		//���۶����ͷ� �����̸� �ۼ�
				writer.write(notefileedit.getText());			//�ؽ�Ʈ�Ƹ��� ���� ���Ϸ� �ۼ�
				writer.close();									//���� �Ϸ� �� �����ۼ��� ����
				JOptionPane.showMessageDialog(null, "���� �Ϸ�");			//���� ������ �� �Ϸ�Ǹ� �˾����� Ȯ��
			}catch(Exception e2) {				//���� �߻���
				JOptionPane.showMessageDialog(null, "���� ����");		//�ȳ��� �˾����� Ȯ��
			}
		}
		else if(e.getSource()==newnote) {			//ȭ�� ���� ��
			notefileedit.setText("");				//�ؽ�Ʈ�Ƹ��� ��ĭ���� �ʱ�ȭ
			notefilename.setText("");				//�ؽ�Ʈ�ʵ� ��ĭ���� �ʱ�ȭ
			}
		}
	}
	public static void main(String [] args) {		//�����Լ� ����
		new NotepadProgram();					//�ش� ���α׷� ����
	}
}