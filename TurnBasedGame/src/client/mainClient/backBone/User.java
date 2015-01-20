package client.mainClient.backBone;

import java.util.ArrayList;

public class User{ //User data structure
	private String name;
	private int money;
	private String[] items;
	private String [] team3; //Team3
	private String [] team1; //Small team of 5
	private String [] team2; //Medium team of 8
	private String [] stats;
	private ArrayList<String> currentTeam = new ArrayList<String>();

	public User(String name, String [] team3, String [] items, int money, String [] stats){
		this.setMoney(money);
		this.setItems(items);
		this.setName(name);
		this.setTeam3(team3);
		this.setStats(stats);
		for (int i = 0; i < 10; i++){
			this.currentTeam.add(this.team3[i]);
		}
		setTeams();
	}
	public void resetCurrentTeam(){
		for (int i = 0; i < 10; i++){
			this.currentTeam.add(this.team3[i]);
		}
	}
	public void setCurrentTeamAsTeam(){
		if (currentTeam.size() == 10){
			for (int i = 0; i < 10; i ++){
				team3[i] = currentTeam.get(i);
				setTeams();
			}
		}
		
	}
	public void setTeams(){
		team1 = new String [5];
		team2 = new String [8];
		for (int i = 0; i < 5 && i<team3.length; i ++){
			team1[i] = team3[i];
			team2[i] = team3[i];
		}
		for (int i = 5; i < 8&& i<team3.length; i ++){
			team2[i] = team3[i];
		}
	}
	public ArrayList<String> getTeam(){
		return currentTeam;
	}
	public void addToTeam(String name){
		currentTeam.add(name);
	}
	public void removeFromTeam(String name){
		currentTeam.remove(name);
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String [] getItems() {
		return items;
	}
	public void setItems(String []items) {
		this.items = items;
	}
	public String [] getTeam1() {
		return team3;
	}
	public void setTeam1(String [] team1) {
		this.team1 = team1;
	}
	public String [] getTeam2() {
		return team3;
	}
	public void setTeam2(String [] team3) {
		this.team3 = team3;
	}
	public String [] getTeam3() {
		return team3;
	}
	public void setTeam3(String [] team3) {
		this.team3 = team3;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String [] getStats() {
		return stats;
	}
	public void setStats(String [] stats) {
		this.stats = stats;
	}
	
}