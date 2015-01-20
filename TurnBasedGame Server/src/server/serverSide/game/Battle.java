package server.serverSide.game;

import server.serverSide.workers.*;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Battle {
	private ArrayList<Character> chars = new ArrayList<>();
	private boolean over = false;
	private Scanner scnr = new Scanner(System.in);
	private Map m;
	private BattleThread b;
	private String n;
	
	public static void xmain(String[] args) {
		// for testing, unused now
		/* commented out because character syntax changed
		Character hongyu = new Character(100, 25, 10,     2, 3,        1,1, "A", "hongyu", null);
		Character brian = new Character(150, 52, 20,      3, 4,         1,6, "A", "Brian", null);
		
		Character danielLi = new Character(120, 33, 40,   2, 3,      6,1, "B", "danielLi", null);
		Character kevinZ = new Character(10, 50, 50,      1, 3,         6,6, "B","kevinZ", null);
		Battle b = new Battle("swampy.map", hongyu, brian, danielLi, kevinZ);
		b.battle();
		*/
	}
	
	public Battle(String name, Character... charas){
		for (Character c: charas){ //dynamic number of characters as arguments
			if (c!=null) chars.add(c);
		}
		n = name;

		
		//System.out.println(getM());
		//System.out.println(getM().getCharMap());
	}
	public void init(BattleThread b){
		setM(Map.generateMapFromFile(n, b)); //get map
		getM().setChararacterList(chars, Map.USEMAPPOSITIONS);//set map to characters
		getM().setThread(b);
	}
	public void battle(){
		//old testing text-based method. todos are implemented in GUI version
		while (chars.size()>1){
			over=true;
			
			//TODO: over if all alive are same team
			
			//TODO: tick all before main loop
			for (int i = 0; i<chars.size(); i++){
				Character c= chars.get(i);
				c.tick();
				
				if (c.getHp()<=0){
					
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
	
	public Character getCharacter(long id){
		for (Character c: chars){
			if (c.getId()== id) return c; //search through characters for char with the specified id
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
	}
	
}
