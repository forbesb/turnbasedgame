package client.mainClient.teamselection;

public class Character{
	private String name;
	private String [] spells;
	public Character(String name, String [] spells){
		this.setName(name);
		this.setSpells(spells);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String [] getSpells() {
		return spells;
	}
	public void setSpells(String [] spells) {
		this.spells = spells;
	}
}