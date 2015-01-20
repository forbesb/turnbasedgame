package game;

import gui.Game;

public class Poison extends EffectTimer {
	private int damage;
	
	public Poison(int length, Character c, int amount) {
		super(length, c, EffectTimer.NONE, 0);
		damage = amount;//doesn't change a stat
		// TODO Auto-generated constructor stub
	}
	
	public boolean tick(){
		if (this.getTime(true) >0){ //deals damage every turn
			Game.console.println(getCharacter().getName()+" is affected by a stack of Poison.");
			getCharacter().takeDamage(damage);
			return false;
		}

		return true;
	}
	
	public EffectTimer copy(Character c){
		EffectTimer e = new Poison(getTime(false), c, damage);
		return e;
	}
	public int getDmg(){
		return damage;
	}
	
	public String toString(){
		return "Poison";
	}

}
