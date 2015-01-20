package Character.hongyu;


import game.Character;
import game.EffectTimer;
import spells.Spell;
import game.Poison;
import gui.Game;
public class HongyuQSpell extends Spell{

	public HongyuQSpell(Character c) {
		super(false,true,false, c);
		setRange(getCaster().getRange());
		setMaxCD(4);
		setName("Mocking Laugh");
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new Poison(3, target, 300));
		target.addEffect(new EffectTimer(3, target, EffectTimer.ATTACK,-100));
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
