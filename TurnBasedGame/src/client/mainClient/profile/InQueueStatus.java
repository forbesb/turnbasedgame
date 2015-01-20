package client.mainClient.profile;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class InQueueStatus extends JLabel implements ActionListener{
	private ProfilePanel panel;
	private GridBagConstraints gbc;
	private boolean inQueue;
	private JLabel text;
	private Timer t;
	private int entry = 0;
	public InQueueStatus(ProfilePanel panel){
		t = new Timer(500, this);
		t.start();
		gbc = new GridBagConstraints();
		this.panel = panel;
		this.setLayout(new GridBagLayout());
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.text = new JLabel("Not in Queue");
		text.setForeground(new Color(0,0,0));
		text.setFont(new Font("Calibri",Font.PLAIN,100));
		this.add(text);
		
	}
	public boolean isInQueue(){
		return inQueue;
	}
	public void setInQueue(boolean b){
		if (!b){
			entry = 0;
			this.text.setText("Not in Queue");
		}else{
			this.text.setText("In Queue");
		}
		this.inQueue = b;
	}
	
	
	
	public ProfilePanel getPanel() {
		return panel;
	}
	public void setPanel(ProfilePanel panel) {
		this.panel = panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (inQueue){
			if (entry == 0){
				this.text.setText("In Queue   ");
				entry ++;
			}
			else if (entry == 1){
				this.text.setText("In Queue.  ");
				entry ++;
			}
			else if (entry == 2){
				this.text.setText("In Queue.. ");
				entry ++;
			}
			else{
				this.text.setText("In Queue...");
				entry = 0;
			}
			repaint();
		}
		
	}
}
