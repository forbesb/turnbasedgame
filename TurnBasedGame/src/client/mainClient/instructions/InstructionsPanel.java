package client.mainClient.instructions;
import java.util.*;
import java.awt.*;

import javax.swing.*;

import java.io.*;


@SuppressWarnings("serial")
public class InstructionsPanel extends JPanel{
	ArrayList<String> file = new ArrayList<String>();
	public InstructionsPanel(){
		this.setBackground(new Color(0,0,0));
		try{
			FileReader inputFile = new FileReader("instructions.txt");
	        BufferedReader bufferReader = new BufferedReader(inputFile);
	        String line;
	        while ((line = bufferReader.readLine()) != null)   {
	        	if (line.length()<150){
	        		file.add(line);
	        	}else{
	        		int x = line.substring(150).indexOf(" ");
	        		if (x == -1){
	        			file.add(line);
	        		}else{
	        			file.add(line.substring(0,150+x));
	        			file.add(line.substring(150+x));
	        			
	        		}
	        	}
	        	
	        }
	        bufferReader.close();
		}catch(Exception e){
			e.printStackTrace();                   
		}
		this.setLayout(new GridLayout(0,1));
		for (String i : file){
			JLabel temp = new JLabel(i);
			temp.setForeground(new Color(255,255,255));
			temp.setFont(new Font("Calibri",Font.PLAIN,15));
			this.add(temp);
		}
	}
}
