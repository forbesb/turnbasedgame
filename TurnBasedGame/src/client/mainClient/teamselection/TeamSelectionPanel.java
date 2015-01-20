package client.mainClient.teamselection;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import server.beans.Team;
import client.mainClient.backBone.Client;
import client.mainClient.teamselection.HeroIcon;
import client.mainClient.teamselection.HeroShelf;

@SuppressWarnings("serial")
public class TeamSelectionPanel extends JPanel  implements MouseListener, ActionListener{
	private boolean heroEntered = false;
	private int xcoor = 0;
	private int ycoor = 0;
	private String [] data = new String[0];
	private String name= "";
	private TeamGrid team;
	private HeroIcon [] totalHeros = new HeroIcon [12];
	private JButton button;
	public static Hashtable<HeroIcon,Integer> ht = new Hashtable<HeroIcon,Integer>();
	private JLabel [] display = new JLabel [12];
	private HeroShelf [] displayPair = new HeroShelf [12];

	public TeamSelectionPanel(){
		this.addMouseListener(this);
		this.setBackground(new Color(255,255,255));
		this.setLayout(null);
		Character [] chars = {
			new Character("Annie", new String [] {"Null Sphere ","Ring Of Fire ","Arcane Shift ","Destroy "}),
			new Character("Brian", new String [] {"Brian's Diplomacy ","Brian's Logic ","Initiate RunningBack ","Death Mark "}),
			new Character("Caitlyn", new String [] {"LR Shot ","Poison Shot ","Stun Shot ","SMDBullet "}),
			new Character("Hongyu", new String [] {"Mocking Laugh ","Piercing Laugh ","Screeching Laugh ","Ultimate Laugh "}),
			new Character("Leesin", new String [] {"Iron Will ","Sonic Wave ","Tempest ","\"Weak\" kick "}),
			new Character("Leona", new String [] {"Zenith Blade ","Eclipse ","Shield of Daybreak ","Solar Flare "}),
			new Character("Lissandra", new String [] {"Blood Boil ","Hypothermia ","FrostBite ","Frozen Tomb "}),
			new Character("Sona", new String [] {"Maven's Shield ","Maven's Song ","Maven's Cheer ","Maven's Kiss "}),
			new Character("Soraka", new String [] {"Astral Blessing ","Armor Of Gods ","Hope ","Wish "}),
			new Character("Taric", new String [] {"Gem Heal ","Shatter ","Dazzle ","Radiance "}),
			new Character("Tryndamere", new String [] {"Slow ","Phys Shield ","Spell Shield ","Execute "}),
			new Character("Vladimir", new String [] {"Sigil of Silence ","Troll Pool ","Transfusion ","BlackKingBar "}),

		};
		for (int i = 1; i < 13; i ++){
			totalHeros[i-1] = new HeroIcon(chars[i-1].getName(),chars[i-1].getName()+"Icon.png",chars[i-1].getSpells());
		}
		for (int i = 0; i < 12; i ++){
			ht.put(totalHeros[i], 0);
		}
		for (int i = 0; i < 10; i ++){
			for (int j = 0; j < 12; j ++){
				if (Client.user.getTeam3()[i].equals(totalHeros[i].getName())){
					if (i<5){
						ht.put(totalHeros[i], 1);
					}else if (i < 8){
						ht.put(totalHeros[i], 2);
					}else{
						ht.put(totalHeros[i], 3);
					}
				}
			}
		}
		int i = 0;
		for (int j = 0; j < 3; j ++){
			for (int k = 0; k < 4; k++){
				display[i] = new JLabel(totalHeros[i].getImage());
				displayPair[i] = new HeroShelf(this,display[i]);
				displayPair[i].setLayout(new BorderLayout());
				displayPair[i].add(display[i],BorderLayout.CENTER);
				displayPair[i].setBounds(0,0,50,50);
				displayPair[i].setLocation(new Point(k*274+40,j*80+200));
				displayPair[i].setMessage(totalHeros[i].getDescription());
				displayPair[i].setName(totalHeros[i].getName());
				this.add(displayPair[i]);
				i++;
			}
		}
		team = new TeamGrid(this, displayPair);
		team.setBounds(0,0,1174,150);
		team.setLocation(0,0);
		this.add(team);
		button = new JButton("Save Team");
		button.setForeground(new Color(255,255,255));
		button.setFont(new Font("Calibri",Font.BOLD, 20));
		button.addActionListener(this);
		button.setBounds(1000,450,button.getPreferredSize().width,40);
		button.setBackground(new Color(0,0,0));
		this.add(button);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (heroEntered){
			g.setColor(new Color(0,0,0));
			g.setFont(new Font("Calibri",Font.BOLD,14));
			g.drawChars(name.toCharArray(), 0, name.length(), xcoor, ycoor);
			g.setFont(new Font("Calibri",Font.BOLD,10));
			for (int i = 0; i < data.length; i ++){
				g.drawChars(data[i].toCharArray(), 0, data[i].length(), xcoor, ycoor+(i+1)*10);
				
			}
			
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button){
			if (Client.user.getTeam().size() == 10){
				Client.user.getTeam().clear();
				for (JLabel i : team.getLabels()){
					Client.user.getTeam().add(i.getName());
				}
				Client.user.setCurrentTeamAsTeam();
				try {
					System.out.println("you tried");
					Client.s.sendTeam(new Team(Client.user.getTeam3()), Client.key);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
		

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	public HeroIcon[] getTotalHeros(){
		return totalHeros;
	}
	public boolean isHeroEntered() {
		return heroEntered;
	}
	public void setHeroEntered(boolean heroEntered) {
		this.heroEntered = heroEntered;
	}
	public int getXcoor() {
		return xcoor;
	}
	public void setXcoor(int xcoor) {
		this.xcoor = xcoor;
	}
	public int getYcoor() {
		return ycoor;
	}
	public void setYcoor(int ycoor) {
		this.ycoor = ycoor;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TeamGrid getTeam() {
		return team;
	}
	public void setTeam(TeamGrid team) {
		this.team = team;
	}
}