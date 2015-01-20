package client.mainClient.armory;


import java.awt.Color;

import Character.annie.*;
import Character.brian.*;
import Character.caitlyn.*;
import Character.hongyu.*;
import Character.leesin.*;
import Character.leona.*;
import Character.lissandra.*;
import Character.sona.*;
import Character.soraka.*;
import Character.taric.*;
import Character.tryndamere.*;
import Character.vlassali.*;
import game.Character;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public class ArmoryPanel extends JPanel{
	private Left left;
	private Right right;
	private Character [] characters = {
		new Annie(0,0,null,null), 
		new Brian(0,0,null,null),
		new Caitlyn(0,0,null,null),
		new Hongyu(0,0,null,null),
		new Leesin(0,0,null,null),
		new Leona(0,0,null,null),
		new Lissandra(0,0,null,null),
		new Sona(0,0,null,null),
		new Soraka(0,0,null,null),
		new Taric(0,0,null,null),
		new TrondoFiora(0,0,null,null),
		new Vladimir(0,0,null,null)
	};
	private String [] names  = new String[12];;
	public ArmoryPanel(){
		this.setBackground(new Color(255,255,255));
		this.setLayout(null);
		for (int i = 0;i < 12; i ++){
			names[i] = characters[i].getName();
		}
		left = new Left(this);
		left.setBounds(10,10,367,483);
		this.add(left);
		right = new Right(this);
		right.setBounds(400,10,764, 483);
		this.add(right);
	}

	public Character [] getCharacters() {
		return characters;
	}

	public void setCharacters(Character [] characters) {
		this.characters = characters;
	}
	public void switchCards(){
		
	}
	public String [] getNames(){
		return names;
	}
	public Right getRight(){
		return this.right;
	}
}