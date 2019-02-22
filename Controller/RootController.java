package Controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;


import javafx.stage.Stage;


public class RootController implements Initializable{
	public Stage primaryStage;
	@FXML private Button mainBtnJoin;
	@FXML private Button mainBtnLogin;
	@FXML private Button mainBtnExit;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//1.ȸ������ ��ư�� �������� �߻��ϴ� �̺�Ʈ�Լ�
		mainBtnJoin.setOnAction(e-> handleBtnJoinAction());
		//2.�α��� ��ư�� �������� �߻��ϴ� �̺�Ʈ�Լ�
		mainBtnLogin.setOnAction(e-> handleBtnLoginAction());
		
		mainBtnExit.setOnAction(e-> primaryStage.close());	
	}

	//1.ȸ������ ��ư�� �������� �߻��ϴ� �̺�Ʈ�Լ�
	private void handleBtnJoinAction() {
		try {
			Stage joinStage=new Stage();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("../View/join.fxml"));
			Parent root=loader.load();
			JoinController joinController=loader.getController();
			joinController.joinStage=joinStage;
			
			Scene scene=new Scene(root);
			joinStage.setScene(scene);
			joinStage.setTitle("ȸ������");
			//primaryStage.close();
			joinStage.show();
		} catch (Exception e) {
			callAlert("ȭ�� ��ȯ ����: ȭ�� ��ȯ�� ������ �ֽ��ϴ�.");
		}
	}
	
	//2.�α��� ��ư�� �������� �߻��ϴ� �̺�Ʈ�Լ�
	private void handleBtnLoginAction() {
		try {
			Stage loginStage=new Stage();
			FXMLLoader loader=new FXMLLoader(getClass().getResource("../View/login.fxml"));
			Parent root=loader.load();
			LoginController loginController=loader.getController();
			loginController.loginStage=loginStage;
			
			Scene scene=new Scene(root);
			loginStage.setScene(scene);
			loginStage.setTitle("�α���");
			primaryStage.close();
			loginStage.show();
		} catch (Exception e) {
			callAlert("ȭ�� ��ȯ ����: ȭ�� ��ȯ�� ������ �ֽ��ϴ�.");
		}
	}

	//��Ÿ �˸�â "��������":���� ����� �Է����ּ���
	private void callAlert(String contentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("�˸�â");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring(contentText.lastIndexOf(":")+1));
		alert.showAndWait();
	}
}
