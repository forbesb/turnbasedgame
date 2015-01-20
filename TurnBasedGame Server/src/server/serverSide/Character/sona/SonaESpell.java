package server.serverSide.Character.sona;

import server.serverSide.game.AoEDmg;
import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;
public class SonaESpell extends Spell{
	public SonaESpell(Character c) {
		super(false, true, true, c);
		setMaxCD(3);
		setRange(0);
		this.setName("Maven's Cheer");
	}

	@Override
	public boolean activeEffect(Character target) {
		String [] teams = {"A","B"};
		int team = 0;
		if (teams[0].equals(getCaster().getTeam())){
			team = 1;
		}
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 6, new EffectTimer(2,server.serverSide.game.Fake.fake,EffectTimer.SPEED, 3));
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 6, new EffectTimer(2,server.serverSide.game.Fake.fake,EffectTimer.ATTACK, 50));
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName());
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
}
