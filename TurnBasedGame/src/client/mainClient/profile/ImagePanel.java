package client.mainClient.profile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ImagePanel extends JPanel implements ActionListener{
	private JButton buttons [];
	private Image images [];
	private String [] imageNames;
	private int imageChoice = 1;
	private JPanel image;
	private ProfilePanel panel;
	public ImagePanel(ProfilePanel panel){
		this.panel = panel;
		images = new Image[5];
		imageNames = new String[]{"image1.jpg","TopBar.jpg","image3.jpg","image4.jpg","image5.jpg"};
		for (int i = 0; i < 5; i ++){
			images[i] = new ImageIcon(imageNames[i]).getImage();
		}
		this.setPreferredSize(new Dimension(300,200));
		this.setLayout(new BorderLayout());
		
		image = new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(images[imageChoice],0,0,this.getWidth(),this.getHeight(),null);
			}
		};
		image.setLayout(null);
		image.setSize(1174,300);
		this.add(image, BorderLayout.CENTER);
		image.repaint();
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,5));
		buttons = new JButton [5];
		for (int i = 0; i < 5; i ++){
			JButton button = new JButton(i+1+"");
			button.setForeground(new Color(255,0,0));
			button.setBackground(new Color(0,0,0));
			button.addActionListener(this);
			button.setFont(new Font("Arial", Font.PLAIN, 8));
			buttons[i] = button;
			buttonPanel.add(button);
		}
		this.add(buttonPanel,BorderLayout.SOUTH);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for (JButton i : buttons){
			if (i == e.getSource()){
				imageChoice = Integer.parseInt(i.getText())-1;
				image.repaint();
				panel.getProfile().getCenter().getMain().getFrame().setImgBackground(images[imageChoice]);
			}
		}
	}
}