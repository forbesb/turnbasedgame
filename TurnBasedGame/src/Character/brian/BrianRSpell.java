package Character.brian;

import game.Character;
import game.DeathMark;
import spells.Spell;

public class BrianRSpell extends Spell{

	public BrianRSpell(Character c) {
		super(false, true, false, c);
		this.setRange(7);
		this.setName("Death Mark");
		this.setMaxCD(8);
		setOnCD();
	}

	@Override
	public boolean activeEffect(Character target) {
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		target.addEffect(new DeathMark(target));
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
