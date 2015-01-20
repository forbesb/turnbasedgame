package Character.soraka;
import javax.swing.ImageIcon;

import game.Character;
import gui.ConsoleBox;
import spells.Spell;


public class Soraka extends Character{

	public Soraka(int x, int y,String t, ConsoleBox c) {
		super(1200, 100, 50, 50, 6, 6, x, y, t, "Soraka", c, new ImageIcon("Soraka.png"), new ImageIcon("SorakaIcon.png"));
		setSpell(new Spell [] {new SorQSpell(this), new SorWSpell(this), new SorESpell(this), new SorRSpell(this)});
	}
	public void tick(){
		super.tick();
		boolean rangeIn = false;
		for (Character i : getMap().returnAllCharacters(getTeam())){
			if ((double)(i.getCurrentHP())/i.getHp()<.4){
				rangeIn = true;
			}
		}
		if (rangeIn){
			setRange(10);
			for (int i = 0; i < 3; i ++){
				this.getSpell(i).setRange(getRange());
			}
		}
		else{
			setRange(6);
			for (int i = 0; i < 3; i ++){
				this.getSpell(i).setRange(getRange());
			}
		}
	}
	
}
