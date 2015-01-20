package Character.taric;

import game.AoEDmg;
import game.Character;
import game.EffectTimer;
import spells.Spell;

public class TaricWSpell extends Spell{

	public TaricWSpell(Character c) {
		super(false, true, true, c);
		setRange(0);
		setName("Shatter");
		setMaxCD(4);
	}

	@Override
	public boolean activeEffect(Character target) {
		
		
		target.getMap().effectTimerAllInRange(getCaster().getX(), getCaster().getY(), getCaster().getTeam(), 2, new EffectTimer(2,gui.Game.fake,EffectTimer.DEFENCE, -100));
		target.getMap().effectTimerAllInRange(getCaster().getX(), getCaster().getY(), getCaster().getTeam(), 2, new AoEDmg(getName(),getCaster().getAttack()));
		gui.Game.console.println(getCaster().getName() + " used "+getName());
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
