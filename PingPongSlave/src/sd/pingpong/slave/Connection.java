package sd.pingpong.slave;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.http.conn.ConnectTimeoutException;

public class Connection {
	private Socket connSocket = null;
	private DataOutputStream out = null;
	private BufferedReader in = null;
	public int errorcounter = 0;
	
		public Connection(String ip, int port) throws UnknownHostException,SocketTimeoutException,IOException{
			SocketAddress adressSocket = new InetSocketAddress(ip, port);
			Socket connSocket = new Socket();
			connSocket.connect(adressSocket, 5000);
			out = new DataOutputStream(connSocket.getOutputStream());
		    in = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
		}
		
		protected void sendData(String data) throws IOException{
			System.out.println(data);
			out.writeBytes(data + "\n");
			out.flush();
		}
		
		protected void closeConnection(){
			try{
				if(in!=null) in.close();
				if(out!=null){
					out.close();
				}
				if(connSocket!=null) connSocket.close();
			}catch(IOException e){
				System.err.println("Erro fechando I/O ou socket");
			}
		}
		
}

