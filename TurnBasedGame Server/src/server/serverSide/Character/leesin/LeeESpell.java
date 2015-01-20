package server.serverSide.Character.leesin;

import server.serverSide.game.AoEDmg;
import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;
public class LeeESpell extends Spell{
	public LeeESpell(Character c) {
		super(false, true, true, c);
		setMaxCD(5);
		setRange(0);
		this.setName("Tempest");
	}

	@Override
	public boolean activeEffect(Character target) {
		String [] teams = {"A","B"};
		int team = 1;
		if (teams[0].equals(getCaster().getTeam())){
			team = 0;
		}
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 2, new EffectTimer(2,server.serverSide.game.Fake.fake,EffectTimer.SPEED, -5));
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 2, new AoEDmg(getName(),getCaster().getAttack()));
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName());
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
}
