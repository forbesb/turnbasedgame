package server.serverSide.Character.soraka;

import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;

public class SorRSpell extends Spell{

	public SorRSpell(Character c) {
		super(false, true, true, c);
		setName("Wish");
		setRange(0);
		setMaxCD(10);
		setOnCD();
	}

	@Override
	public boolean activeEffect(Character target) {
		for (Character i :target.getMap().returnAllCharacters(getCaster().getTeam())){
			getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ i);
			i.heal(1000);
		}
		
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
