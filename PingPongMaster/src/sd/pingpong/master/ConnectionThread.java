package sd.pingpong.master;
import java.io.*;
import java.net.*;

public class ConnectionThread implements Runnable{
	
	Socket s;
    String clientSentence;
    String capitalizedSentence;
	
	ConnectionThread(Socket s){
		this.s = s;
	}
	
	public void run(){
		
		BufferedReader inFromClient = null;
		DataOutputStream outToClient = null;
		try {
			inFromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
			outToClient = new DataOutputStream(s.getOutputStream());
			while((clientSentence = inFromClient.readLine())!=null){
				System.out.println(clientSentence);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inFromClient.close();
				outToClient.close();
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.currentThread().interrupt();
		}
		return;
	}
		
}
