package server;

import game.Map;
import server.beans.*;

public class ServerImplementation {
	
	public ServerImplementation() {
	}
	
	
	public void initializeSearch(String key, Team t){
		
	}
	
	public boolean hasOpponent(String key){
		
		return true;
	}
	
	public MapB getMap(String key){
		
		return Map.generateBeanFromMap(Map.generateMapFromFile("swampy.map"));
	}
	
	public Team initBattle(String key){
		return new Team(new String[]{"test"});
	}
	
	
	
	public String login(String name, byte[] password){
		
		
		return "key would go here";
	}
	
	public boolean createAccount(String name, byte[] password){
		
		return true;
	}
	
	public Profile getProfile(String username){
		return new Profile("bear.png", 0, username, 1);
	}
	
	
	
	public Team getTeam(String username){
		return new Team(new String[]{"Vlassali", "Trondofiora"});
	}
	
	public boolean sendTeam(Team t, String key){
		
		
		return true;
	}
	
	
	
	public boolean hasNewMoves(String token){
		//hasNextAction
		return true;
	}
	
	public String getNextAction(String token){
		//getNextAction
		return "damage 0 30";
	}
	
	public boolean actionPossible(String action, String token){
		//actionPossible
		return true;
	}
	
	public boolean sendAction(String action, String token){
		//takeAction
		return true;
	}

}
