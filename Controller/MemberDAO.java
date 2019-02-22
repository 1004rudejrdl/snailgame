package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Member;



public class MemberDAO {
			
	public static ArrayList<Member> memberArrayList = new ArrayList<>();
	
	public static int insertMemberData(Member member) {
		StringBuffer insertMember=new StringBuffer();
		
		insertMember.append("insert into membertbl ");
		insertMember.append("(nickname,password,score) ");
		insertMember.append("values ");
		insertMember.append("(?,?,?) ");
		
		Connection con = null;
		PreparedStatement psmt = null;
		int count = 0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(insertMember.toString());
			
			psmt.setString(1, member.getNickname());
			psmt.setString(2, member.getPassword());
			psmt.setInt(3, member.getScore());
			
			count = psmt.executeUpdate();
			if (count == 0) {
				GameController.callAlert("���� ��������:�����Ͽ����ϴ�.");
				return count;
			}

		} catch (SQLException e) {
			GameController.callAlert("���� ����:���� �����Ͽ����ϴ�.");
		} finally {
			
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				GameController.callAlert("�ݱ� ����:�ݱ� �����Ͽ����ϴ�.");
			}
		}
		return count;	
	}
	
	public static int getMemberData(String str) {
		
		String selectMember = "select count(*) from membertbl where nickname = ? ";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int count=0;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectMember);
			psmt.setString(1,str );
			rs = psmt.executeQuery();
			if (rs == null) {
				GameController.callAlert("select ��������:�����Ͽ����ϴ�.");
				return count;
			}
			while (rs.next()) {
				count=rs.getInt(1);
			}

		} catch (SQLException e) {
			GameController.callAlert("���� ����:���ƾƾ�");
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				GameController.callAlert("�ݱ� ����:�ݱ� �����Ͽ����ϴ�.");
			}
		}
		return count;
	}
	
	public static int Check(String nick,String pass) {
		StringBuffer findMember=new StringBuffer();
		findMember.append("select * from membertbl where nickname = ");
		findMember.append("? ");
		findMember.append("and password = ");
		findMember.append("? ");
		
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs=null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(findMember.toString());
			psmt.setString(1, nick);
			psmt.setString(2, pass);
			rs = psmt.executeQuery();
			String str=null;
			while(rs.next()) {
				str=rs.getString(1);
			}
			if (str == null) {
				
				return -1;
			}

		} catch (SQLException e) {
			GameController.callAlert("üũ�Լ�����:�����Ͽ����ϴ�.");
		}
		return 0;
	}
	
	public static int getScoreData() throws Exception {
		int score=0;
		String selectMember = "select score from membertbl where nickname = ? ";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnection();
			psmt = con.prepareStatement(selectMember);
			psmt.setString(1, LoginController.nickname);
			rs = psmt.executeQuery();
			if (rs == null) {
				GameController.callAlert("select ��������:�����Ͽ����ϴ�.");
				return 0;
			}
			while (rs.next()) {
				score=rs.getInt(1);
			}

		} catch (SQLException e) {
			GameController.callAlert("���� ����:���ƾƾ�");
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				GameController.callAlert("�ݱ� ����:�ݱ� �����Ͽ����ϴ�.");
			}
		}
		return score;
	}
}
