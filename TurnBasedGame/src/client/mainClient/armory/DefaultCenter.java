package client.mainClient.armory;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class DefaultCenter extends JPanel {
	private JLabel message;
	private GridBagConstraints gbc;
	public DefaultCenter(){
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0,0,0));
		gbc = new GridBagConstraints();
		message = new JLabel("Press Any Character to View Detailed Stats");
		message.setFont(new Font("Calibri",Font.BOLD,40));
		message.setForeground(new Color(255,255,255));
		message.setOpaque(false);
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(message,gbc);
	}
}
