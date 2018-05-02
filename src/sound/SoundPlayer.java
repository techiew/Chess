package sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer implements Runnable {
	
	public enum SoundName {
		PIECE_MOVED,
		CONNECTED
	};
	
	private final File pieceMoved = new File("src/sounds/piece_moved.wav");
	private final File connected = new File("src/sounds/connected.wav");
	private volatile File currentSound = null; //Volatile for å kunne bruke den fra forskjellige tråder
	
	@Override
	public void run() {
		
		while(true) {

			if(currentSound != null) {
				
				try {
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(currentSound));
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-20.0f);
					clip.start();

					Thread.sleep(clip.getMicrosecondLength()/1000);
					currentSound = null;
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			} else {
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	public void playSound(SoundName soundName) {
		File soundFile = null;
		
		switch(soundName) {
		case PIECE_MOVED:
			soundFile = pieceMoved;
			break;
		case CONNECTED:
			soundFile = connected;
			break;
		}

		currentSound = soundFile;
	}
	
}
