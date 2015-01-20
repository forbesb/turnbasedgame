package game;

public class DeathMark extends EffectTimer{
	private int damage = 0;
	public DeathMark(Character c) {
		super(3, c, -1, 0);
		
	}
	public boolean tick(){
		if (getTime(true) >0){
			return false;
		}
		gui.Game.console.println("Brian's Death Mark Exploded.");
		getCharacter().defendSpell(damage);
		return true;
	}
	public void addDamage(int damage){
		this.damage += damage;
	}
	
	public String toString(){
		return "DeathMark";
	}
}
