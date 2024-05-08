package kh.student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		boolean quit = false;
		int numberSelection = 0;

		while (!quit) {
			menuIntroduction();
			System.out.println("메뉴 번호를 선택해주세요.");
			numberSelection = sc.nextInt();
			sc.nextLine();
			if (numberSelection < 1 || numberSelection > 7) {
				System.out.println("1부터 7까지의 숫자를 입력");
			} else {
				switch (numberSelection) {
				case 1:
					selectAllStudent();
					break;
				case 2:
					Students students = inputStudent();
					insertStudent(students);
					break;
				case 3:
					Students stu = searchStudent();
					if (stu != null) {
						System.out.println(stu);
					}
					break;
				case 4:
					updateStudent();
					break;
				case 5:
					deleteStudent();
					break;
				case 6:
					loginStudent();
					break;
				case 7:
					System.out.println("종료하겠습니다.");
					quit = true;
					break;
				}
			}
		}
	}

	public static void loginStudent() {
		selectAllStudent();
		System.out.println("학생 아이디 입력");
		String stuid = sc.nextLine();
		System.out.println("학생 비밀번호 입력");
		String stupw = sc.nextLine();
		try (Connection con = SQLStudentConnect.makeConnection()) {
			String sql = "SELECT * FROM studentTBL WHERE SD_ID = ? AND SD_PASSWD = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, stuid);
			pstmt.setString(2, stupw);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(stuid + " 로그인 성공");
			} else {
				System.out.println("로그인 실패");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void deleteStudent() {
		Students students = searchStudent();
		try (Connection con = SQLStudentConnect.makeConnection()) {
			String sql = "DELETE FROM studentTBL WHERE no = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, students.getNo());
			int i = pstmt.executeUpdate();
			System.out.println((i == 1) ? "학생 정보를 삭제하였습니다." : "학생 정보 삭제 실패하였습니다.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateStudent() {
		Students students = searchStudent();
		System.out.print("update name (" + students.getSd_name() + ") >> ");
		String name = sc.nextLine();
		System.out.print("update  SD_PASSWD (" + students.getSd_passwd() + ") >> ");
		String passwd = sc.nextLine();
		System.out.print("update num (" + students.getS_num() + ") >> ");
		String s_num = sc.nextLine();
		System.out.print("update SD_ADDRESS (" + students.getSd_address() + ") >> ");
		String addr = sc.nextLine();

		try (Connection con = SQLStudentConnect.makeConnection()) {
			String sql = "UPDATE studentTBL SET SD_NAME = ?, SD_PASSWD = ?, S_NUM = ?, SD_ADDRESS = ? WHERE no = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, passwd);
			pstmt.setString(3, s_num);
			pstmt.setString(4, addr);
			pstmt.setInt(5, students.getNo());
			int i = pstmt.executeUpdate();
			System.out.println((i == 1) ? "학생 정보를 수정하였습니다." : "학생 정보 수정 실패하였습니다.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Students searchStudent() {
		selectAllStudent();
		System.out.print("찾고자 하는 학생 번호 입력 >>");
		int no = sc.nextInt();
		sc.nextLine();
		Students students = null;
		try (Connection con = SQLStudentConnect.makeConnection()) {
			String sql = "SELECT * FROM studentTBL WHERE no=?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int _no = rs.getInt("no");
				String sd_name = rs.getString("sd_name");
				String sd_id = rs.getString("sd_id");
				String sd_passwd = rs.getString("sd_passwd");
				String s_num = rs.getString("s_num");
				String sd_address = rs.getString("sd_address");
				students = new Students(_no, sd_name, sd_id, sd_passwd, s_num, sd_address);
			} else {
				System.out.println("찾지 못하였습니다.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;

	}

	public static Students inputStudent() {
		System.out.print("input sd_name >>");
		String sd_name = sc.nextLine();
		System.out.print("input sd_id >>");
		String sd_id = sc.nextLine();
		System.out.print("input sd_passwd >>");
		String sd_passwd = sc.nextLine();
		System.out.print("input s_num >>");
		String s_num = sc.nextLine();
		System.out.print("input sd_address >>");
		String sd_address = sc.nextLine();

		Students students = new Students(0, sd_name, sd_id, sd_passwd, s_num, sd_address);
		return students;
	}

	public static void insertStudent(Students students) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = SQLStudentConnect.makeConnection();
			String sql = "INSERT INTO studentTBL(no, SD_NAME, SD_ID, SD_PASSWD, S_NUM, SD_ADDRESS) VALUES (studentTBL_seq.nextval, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, students.getSd_name());
			pstmt.setString(2, students.getSd_name());
			pstmt.setString(3, students.getSd_passwd());
			pstmt.setString(4, students.getS_num());
			pstmt.setString(5, students.getSd_address());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(students.getNo() + "학생 입력 성공하였습니다.");
			} else {
				System.out.println(students.getNo() + "학생 입력 실패하였습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("pstmt,con close error!!");
				e.printStackTrace();
			}
		}
	}

	// 학생 정보 보기
	public static void selectAllStudent() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = SQLStudentConnect.makeConnection();
			String sql = "SELECT * FROM studentTBL order by no asc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String sd_num = rs.getString("sd_num");
				String sd_name = rs.getString("sd_name");
				String sd_id = rs.getString("sd_id");
				String sd_passwd = rs.getString("sd_passwd");
				String s_num = rs.getString("s_num");
				String sd_birthday = rs.getString("sd_birthday");
				String sd_phone = rs.getString("sd_phone");
				String sd_address = rs.getString("sd_address");

				Students students = new Students(no, sd_num, sd_name, sd_id, sd_passwd, s_num, sd_birthday, sd_phone,
						sd_address);
				System.out.println(students.toString());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("rs,pstmt,con close error!!");
				e.printStackTrace();
			}
		}
	}

	// 책 정보 메뉴
	public static void menuIntroduction() {
		System.out.println("**");
		System.out.println("\tStudent Info Menu");
		System.out.println("**");
		System.out.println("1. 학생정보보기");
		System.out.println("2. 학생정보입력하기");
		System.out.println("3. 학생정보조회하기");
		System.out.println("4. 학생정보수정하기");
		System.out.println("5. 학생정보삭제하기");
		System.out.println("6. 로그인");
		System.out.println("7. 종료하기");
		System.out.println("**");

	}
}
