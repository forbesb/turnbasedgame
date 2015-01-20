package Character.tryndamere;

import javax.swing.ImageIcon;

import spells.Spell;
import game.Character;
import gui.ConsoleBox;
public class TrondoFiora extends Character{
	public TrondoFiora(int x, int y, String t,  ConsoleBox c) {
		super(1500, 700, 70, 70, 1, 5, x, y, t, "Tryndamere", c, new ImageIcon("trondofiora.png"), new ImageIcon("TryndamereIcon.png"));
		
		super.setSpell(new Spell[]{new DamageMeleeQ(this),new DamageMeleeW(this),new DamageMeleeE(this),new DamageMeleeR(this)});
		
	}
	public boolean defendSpell(int damage){

		double denom = 100+getMagicres();
		double dmg = 100*damage/denom; //damage formula
		
		if (getsShieldCount() <= 0) //if no shield, take damage
			if (Math.random()>0.6){ //TODO Tryndamere passive line
				takeDamage((int)dmg);
			}
		else { //if shield, destroy shield, no damage
			setsShieldCount(getsShieldCount() - 1);
			//TODO con.println(name+" defended the attack with a shield");
		}
		
		
		//takeDamage((int)dmg);
		return true;
	}
	public boolean defend(Character attacker){
		int damage = attacker.getAttack();
		
		double denom = 100+getDefence()+getSpace().getDef();
		double dmg = 100*damage/denom; //damage formula
		
		if (getpShieldCount() <= 0)
			if (Math.random()>0.6){ //TODO Tryndamere passive line
				takeDamage((int)dmg);
			}
		else {
			setpShieldCount(getpShieldCount() - 1);
			//con.println(name+" defended the attack with a shield");
		}
		return true;
	}
}
