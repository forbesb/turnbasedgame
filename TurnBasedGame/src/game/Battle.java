package game;

import gui.Game;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Battle {
	private ArrayList<Character> chars = new ArrayList<>();
	private boolean over = false;
	private Scanner scnr = new Scanner(System.in);
	private Map m;
	
	public Battle(String name, Character... charas){
		for (Character c: charas){ //dynamic number of characters as arguments
			chars.add(c);
		}
		setM(Map.generateMapFromFile(name)); //get map
		getM().setChararacterList(chars, Map.USECHARPOSITIONS);//set map to characters
		Game.charlist.setChar(chars);//set sidebar to characters
		
		//System.out.println(getM());
		//System.out.println(getM().getCharMap());
	}
	
	public void battle(){
		//old testing text-based method. todos are implemented in GUI version
		while (chars.size()>1){
			setOver(true);
			//TODO: over if all alive are same team
			
			//TODO: tick all before main loop
			for (int i = 0; i<chars.size(); i++){
				Character c= chars.get(i);
				c.tick();
				
				if (c.getHp()<=0){
					System.out.println(c.getName()+" has died.");
					chars.remove(c);
					getM().remove(c);
					i--;
					continue;
				}
				
				for (Character d: chars){
					System.out.print(d+", ");
				}
				System.out.println();
				
				System.out.println(getM().getCharMap());
				System.out.println(c.getName()+", where do you want to move? (x y): ");
				int x = scnr.nextInt();
				int y = scnr.nextInt();
				scnr.nextLine();
				if (!getM().move(c, x, y))
					System.out.println("invalid move");
				getM().effect(c);
				
				System.out.println(c.getName()+", who do you want to attack? (What position): ");
				int pos = Integer.parseInt(scnr.nextLine()); //TODO: try-catch this
				Character target = chars.get(pos);
				if (!c.attack(target))
					System.out.println("invalid attack");
				else {
					if (target.getHp()<=0){
						System.out.println(target.getName()+" has died.");
						chars.remove(target);
						getM().remove(target);
					}
				}
			}
			
			
		}
	}
	
	public boolean move(Character c, Terrain t){
		return getM().move(c, t);
	}
	
	public int[] getMapSize(){
		int[] dimensions = new int[]{getM().getLength(), getM().getWidth()};
		return dimensions;
	}
	
	public void updateMap(Graphics g, int sx, int sy, int[] tilesize){
		getM().updateMap(g, sx, sy, tilesize);
	}
	
	public Character getIntersectedChar(int mx, int my, int[] tilesize){
		//used for gui, checks mouse intersecting a character icon
		for (Character c: chars){
			if (c.intersects(mx, my, tilesize)){
				return c;
			}
		}
		return null;
	}
	
	public Terrain getIntersectedTerrain(int mx, int my, int[] tilesize){
		//used for gui
		for (int i = 0; i<getM().getLength(); i++){
			for (int y = 0; y<getM().getWidth(); y++){
				Terrain t = getM().getTerrain(i, y);
				//System.out.println(t);
				if (t.intersects(mx, my, tilesize)){
					return t;
				}
			}
			
		}
		return null;
	}
	
	public Character getCharacter(long id){
		for (Character c: chars){
			if (c.getId() == id) return c; //search through characters for char with the specified id
		}
		return null;
		
	}

	
	public Hashtable<String, Terrain> getMoves(Character c){
		Hashtable<String, Terrain> ht = new Hashtable<>(); //could be done in one line, this is cleaner
		getM().getPossibleMoves(c.getX(), c.getY(), c.getSpeed(), c.getTeam(), ht);
		return ht; //returns the hashtable of coordinates and objects of spaces
	}
	
	public Hashtable<String, Terrain> getAttacks(Character c){
		//same as above, but for attacks
		Hashtable<String, Terrain> ht = new Hashtable<>();
		getM().getAttacks(c.getX(), c.getY(), c.getRange(), c.getTeam(), ht);
		return ht;
	}

	public Map getM() {
		return m;
	}

	public void setM(Map m) {
		this.m = m;
	}
	
	public ArrayList<Character> getChars(){
		return chars;
	}
	
	public void removeChar(Character c){
		chars.remove(c);
		getM().remove(c);
	}

	/**
	 * @return the over
	 */
	public boolean isOver() {
		return over;
	}

	/**
	 * @param over the over to set
	 */
	public void setOver(boolean over) {
		this.over = over;
	}
	
}
