package gui;

import game.*; //for use later
import game.Character;
import server.beans.Team;
import spells.Spell;
import workers.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import Character.caitlyn.Caitlyn;
import Character.hongyu.Hongyu;
import Character.tryndamere.TrondoFiora;
import Character.vlassali.Vladimir;
import Character.annie.Annie;
import Character.brian.Brian;
import Character.leesin.Leesin;
import Character.leona.Leona;
import Character.lissandra.Lissandra;
import Character.sona.Sona;
import Character.soraka.Soraka;
import Character.taric.Taric;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;

public class Game extends JPanel implements ActionListener, MouseListener {
	String gg;
	int[] tilesize = new int[]{10,10};
	private Timer t = new Timer(68, this);
	public static BattleThread battle;
	private Character selected = new Character(0, 0, 0, 0, 0, 0, 0, 0, "0", "0",console, null, null);
	public static Character fake =  new Character(0, 0, 0, 0, 0, 0, 0, 0, "0", "0",null, null, null);
	private Spell litSpell;
	private String yourturn;
	private Hashtable<String, Terrain> lit = new Hashtable<>();
	public static ConsoleBox console;
	public static Topbar topbar;
	public static CharaList charlist;
	public static StatsWindow BRS;
	private static JFrame root;
	
	public static JFrame createSelf(Team t, String key){
		root = new Root(t, key);
		root.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		return root;
	}
	
	
	public void initialize(Team team, String key){
		Character[] charas = parseTeam(team);
		
		battle = new BattleThread(key, "swampy.map", charas);
		//battle = new BattleThread(tempqueue, "swampy.map", hongyu, brian, danielLi, kevinZ, vlassali, platoon, jerry, jerry2, brian2, brian3, brian4, brian5, brian6, brian7, brian8, brian9, brian10, brian11, brian12, trondo, cait);
		new Thread(battle).start();
		
		int[] dims = battle.getMapSize();
		int width = this.getWidth();
		int height = this.getHeight();
		tilesize[1] = (int)(width/dims[0]);
		tilesize[0] = (int)(height/dims[1]);
		//System.out.println(width + "," +dims[0]);
		//System.out.println(height + "," + dims[1]);
		//System.out.println(tilesize[0]);
		//System.out.println(tilesize[1]);
		repaint();
		
		SoundThread s= new SoundThread("./sound/Normal_Battle_2.wav", true);
		s.setLengthInMillis(53000); //this is about as well as I could do tbh
		s.start();
		yourturn = null;
		while (yourturn == null){
			yourturn = battle.getNextAction();
		}
		System.out.println(yourturn);
		gui.Game.topbar.setYourTurn(yourturn);
		t.start();

		
	}
	
	
	private Character[] parseTeam(Team team) {
		Character[] parsed = new Character[team.getChars().length];
		for (int i = 0; i<team.getChars().length; i++){
			/*
			 * tokens[0]=name
			 * tokens[1]=id
			 * tokens[2]=team
			 * tokens[3]=x:y
			 */
			String[] tokens = team.getChars()[i].split(" ");
			int x = Integer.valueOf(tokens[3].substring(0, tokens[3].indexOf(":")));
			int y = Integer.valueOf(tokens[3].substring(tokens[3].indexOf(":")+1));
			Character c;
			String tt = tokens[2];
			switch (tokens[0]){
				case "Annie":
					c = new Annie(x, y, tt, console);
					break;
				case "Brian":
					c = new Brian(x, y, tt, console);
					break;
				case "Caitlyn":
					c = new Caitlyn(x, y, tt, console);
					break;
				case "Hongyu":
					c = new Hongyu(x, y, tt, console);
					break;
				case "Leesin":
					c = new Leesin(x, y, tt, console);
					break;
				case "Leona":
					c = new Leona(x, y, tt, console);
					break;
				case "Lissandra":
					c = new Lissandra(x, y, tt, console);
					break;
				case "Sona":
					c = new Sona(x, y, tt, console);
					break;
				case "Soraka":
					c = new Soraka(x, y, tt, console);
					break;
				case "Taric":
					c = new Taric(x, y, tt, console);
					break;
				case "Tryndamere":
					c = new TrondoFiora(x, y, tt, console);
					break;
				case "Vladimir":
					c = new Vladimir(x, y, tt, console);
					break;
				default: c = new Character(150,10,10,10,3,5,0,0,tokens[2], "Testeroony"+i, console, new ImageIcon("brian.png"), new ImageIcon("brian.png"));
						System.out.println(tokens[0]);
						
						break;
			}
			c.setId(Long.valueOf(tokens[1]));
			c.setX(x);
			c.setY(y);
			parsed[i] = c;
		}
		return parsed;
	}


	public Game(String s){
		gg = s;
	}
	
	public String toString(){
		return gg;
	}
	
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		battle.updateMap(g, 0, 0, tilesize);
	}
	@Override
	public void actionPerformed(ActionEvent ev) {
		//timer should just be a repaint loop
		
		if (ev.getSource().equals(t)){
			//TODO: Call hasNextAction;
			if (battle.hasNextAction()){
				String action = battle.getNextAction();
				parseAction(action);
				System.out.println("got next action");
				
			}
			if (root!= null)
				root.repaint();
		}
		else if (ev.getSource().equals(topbar.getTurnButton())&& battle.getTurn().equals(yourturn)){
			battle.switchTurn();
			System.out.println("got past bad things");
			selected = null;
			console.println("Team " +battle.getTurn()+"'s turn!");
		} else if (ev.getSource() instanceof SpellButton){
			System.out.println("wotttttttt");
			for (int i = 0; i<4; i++){
				if (ev.getSource().equals(BRS.getButton(i)) && BRS.getSelTeam().equals(battle.getTurn())){
					for (Terrain t: lit.values()){
						t.dark();
					}
					lit = BRS.getButton(i).getSpell().getTilesInRange(battle.getM());
					for (Terrain t: lit.values()){
						t.light(new Color(0,255,0));
					}
					litSpell = BRS.getButton(i).getSpell();
					//deal with self cast spells here?
					if (litSpell.getRange() == 0 && !litSpell.getCaster().hasAttacked()){
						battle.spell(litSpell.getCaster(), litSpell.getCaster(), litSpell.getId());
						for(Terrain t: lit.values()){
							t.dark();
						}
						lit = new Hashtable<>();
						litSpell = null;
						//console.println("casted self cast spell");
					}
					
					
				}
			}
		}
		
	}
	private void parseAction(String action) {
		// TODO Auto-generated method stub
		//"[atype(string)] [id(long)]
		if (action.length()==1){
			yourturn=action;
			return;
		}
		System.out.println(action);
		String atype = action.substring(0, action.indexOf(" "));
		action = action.substring(action.indexOf(" ") + 1);
		long id = Long.parseLong(action.substring(0, action.indexOf(" ")));
		
		
		
		//System.out.println(atype);
		//System.out.println(id);
		
		Character c = battle.getCharacter(id);
		 //I was dumb enough to mix x and y randomly, nice job me
		long tid;
		switch (atype){
			case "console":
				System.out.println(action);
				String message = action.substring(action.indexOf("\"")+1, action.length()-1);
				console.println(message);
				break;
			case "unmove":
				c.setMoved(false);
				break;
			case "damage":
				//"damage [id]:[d]"
				int damage = Integer.parseInt(action.substring(action.indexOf(" ")+1));
				if (damage<0){
					c.heal(-damage);
					SoundThread th = new SoundThread("./sound/Item.wav", false);
					th.start();
				} else {
					c.takeDamage(damage);
					SoundThread th = new SoundThread("./sound/Attack Hit 1.wav", false);
					th.start();
				}
				break;
			case "effect":
				//"effect [id] [effect #]-[amount]/[length]" 
				int n = Integer.parseInt(action.substring(action.indexOf(" ")+1, action.indexOf("|")));
				int a = Integer.parseInt(action.substring(action.indexOf("|")+1, action.indexOf("/")));
				int l = Integer.parseInt(action.substring(action.indexOf("/")+1));
				
				EffectTimer eff = new EffectTimer(l, c, n, a);
				c.addEffect(eff);
				break;
			case "kill":
				//"kill [id]"
				battle.removeChar(c);
				SoundThread deat = new SoundThread("./sound/Death.wav",false);
				deat.start();
				break;
				
			case "move":
				String xy = action.substring(action.indexOf(" ")+1);
				int x = Integer.parseInt(xy.substring(0, xy.indexOf(":")));
				int y = Integer.parseInt(xy.substring(xy.indexOf(":")+1));
				Terrain t = battle.getM().getTerrain(y, x);
				battle.getM().move(c, t);
				c.setMoved(true);
				//battle.getM().effect(c);
				SoundThread mv = new SoundThread("./sound/Skeleton Steps.wav",false);
				mv.start();
				break;
				
			case "attack":
				tid = Long.parseLong(action.substring(action.indexOf(" ") + 1));
				battle.getCharacter(id).attack(battle.getCharacter(tid)); 
				battle.getCharacter(id).setAttacked(true);
				if (battle.getM().checkDeath(battle.getCharacter(tid))){
					battle.removeChar(battle.getCharacter(tid));
					BRS.setSelected(battle.getCharacter(id));
				}if (battle.getM().checkDeath(battle.getCharacter(id)))
					battle.removeChar(battle.getCharacter(id));
				SoundThread thr = new SoundThread("./sound/Attack Hit 1.wav", false);
				thr.start();
				break;
			case "spell":
				tid = Long.parseLong(action.substring(action.indexOf(" ") + 1, action.indexOf(":")));
				int spellId = Integer.parseInt(action.substring(action.indexOf(":")+1));
				battle.getCharacter(id).getSpell(spellId).activeEffect(battle.getCharacter(tid)); 
				battle.getCharacter(id).getSpell(spellId).setOnCD();
				System.out.println(battle.getCharacter(id).getSpell(spellId).getCooldown());
				battle.getCharacter(id).setAttacked(true);
				if (battle.getM().checkDeath(battle.getCharacter(tid))){
					battle.removeChar(battle.getCharacter(tid));
					BRS.setSelected(battle.getCharacter(id));
				}if (battle.getM().checkDeath(battle.getCharacter(id)))
					battle.removeChar(battle.getCharacter(id));
				break;
			case "switch":
				for(Terrain te: lit.values()){
					te.dark();
				}
				lit = new Hashtable<>();
				litSpell = null;
				SoundThread swit = new SoundThread("./sound/Next Turn.wav",false);
				swit.start();
				battle.reMoveAndAttack(battle.getTurn());
				battle.setTurn(battle.getTurn().equals("A")?"B":"A");
				Game.topbar.setTurn(battle.getTurn());
				if (battle.getTurn().equals("A")){
					battle.getM().tick();
				}
				
				break;
			case "victory":
				battle.setRunning(false);
				JOptionPane.showMessageDialog(this, "You win!!!");
				battle.setOver(true);
				root.dispose();
				break;
			case "defeat":
				battle.setRunning(false);
				JOptionPane.showMessageDialog(this, "You lose :<");
				battle.setOver(true);
				root.dispose();
				break;
				
			default: System.out.println("error");
		}
	}
	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	boolean attacking = false;
	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		Character sel = battle.getIntersectedChar(me.getY(), me.getX(), tilesize);
		Terrain terr = battle.getIntersectedTerrain(me.getX(), me.getY(), tilesize);
		//System.out.println(sel);
		//System.out.println(selected);
		//System.out.println(terr);
		
		
		if (sel!= null){
			System.out.println(sel.getTeam());
			System.out.println(yourturn);
			SoundThread selecterino = new SoundThread("./sound/Select 1.wav", false);
			selecterino.start();
			BRS.setSelected(sel);
			if (sel!=selected){
				System.out.println("sel and selected are diff");
				for(Terrain t: lit.values()){
					t.dark();
				}
				if (litSpell!=null && selected == null)
					selected = litSpell.getCaster();
					
				
				
				if (litSpell!=null && selected.getTeam().equals(battle.getTurn()) && !selected.hasAttacked() && litSpell.offCD()){
					battle.spell(selected, sel, litSpell.getId());
					for(Terrain t: lit.values()){
						t.dark();
					}
					lit = new Hashtable<>();
				}
				if (!sel.isMoved() && sel.getTeam().equals(battle.getTurn())){
					selected = sel;
					System.out.println(sel.isMoved());
					Hashtable<String, Terrain> ht = battle.getMoves(sel);
					for (Terrain t: lit.values()){
						t.dark();
					}
					litSpell = null;
					for (String s: ht.keySet()){
						ht.get(s).light(new Color(247,0,255));
						//System.out.println(s);
					}
					lit = ht;
				} else if (selected != null && !selected.hasAttacked() && selected.getTeam().equals(battle.getTurn()) && selected.inRange(sel) && litSpell == null){
					battle.attack(selected, sel);
					selected = null;
					attacking = false;
					for(Terrain t: lit.values()){
						t.dark();
					}
					lit = new Hashtable<>(); 
				} else if (/*selected == null &&*/ !sel.hasAttacked() && sel.getTeam().equals(battle.getTurn())) {
					selected = sel;
					for (Terrain t: lit.values()){
						t.dark();
					}
					
					Hashtable<String, Terrain> ht = battle.getAttacks(sel);
					for (String s: ht.keySet()){
						ht.get(s).light(new Color(255,0,100));
					}
					lit = ht;
				} else {
					System.out.println("got to else");
					System.out.println("Attacked?"+sel.hasAttacked());
					selected = sel;
					
					for (Terrain t: lit.values()){
						t.dark();
					}
					
					Hashtable<String, Terrain> ht = battle.getMoves(sel);
					for (String s: ht.keySet()){
						ht.get(s).light(new Color(247,0,255));
						//System.out.println(s);
					}
					lit = ht;
				}
				
			} else {
				//System.out.println("sel == selected");
				if (litSpell!=null && litSpell.isSelfCastable() && !litSpell.getCaster().hasAttacked()){
					battle.spell(litSpell.getCaster(), litSpell.getCaster(), litSpell.getId());
					for(Terrain t: lit.values()){
						t.dark();
					}
					lit = new Hashtable<>();
					litSpell = null;
				}
				
				for (Terrain t: lit.values()){
					t.dark();
				}
				
				Hashtable<String, Terrain> ht = battle.getAttacks(sel);
				for (String s: ht.keySet()){
					ht.get(s).light(new Color(255,0,100));
					//System.out.println(s);
				}
				lit = ht;
			}
			
		} else if (terr!= null) {
			if (lit.containsValue(terr) && selected.getTeam().equals(battle.getTurn())){
				if (attacking){
					//System.out.println(attacking);
					
				} else if (!selected.isMoved()){
					battle.move(selected, terr);
					selected = null;
					for(Terrain t: lit.values()){
						t.dark();
					}
					lit = new Hashtable<>();
					//terr.light(new Color(255,0,0));
				} else {
				}
			} else {
				//battle.getM().effectTimerAllInRange(terr.getX(), terr.getY(), "A", 3, new EffectTimer(1, fake, EffectTimer.ATTACK, 50));
				
				//for (String t: lit.keySet())
				//	System.out.println(t);
				//console.println("badplaysm8");
			}
		}
		
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
