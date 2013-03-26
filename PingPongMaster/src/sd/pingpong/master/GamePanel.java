package sd.pingpong.master;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{

		BouncingBall bb;
		Racket r1;
		double aceleration;
	
		private int mInterval = 50; // Milliseconds between updates.
		private Timer mTimer; // Ball moves every time
		
		public GamePanel(){
			this.setBackground(Color.white);
			this.setForeground(Color.black);
			this.setPreferredSize(new Dimension(400,200));
			mTimer = new Timer(50,this); // Calls actionPerformed every 50 ms.

			bb = new BouncingBall();
			r1 = new Racket(400,100);
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
			
			r1.recalcularLocal();
			bb.resolveColision(r1.mX, r1.mY, r1.width, r1.height);
			bb.recalcularLocal();
			repaint();
				 
			//end method paintComponent

		}
		
		public void setAceleration(double aceleration){
			r1.aY = aceleration;
		}

}
