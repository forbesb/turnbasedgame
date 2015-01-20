package spells;

import game.Character;
import game.EffectTimer;

public class FakeSpell extends Spell {

	public FakeSpell(boolean p, boolean a, boolean s, Character c) {
		super(p, a, s, c); //used to create fake spell buttons
		setName("fake");
		setRange(5);
		setMaxCD(3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activeEffect(Character target) {
		// TODO Auto-generated method stub
		target.getMap().effectTimerAllInRange(target.getY(),target.getX(), target.getTeam() == "A" ? "B":"A", 5, new EffectTimer(1, gui.Game.fake, EffectTimer.ATTACK, 1000));
		return true;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
