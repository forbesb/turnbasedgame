package gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Topbar extends JPanel{
	private JButton turn;
	private JLabel turnText, yT;
	private String yourTurn;
	
	public Topbar(Game g) {
		super();
		this.setLayout(new FlowLayout()); //not much in this now: can add more later
		this.setSize(1600, 31);
		turn = new JButton("End Turn");//end turn button
		turn.addActionListener(g);
		turnText = new JLabel("Turn: A"); //displays turn
		this.add(turnText);
		this.add(turn);
	
		//flowLayout is so I can add things as needed and it will fit them in.
	}
	
	public JButton getTurnButton(){
		return turn;
	}
	
	public void setTurn(String team){
		turnText.setText("Turn: "+team);
	}
	
	public void setYourTurn(String s){
		yourTurn = s;
		yT = new JLabel("Your Team:"+s);
		
		this.add(yT);
	}

}
