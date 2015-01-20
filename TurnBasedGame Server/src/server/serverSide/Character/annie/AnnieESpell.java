package server.serverSide.Character.annie;
import server.serverSide.game.Character;
import server.serverSide.spells.Spell;
public class AnnieESpell extends Spell{

	public AnnieESpell(Character c) {
		super(false, true, true, c);
		setRange(0);
		setName("Arcane Shift");
		setMaxCD(10);
	}

	@Override
	public boolean activeEffect(Character target) {
		getCaster().getThread().sendConsole(getCaster().getName() + "has casted Arcane Shift.");
		getCaster().setMoved(false);
		getCaster().getThread().sendUnmove(getCaster().getId());
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
