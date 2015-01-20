package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.JPanel;

import game.*;
import game.Character;


public class CharaList extends JPanel {
	private ArrayList<Character> chars = new ArrayList<Character>();
	public void setChar(ArrayList<Character> chs){
		chars = chs;
	}
	
	public void paintComponent(Graphics g){
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//TODO: manually drawing bg: could just call super.paintComponent(g)?
		int[] tilesize = new int[2];
		//int[] dims = new int[]{2,1};
		
		int width = this.getWidth();
		int height = this.getHeight();
		tilesize[0] = (int)(width/3);
		tilesize[1] = tilesize[0]; //making sure the size of boxes is correct
		g.setColor(new Color(0,0,0));
		int apointx = 0, apointy = 0;
		int bpointx = 0, bpointy = 0;//pointers for loops
		int arrayPoint = 0;
		
		for (int y = 0; y<3; y++){
			for (int i = 0; i<8; i++){
				if (i%2==0){ //puts it on the top or the bottom depending on team
					g.drawRect(apointx*tilesize[0], apointy++*tilesize[0], tilesize[0], tilesize[0]);
				} else {
					g.drawRect(bpointx*tilesize[0], height-(++bpointy*tilesize[0]), tilesize[0], tilesize[0]);
				}
				
			}
			apointx++; bpointx++;
			apointy=0; bpointy=0;
			
		}
		apointx = 0; bpointx=0; apointy=0; bpointy=0; //reset pointers for next loop structure
		for (Character c: chars){
			if (c.getTeam().equals("A")){ //draws health bar in different space depending on team
				c.drawIcon(g, apointx*tilesize[0], apointy*tilesize[0], tilesize);
				c.drawHealthBar(g, apointx*tilesize[0], apointy++*tilesize[0]+5*tilesize[0]/6, tilesize[0], tilesize[0]/6);
			} else{ 
				c.drawIcon(g, bpointx*tilesize[0], height-(++bpointy*tilesize[0]), tilesize);
				c.drawHealthBar(g, bpointx*tilesize[0], height-(bpointy*tilesize[0])+5*tilesize[0]/6, tilesize[0], tilesize[0]/6);
			}
			if (apointy>3){
				apointx++; 
				apointy=0; 
			}
			if (bpointy>3){
				bpointx++;
				bpointy=0;
			}
		}

	}
}