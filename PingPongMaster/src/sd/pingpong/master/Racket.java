package sd.pingpong.master;

import java.awt.Color;

public class Racket {
	protected int mX = 400 - 6; // Initial position of racket
	protected int mY = 100;
	protected int width = 6;
	protected int height = 40;
	protected double aY = 0;
	protected Color mRacketColor; // Color of the racket
	
	public Racket(int inicialX, int inicialY){
		mRacketColor = Color.black;
		mX = inicialX - width;
		mY = inicialY - (height/2);
	}
	
	public void setAceleration(String a){
		double aD = Double.parseDouble(a);
		this.aY = aD*10;
	}
	
	public void recalcularLocal() {

		//--\- Get the the current size of the Panel.

		int w = 400; //getWidth(); // get width and height of panel

		int h = 200; //getHeight();

		mY -= aY;

		if (mY < 0) { // if we're at top

			mY = 0;

			aY = - aY;

		} else if (mY > h - height) { // if we're at bottom

			mY = h - height;

			aY = - aY;

		}

		// Calling repaint goes thru a series of calls, with possible

		// optimizations: repaint \-> update \-> paint \-> paintComponent.

		// Typically animation is done by changing instance variables

		// in a timer's actionListener, then calling repaint().

		//janela.repaint();

	}//end actionPerformed

	public void setColor(Color color) {

		mRacketColor = color; // Should a copy be made?

		///this.repaint(); // Display it.

	}//end setBallColor


}
