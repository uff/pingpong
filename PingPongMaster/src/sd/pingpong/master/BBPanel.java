package sd.pingpong.master;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


class BBPanel extends JPanel {

	GamePanel jogo; // The bouncing ball panel

	//========================================================== constructor

	/*\* Creates a panel with the controls and bouncing ball display. \*/

	BBPanel() throws IOException {

		//-\- Create components

		jogo = new GamePanel();

		JButton startButton = new JButton("Start");

		JButton redButton = new JButton("Red");

		//-\- Layout inner panel with two buttons horizontally in BoxLayout

		JPanel buttonPanel = new JPanel();

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

		buttonPanel.add(startButton);

		buttonPanel.add(redButton);

		//-\- Layout outer panel with button panel above bouncing ball

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(buttonPanel);

		this.add(jogo);

		//-\- Add Listeners

		startButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jogo.startGame(true);

			}

		});

		redButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				jogo.setBallColor(Color.red);
 
			}

		});

	}//end constructor
	
	//Deprecated Method
	public void sendAceleration(double aceleration){
		jogo.setAceleration(aceleration);
	}

}