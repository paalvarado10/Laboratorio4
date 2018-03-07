package TCP;

import java.io.IOException;
import java.util.Scanner;

import Threads.ClientThread;

public class Client {
	static ClientThread ct;
	static boolean end = false;
	static Scanner s = new Scanner(System.in);
	public static void main(String[] args) {
//		ct = new ClientThread("IP DE LA MAQUINA EN LA QUE ESTE EL SERVIDOR");
		ct = new ClientThread("192.168.56.1");
		ct.start();
		do {
			System.out.println("Choose an option");
			System.out.println("1 : Send message");
			System.out.println("2 : Get message");
			System.out.println("3 : Close Connection");
			
			
			int opt = s.nextInt();
			
			if(opt == 1) {
				try {
					ct.sendData("Message sent from client");
				} catch (IOException e) {
					e.printStackTrace();				
					}
			}
			if(opt == 3) {
				end = !end;
			}
		}while(!end);
		ct.closeConnection();
	}

}
