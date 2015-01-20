package server.serverSide.Character.soraka;
import server.serverSide.game.Character;
import server.serverSide.spells.Spell;
public class SorQSpell extends Spell{

	public SorQSpell(Character c) {
		super(false, true, true, c);
		setMaxCD(3);
		setRange(getCaster().getRange());
		setName("Astral Blessing");
	}

	@Override
	public boolean activeEffect(Character target) {
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		target.heal(500);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
