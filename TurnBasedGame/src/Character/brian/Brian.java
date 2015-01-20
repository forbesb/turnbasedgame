package Character.brian;
import javax.swing.ImageIcon;
import spells.Spell;
import game.Character;
import game.EffectTimer;
import gui.ConsoleBox;


public class Brian extends Character{

	public Brian(int x, int y,String t, ConsoleBox c) {
		super(2000, 500, 100, 100, 1, 7, x, y, t, "Brian", c, new ImageIcon("brian.png"), new ImageIcon("BrianIcon.png"));
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
