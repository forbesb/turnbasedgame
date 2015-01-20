package client.mainClient.empty;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class CentreLabel extends JPanel implements ActionListener{ //CenterLabel appearing and dissappearing
	private JLabel label;
	private Timer timer;
	private int x = 0;
	public CentreLabel(){
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		timer = new Timer(1000,this);
		timer.start();
		label = new JLabel("<<<WELCOME TO EMBLEM OF LEGENDS!!!>>>");
		label.setFont(new Font("Harrington",Font.BOLD,50));
		label.setForeground(new Color(255,255,255));
		this.add(label);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (x%2 == 0){
			label.setText("<<<WELCOME TO EMBLEM OF LEGENDS!!!>>>");
		}else{
			label.setText("");
		}
		x++;
		repaint();
	}
}