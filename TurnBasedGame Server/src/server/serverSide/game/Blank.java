package server.serverSide.game;



import server.serverSide.workers.BattleThread;

public class Blank extends Terrain {

	public Blank(int x2, int y2, BattleThread b) {
		super(x2, y2, b);
		setDef(5);
		setPassable(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void terrEffect(Character c) {
		// TODO Auto-generated method stub

	}

}
