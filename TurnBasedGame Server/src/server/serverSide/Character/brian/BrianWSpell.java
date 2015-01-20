package server.serverSide.Character.brian;
import server.serverSide.game.AoEDmg;
import server.serverSide.game.Character;
import server.serverSide.spells.Spell;
public class BrianWSpell extends Spell{

	public BrianWSpell(Character c) {
		super(false, true, true, c);
		this.setRange(0);
		this.setName("Brian's Logic");
		this.setMaxCD(3);
	}

	@Override
	public boolean activeEffect(Character target) {
		target.getMap().effectTimerAllInRange(getCaster(),getCaster().getY(), getCaster().getX(), getCaster().getTeam(), 2, new AoEDmg(getName(),getCaster().getAttack()));
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName());
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
