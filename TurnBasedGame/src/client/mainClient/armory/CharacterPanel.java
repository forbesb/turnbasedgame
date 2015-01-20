package client.mainClient.armory;

import javax.swing.*;

import client.mainClient.profile.UserNamePanel;

import java.awt.*;

/*import Character.annie.*;
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
import Character.vlassali.*;*/
import game.Character;

@SuppressWarnings("serial")
public class CharacterPanel extends JPanel {
	private Image image;
	private static String [] headings = {"Spells","Stats","Description"};
	private static String [] cSH = {
		"Health", //0
		"Attack", //1
		"Armor", //2
		"MagicRes", //3
		"Range", //4
		"Speed"}; //5
	private static String [] eT = {
		"Apply: Increase ", //0
		"Apply: Decrease ", //1
		"Apply: STUN ", //2
		"Apply: SNARE ", //3
		"Apply: Damage ", //4
		"Apply: AOE Damage ", //5
		"Apply: AOE Increase ", //6
		"Apply: AOE Decrease ", //7
		"Apply: Silence", //8
		"Type: Non-Target Movement",//9
		"Type: Gap Closer",//10
		"Apply: AOE STUN ", //11
		"Apply: AOE SNARE ", //12
		"Apply: Bleed", //13
		"Apply: AOE Bleed", //14
		"Apply: BLEED", //15
		"Apply: True Damage",//16
		"Apply: Invulnerability", // 17
		"Apply: Percentage HP",//18
		"Apply: Physical shield",//19
		"Apply: Magical shield",//20
		"Apply: Damage Modifier",//21
		"Type: Heal" // 22
	};
	private String [] stats;
	private String [] data;
	private boolean inIcon = false;
	private JPanel statPanel = new JPanel();
	private JPanel spellPanel = new JPanel();
	private static String [][][] spellDescriptions = new String[][][]{
		{//Annie
			{"Passive: Stunning Mania","The next spell after ","2 spell casts will stun"},
			{eT[4],eT[8]},
			{eT[5],eT[12]},
			{eT[9]},
			{eT[12], eT[5]}
		},
		{//Brian
			{"Passive: Destroy the Weak","Your next attack","will deal more damage","to weaker targets."},
			{eT[13],eT[4]},
			{eT[5]},
			{eT[9], eT[8]},
			{"Apply: Mark of Death:","","Will damage character","for all damage the","character has taken by this", "in the past 3 turns"}
		},
		{//Caitlyn
			{"Passive: Dangerous Game", "Deals more damage", "and recieves more damage"},
			{eT[4]},
			{eT[15],eT[1] + cSH[5], eT[4]},
			{eT[2],eT[4]},
			{eT[4],eT[16], eT[18]}
		},
		{//Hongyu
			{"Passive: Confusion", "All spells will apply a status"},
			{eT[15]},
			{eT[8], eT[0]+cSH[2],eT[0]+cSH[3]},
			{eT[2],eT[0]+cSH[2],eT[0]+cSH[3]},
			{eT[5],eT[11],eT[18],"Targets all enemies on map"}
		},
		{//Leesin
			{"Passive: Monk's regeneration","Regenerate 10%","of Max HP per turn"},
			{eT[17],"Regenerate 50%","of Max HP per turn"},
			{eT[10],eT[4]},
			{eT[5],eT[1]+cSH[5]},
			{eT[2], eT[16], eT[18]}
		},
		{//Leona
			{"Passive: Warrior's Tenacity", "All damage that would","do more than 10%", "of current HP is","reduced by 50%"},
			{eT[10],"","ALLY",eT[19],eT[20],"","ENEMY",eT[1]+cSH[5],eT[4]},
			{eT[0]+cSH[2],eT[0]+cSH[3]},
			{eT[2],eT[4]},
			{eT[11],eT[5]}
		},
		{//Lissandra
			{"Passive: True Potential", "On every third turn", "All spells become global"},
			{eT[0]+cSH[5],eT[0]+cSH[1]},
			{eT[1]+cSH[5],eT[21]+" 1.5X"},
			{eT[4],"","If Hypothermia","Deals bonus damage",eT[3]},
			{eT[3],"Apply: AOE Hypothermia"}
		},
		{//Sona
			{"Passive: Music of the Maiden", "All spells cast by","this character will","increase Attack"},
			{eT[19],eT[20]},
			{eT[22],eT[0]+cSH[2],eT[0]+cSH[3],"This spell can be self cast", "or cast on both self and ally"},
			{eT[6]+cSH[5]},
			{eT[17]}
		},
		{//Soraka
			{"Passive: Healer","If there are wounded allies","this character will","increase its range"},
			{eT[22]},
			{eT[0]+cSH[2],eT[0]+cSH[3]},
			{eT[19],eT[20]},
			{eT[22], "Affects all allies on map"}
		},
		{//Taric
			{"Passive: Gem Warrior","This character is super tanky."},
			{eT[22],"","If cast on ally", "ally is healed","as well as Taric"},
			{eT[7]+cSH[2],eT[5]},
			{eT[2],eT[4]},
			{"If Ally",eT[6]+cSH[1],eT[6]+cSH[2],eT[6]+cSH[3],"", "If Enemy",eT[7]+cSH[1],eT[7]+cSH[2],eT[7]+cSH[3],eT[5]}
		},
		{//Tryndamere
			{"Passive: Feel No Pain","Ignores a percentage of ","damage dealt"},
			{eT[2],eT[5]},
			{eT[19]},
			{eT[20]},
			{"Type: Execute",eT[16]}
		},
		{
			{"Passive: Vampire", "Heals 20% of damage done."},
			{"Apply: Sigil of Silence",eT[4], "", "If break by Attack", "or break by Spell",eT[4],eT[8]},
			{eT[17],eT[22]},
			{eT[4],eT[22]},
			{"Makes self magic and","effect immune."}
		},
	};
	public CharacterPanel(Character c, int n){
		this.setLayout(null);
		this.setBackground(new Color (32,178,179));
		this.image = c.getImg().getImage();
		//Name
		UserNamePanel name = new UserNamePanel(c.getName());
		name.setBounds(160,20,594,150);
		this.add(name);
				
		//Stats
		stats = new String[6];
		int [] charStats = new int[]{c.getHp(),c.getAttack(),c.getDefence(),c.getMagicres(),c.getRange(),c.getSpeed()};
		statPanel.setLayout(new GridLayout(6,1));
		statPanel.setBackground(new Color(0,0,0));
		for (int i = 0; i < 6; i ++){
			stats[i] = " "+cSH[i] + ": " + charStats[i];
			JLabel info = new JLabel(stats[i]);
			info.setFont(new Font("Calibri", Font.PLAIN, 15));
			info.setForeground(new Color(255,255,255));
			statPanel.add(info);
		}
		statPanel.setBounds(10,250, 200, 200);
		this.add(statPanel);
		
		//Stat Heading
		JLabel statHeading = new JLabel(headings[1]);
		statHeading.setBounds(25,220, 100, 20);
		statHeading.setFont(new Font("Calibri", Font.BOLD, 30));
		statHeading.setForeground(new Color(255,255,255));
		this.add(statHeading);
		
		//Spells Heading
		JLabel spells = new JLabel(headings[0]);
		spells.setBounds(220,220, 200, 20);
		spells.setFont(new Font("Calibri", Font.BOLD, 30));
		spells.setForeground(new Color(255,255,255));
		this.add(spells);
		
		
		//Spells Thing
		spellPanel.setLayout(new GridLayout(5,1));
		spellPanel.setBackground(new Color(0,0,0));
		for (int i = 0; i < 5; i ++){
			SpellPanel temp = new SpellPanel(this, i, spellDescriptions[n][i], c);
			spellPanel.add(temp);
		}
		spellPanel.setBounds(200,250, 100, 200);
		this.add(spellPanel);
		
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(image,0,20,150,150,null);
		if (inIcon){
			g.setColor(new Color(255,255,255));
			g.fillRect(350, 200, 200, 250);
			g.setColor(new Color(0,0,0));
			for (int i = 0; i < data.length; i ++){
				if (i == 0) {
					g.setFont(new Font("Calibri",Font.BOLD,15));
					g.drawChars(data[i].toCharArray(), 0, data[i].length(), 360, 220+i*15);
					g.drawChars(" ".toCharArray(), 0, 1, 360, 250);
					
				}
				else{
					g.setFont(new Font("Calibri",Font.PLAIN,15));
					g.drawChars(data[i].toCharArray(), 0, data[i].length(), 360, 235+i*15);
				}
				
			}
		}
	}
	
	public void setInIcon(boolean inIcon) {
		this.inIcon = inIcon;
		repaint();
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}
}
