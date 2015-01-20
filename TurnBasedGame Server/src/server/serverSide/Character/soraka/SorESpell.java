package server.serverSide.Character.soraka;

import server.serverSide.game.Character;
import server.serverSide.spells.Spell;

public class SorESpell extends Spell{
	public SorESpell(Character c) {
		super(false, true, true, c);
		setMaxCD(3);
		setRange(getCaster().getRange());
		setName("Hope");
	}

	@Override
	public boolean activeEffect(Character target) {
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		target.addPhysShield();
		target.addSpellShield();
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
}
