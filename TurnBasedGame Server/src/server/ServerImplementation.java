package server;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.ImageIcon;

import server.serverSide.Character.annie.Annie;
import server.serverSide.Character.brian.Brian;
import server.serverSide.Character.caitlyn.Caitlyn;
import server.serverSide.Character.hongyu.Hongyu;
import server.serverSide.Character.leesin.Leesin;
import server.serverSide.Character.leona.Leona;
import server.serverSide.Character.lissandra.Lissandra;
import server.serverSide.Character.sona.Sona;
import server.serverSide.Character.soraka.Soraka;
import server.serverSide.Character.taric.Taric;
import server.serverSide.Character.tryndamere.TrondoFiora;
import server.serverSide.Character.vlassali.Vladimir;
import server.beans.*;
import server.database.DBAccess;
import server.serverSide.game.Character;
import server.serverSide.game.Map;
import server.serverSide.workers.*;

public class ServerImplementation {
	private static Hashtable<String, User> keyToUser = new Hashtable<>();
	private static Hashtable<String, BattleThread> keyToBattle = new Hashtable<>();
	private static ArrayBlockingQueue<User> queue = new ArrayBlockingQueue<User>(50);
	private static Random keyGen = new Random(System.currentTimeMillis());
	
	private static DBAccess dba = new DBAccess();
	private static Connection conn = dba.getConnection();
	public ServerImplementation() {
		
	}
	
	
	public void initializeSearch(String key, Team t){
		//TODO: search without team? because there's already a team
		User you = keyToUser.get(key);
		if (you==null) return;
		you.setT(t);
		you.setInBattle(false);
		if (queue.peek()!=null){
			 User opponent = queue.poll();
			 opponent.setInBattle(true);
			 you.setInBattle(true);
			 Character[] charas = new Character[24];
			 Team ta = getTeam(you.getUsername());
			 int x = 0; int y = 0;
			 String tt = "A";
			 for (int i = 0; i<ta.getChars().length; i++){
				 Character c;
				 switch (ta.getChars()[i]){
					case "Annie":
						c = new Annie(x, y, tt);
						break;
					case "Brian":
						c = new Brian(x, y, tt);
						break;
					case "Caitlyn":
						c = new Caitlyn(x, y, tt);
						break;
					case "Hongyu":
						c = new Hongyu(x, y, tt);
						break;
					case "Leesin":
						c = new Leesin(x, y, tt);
						break;
					case "Leona":
						c = new Leona(x, y, tt);
						break;
					case "Lissandra":
						c = new Lissandra(x, y, tt);
						break;
					case "Sona":
						c = new Sona(x, y, tt);
						break;
					case "Soraka":
						c = new Soraka(x, y, tt);
						break;
					case "Taric":
						c = new Taric(x, y, tt);
						break;
					case "Tryndamere":
						c = new TrondoFiora(x, y, tt);
						break;
					case "Vladimir":
						c = new Vladimir(x, y, tt);
						break;
					default: c = new Character(150,10,10,10,3,5,0,0,tt, "Testeroony"+i);
							break;
				}
				 charas[i] = c;
			 }
			 Team tb = getTeam(opponent.getUsername());
			 tt = "B";
			 for (int i = 0; i<tb.getChars().length; i++){
				 Character c;
				 
				 switch (tb.getChars()[i]){
					case "Annie":
						c = new Annie(x, y, tt);
						break;
					case "Brian":
						c = new Brian(x, y, tt);
						break;
					case "Caitlyn":
						c = new Caitlyn(x, y, tt);
						break;
					case "Hongyu":
						c = new Hongyu(x, y, tt);
						break;
					case "Leesin":
						c = new Leesin(x, y, tt);
						break;
					case "Leona":
						c = new Leona(x, y, tt);
						break;
					case "Lissandra":
						c = new Lissandra(x, y, tt);
						break;
					case "Sona":
						c = new Sona(x, y, tt);
						break;
					case "Soraka":
						c = new Soraka(x, y, tt);
						break;
					case "Taric":
						c = new Taric(x, y, tt);
						break;
					case "Tryndamere":
						c = new TrondoFiora(x, y, tt);
						break;
					case "Vladimir":
						c = new Vladimir(x, y, tt);
						break;
					default: c = new Character(150,10,10,10,3,5,0,0,tt, "Testeroony"+i);
							break;
				}
				 charas[i+ta.getChars().length] = c;
			 }
			
			 BattleThread b = new BattleThread("E:\\Brian\\swampy.map", opponent.getKey()	,you.getKey(), charas);
			 keyToBattle.put(you.getKey(), b);
			 keyToBattle.put(opponent.getKey(), b);
			 new Thread(b).start();
		} else {
			//I'm pretty sure if 50 people all try to search at the exact same time
			//this will break. Maybe. I really doubt that will happen though.
			queue.add(you);
		}
	}
	
	public boolean hasOpponent(String key){
		return keyToUser.get(key).isInBattle();
	}
	
	public Team initBattle(String key){
		try{
			BattleThread b = null;
			while (b == null){
				b = keyToBattle.get(key);
				
			}
			Team t =  b.returnAllChars();
			return t;
		} catch (Exception e){
			e.printStackTrace();
		}
		return new Team(new String[]{"test"});
	}
	
	public String login(String name, byte[] password){
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "select encryptedPass as password from Users where name='"+name+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()){
				byte[] encget = rs.getBytes("password");
				if (MessageDigest.isEqual(encget, password)){
					long ikey = keyGen.nextInt(899999999)+100000000;
					String skey = ikey+"";
					User u = new User();
					u.setUsername(name);
					u.setInBattle(false);
					u.setT(getTeam(name));
					u.setKey(skey);
					keyToUser.put(skey, u);
					return skey;
				}
			}
				
		} catch (SQLException e) {
			System.out.println("gg");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "failed";
	}
	
	public boolean createAccount(String name, byte[] password){
		String prepared = "insert into Users (name, encryptedPass, wins, losses, recentGames) "
				+"values (?,?,?,?,?)";
		String teamStatement = "insert into Teams (name, chars) values (?,?)";
		String findStatement = "select name from Users where name = '"+name+"'";
		try {
			Statement valid = conn.createStatement();
			ResultSet rs = valid.executeQuery(findStatement);
			if (rs.next()){
				return false;
			}
			PreparedStatement stmt = conn.prepareStatement(prepared);
			stmt.setString(1, name);
			stmt.setBytes(2, password);
			stmt.setInt(3, 0);
			stmt.setInt(4, 0);
			stmt.setString(5, "");
			stmt.execute();
			PreparedStatement tstmt = conn.prepareStatement(teamStatement);
			tstmt.setString(1, name);
			tstmt.setString(2, "Brian, Caitlyn, Hongyu, Leesin, Lissandra, Leona, Annie, Taric, Vladimir, Soraka");
			tstmt.execute();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}
	
	public Profile getProfile(String username){
		String getWaL = "select wins, losses from Users where name='"+username+"'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getWaL);
			if (rs.next()){
				int wins = rs.getInt("wins");
				int losses = rs.getInt("losses");
				return new Profile("bear.png", losses, username, wins);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new Profile("bear.png", 0, username, 1);
	}
	
	
	
	public Team getTeam(String username){
		String[] team = new String[]{"No team exists for this User"};
		try {
			Statement stmt = conn.createStatement();
			String query = "select chars as team from Teams where name='"+username+"'";
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()){
				String result = rs.getString("team");
				team = result.split(", ");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new Team(team);
	}
	
	public boolean sendTeam(Team t, String key){
		String[] tarray = t.getChars();
		String st = "";
		for (String character: tarray){
			st+=character+", ";
		}
		st = st.substring(0, st.length()-2);
		User u = keyToUser.get(key);
		u.setT(t);
		String sendStmt = "update Teams set chars='"+st+"' where name='"+u.getUsername()+"'";
		System.out.println(sendStmt);
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sendStmt);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public MapB getMap(String key){
		
		return /*new MapB();*/Map.generateBeanFromMap(Map.generateMapFromFile("swampy.map", null));
	}
	
	public boolean hasNewMoves(String key){
		//hasNextAction
		try {
			BattleThread b = keyToBattle.get(key);
			
			return b.hasNextAction(key);
		} catch (Exception e){
			return false;
		}
	}
	
	public String getNextAction(String key){
		BattleThread b = keyToBattle.get(key);
		String action = b.getNextAction(key);
		if (action.length()>=7 && (action.substring(0, 7).equals("victory"))){
			keyToBattle.remove(key);
			User u = keyToUser.get(key);
			u.setInBattle(false);
			System.out.println("Removed! (won)");
			dba.boostWins(u.getUsername(), conn);
		} else if (action.length()>=6 && action.substring(0, 6).equals("defeat")){
			keyToBattle.remove(key);
			User u = keyToUser.get(key);
			u.setInBattle(false);
			System.out.println("removed! (lost)");
			dba.boostLosses(u.getUsername(), conn);
		}
		
		
		return action;
	}
	
	public boolean actionPossible(String action, String token){
		//actionPossible
		try{
		BattleThread b = keyToBattle.get(token);
		return b.actionPossible(action, token);
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendAction(String action, String token){
		//takeAction
		try{
			BattleThread b = keyToBattle.get(token);
			return b.takeAction(action, token);
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
