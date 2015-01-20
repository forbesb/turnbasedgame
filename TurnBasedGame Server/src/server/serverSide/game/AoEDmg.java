package server.serverSide.game;


public class AoEDmg extends Poison{
	private String name;
	public AoEDmg(String name, int amount) {
		super(2, server.serverSide.game.Fake.fake, amount);
		this.name = name;
	}
	public AoEDmg(String name, Character c, int amount){
		super(2, c, amount);
		this.name = name;
	}
	public boolean tick(){
		if (this.getTime(true) >0){ //deals damage every turn
			getCharacter().getThread().sendConsole(getCharacter().getName()+" is damaged by "+toString());
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
		return name;
	}
}
