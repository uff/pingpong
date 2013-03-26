package sd.pingpong.master;
/* GameServer.java */

import java.net.*;
import javax.swing.JFrame;

class GameServer {
   public static void main(String argv[]) throws Exception
   {
	   JFrame win = new JFrame("Bouncing Ball With Android");

		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		BBPanel conteudo = new BBPanel();
		
		win.setContentPane(conteudo);

		win.pack();

		win.setVisible(true);
		/*
		ServerSocket welcomeSocket = new ServerSocket(6900);
		while(true) {
			Socket connectionSocket = welcomeSocket.accept();
			ConnectionThread s = new ConnectionThread(connectionSocket);
			new Thread(s).start();
			
			System.out.println("z:" + s.clientSentence);
			
			if (s.clientSentence != null){
				conteudo.sendAceleration(Double.parseDouble(s.clientSentence));
			}
		}
		*/
   }
}