package game;

public class SuperHeal extends EffectTimer{
	private int am;
	public SuperHeal(Character c, int a) {
		super(2, c, -1, a);
		this.am = a;
		// TODO Auto-generated constructor stub
	}
	public boolean tick(){
		getCharacter().heal(am); 
		return super.tick();
	}
	
	public String toString(){
		return "Super Heal";
	}
}
