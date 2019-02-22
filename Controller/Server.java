package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException{
		ServerSocket serverSocket=null;
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("192.168.0.175", 9000));
			while(true) {
				Socket socket1=serverSocket.accept();
				Player player1=new Player(socket1, '1');
				
				Socket socket2=serverSocket.accept();
				Player player2=new Player(socket2, '2');
				
				player1.other=player2;
				player2.other=player1;
				
				player1.start();
				player2.start();		
			}

		} catch (IOException e) {
			
		}finally {
			if(!serverSocket.isClosed()) {
				serverSocket.close();
			}
		}
	}
}

class Player extends Thread {
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	char pm;
	Player other;
	

	public Player( Socket socket, char pm) throws IOException {
		super();
		this.socket = socket;
		this.pm = pm;
		
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		br = new BufferedReader(isr);

		OutputStream os = socket.getOutputStream();
		pw = new PrintWriter(os, true);

		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {}
		pw.println("PRINT ȯ���մϴ�.");
		pw.println("PRINT ��븦 ��ٸ��ϴ�.");
		
	}

	@Override
	public void run() {
		try {
			pw.println("PRINT ������ �����Ͽ����ϴ�.");
			pw.println("START " + pm);
			
			if (pm == '1') {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {}
				pw.println("PRINT ������ �����Դϴ�.");
				other.pw.println("PRINT ���� �����Դϴ�.");		
			}
			
			while(true) {
			String command = br.readLine();
				
				if(command.startsWith("PLAY")) {
					pw.println("CLICK "+pm);
					if(pm!='1') {
						pw.println("CLIC2 ");
					}
					other.pw.println("PRINT �����ϼ���.");
				}
				
				if(command.startsWith("PLAY2")) {
					int image1Speed=(int) ((Math.random() * (30000 - 25000 + 1)) + 25000);
					int image2Speed=(int) ((Math.random() * (30000 - 25000 + 1)) + 25000);
					int image3Speed=(int) ((Math.random() * (30000 - 25000 + 1)) + 25000);
					pw.println("SGAME"+image1Speed+image2Speed+image3Speed);
					other.pw.println("SGAME"+image1Speed+image2Speed+image3Speed);
				}
				
				if(command.startsWith("SCORE")) {
					String str=command.substring(5);
					other.pw.println("OTHER "+str);
				}
				if(command.startsWith("RESUT")) {
					pw.println("PRINT �¸��Ͽ����ϴ�\n");
					other.pw.println("PRINT �й��Ͽ����ϴ�\n");
				}
				if(command.startsWith("RESU2")) {
					pw.println("PRINT �й��Ͽ����ϴ�\n");
					other.pw.println("PRINT �¸��Ͽ����ϴ�\n");
				}
				if(command.startsWith("RESTR")) {
					
					if (pm == '1') {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {}
						pw.println("PRINT ������ �����Դϴ�.");
						other.pw.println("PRINT ���� �����Դϴ�.");
					}
				}
				if(command.startsWith("NOGAM")) {
					pw.println("PRINT ���º��Դϴ�\n");
					other.pw.println("PRINT ���º��Դϴ�\n");	
				}
			}
			
			
		} catch (Exception e) {
			
		}finally {
			if(!socket.isClosed()) {
				try {
					socket.close();
				} catch (Exception e) {
					
				}
			}
		}
	}
}
