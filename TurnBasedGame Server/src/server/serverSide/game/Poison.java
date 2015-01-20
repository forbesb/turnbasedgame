package server.serverSide.game;


public class Poison extends EffectTimer {
	private int damage;
	
	public Poison(int length, Character c, int amount) {
		super(length, c, EffectTimer.NONE, 0);
		damage = amount;//doesn't change a stat
		// TODO Auto-generated constructor stub
	}
	
	public boolean tick(){
		if (this.getTime(true) >0){ //deals damage every turn
			getCharacter().takeDamage(damage);
			return false;
		}

		return true;
	}
	
	public int getDmg(){
		return damage;
	}
}
