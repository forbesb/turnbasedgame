package Character.annie;
import game.Character;
import spells.Spell;
public class AnnieESpell extends Spell{

	public AnnieESpell(Character c) {
		super(false, true, true, c);
		setRange(0);
		setName("Arcane Shift");
		setMaxCD(10);
	}

	@Override
	public boolean activeEffect(Character target) {
		gui.Game.console.println(getCaster().getName() + "has casted Arcane Shift.");
		getCaster().setMoved(false);
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
