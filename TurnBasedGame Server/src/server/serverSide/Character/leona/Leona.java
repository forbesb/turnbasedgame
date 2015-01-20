package server.serverSide.Character.leona;

import server.serverSide.spells.Spell;

import server.serverSide.game.Character;

public class Leona extends Character{

	public Leona(int x, int y, String t) {
		super(4000, 200, 100, 100, 1, 5, x, y, t, "Leona");
		this.setSpell(new Spell[]{new LeonaQSpell(this), new LeonaWSpell(this), new LeonaESpell(this), new LeonaRSpell(this)});
	}
	public boolean defend(Character attacker){
		int damage = attacker.getAttack();
		
		double denom = 100+getDefence()+getSpace().getDef();
		double dmg = 100*damage/denom*getModifier(); //damage formula
		if (dmg/getCurrentHP()>0.1){
			dmg*=.5;
		}
		if (getpShieldCount() <= 0)
			takeDamage((int)dmg); //same as below, but with physical shield
		else {
			setpShieldCount(getpShieldCount() - 1);
		}
		return true;
	}
	
	public boolean defendSpell(int damage){
		
		double denom = 100+getMagicres();
		double dmg = 100*damage/denom*getModifier(); //damage formula
		if (dmg/getCurrentHP()>0.1){
			dmg*=.5;
		}
		if (getsShieldCount() <= 0) //if no shield, take damage
			takeDamage((int)dmg);
		else { //if shield, destroy shield, no damage
			setsShieldCount(getsShieldCount() - 1);
			//con.println(name+" defended the attack with a shield");
		}
		
		
		//takeDamage((int)dmg);
		return true;
	}
	

}
