package TCP;

import Threads.ClientThread;

public class Client {
	static ClientThread ct;
	static boolean end = false;

	public static void main(String[] args) {
//		ct = new ClientThread("IP DE LA MAQUINA EN LA QUE ESTE EL SERVIDOR");
		ct = new ClientThread("192.168.56.1");
		ct.start();
		do {
			
		}while(!end);
	}

}
