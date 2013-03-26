package sd.pingpong.master;
import java.io.*;
import java.net.*;

public class ConnectionThread implements Runnable{
	
	Socket s;
    String clientSentence;
    String capitalizedSentence;
    String lastLine;
    Racket racket;
    BufferedReader inFromClient = null;
	DataOutputStream outToClient = null;
	
	ConnectionThread(Socket s, Racket racket){
		this.s = s;
		this.lastLine = null;
		this.racket = racket;
	}
	
	public String getLastLine(){
		return this.lastLine;
	}
	
	public void run(){
		
		try {
			inFromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
			outToClient = new DataOutputStream(s.getOutputStream());
			//while((clientSentence = inFromClient.readLine())!=null){
			if ((clientSentence = inFromClient.readLine())!=null){
				System.out.println(clientSentence);
				lastLine = clientSentence;
				racket.setAceleration(lastLine);
				System.out.println(lastLine);
			}
			else{
				racket.setAceleration("0");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				//inFromClient.close();
				//outToClient.close();
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.currentThread().interrupt();
		}
		return ;
	}
	
	public void getAceleration(){
		try {
			inFromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
			outToClient = new DataOutputStream(s.getOutputStream());
			//while((clientSentence = inFromClient.readLine())!=null){
			if ((clientSentence = inFromClient.readLine())!=null){
				System.out.println(clientSentence);
				lastLine = clientSentence;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				//inFromClient.close();
				//outToClient.close();
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.currentThread().interrupt();
		}
		return ;
	}
		
}
