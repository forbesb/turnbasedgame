package client.login;

import java.rmi.RemoteException;

import server.*;
import server.beans.Team;
import workers.GameWaitThread;

public class GameGenerator {

	public GameGenerator(Server s, String key, String username) {
		Team team;
		try {
			team = s.getTeam(username);
			GameWaitThread gt = new GameWaitThread(s, key, team);
			gt.start();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
