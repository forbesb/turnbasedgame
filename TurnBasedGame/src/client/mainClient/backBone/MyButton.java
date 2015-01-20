package client.mainClient.backBone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;





import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MyButton extends JPanel implements MouseListener{
	private String names [];
	private Color [] colors;
	private JLabel label;
	private GridBagConstraints gbc = new GridBagConstraints();
	private int controller;
	private Tabs tab;
	public MyButton(int x){
		this.setLayout(new GridBagLayout());
		colors = new Color[]{new Color (0,0,0),new Color(255,0,0),new Color(160,32,240),new Color(0,0,255),new Color(32,178,170), new Color(255,255,204), new Color (255,0,0)};
		setNames(new String[]{"Instructions","Profile","Shop","TeamSelection","Armory","Home","Play"});
		label = new JLabel(getNames()[x]);
		label.setFont(new Font("comic sans",5,20));
		label.setForeground(new Color(255,255,255));
		setController(x);
		
		if (x != 6){
			if (x == 5){
				label.setForeground(new Color(0,0,0));
			}
			gbc.anchor = GridBagConstraints.CENTER;
			this.add(label, gbc);
		}
		this.setOpaque(true);
		addMouseListener(this);
	}
	
	public MyButton(int x, Tabs tab){
		this.tab = tab;
		this.setLayout(new GridBagLayout());
		colors = new Color[]{new Color (0,0,0),new Color(255,0,0),new Color(160,32,240),new Color(0,0,255),new Color(32,178,170), new Color(255,255,204), new Color (255,0,0)};
		setNames(new String[]{"Instructions","Profile","Shop","TeamSelection","Armory","Home","Play"});
		label = new JLabel(getNames()[x]);
		label.setFont(new Font("comic sans",5,20));
		label.setForeground(new Color(255,255,255));
		setController(x);
		
		if (x != 6){
			if (x == 5){
				label.setForeground(new Color(0,0,0));
			}
			gbc.anchor = GridBagConstraints.CENTER;
			this.add(label, gbc);
		}
		this.setOpaque(true);
		addMouseListener(this);
	}
	public String toString(){
		return label.getText();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (getNames()[getController()].equals("Play") == false || getNames()[getController()].equals("Home") == false){
			g.setColor(colors[getController()]);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		else{
			this.setSize(200,100);
			g.setColor(colors[getController()]);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (getNames()[getController()].equals("Play") == false){
			tab.getPanel().getCenter().setShowPanel(names[controller]);;
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String names[]) {
		this.names = names;
	}

	public int getController() {
		return controller;
	}

	public void setController(int controller) {
		this.controller = controller;
	}
}