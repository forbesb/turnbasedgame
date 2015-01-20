package workers;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import sun.audio.*;

import java.io.*;
public class SoundThread extends Thread {
	private static boolean muted = false;
	AudioStream BGM;
	boolean looping;
	private int LengthInMillis = 1;
	
	public SoundThread(String filename, boolean loop) {
		try {
			BGM=new AudioStream(new FileInputStream(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//enter the sound directory and name here
		looping=loop;
	}
	
	 public void run(){
		 if (!muted){
			 if (looping){
				 int i = 0;
				 AudioPlayer.player.start(BGM);
				 
		         for(;;){
		        	 try{
		        		BGM.mark(BGM.getLength());
		        		sleep(LengthInMillis);// overlap
		        		BGM.reset();
					}catch(Exception e){
						e.printStackTrace();
					}
		
		     	}
			 } else {
				 AudioPlayer.player.start(BGM);
			 }
		 }
	}

	public int getLengthInMillis() {
		return LengthInMillis;
	}

	public void setLengthInMillis(int lengthInMillis) {
		LengthInMillis = lengthInMillis;
	}
	public static void mute(){
		muted = true;
	}
}
