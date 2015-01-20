package workers;

import java.rmi.RemoteException;
import java.util.ArrayList;

import server.Server;
import server.ServerImplementation;
import server.beans.*;
import game.Character;

public class GameWaitThread extends Thread {
	private String akey;
	private boolean ready;
	private Server server;
	private Team team, opteam;
	public GameWaitThread(Server s, String key, Team t) {
		// TODO Auto-generated constructor stub
		server = s;
		akey = key;
		team = t;
	}

	public void run(){
		ready = false;
		try {
			team = new Team();
			System.out.println(akey);
			System.out.println(team);
			server.initializeSearch(akey, team);
			while (!ready){
				System.out.println("not ready");
				ready = server.hasOpponent(akey);
				System.out.println(ready);
			}
			opteam = server.initBattle(akey);
			gui.Game.createSelf(opteam, akey);
			System.out.println("done? O.o");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public boolean isReady(){
		return ready;
	}
	
	public String getKey(){
		return akey;
	}
	
	public Team getOpTeam(){
		return opteam;
	}

}
