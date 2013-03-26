package sd.pingpong.master;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

class BouncingBall {

	//============================================== fields

	protected int mRadius = 10; // Radius of the ball

	protected int mDiameter = 2 * mRadius;

	private int mVelocityX = 3; // Initial x velocity (pixels/interval)

	private int mVelocityY = 5; // Initial y velocity

	protected int mX = mRadius; // Initial position of ball

	protected int mY = mRadius;

	protected Color mBallColor; // Color of the ball

	//private GamePanel janela;
	//========================================================== constructor

	/*\* Initializes the bouncing ball panel parameters. \*/

	public BouncingBall() {

		//this.setForeground(Color.black);

		//this.setPreferredSize(new Dimension(400,200));

		mBallColor = Color.black;
		
		//this.janela = caller;
		
		
	}//end constructor

	//========================================================= setAnimation

	/*\* Turns the animation on or off.

\*@param turnOn Specifies state of animation.

\*/



	//========================================================= setBallColor

	/*\* Sets color of the bouncing ball.

\*@param ballColor Specifies the ball color.

\*/

	public void setColor(Color color) {

		mBallColor = color; // Should a copy be made?

		///this.repaint(); // Display it.

	}//end setBallColor



	public void recalcularLocal() {

		//--\- Get the the current size of the Panel.

		int w = 400; //getWidth(); // get width and height of panel

		int h = 200; //getHeight();

		//--\- Compute new x, y coordinates of the ball

		mX += mVelocityX;

		if (mX < mRadius) { // if we're at left side

			mX = mRadius;

			mVelocityX = - mVelocityX;

		} else if (mX > w-mRadius) { // if we're at right side

			mX = w - mRadius;

			mVelocityX = - mVelocityX;

		}

		mY += mVelocityY;

		if (mY < mRadius) { // if we're at top

			mY = mRadius;

			mVelocityY = - mVelocityY;

		} else if (mY > h - mRadius) { // if we're at bottom

			mY = h - mRadius;

			mVelocityY = - mVelocityY;

		}

		// Calling repaint goes thru a series of calls, with possible

		// optimizations: repaint \-> update \-> paint \-> paintComponent.

		// Typically animation is done by changing instance variables

		// in a timer's actionListener, then calling repaint().

		//janela.repaint();

	}//end actionPerformed


	public void resolveColision(int obj1X, int obj1Y, int obj1W, int obj1H) {
		int nextX = mX + mVelocityX;
		int nextY = mY + mVelocityY;
				
		if ((obj1X >= nextX && obj1X <= nextX + this.mRadius) && (obj1Y >= nextY && obj1Y <= nextY + this.mRadius)) {
			this.mVelocityX = -this.mVelocityX;
	    } else if ((obj1X + obj1W >= nextX && obj1X + obj1W <= nextX + this.mRadius) && (obj1Y >= nextY && obj1Y <= nextY + this.mRadius)) {
	    	this.mVelocityX = -this.mVelocityX;
		} else if ((obj1X >= nextX && obj1X <= nextX + this.mRadius) && (obj1Y + obj1H >= nextY && obj1Y + obj1H <= nextY + this.mRadius)) {
			this.mVelocityX = -this.mVelocityX;
		} else if ((obj1X + obj1W >= nextX && obj1X + obj1W <= nextX + this.mRadius) && (obj1Y + obj1H >= nextY && obj1Y + obj1H <= nextY + this.mRadius)) {
			this.mVelocityX = -this.mVelocityX;
		}
	}
	//======================================================= paintComponent
/*
	public void paintComponent(Graphics g) {

		super.paintComponent(g); // paint background, border

		g.setColor(mBallColor);

		g.fillOval(mX - mRadius, mY - mRadius, mDiameter, mDiameter);
		
	}//end method paintComponent
*/
}//