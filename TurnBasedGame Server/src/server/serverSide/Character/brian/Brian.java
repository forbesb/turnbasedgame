package server.serverSide.Character.brian;

import server.serverSide.spells.Spell;
import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;



public class Brian extends Character{

	public Brian(int x, int y,String t) {
		super(2000, 500, 100, 100, 1, 7, x, y, t, "Brian");
		this.setSpell(new Spell[]{new BrianQSpell(this), new BrianWSpell(this), new BrianESpell(this), new BrianRSpell(this)});
	}
	public boolean attack(Character defender){
		for (EffectTimer i : defender.getEffs()){
			if (i.toString().equals("DeathMark")){
				i.addDamage(getAttack());
			}
		}
		if (defender.getCurrentHP()/defender.getHp()<1/2){
			defender.defendSpell(getAttack());
		}
		return super.attack(defender);
	}

}
