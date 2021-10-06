import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class NotepadProgram extends JFrame {		//간단한 메모장 프로그램
	private JTextField notefilename = new JTextField(30);		//파일이름을 작성할 텍스트필드 생성
	private JTextArea notefileedit = new JTextArea(15, 55);		//파일내용을 작성할 텍스트아리아 생성
	private JButton notefileopen = new JButton("열기");			//파일열기를 위한 버튼 생성
	private JButton notefilesave = new JButton("저장");			//파일저장을 위한 버튼 생성
	private JButton newnote = new JButton("새로 만들기");			//화면 지우기 위한 버튼 생성
	private NotepadProgram() {
		setTitle("메모장 예제");									//창 이름 작성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//종료문 작성
		Container c = getContentPane();							//컨테이너 생성
		c.setLayout(new FlowLayout());							//레이아웃 자동설정
		notefileopen.setSize(40,40);							//파일열기 버튼 크기설정
		notefilesave.setSize(40,40);							//파일저장 버튼 크기설정
		newnote.setSize(80,40);									//화면지우기 버튼 크기설정
		c.add(new JScrollPane(notefileedit));					//스크롤기능이 가능하도록 파일내용 컨테이너에 삽입
		c.add(notefilename);									//파일이름 컨테이너에 삽입
		c.add(notefileopen);									//파일열기 버튼 컨테이너에 삽입
		c.add(notefilesave);									//파일저장 버튼 컨테이너에 삽입
		c.add(newnote);											//화면지우기 버튼 컨테이너에 삽입
		
		notefileopen.addActionListener(new EventHandler());		//파일열기 버튼을 반응시키기위한 액션리스너 기능 추가
		notefilesave.addActionListener(new EventHandler());		//파일저장 버튼을 반응시키기위한 액션리스너 기능 추가
		newnote.addActionListener(new EventHandler());			//화면지우기 버튼을 반응시키기위한 액션리스너 기능 추가
		
		setSize(700,450);						//창크기 설정
		setVisible(true);						//창 보이게 설정
	}
	class EventHandler implements ActionListener {		//각 버튼에 기능 이벤트핸들러에 작성
		@Override
		public void actionPerformed(ActionEvent e) {
		if(e.getSource()==notefileopen) {				//파일을 열었을 때
			String tmpfilename = notefilename.getText();	//구문 형식을 맞추기 위해 텍스트필드 내용 스트링으로 변환
			try {
				BufferedReader reader = new BufferedReader(new FileReader(tmpfilename));		//버퍼리더로 파일에서 읽어오기
				notefileedit.setText("");		//파일내용이 겹치지 않도록 파일내용작성란 빈칸으로 초기화
				String line;					//파일내용을 한줄식 읽어오기 위해 스트링 변수 선언
				while((line=reader.readLine())!=null) {	//읽어온 파일에서 줄이 없어지면
					notefileedit.append(line+"\n");		//\n추가
				}
				reader.close();						//최종 완료 시 파일리더기 종료
				JOptionPane.showMessageDialog(null, "파일 열기 완료");		//파일 내용이 다 출력되면 팝업으로 확인
			}catch(Exception e2) {				//오류 발생시
				JOptionPane.showMessageDialog(null, "입출력 오류");		//안내문 팝업으로 확인
			}
		}
		else if(e.getSource()==notefilesave) {		//파일 저장할 때
			String tmpfilename = notefilename.getText();		//구문 형식을 맞추기 위해 텍스트필드 내용 스트링으로 변환
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(tmpfilename));		//버퍼라이터로 파일이름 작성
				writer.write(notefileedit.getText());			//텍스트아리아 내용 파일로 작성
				writer.close();									//최종 완료 시 파일작성기 종료
				JOptionPane.showMessageDialog(null, "저장 완료");			//파일 저장이 다 완료되면 팝업으로 확인
			}catch(Exception e2) {				//오류 발생시
				JOptionPane.showMessageDialog(null, "저장 오류");		//안내문 팝업으로 확인
			}
		}
		else if(e.getSource()==newnote) {			//화면 지울 때
			notefileedit.setText("");				//텍스트아리아 빈칸으로 초기화
			notefilename.setText("");				//텍스트필드 빈칸으로 초기화
			}
		}
	}
	public static void main(String [] args) {		//메인함수 실행
		new NotepadProgram();					//해당 프로그램 실행
	}
}