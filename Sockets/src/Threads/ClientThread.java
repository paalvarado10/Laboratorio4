package Threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThread extends Thread{
	private Socket client;
	private ObjectInputStream entry;
	private ObjectOutputStream exit;
	
	public ClientThread(String IPServidor) {
		try {
			client = new Socket(InetAddress.getByName(IPServidor), 8080);
			getStreams();
			sendData("start conection");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		do {
			try {
				processConection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				
				try {
					client.close();
					entry.close();
					exit.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
			}
		} while(true);
	}
	
	private void processConection() throws ClassNotFoundException ,IOException{
		
		String msg;
		msg = (String) entry.readObject();
		System.out.println(msg);
	}
	
	public void sendData(String msg) throws IOException{
		exit.writeObject(msg);
	}

	public void getStreams() throws IOException{
		exit = new ObjectOutputStream(client.getOutputStream());
		exit.flush();
		entry = new ObjectInputStream(client.getInputStream());
	}
	
	public void closeConnection() {
		try {
			sendData("close connection");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Conection Closed");
	
	}
}
