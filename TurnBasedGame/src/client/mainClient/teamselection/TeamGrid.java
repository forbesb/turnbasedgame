package client.mainClient.teamselection;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;





import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.mainClient.backBone.Client;
import client.mainClient.teamselection.HeroIcon;
import client.mainClient.teamselection.HeroShelf;

@SuppressWarnings("serial")
public class TeamGrid extends JPanel{
	private TeamSelectionPanel panel;
	private Icon img = null;
	private JLabel [] labels = new JLabel [10];
	private Icon dummyIcon = new ImageIcon("DummyIcon.png");
	private String str="";
	
	public TeamGrid(TeamSelectionPanel t, HeroShelf [] shelves){
		this.panel = t;
		this.setBackground(new Color(255,255,255));
		this.setVisible(true);
		this.setLayout(new GridLayout(1,10));
		int j = 0;
		for (HeroIcon i : t.getTotalHeros()){
			if (Client.user.getTeam().contains(i.getName())){
				labels[j] = new JLabel(i.getImage());
				labels[j].setName(i.getName());
				this.add(this.add(labels[j]));
				j++;
			}
		}
		
	}
	public void reset(){
		for (HeroIcon i : panel.getTotalHeros()){
			boolean entered1 = false;
			JLabel temp = null;
			if (!Client.user.getTeam().contains(i.getName())){
				for (JLabel k : labels){
					if (i.getImage().equals(k.getIcon())){
						k.setIcon(dummyIcon);
						entered1 = true;
						temp = k;

					}
				}
			}else {
				boolean set = false;
				for (JLabel k : labels){
					if (k.getIcon().equals(img)){
						set = true;
						break;
					}
				}
				if (!set){
					for (JLabel k : labels){
						if (k.getIcon().equals(dummyIcon)){
							k.setName(str);
							k.setIcon(img);
							break;
						}
					}
					
				}
			}
			if (entered1){
				temp.setIcon(dummyIcon);
				break;
			}
			
		}
	}
	public void paintComponent(Graphics g){
		g.setColor(new Color(0,0,0));
		g.drawRect(1, 1, 1171, 148);
	}

	public Icon getImg() {
		return img;
	}
	public void setImg(Icon img) {
		this.img = img;
	}
	public JLabel [] getLabels(){
		return labels;
	}
	public void setStr(String str){
		this.str=str;
	}
	
}