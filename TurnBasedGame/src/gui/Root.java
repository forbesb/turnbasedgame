package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import server.beans.Team;

public class Root extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8454714428926359247L;
	private Game g;
	public Root(Team t, String k) throws HeadlessException {
		super();
		setSize(1250,930);
		setDefaultCloseOperation (WindowConstants.DISPOSE_ON_CLOSE);  
		setResizable(false);
		
		Container co = new Container();
		
		g = new Game("ok");
		g.setBackground(new Color(255,255,255));
		g.setSize(860, 860);
		
		co.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		Game.topbar = new Topbar(g);
		Game.topbar.setBackground(new Color(255,0,0));
		Game.topbar.setPreferredSize(new Dimension(15,1600));
		c.ipady = 15;
		c.ipadx = 1600;
		c.weightx = 10;
		c.weighty = 1;
		
		c.gridwidth=30;
		
		c.fill=c.BOTH;
		co.add(Game.topbar, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth =5;
		c.ipadx = 100;
		c.ipady = 840;
		c.weighty = 9;
		c.weightx = 0.2;
		Game.charlist = new CharaList();
		Game.charlist.setBackground(new Color(0,255,255));
		co.add(Game.charlist, c);
		
		c.gridx = 15;
		c.gridy = 1;
		c.gridwidth = 10;
		c.ipadx = 840;
		c.ipady = 840;
		c.weightx = 9;
		c.weighty = 15;
		
		co.add(g, c);
	
		
		c.gridx = 25;
		c.gridy = 1;
		c.gridwidth = 5;
		c.ipadx = 80;
		c.ipady = 0;
		c.weightx = 3;
		c.weighty = 0.5;
		
		JPanel rs = new JPanel();
		rs.setLayout(new GridLayout(2,1));
		//TODO: make top one smaller than bottom
		Game.console = new ConsoleBox(298,459);
		Game.console.setBackground(new Color(0,0,255));
		rs.add(Game.console);
		Game.BRS = new StatsWindow();
		Game.BRS.setBackground(new Color(255,0,255));
		//BRS.setPreferredSize(new Dimension(10,10));
		rs.setPreferredSize(new Dimension(300,885));
		rs.add(Game.BRS);

		for (int i = 0; i<4; i++){
			Game.BRS.getButton(i).addActionListener(g);
		}
		
		co.add(rs, c);
		
		this.add(co);
		g.initialize(t,k);
		g.addMouseListener(g);
		this.setVisible(true);
		
		this.repaint();

	}
	
	public void dispose(){
		g.battle.disconnect();
		client.mainClient.profile.ProfilePanel.getIqs().setInQueue(false);
		super.dispose();
	}



}
