package client.mainClient.shop;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import client.mainClient.backBone.Client;

@SuppressWarnings("serial")
public class ConfirmPanel extends JPanel implements ActionListener{
	private GridBagConstraints gbc = new GridBagConstraints();
	private JButton purchase = new JButton("Purchase: ");
	private Timer t;
	private JLabel total = new JLabel("0000");
	private JLabel balance;
	public ConfirmPanel(){
		t = new Timer(20,this);
		t.start();
		purchase.addActionListener(this);
		purchase.setBackground(new Color(255,255,255));
		this.setBackground(new Color(0,0,0));
		this.setLayout(new GridBagLayout());
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipady = 20;
		gbc.ipadx = 40;
		this.add(purchase,gbc);
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.gridy = 1;
		gbc.weighty = 0.01;
		gbc.anchor = GridBagConstraints.NORTH;
		total.setForeground(new Color(255,255,255));
		total.setFont(new Font("Calibri",Font.BOLD,20));
		this.add(total, gbc);
		gbc.weighty = 0.01;
		gbc.gridy = 2;
		this.add(balance = new JLabel("Balance: $"+Client.user.getMoney()+".00"), gbc);
		balance.setForeground(new Color(255,255,255));
		balance.setFont(new Font("Calibri",Font.BOLD,20));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == t)
			total.setText("Total: $"+ItemPanel.total+".00");
		
		if (e.getSource() == purchase){
			if (Client.user.getMoney()-ItemPanel.total >= 0){
				balance.setForeground(new Color(255,255,255));
				String [] boughtItems = new String[ItemPanel.boughtItems.size()];
				for (int i = 0; i < ItemPanel.boughtItems.size();i++){
					boughtItems[i] = ItemPanel.boughtItems.get(i)+"";
				}
				System.out.println(Arrays.toString(Client.user.getItems()));
				Client.user.setItems(concat(boughtItems,Client.user.getItems()));
				System.out.println(Arrays.toString(Client.user.getItems()));
				Client.user.setMoney(Client.user.getMoney()-ItemPanel.total);
				balance.setText("Balance: $"+Client.user.getMoney()+".00");
				ItemPanel.reset();
			}else{
				balance.setForeground(new Color(255,0,0));
			}
		}
	}
	public String[] concat(String[] a, String[] b) {
		int aLen = a.length;
		int bLen = b.length;
		String[] c= new String[aLen+bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}
}