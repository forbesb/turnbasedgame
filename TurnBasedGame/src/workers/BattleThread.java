package workers;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.concurrent.ArrayBlockingQueue;

import javax.xml.rpc.ServiceException;

import client.login.Login;
import server.BrianSoapSoapBindingStub;
import server.Server;
import server.ServerServiceLocator;
import game.*;
import game.Character;
import gui.Game;

public class BattleThread extends Battle implements Runnable{
	//private static String url = "http://sxtesta.sys-x.com:8080/BrianSoap/services/BrianSoap";
	private String url = Login.url;
	private ArrayBlockingQueue<String> queue, kyubey; //kyubey is a reference to like 3 different things
										//one is a game, one is an anime, and one is queueB, the second queue on the server
							//queue == incoming from server -> sent to battle
							//kyubey == incoming from the battle -> send to server
	private String turn = "A", key;
	private Server server;
	private boolean running = true;
	public BattleThread(String key, String name, Character... charas) {
		super(name, charas); //this runs all the time as a thread holding a battle
		queue = new ArrayBlockingQueue<String>(60);
		kyubey = new ArrayBlockingQueue<String>(60);
		this.key = key;
		ServerServiceLocator sl = new ServerServiceLocator();
		try {
			server = sl.getBrianSoap(new URL(url));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		System.out.println("Thread made");

	}
	
	@Override
	public void run(){
		String aclient = "";
		String aserver = "";
		
		while (running){
			if (!kyubey.isEmpty()){
				aclient = kyubey.poll();
				try {
					if (server.actionPossible(aclient, key)){
						if (aclient.substring(0, 5).equals("spell")){
							String atemp = aclient.substring(6);
							long id = Long.parseLong(atemp.substring(0, atemp.indexOf(" ")));
							int spellId = Integer.parseInt(atemp.substring(atemp.indexOf(":")+1));
							getCharacter(id).getSpell(spellId).setOnCD();;
						}
						server.sendAction(aclient, key);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			try {
				if (server.hasNewMoves(key)){
					aserver = server.getNextAction(key);
					queue.put(aserver);
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean hasNextAction(){
		return !queue.isEmpty();
	}
	
	public String getNextAction(){
		return queue.poll();
	}
	
	public boolean move(Character c, Terrain t){
		//send move action
		String action = "move ";
		action+=c.getId()+" ";
		
		action+=t.getX()+":";
		action+=t.getY();
		
		//System.out.println(action);
		//boolean success = server.send(action);
		try {
			kyubey.put(action);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		/*
		try {
			queue.put(action);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}*/
		
		return true;
	}
	
	public boolean attack(Character attacker, Character target){
		String action = "attack ";
		action += attacker.getId()+" ";
		action += target.getId();
		/*
		//System.out.println(action);
		System.out.println("tried to send");
		try {
			if (server.actionPossible(action, key)){
				System.out.println("actually sent apparently");
				return server.sendAction(action, key);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		*/
		try {
			kyubey.put(action);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		try {
			queue.put(action);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		return true;
	}
	
	public boolean spell(Character caster, Character target, int spellId){
		//add spell action to queue. TODO: Change this to specific effects?
		String action = "spell ";
		action+= caster.getId()+" ";
		action+= target.getId()+":";
		action+= spellId;
		//System.out.println(action);
		//boolean success = server.send(action);
		try {
			kyubey.put(action);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			if (server.actionPossible(action, key)){
				System.out.println("spell sent");
				caster.getSpell(spellId).setOnCD();
				return server.sendAction(action, key);
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		/*
		try {
			queue.put(action);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		return true;
	}

	public String getTurn() {
		//return server.getTurn()?
		return turn;
	}

	public void setTurn(String turn) {
		Game.topbar.setTurn(turn);
		this.turn = turn;
	}
	
	public void tickIfNotMoved(String turn){
		for (Character c: this.getChars()){
			if (!c.isMoved() && c.getTeam() != turn){ //tick all characters of the team if not moved
				getM().effect(c);
				//c.setMoved(true);
			}
		}
	}
	
	public void switchTurn(){
		//put switch action into queue
		String action = "switch 0 0";
		/*
		System.out.println();
		System.out.println("TRIED TO SWITCH!!!!");
		System.out.println();
		try {
			if (server.actionPossible(action, key)){
				System.out.println("PRE-SENT!!");
				server.sendAction(action, key);
				System.out.println("SENT!!!");
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println();
		System.out.println("DONE!!!!!!");
		System.out.println();
		*/
		try {
			kyubey.put(action);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reMoveAndAttack(String turn) {
		
		
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void disconnect() {
		String action = "DISCONNECT";
		try {
			kyubey.put(action);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
