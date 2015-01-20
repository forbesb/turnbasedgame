package server.serverSide.Character.vlassali;

import server.serverSide.game.Character;
import server.serverSide.spells.Spell;

public class VladESpell extends Spell{

	public VladESpell(Character caster) {
		super(false, true, false, caster);
		setName("Transfusion");
		setMaxCD(3);
		setRange(caster.getRange());
	}

	@Override
	public boolean activeEffect(Character target) {
		getCaster().heal(getCaster().getAttack()/4);
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		getCaster().heal(getCaster().getAttack()*100/target.getMagicres()*1/5);
		return target.defendSpell(getCaster().getAttack());
		
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
