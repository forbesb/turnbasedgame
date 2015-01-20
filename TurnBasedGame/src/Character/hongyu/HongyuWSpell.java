package Character.hongyu;


import game.Character;
import game.EffectTimer;
import spells.Spell;
public class HongyuWSpell extends Spell{

	public HongyuWSpell(Character c) {
		super(false,true,false, c);
		setRange(getCaster().getRange());
		setMaxCD(4);
		setName("Piercing Laugh");
	}

	@Override
	public boolean activeEffect(Character target) {
		if (target.getTeam().equals(getCaster().getTeam())){
			target.addEffect(new EffectTimer(2, target, EffectTimer.SILENCE,0));
			target.addEffect(new EffectTimer(5, target, EffectTimer.DEFENCE, 10-target.getDefence()));
			target.addEffect(new EffectTimer(5, target, EffectTimer.MAGICDEFENCE, 10-target.getMagicres()));
			gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		}
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
