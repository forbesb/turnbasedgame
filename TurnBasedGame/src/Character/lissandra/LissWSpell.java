package Character.lissandra;

import game.Character;
import game.DamageModifier;
import game.EffectTimer;
import game.Poison;
import spells.Spell;

public class LissWSpell extends Spell{

	public LissWSpell(Character c) {
		super(false, true, false, c);
		setName("Hypothermia");
		setMaxCD(4);
		setRange(getCaster().getRange());
	}

	@Override
	public boolean activeEffect(Character target) {
		
		target.addEffect(new DamageModifier(4, target, 1.5));
		target.addEffect(new Poison(4, target, getCaster().getAttack()*2/5));
		target.addEffect(new EffectTimer(4, target, EffectTimer.SPEED, -3));
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	public String toString(){
		return "hypothermia";
	}
}
