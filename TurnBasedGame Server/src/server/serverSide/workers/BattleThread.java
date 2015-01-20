package server.serverSide.workers;

import server.beans.Team;
import server.serverSide.game.*;
import server.serverSide.game.Character;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;

public class BattleThread extends Battle implements Runnable{
	private ArrayBlockingQueue<String> queue, queueB;
	private String keyA, keyB;
	private String turn = "A";
	
	public BattleThread(String mapname, String kA, String kB, Character... charas) {
		super(mapname, charas); //this runs all the time as a thread holding a battle
		super.init(this);
		for (Character c: getChars()){
			c.setThread(this);
		}
		queue = new ArrayBlockingQueue<String>(60); //apparently 30 was too small
		queueB = new ArrayBlockingQueue<String>(60);
		try {
			queue.put("A");
			queueB.put("B");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		keyA = kA;
		keyB = kB;
		//System.out.println("Thread made");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run(){
		//doesn't really do anything right now but may be used later
		//System.out.println("Thread started");
	}
	
	public boolean hasNextAction(String key){
		if (key.equals(keyA)){
			return queue.peek()!=null;
		} else if (key.equals(keyB)){
			return queueB.peek()!=null;
		} else {
			
			System.out.println("Incorrect key - does not match either key. Given: "+key+", A: "+keyA+" B:"+keyB); 
			return false;
		}
	}
	
	public String getNextAction(String key) {
		//System.out.println("getNextAction");
		if (key.equals(keyA)){
			//System.out.println("got: "+queue.peek());
			return queue.poll();
		} else if (key.equals(keyB)){
			//System.out.println("got: "+queueB.peek());
			return queueB.poll();
		} else {
			System.out.println("Incorrect key - does not match either key. Given: "+key+", A: "+keyA+" B:"+keyB); 
			return "failed"; //TODO: deal with this in client too
		}
	}
	
	public boolean actionPossible(String action, String key){
		if (action.equals("DISCONNECT")){
			System.out.println("D/Ced");
			if (key.equals(keyA)){
				try {
					queueB.put("victory 0 0");
					queue.put("defeat 0 0");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					queue.put("victory 0 0");
					queueB.put("defeat 0 0");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return false;
		}
		System.out.println("actionPossible?"+action);
		String atype = action.substring(0, action.indexOf(" "));
		action = action.substring(action.indexOf(" ") + 1);
		long id = Long.parseLong(action.substring(0, action.indexOf(" ")));
		Character c = getCharacter(id); 
		//System.out.println(c.toString());
		if (turn == "A" && !key.equals(keyA)){
			return false;
		} else if (turn == "B" && !key.equals(keyB)){
			return false;
		}
		long tid;
		int x, y;
		switch (atype){
			case "move":
				String xy = action.substring(action.indexOf(" ")+1);
				x = Integer.parseInt(xy.substring(0, xy.indexOf(":")));
				y = Integer.parseInt(xy.substring(xy.indexOf(":")+1));
				Terrain t = getM().getTerrain(y, x);
				Hashtable<String, Terrain> ht = new Hashtable<>();
				/*
				System.out.println(getM().getLength());
				System.out.println(getM().getCharMap());
				*/
				
				getM().getPossibleMoves(c.getY(), c.getX(), c.getSpeed(), c.getTeam(), ht);
				/*
				System.out.println(c.getSpeed());
				System.out.println();
				for (String k: ht.keySet()){
					System.out.print(k+",");
				}
				System.out.println();
				System.out.println();
				System.out.println(c.getX()+","+c.getY());
				System.out.println("end of move: "+(ht.containsKey(xy) && !c.isMoved()));
				System.out.println("Contains key "+xy+"?"+ht.containsKey(xy));
				String reverse = y+":"+x;
				System.out.println("Contains reverse"+reverse+"?"+ht.containsKey(reverse));
				System.out.println("is moved?"+c.isMoved());
				System.out.println();
				*/
				return ht.containsKey(xy) && !c.isMoved();
			case "attack":
				tid = Long.parseLong(action.substring(action.indexOf(" ") + 1));
				Character targ = getCharacter(tid);
				x = c.getX();
				y = c.getY();
				String txy = targ.getY()+":"+targ.getX();
				System.out.println("target xy "+txy);
				System.out.println("your xy?"+y+":"+x);
				Hashtable<String, Terrain> hash = new Hashtable<>();
				getM().getAttacks(y, x, c.getRange(), c.getTeam(), hash);
				System.out.println("Attack success??");
				System.out.println(hash.containsKey(txy) && !c.hasAttacked());
				return hash.containsKey(txy) && !c.hasAttacked();
			case "spell":
				tid = Long.parseLong(action.substring(action.indexOf(" ") + 1, action.indexOf(":")));
				int spellId = Integer.parseInt(action.substring(action.indexOf(":")+1));
				Character target = getCharacter(tid);
				System.out.println((c.getSpell(spellId).inRange(target) && !c.hasAttacked()));
				return c.getSpell(spellId).inRange(target) && !c.hasAttacked();
			case "switch":
				System.out.println("switch possible?"+ ((turn == "A" && key != keyA) || (turn == "B" && key != keyB)));
				return (turn == "A" && key != keyA) || (turn == "B" && key != keyB);	
		}
		
		return false;
		
	}
	
	public boolean takeAction(String action, String token){
		
		
		if (actionPossible(action, token)){
			//I can use a simple if-else because actionPossible will deal with incorrect keys
			return parseAction(action, token.equals(keyA) ? "A":"B");
		}
		
		return false;
		
	}
	
	public boolean parseAction(String action, String turn){
		String atype = action.substring(0, action.indexOf(" "));
		action = action.substring(action.indexOf(" ") + 1);
		long id = Long.parseLong(action.substring(0, action.indexOf(" ")));
		
		
		
		//System.out.println(atype);
		//System.out.println(id);
		
		Character c = getCharacter(id);
		 //I was dumb enough to mix x and y randomly, nice job me
		long tid;
		switch (atype){
			case "move":
				String xy = action.substring(action.indexOf(" ")+1);
				int x = Integer.parseInt(xy.substring(0, xy.indexOf(":")));
				int y = Integer.parseInt(xy.substring(xy.indexOf(":")+1));
				Terrain t = getM().getTerrain(y, x);
				getM().move(c, t);
				c.setMoved(true);
				sendMove(c, t);
				getM().effect(c);
				System.out.println("sent move");
				System.out.println("new Position: "+c.getX()+","+c.getY());
				return true;
				
			case "attack":
				tid = Long.parseLong(action.substring(action.indexOf(" ") + 1));
				getCharacter(id).attack(getCharacter(tid)); 
				getCharacter(id).setAttacked(true);
				if (getM().checkDeath(getCharacter(tid))){
					sendKill(getCharacter(tid));
					removeChar(getCharacter(tid));
					
				}if (getM().checkDeath(getCharacter(id))){
					sendKill(getCharacter(tid));
					removeChar(getCharacter(id));
				}
				
				
				return true;
			case "spell":
				tid = Long.parseLong(action.substring(action.indexOf(" ") + 1, action.indexOf(":")));
				int spellId = Integer.parseInt(action.substring(action.indexOf(":")+1));
				getCharacter(id).getSpell(spellId).activeEffect(getCharacter(tid)); 
				getCharacter(id).getSpell(spellId).setOnCD();
				
				getCharacter(id).setAttacked(true);
				if (getM().checkDeath(getCharacter(tid))){
					sendKill(getCharacter(tid));
					removeChar(getCharacter(tid));
					
				}if (getM().checkDeath(getCharacter(id))){
					sendKill(getCharacter(id));
					removeChar(getCharacter(id));
				};
				
				return true;
			case "switch":
				tickIfNotMoved(turn);
				switchTurn();
				return true;
				
			default: return false;
		}
	}
	
	public boolean sendMove(Character c, Terrain t){
		//send move action
		String action = "move ";
		action+=c.getId()+" ";
		
		action+=t.getX()+":";
		action+=t.getY();
		
		System.out.println("Send Move: "+action);
		//boolean success = server.send(action);
		try {
			queue.put(action);
			queueB.put(action);
			System.out.println(queue.peek());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean sendDamage (Character c, int dmg){
		String action = "damage ";
		action+=c.getId()+" ";
		action+=dmg;
		try {
			queue.put(action);
			queueB.put(action);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	public boolean sendKill (Character c){
		String action = "kill ";
		action+=c.getId()+ " ";
		
		try {
			queue.put(action);
			queueB.put(action);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (testVictory("A")){
			try {
				queue.put("victory 0 0");
				queueB.put("defeat 0 0");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		} else if (testVictory("B")){
			try {
				queueB.put("victory 0 0");
				queue.put("defeat 0 0");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	public boolean sendEffect(Character c, int eff, int amount, int length){
		String action = "effect ";
		action+=c.getId();
		action += " " +eff;
		action += "|" +amount;
		action += "/" +length;
		
		try {
			queue.put(action);
			queueB.put(action);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
	
	public boolean sendConsole(String text){
		String action = "console ";
		action+= "0 \""+text+"\"";
		try {
			queue.put(action);
			queueB.put(action);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	public boolean sendUnmove(long id){
		String action = "unmove ";
		action += id+" ";
		try {
			queue.put(action);
			queueB.put(action);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String getTurn() {
		//return server.getTurn()?
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}
	
	public void tickIfNotMoved(String turn){
		for (Character c: this.getChars()){
			if (!c.isMoved() && c.getTeam().equals(turn)){ //tick all characters of the team if not moved
				getM().effect(c);
				//c.setMoved(true);
			}
			
		}
	}
	
	public void switchTurn(){
		//put switch action into queue
		if (turn=="A"){
			turn = "B";
			String action = "switch 0 0"; 
			try {
				queue.put(action);
				queueB.put(action);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			turn = "A";
			String action = "switch 0 0";
			try {
				queue.put(action);
				queueB.put(action);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getM().tick();
		}
		System.out.println(queue.size());
		System.out.println(queueB.size());
	}
	
	public Team returnAllChars(){
		Team t = new Team();
		ArrayList<Character> chs = this.getChars();
		String[] chars = new String[chs.size()];
		String character;
		Character c;
		for (int i = 0; i< chars.length; i++){
			c = chs.get(i);
			//"[name] [id] [team] [x]:[y]"
			character = c.getName()+" ";
			character += c.getId()+" ";
			character+= c.getTeam()+" ";
			character += c.getX()+":"+c.getY();
			
			chars[i] = character;
		}
		t.setChars(chars);
		
		return t;
	}
	
	public boolean testVictory(String team){
		for (Character c: getChars()){
			if (!c.getTeam().equals(team) && c.getCurrentHP()>0){
				System.out.println("list size?" +getChars().size());
				return false;
			}
		}
		
		return true;
	}

}
