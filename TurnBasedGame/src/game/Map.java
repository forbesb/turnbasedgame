//TODO: build a map editor jfc this sucks to look at
package game;


import gui.Game;
import server.beans.*;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;


public class Map {
	public static boolean USEMAPPOSITIONS = true;
	public static boolean USECHARPOSITIONS = false;
	private int length, width, pointerx, pointery;
	private String[] startposa, startposb;
	private Terrain[][] tiles;
	private Character[][] chars;
	
	private Hashtable<String, Terrain> spaces = new Hashtable<>();

	public Map(int l, int w){
		length = l;
		width =w;
		tiles = new Terrain[l][w];
		chars = new Character[l][w];
	}

	
	
	
	public static Map generateMapFromFile(String filename) {
		//TODO: Properly try-catch this and don't be lazy
		BufferedReader fre;
		Map base;
		
		try {
			fre = new BufferedReader(new FileReader(new File(filename)));
			/*FILES SHOULD BE NAMED "name.map"
			 * FIRST LINE: "l : w"
			 * AFTER THAT: L lines of W terrain each
			 * then: "n" where n is the number of start places per team
			 * next: n lines of team A starts
			 *       n lines of team B starts
			 */ 
			
			String[] sizes = fre.readLine().split(":");
			int length = Integer.parseInt(sizes[0]);
			int width = Integer.parseInt(sizes[1]);
			base = new Map(length, width);
			int xpos = 0;
			int ypos = 0;
			while (ypos<length){
				String line = fre.readLine();
				for (char ch: line.toCharArray()){ //add proper terrain
					if (ch=='G')
						base.add(new Grass(xpos, ypos));
					else if (ch=='R')
						base.add(new Rock(xpos, ypos));
					else if (ch=='F')
						base.add(new Fort(xpos, ypos));
					else if (ch=='S')
						base.add(new Swamp(xpos, ypos));
					else if (ch=='B')
						base.add(new Blank(xpos, ypos));
					xpos++;
				}
				ypos++;
				xpos=0;
			}
			int posn = Integer.parseInt(fre.readLine());
			base.startposa = new String[posn];
			base.startposb = new String[posn];
			for (int i = 0; i<posn; i++){
				base.startposa[i] = fre.readLine();
			}
			fre.readLine();
			for (int i = 0; i<posn; i++){
				base.startposb[i] = fre.readLine();
			}
			
			fre.close();
		} 
			catch (IOException e) {
			base = new Map();
			//e.printStackTrace();
		}
		
		return base;	
	}
	
	public static Map generateMapFromBean(MapB bean){
		Map base = null;
		int length = bean.getTiles().length;
		int width = bean.getTiles()[0].length;
		base = new Map(length, width);
		int xpos = 0;
		int ypos = 0;
		while (ypos<length){
			String[] line = bean.getTiles()[ypos];
			for (String ch: line){ //add proper terrain
				if (ch.equals("G"))
					base.add(new Grass(xpos, ypos));
				else if (ch.equals("R"))
					base.add(new Rock(xpos, ypos));
				else if (ch.equals("F"))
					base.add(new Fort(xpos, ypos));
				else if (ch.equals("S"))
					base.add(new Swamp(xpos, ypos));
				else if (ch.equals("B"))
					base.add(new Blank(xpos, ypos));
				xpos++;
			}
			ypos++;
			xpos=0;
		}
		
		
		base.startposa= bean.getStartposa();
		
		base.startposb = bean.getStartposb();
		
		
		return base;	
	}
	
	public static MapB generateBeanFromMap(Map m){
		String[][] x = new String[m.length][m.width];
		
		int xpos = 0;
		int ypos = 0;
		while (ypos<m.length){
			Terrain[] line = m.tiles[ypos];
			for (Terrain t: line){ //add proper terrain
				if (t instanceof Grass)
					//'G'
					x[ypos][xpos] = "G";
				else if (t instanceof Rock)
					//'R'
					x[ypos][xpos] = "R";
				else if (t instanceof Fort)
					//'F'
					x[ypos][xpos] = "F";
				else if (t instanceof Swamp)
					//'S'
					x[ypos][xpos] = "S";
				else if (t instanceof Blank)
					//'B'
					x[ypos][xpos] = "B";
				
				xpos++;
			}
			ypos++;
			xpos=0;
		}
		MapB bean = new MapB();
		bean.setTiles(x);
		bean.setStartposa(m.startposa);
		return bean;
	}
	
	
	
	
	
	
	
	
	public boolean add(Terrain t){
		tiles[pointery][pointerx++] = t;
		if (pointerx==width){
			pointerx=0;
			pointery++;
		}
		
		return true; //never really made a false condition...
	}
	
	public Map(){
		tiles = new Terrain[1][1];
		chars = new Character[1][1]; //default
		tiles[0][0] = new Grass(0,0);
	}
	
	public void setChararacterList( ArrayList<Character> ch, boolean useMapPositions){
		int apoint = 0;//array pointers
		int bpoint = 0;
		if (useMapPositions){
			for (Character c: ch){
				c.setMap(this);
				String start;
				if (c.getTeam().equals("A")){ //put chars on their team starting places
					 start = startposa[apoint++];
					
				} else {
					 start = startposb[bpoint++];
						
				}
				int x = Integer.valueOf(start.substring(0, start.indexOf(":")));
				int y = Integer.valueOf(start.substring(start.indexOf(":")+1));
				//X and Y are switched again gratz
				chars[y][x] = c;
				c.setSpace(tiles[y][x]);
				c.setX(y); //set character to given space
				c.setY(x);
			}
		} else {
			for (Character c: ch){
				//not sure if switched, test for it?
				//seems fine I think...
				
				c.setMap(this);
				chars[c.getX()][c.getY()] = c;
				c.setSpace(tiles[c.getX()][c.getY()]);
			}
			
		}
	}
	
	public String toString(){
		//for testing
		String out = "";
		for (Terrain[] x: tiles){
			for(Terrain y: x){
				out+=y+" ";
			}
			out+="\n";
		}
		
		return out;
	}
	
	public String getCharMap(){
		//for testing
		String out = "";
		System.out.print("x/y ");
		for (int i = 0; i<length; i++){
			System.out.print(i+"  ");
		}
		System.out.println();
		for (int i = 0; i<length; i++){
			Character[] x = chars[i];
			out+=i+": ";
			for (int y = 0; y<width; y++){
				Character c = x[y];
				if (c==null){
					out+= tiles[i][y]+" ";
				} else {
					out+=" "+c.getName().charAt(0)+" ";
				}
			}
			out+="\n";
		}
		return out;
	}
	
	public void remove(Character c){
		chars[c.getX()][c.getY()] = null; //remove char from the list
	}
	
	public boolean move(Character c, int newx, int newy){
		if (newx>=length || newy>=width){
			return false;
		}
		if (true){
			chars[c.getX()][c.getY()] = null;
			c.setX(newx); //move character
			c.setY(newy);
			c.setSpace(tiles[newx][newy]);
			c.setMoved(true);
			chars[newx][newy] = c; //change its space
			return true;
		}
		return true;
	}
	
	public boolean move(Character c, Terrain t){
		return move(c, t.getY(), t.getX());
	}
	
	
	
	public void getPossibleMoves(int x, int y, int range, String team, Hashtable<String, Terrain> target){
		//recursive path test
		if (range>=0 && tiles[x][y].isPassable() && (chars[x][y]==null || chars[x][y].getTeam().equals(team))){
			//if it is a valid tile
			
			if (!target.contains(tiles[x][y])) //and not in the list already
				target.put(x+":"+y, tiles[x][y]); //add it
			
			//System.out.println(x+":"+y);
			
			for (int dx: new int[]{1,-1}){
				getPossibleMoves(x+dx, y, range-1, team, target);
			} //test all possible directions from the space
			for (int dy: new int[]{1,-1}){
				getPossibleMoves(x, y+dy, range-1, team, target);
			}
			
		}
		//this returns to the given array
		//return target;
	}
	
	public void getAttacks(int x, int y, int range, String team, Hashtable<String, Terrain> target){
		
		if (range>=0 && tiles[x][y].isPassable()){ //similar to the last one, but it can go on characters
			//System.out.println(chars[x][y]);
			
			if (!target.contains(tiles[x][y])) // && (chars[x][y]!=null && !chars[x][y].getTeam().equals(team)))
				target.put(x+":"+y, tiles[x][y]); //same as pathfind
			
			//System.out.println(x+":"+y);
			
			for (int dx: new int[]{1,-1}){
				getAttacks(x+dx, y, range-1, team, target);
			} //check all directions
			for (int dy: new int[]{1,-1}){
				getAttacks(x, y+dy, range-1, team, target);
			}
			
		}

		//return target;
	}

	public void returnAllSpaces(Hashtable<String, Terrain> target){
		for (int i= 0; i< length; i++){
			for (int y = 0; y<width; y++){
				target.put(i+":"+y, tiles[i][y]); //add it
			}
		}
	}
	private boolean hasPath(Character c, int newx, int newy) {
		spaces.clear();
		Hashtable<String, Terrain> sp2 = new Hashtable<String, Terrain>();
		getPossibleMoves(c.getX(), c.getY(), c.getSpeed(), c.getTeam(), sp2);
		for (String s: sp2.keySet()){
			//System.out.println(s);
		}
		/*
		System.out.println();
		
		for (String coords: sp2.keySet()){
			System.out.println(coords);
		}
		*/
		if (sp2.get(newx+":"+newy)!=null && tiles[newx][newy].isPassable() && (chars[newx][newy] == null || chars[newx][newy].equals(c))){
			return true; //uses pathfind to check that the tile is valid
		}
		return false;
	}
	
	public void effect(Character c){
		c.getSpace().terrEffect(c); //calls the terrain effect on the character
	}
	 //getters and setters
	public int getWidth(){
		return width;
	}
	public int getLength(){
		return length;
	}
	
	public Terrain getTerrain(int x, int y){
		return tiles[x][y];
	}
	
	public void updateMap(Graphics g, int sx, int sy, int[] tilesize){
		int ox = sx, oy = sy; //oy, ox = original x and y
						      //sx and sy = current x and y
		for (Terrain[] tl: tiles){
			for (Terrain t: tl){
				t.drawSelf(g, sx, sy, tilesize);
				sx+=tilesize[0]; //draw all tiles
			}
			sy+=tilesize[1];
			sx = ox;//reset x
		}
		sy = oy; //reset y
		
		for (int i = 0; i<chars.length; i++){
			for (int y = 0; y<chars[i].length; y++){
				if (chars[i][y]!= null){
					chars[i][y].drawSelf(g, sx, sy, tilesize);
					//draw all characters
				}
				sx+=tilesize[0];
				
			}
			sy+=tilesize[1];
			sx = ox;
		}
	}

	public void tick() {
		//called every time turns change
		for (Character[] cl: chars){
			for (Character c: cl){
				if (c!=null){
					//Game.console.println(String.valueOf(c.getCurrentHP()));
					//c.setMoved(false); //resets move and attacked
					//c.setAttacked(false);
					c.tick(); //ticks character
					if (checkDeath(c)) //removes characters who die after tick
						Game.battle.removeChar(c);
				}
			}
			
		}
		// TODO Auto-generated method stub
		return;
	}
	
	public boolean checkDeath(Character c){
		if (c.getCurrentHP()<=0){ //testc current hp for death
			chars[c.getX()][c.getY()] = null; //removes dead characters
			Game.console.println(c.getName() + " has died.");
			return true;
		}
		return false;
	}
	
	public boolean effectTimerAllInRange(int x, int y, String team, int range, EffectTimer t){
		Hashtable<String, Terrain> spacesInRange = new Hashtable<String, Terrain>();
		getAttacks(y, x, range, team, spacesInRange);
		for(Terrain terr: spacesInRange.values()){
			int ty = terr.getX();
			int tx = terr.getY();
			if (chars[tx][ty] != null && chars[tx][ty].getTeam()!=team)
				chars[tx][ty].addEffect(t.copy(chars[tx][ty]));
				//terr.light(new Color(255,255,0));
		}
		return true;
	}
	
	public boolean effectTimerAllInRange(Character c, int x, int y, String team, int range, EffectTimer t){
		Hashtable<String, Terrain> spacesInRange = new Hashtable<String, Terrain>();
		getAttacks(y, x, range, team, spacesInRange);
		for(Terrain terr: spacesInRange.values()){
			int ty = terr.getX();
			int tx = terr.getY();
			if (chars[tx][ty] != null && chars[tx][ty].getTeam()!=team){
				for (EffectTimer i : chars[tx][ty].getEffs()){
					if (i.toString().equals("DeathMark")){
						i.addDamage(c.getAttack());
					}
				}
				chars[tx][ty].addEffect(t.copy(chars[tx][ty]));
				chars[tx][ty].getEffs().get(chars[tx][ty].getEffs().size()-1).tick();
				
				//terr.light(new Color(255,255,0));
			}
		}
		return true;
	}
	
	public boolean spaceEmpty(int x, int y){
		return (tiles[x][y].isPassable() && chars[x][y]==null);
	}
	
	public ArrayList<Character> returnAllCharacters(String team){
		ArrayList<Character> c = new ArrayList<>();
		for(Character[] ch: chars){
			for (Character cr: ch){
				if (cr!=null && cr.getTeam().equals(team)){
					c.add(cr);
				}
			}
		}
		return c;
	}
		
}