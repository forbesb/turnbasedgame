package Character.annie;
import game.AoEDmg;
import game.Character;
import game.EffectTimer;
import spells.Spell;
public class AnnieWSpell extends Spell{

	public AnnieWSpell(Character c) {
		super(false, true, true, c);
		setMaxCD(5);
		setRange(0);
		this.setName("Ring Of Fire");
	}

	@Override
	public boolean activeEffect(Character target) {
		String [] teams = {"A","B"};
		int team = 1;
		if (teams[0].equals(getCaster().getTeam())){
			team = 0;
		}
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 2, new EffectTimer(2,gui.Game.fake,EffectTimer.SNARE, 0));
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 2, new AoEDmg(getName(),getCaster().getAttack()));
		gui.Game.console.println(getCaster().getName() + " used "+getName());
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
