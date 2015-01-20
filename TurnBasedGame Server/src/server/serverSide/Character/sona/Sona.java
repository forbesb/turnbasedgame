package server.serverSide.Character.sona;


import server.serverSide.game.Character;
import server.serverSide.spells.Spell;
public class Sona extends Character{

	public Sona(int x, int y, String t) {
		super(1200, 300, 100, 100, 7, 5, x, y, t, "Sona");
		this.setSpell(new Spell[]{new SonaQSpell(this),new SonaWSpell(this),new SonaESpell(this),new SonaRSpell(this)});
	}

}
