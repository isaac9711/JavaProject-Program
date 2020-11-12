import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class StudentInfor extends JFrame {		//데이터베이스를 활용하는 학생 정보 조회, 입력 프로그램
	public JLabel idLabel = new JLabel("학번 : ");		//학번 라벨 생성
	public JTextField idTField = new JTextField(25);	//학번 입력 및 조회란 생성
	public JLabel nameLabel = new JLabel("이름 : ");		//이름 라벨 생성
	public JTextField nameTField = new JTextField(25);	//이름 입력 및 조회란 생성
	public JLabel deptLabel = new JLabel("학과 : ");		//학과 라벨 생성
	public JTextField deptTField = new JTextField(25);	//학과 입력 및 조회란 생성
	public JLabel phoneLabel = new JLabel("전화번호 : ");	//전화번호 라벨 생성
	public JTextField phoneTField = new JTextField(25); //전화번호 입력 및 조회란 생성
	public JLabel emailLabel = new JLabel("이메일 : ");	//이메일 라벨 생성
	public JTextField emailTField = new JTextField(25);	//이메일 입력 및 조회란 생성
	public JLabel addrLabel = new JLabel("주소 : ");		//주소 라벨 생성
	public JTextField addrTField = new JTextField(25);	//주소 입력 및 조회란 생성
	public JButton search = new JButton("조회");			//조회 버튼 생성
	public JButton save = new JButton("저장");			//저장 버튼 생성
	public StudentInfor(){
		setTitle("학생 정보 조회/입력");						//창 이름 작성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//종료문 작성
		Container c = getContentPane();					//컨테이너 생성
		c.setLayout(null);								//레이아웃 임의 값으로 설정
		idLabel.setLocation(15, 10);					//학번 라벨 위치 지정
		idLabel.setSize(60, 25);						//학번 라벨 크기 설정
		idTField.setLocation(95, 10);					//학번 입력 및 조회란 위치 지정
		idTField.setSize(250, 25);						//학번 입력 및 조회란 크기 설정
		nameLabel.setLocation(15, 50);					//이름 라벨 위치 지정
		nameLabel.setSize(60, 25);						//이름 라벨 크기 설정
		nameTField.setLocation(95, 50);					//이름 입력 및 조회란 위치 지정
		nameTField.setSize(250, 25);					//이름 입력 및 조회란 크기 설정
		deptLabel.setLocation(15, 90);					//학과 라벨 위치 지정
		deptLabel.setSize(60, 25);						//학과 라벨 크기 설정
		deptTField.setLocation(95, 90);					//학과 입력 및 조회란 위치 지정
		deptTField.setSize(250, 25);					//학과 입력 및 조회란 크기 설정
		phoneLabel.setLocation(15, 130);				//전화번호 라벨 위치 지정
		phoneLabel.setSize(60, 25);						//전화번호 라벨 크기 설정
		phoneTField.setLocation(95, 130);				//전화번호 입력 및 조회란 위치 지정
		phoneTField.setSize(250, 25);					//전화번호 입력 및 조회란 크기 설정
		emailLabel.setLocation(15,170);					//이메일 라벨 위치 지정
		emailLabel.setSize(60,25);						//이메일 라벨 크기 설정
		emailTField.setLocation(95,170);				//이메일 입력 및 조회란 위치 지정
		emailTField.setSize(250,25);					//이메일 입력 및 조회란 크기 설정
		addrLabel.setLocation(15,210);					//주소 라벨 위치 지정
		addrLabel.setSize(60,25);						//주소 라벨 크기 설정
		addrTField.setLocation(95,210);					//주소 입력 및 조회란 위치 지정
		addrTField.setSize(250,25);						//주소 입력 및 조회란 크기 설정
		search.setLocation(200,250);					//조회 버튼 위치 지정
		search.setSize(60,30);							//조회 버튼 크기 설정
		save.setLocation(280,250);						//저장 버튼 위치 지정
		save.setSize(60,30);							//저장 버튼 크기 설정
		c.add(idLabel);				//학번 라벨 컨테이너에 삽입
		c.add(idTField);			//학번 입력 및 조회란 컨테이너에 삽입
		c.add(nameLabel);			//이름 라벨 컨테이너에 삽입
		c.add(nameTField);			//이름 입력 및 조회란 컨테이너에 삽입
		c.add(deptLabel);			//학과 라벨 컨테이너에 삽입
		c.add(deptTField);			//학과 입력 및 조회란 컨테이너에 삽입
		c.add(phoneLabel);			//전화번호 라벨 컨테이너에 삽입
		c.add(phoneTField);			//전화번호 입력 및 조회란 컨테이너에 삽입
		c.add(emailLabel);			//이메일 라벨 컨테이너에 삽입
		c.add(emailTField);			//이메일 입력 및 조회란 컨테이너에 삽입
		c.add(addrLabel);			//주소 라벨 컨테이너에 삽입
		c.add(addrTField);			//주소 입력 및 조회란 컨테이너에 삽입
		c.add(search);				//조회 버튼 컨테이너에 삽입
		c.add(save);				//저장 버튼 컨테이너에 삽입
		search.addActionListener(new EventHandler());		//조회 버튼을 반응시키기 위한 액션리스너 기능 추가
		save.addActionListener(new EventHandler());			//저장 버튼을 반응시키기 위한 액션리스너 기능 추가
		setSize(390,330);			//창 크기 설정
		setVisible(true);			//창 보이게 설정
	}
	class EventHandler implements ActionListener {			//각 버튼 별 기능 이벤트핸들러에 작성
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==search) {						//조회 버튼 클릭 시
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");			//JDBC 드라이버 로딩
					String url ="jdbc:mysql://localhost:3306/javadb?serverTimezone=UTC";		//DriverManager를 이용해서 connection 얻기
					String id = "root";  String pw = "1234";			//사용자명과 사용자암호를 입력하기위한 String 변수 설정
					Connection con = DriverManager.getConnection(url, id, pw);		//db연결을 위한 사용자 입력값 삽입
					Statement stmt = con.createStatement();				//connection으로부터 statement 생성 및 실행
					ResultSet rs = stmt.executeQuery("select * from Student where id = "+idTField.getText().toString());	//id 텍스트필드에 입력된 값을 이용해 db 데이터 값을 반환하는 sql 쿼리 작성
					while(rs.next()!=false) {		//데이터값이 끝나는곳까지 반복
						nameTField.setText(rs.getString("name"));		//db에서 name 필드의 문자열 반환
						deptTField.setText(rs.getString("dept"));		//db에서 dept 필드의 문자열 반환
						phoneTField.setText(rs.getString("phone"));		//db에서 phone 필드의 문자열 반환
						emailTField.setText(rs.getString("email"));		//db에서 email 필드의 문자열 반환
						addrTField.setText(rs.getString("addr"));		//db에서 addr 필드의 문자열 반환
					}
					stmt.close();			//statement 종료
					con.close();			//db접속 종료
					JOptionPane.showMessageDialog(null, "조회 완료");		//조회 완료 되었을때 팝업을 이용해서 확인
				}catch(Exception e2){		//오류발생시 실행 구문
					JOptionPane.showMessageDialog(null, "탐색 오류");		//조회 오류 발생 할 경우 팝업을 이용해서 확인
				}
			}
			else if (e.getSource()==save) {					//저장 버튼 클릭 시
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");		//JDBC 드라이버 로딩
					String url ="jdbc:mysql://localhost:3306/javadb?serverTimezone=UTC";		//DriverManager를 이용해서 connection 얻기
					String id = "root";  String pw = "1234";					//사용자명과 사용자암호를 입력하기위한 String 변수 설정
					Connection con = DriverManager.getConnection(url, id, pw);			//db연결을 위한 사용자 입력값 삽입
					String sql = "insert into Student (id, name, dept, phone, email, addr) values(?, ?, ?, ?, ?, ?)";		//prepared statement로 사용할 명령문 문자열에 저장(와일드카드문자(?)로 매개변수전달 사용)
					PreparedStatement pstmt = con.prepareStatement(sql);			//실행속도가 비교적 빠른 prepared statement로 쿼리문 실행
					pstmt.setInt(1, Integer.parseInt(idTField.getText().toString()));		//첫번째 인자에 학번 전달(데이터형식이 정수형임으로 형변환 사용)
					pstmt.setString(2, nameTField.getText());		//두번째 인자에 이름 전달
					pstmt.setString(3, deptTField.getText());		//세번째 인자에 학과 전달
					pstmt.setString(4, phoneTField.getText());		//네번째 인자에 전화번호 전달
					pstmt.setString(5, emailTField.getText());		//다섯번째 인자에 이메일 전달
					pstmt.setString(6, addrTField.getText());		//여섯번째 인자에 주소 전달
					pstmt.executeUpdate();						//executeUpdate로 최종 적용
					pstmt.close();								//prepared 종료
					con.close();								//db 접속 종료
					JOptionPane.showMessageDialog(null, "저장 완료");		//저장 완료 되었을때 팝업을 이용해서 확인
				}catch(Exception e2) {		//오류발생시 실행 구문
					JOptionPane.showMessageDialog(null, "저장 오류");		//저장 오류 발생 할 경우 팝업을 이용해서 확인
				}
			}
		}
	}
	public static void main(String [] args) throws ClassNotFoundException, SQLException  {		//메인함수 실행, throws로 예외처리
		Class.forName("com.mysql.cj.jdbc.Driver");				//JDBC 드라이버 로딩
		String url ="jdbc:mysql://localhost:3306/javadb?serverTimezone=UTC";			//DriverManager를 이용해서 connection 얻기
		String id = "root";  String pw = "1234";				//사용자명과 사용자암호를 입력하기위한 String 변수 설정
		Connection con = DriverManager.getConnection(url, id, pw);			//db연결을 위한 사용자 입력값 삽입
		Statement stmt = con.createStatement();			//connection으로부터 statement 생성 및 실행
		String sql = "create table if not exists Student(id int, name varchar(10), dept varchar(20), phone varchar(20), email varchar(20), addr varchar(30), PRIMARY KEY(ID)) ";		//테이블이 생성되어있지 않을경우에 한하여 student 테이블 생성하는 문자열 선언
		stmt.execute(sql);		//statement로 쿼리 실행
		stmt.close();			//statement 종료
		con.close();			//db 접속 종료
		new StudentInfor();		//프로그램 실행
	}
}
