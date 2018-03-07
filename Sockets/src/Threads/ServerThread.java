package Threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {

	private ServerSocket server;
	private Socket conection;
	static boolean active = true;
	private ObjectInputStream entry;
	private ObjectOutputStream exit;
	
	public ServerThread () {
		try {
			server = new ServerSocket(8080, 10);
			waitingConections();
			getStreams();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public void run() {
		do {
			try {
				processConection();
			} catch (ClassNotFoundException e)  {
				e.printStackTrace(); 
			} catch  (IOException e) {
				e.printStackTrace();
			}
		}while(active);
		
	}
	private void processConection() throws ClassNotFoundException ,IOException{
		// TODO Auto-generated method stub
		String msg;
		msg = (String) entry.readObject();
		System.out.println("String entered: "+msg);
		if(msg.compareTo("start conection")==0) {
			System.out.println("conection established with "+conection.getInetAddress().getHostName());
			sendData("Server accepted your connection");
		}
		if (msg.equals("close connection")) {
			active = false;
			server.close();
			conection.close();
			entry.close();
			exit.close();
		}
	}
	private void sendData(String  msg) throws IOException{
		exit.writeObject(msg);
		
	}
	public void waitingConections() throws IOException{
		System.err.print("Waiting conection");
		conection = server.accept();
	}
	
	public void getStreams() throws IOException{
		exit = new ObjectOutputStream(conection.getOutputStream());
		exit.flush();
		entry = new ObjectInputStream(conection.getInputStream());
	}
}
