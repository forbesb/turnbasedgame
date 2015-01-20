package game;

import gui.Game;

public class DamageModifier extends EffectTimer{
	private double amount = 1;
	public DamageModifier(int length, Character c,double amount) {
		super(length, c, EffectTimer.NONE,0);
		this.amount = amount;
	}
	public boolean tick(){
		if (this.getTime(true) >0){ //deals damage every turn
			getCharacter().setModifier(amount);
			return false;
		}
		getCharacter().setModifier(1);
		return true;
	}
	public EffectTimer copy(Character c){
		  EffectTimer e = new DamageModifier(getTime(false), c, amount);
		  return e;
	}
	public String toString(){
		return "Damage Modifier";
	}
}
