package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {

	public static Connection getConnection() {
		Connection con = null;
		try {
			// 1.MySql database class �ε��Ѵ�
			Class.forName("com.mysql.jdbc.Driver");
			// 2.�ּ�,���̵�,��й�ȣ�� ���ؼ� ���ӿ�û�Ѵ�.
			con = DriverManager.getConnection("jdbc:mysql://localhost/gamedb", "root", "123456");
		} catch (Exception e) {
			GameController.callAlert("�����ͺ��̽� ���� ����:���� �����Ͽ����ϴ�.");
			return null;
		}
		return con;
	}

}
