package TCP;

import Threads.ServerThread;

public class Server {
	static ServerThread sT;
	static boolean end = false;
	
	public static void main(String[] args) {
		sT = new ServerThread();
		sT.start();
		do {
			
		}while(!end);
	}

}
