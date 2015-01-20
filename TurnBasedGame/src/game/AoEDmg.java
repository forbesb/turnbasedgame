package game;

import gui.Game;

public class AoEDmg extends Poison{
	private String name;
	public AoEDmg(String name, int amount) {
		super(2, gui.Game.fake, amount);
		this.name = name;
	}
	public AoEDmg(String name, Character c, int amount){
		super(2, c, amount);
		this.name = name;
	}
	public boolean tick(){
		if (this.getTime(true) >0){ //deals damage every turn
			Game.console.println(getCharacter().getName()+" is damaged by "+toString());
			getCharacter().takeDamage(getDmg());
			return false;
		}
		System.out.println('a');
		return true;
	}
	public EffectTimer copy(Character c){
		  EffectTimer e = new AoEDmg(name, c, getDmg());
		  return e;
	}
	public String toString(){
		System.out.println(name);
		return name;
	}
}
