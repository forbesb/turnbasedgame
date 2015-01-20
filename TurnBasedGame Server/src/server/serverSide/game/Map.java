//TODO: build a map editor jfc this sucks to look at
package server.serverSide.game;




import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import server.beans.MapB;
import server.serverSide.workers.*;

public class Map {
	public static boolean USEMAPPOSITIONS = true;
	public static boolean USECHARPOSITIONS = false;
	private int length, width, pointerx, pointery;
	private String[] startposa, startposb;
	private Terrain[][] tiles;
	private Character[][] chars;
	
	private BattleThread thread;
	
	public Map(int l, int w){
		length = l;
		width =w;
		tiles = new Terrain[l][w];
		chars = new Character[l][w];
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
		chars = new Character[1][1]; //default, should never be used
		tiles[0][0] = new Grass(0,0, null);//the null will cause problems
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
	
	public static Map generateMapFromFile(String filename, BattleThread thr) {
		//TODO: Properly try-catch this and don't be lazy
		BufferedReader fre;
		Map base;
		
		try {
			fre = new BufferedReader(new FileReader(new File(filename)));
			/* 
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
						base.add(new Grass(xpos, ypos, thr));
					else if (ch=='R')
						base.add(new Rock(xpos, ypos, thr));
					else if (ch=='F')
						base.add(new Fort(xpos, ypos, thr));
					else if (ch=='S')
						base.add(new Swamp(xpos, ypos, thr));
					else if (ch=='B'){
						base.add(new Blank(xpos, ypos, thr));
					}
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
	
	public void remove(Character c){
		chars[c.getX()][c.getY()] = null; //remove char from the list
	}
	
	public boolean move(Character c, int newx, int newy){
		if (newx>=length || newy>=width){
			return false;
		}
		
		/*hasPath(c, newx, newy)*/ //we already checked for possible moves, so...used to be an if here but not needed
		chars[c.getX()][c.getY()] = null;
		c.setX(newx); //move character
		c.setY(newy);
		System.out.println("NEW X?Y: "+c.getX()+","+c.getY());
		c.setSpace(tiles[newx][newy]);
		chars[newx][newy] = c; //change its space
		return true;
		
	}
	
	public boolean move(Character c, Terrain t){
		return move(c, t.getY(), t.getX());
	}
	
	
	
	public void getPossibleMoves(int x, int y, int range, String team, Hashtable<String, Terrain> target){
		//recursive path test


		//System.out.println("or Friendly?"+chars[x][y].getTeam().equals(team));
		if (range>=0 && tiles[y][x].isPassable() && (chars[y][x]==null || chars[y][x].getTeam().equals(team))){
			//if it is a valid tile
			
			if (!target.contains(tiles[y][x])) //and not in the list already
				target.put(x+":"+y, tiles[y][x]); //add it
			
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
		
		if (range>=0 && tiles[y][x].isPassable()){ //similar to the last one, but it can go on characters
			//System.out.println(chars[x][y]);
			
			if (!target.contains(tiles[y][x])) // && (chars[x][y]!=null && !chars[x][y].getTeam().equals(team)))
				target.put(x+":"+y, tiles[y][x]); //same as pathfind
			
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

	
	public void effect(Character c){
		c.getSpace().terrEffect(c); //calls the terrain effect on the character
		System.out.println("effected"+c.getName());
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
					//chars[i][y].drawSelf(g, sx, sy, tilesize);
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
					c.tick(); //ticks character
					if (checkDeath(c)){ //removes characters who die after tick
						thread.sendKill(c);
						thread.removeChar(c);
					}
						
				}
			}
			
		}
		// TODO Auto-generated method stub
		return;
	}
	
	public boolean checkDeath(Character c){
		if (c.getCurrentHP()<=0){ //tests current hp for death
			System.out.println(c.getCurrentHP());
			chars[c.getX()][c.getY()] = null; //removes dead characters
			return true;
		}
		return false;
	}
	
public boolean effectTimerAllInRange(int x, int y, String team, int range, EffectTimer t){
		
		Hashtable<String, Terrain> spacesInRange = new Hashtable<String, Terrain>();
		getAttacks(x, y, range, team, spacesInRange);
		System.out.println("Size?"+spacesInRange.size());
		for(Terrain terr: spacesInRange.values()){
			int tx = terr.getX();
			int ty = terr.getY();
			System.out.println(chars[ty][tx]!= null);
			if (chars[ty][tx] != null) System.out.println(chars[ty][tx].getTeam());
			System.out.println("actual team?"+team);
			if (chars[ty][tx] != null && !chars[ty][tx].getTeam().equals(team)){
				EffectTimer tcopy = t.copy(chars[ty][tx]);
				chars[ty][tx].addEffect(tcopy);
				System.out.println("SENT ONE");

			}
			System.out.println("DIDN'T SEND ONE!");
				
		//terr.light(new Color(255,255,0));
		}
		return true;
	}

	public boolean effectTimerAllInRange(Character c, int x, int y, String team, int range, EffectTimer t){
		Hashtable<String, Terrain> spacesInRange = new Hashtable<String, Terrain>();
		getAttacks(y, x, range, team, spacesInRange);
		System.out.println("Size?"+spacesInRange.size());
		for(Terrain terr: spacesInRange.values()){

			int ty = terr.getX();
			int tx = terr.getY();
			System.out.println(tx+","+ty);
			if (chars[ty][tx] != null && chars[ty][tx].getTeam()!=team){
				for (EffectTimer i : chars[ty][tx].getEffs()){
					if (i.toString().equals("DeathMark")){
						i.addDamage(c.getAttack());
					}
				}
				EffectTimer tcopy = t.copy(chars[ty][tx]);
				chars[ty][tx].addEffect(tcopy);
				chars[ty][tx].getEffs().get(chars[ty][tx].getEffs().size()-1).tick();
				System.out.println("SENT ONE");

				//terr.light(new Color(255,255,0));
			}
		}
		return true;
	}

	public boolean spaceEmpty(int x, int y){
		System.out.println(tiles[x][y].isPassable()+"is passable and"+chars[x][y]);
		return (tiles[x][y].isPassable() && chars[x][y]==null);
	}

	public static MapB generateBeanFromMap(Map m) {
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
					System.out.println("BLANK");
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
	
	public static Map generateMapFromBean(MapB bean, BattleThread b){
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
					base.add(new Grass(xpos, ypos,b));
				else if (ch.equals("R"))
					base.add(new Rock(xpos, ypos, b));
				else if (ch.equals("F"))
					base.add(new Fort(xpos, ypos, b));
				else if (ch.equals("S"))
					base.add(new Swamp(xpos, ypos, b));
				else if (ch.equals("B"))
					base.add(new Blank(xpos, ypos, b));
				xpos++;
			}
			ypos++;
			xpos=0;
		}
		
		
		base.startposa= bean.getStartposa();
		
		base.startposb = bean.getStartposb();
		
		
		return base;	

	}

	public BattleThread getThread() {
		return thread;
	}

	public void setThread(BattleThread thread) {
		this.thread = thread;
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
	
	public void returnAllSpaces(Hashtable<String, Terrain> target){
		for (int i= 0; i< length; i++){
			for (int y = 0; y<width; y++){
				target.put(i+":"+y, tiles[i][y]); //add it
			}
		}
	}
	
}