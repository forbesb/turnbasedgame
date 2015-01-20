package game;

public class UnAffectable extends EffectTimer{

	public UnAffectable(int length, Character c) {
		super(length, c, -1, 0);
	}
	public String toString(){
		return "UnAffectable";
	}
}
