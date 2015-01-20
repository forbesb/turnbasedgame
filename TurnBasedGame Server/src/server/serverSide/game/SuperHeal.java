package server.serverSide.game;

public class SuperHeal extends EffectTimer{
	private int am;
	public SuperHeal(Character c, int a) {
		super(2, c, -1, a);
		this.am = a;
		// TODO Auto-generated constructor stub
	}
	public boolean tick(){
		getCharacter().heal(am); 
		getCharacter().getThread().sendDamage(getCharacter(), -am);
		return super.tick();
	}
}
