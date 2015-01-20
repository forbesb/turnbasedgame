package Character.caitlyn;

import game.Character;
import spells.Spell;
public class CaitRSpell extends Spell{

	public CaitRSpell(Character c) {
		super(false,true,false, c);
		setRange(-1);
		setMaxCD(20);
		setOnCD();
		setName("SMDBullet");
	}

	@Override
	public boolean activeEffect(Character target) {
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return target.takeDamage((int)(target.getHp()*.66));
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
