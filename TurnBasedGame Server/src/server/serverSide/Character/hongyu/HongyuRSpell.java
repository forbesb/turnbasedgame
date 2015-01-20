package server.serverSide.Character.hongyu;

import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;

public class HongyuRSpell extends Spell {

	public HongyuRSpell(Character c) {
		super(false, true, true, c);
		setRange(0);
		setMaxCD(10);
		setOnCD();
		setName("Ultimate Laugh");
	}

	@Override
	public boolean activeEffect(Character target) {
		String [] teams = {"A","B"};
		int team = 1;
		if (teams[1].equals(getCaster().getTeam())){
			team = 0;
		}
		for (Character i :target.getMap().returnAllCharacters(teams[team])){
			i.addEffect(new EffectTimer(3, i, EffectTimer.SILENCE,0));
			i.addEffect(new EffectTimer(3, i, EffectTimer.SNARE,0));
			i.takeDamage(target.getAttack()*3);
		}
		
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
