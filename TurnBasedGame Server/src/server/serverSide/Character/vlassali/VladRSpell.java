package server.serverSide.Character.vlassali;

import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.game.UnAffectable;
import server.serverSide.spells.Spell;

public class VladRSpell extends Spell{

	public VladRSpell(Character caster) {
		super(false, true, true, caster);
		setName("BlackKingBar");
		setMaxCD(10);
		setRange(0);
		setOnCD();
	}

	@Override
	public boolean activeEffect(Character target) {
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		target.addEffect(new EffectTimer(5, target, 7, (int)10E8));
		target.addEffect(new UnAffectable(5, target));
		
		return false;
		
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
