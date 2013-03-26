package sd.pingpong.master;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {

		BouncingBall bb;
		Racket r1;
		double aceleration;
		
		ServerSocket welcomeSocket;
		ConnectionThread s;
	
		private int mInterval = 50; // Milliseconds between updates.
		private Timer mTimer; // Ball moves every time
		
		public GamePanel() throws IOException{
			this.setBackground(Color.white);
			this.setForeground(Color.black);
			this.setPreferredSize(new Dimension(400,200));
			mTimer = new Timer(50,this); // Calls actionPerformed every 50 ms.

			bb = new BouncingBall();
			r1 = new Racket(400,100);
			
			welcomeSocket = new ServerSocket(6900);
				
		}
		
		public void startGame(boolean turnOn) { 


				if (turnOn) {

					mTimer.start(); // start animation by starting the timer.

				} else {

					mTimer.stop(); // stop timer

				}

		}
		
		public void setBallColor(Color ballColor) {
			bb.setColor(ballColor);
		}
		
		public void paint(Graphics g){
			super.paint(g);
			
			//Bola
			g.setColor(bb.mBallColor);
			g.fillOval(bb.mX - bb.mRadius, bb.mY - bb.mRadius, bb.mDiameter, bb.mDiameter);
			
			//Raquete
			g.fillRect(r1.mX, r1.mY, r1.width, r1.height);
			
			//Toolkit.getDefaultToolkit().sync();
			g.dispose();
		}
		
		public void actionPerformed(ActionEvent e){
			
			try{
				System.out.println("Inicio do actionPerformed>>try");
				
				Socket connectionSocket;
				do{
						connectionSocket = welcomeSocket.accept();
				}while(connectionSocket == null);
				
				System.out.println("conexão aceita");
				s = new ConnectionThread(connectionSocket,r1);
				
				System.out.println("thread iniciada");
				new Thread(s).start();
				
				System.out.println("fora da thread depois do start");
				
				/* a parte comentada abaixo agora é resolvida dentro do ConnectionThread
				if ( s.getLastLine() != null ){
					r1.aY = Double.parseDouble(s.getLastLine())*10;
					System.out.println("dentro do if: " + r1.aY);
				}
				else{
					r1.aY = 0;
				}
				*/
			} catch (IOException e1) {
				//r1.aY = 0;
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally{
				
			}
			System.out.println("Aceleração: " + r1.aY);
			r1.recalcularLocal();
			bb.resolveColision(r1.mX, r1.mY, r1.width, r1.height);
			bb.recalcularLocal();
			repaint();
			//end method paintComponent

		}
		
		public void setAceleration(double aceleration){
			r1.aY = aceleration*10;
		}

}
