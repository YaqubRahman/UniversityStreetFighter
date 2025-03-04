package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

public class SoundHandler {

    public static void playSound(String path, boolean loop){
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(0);
        } catch(UnsupportedAudioFileException e){
            System.err.println("Error: Unsupported audio file fomat");
        } catch(LineUnavailableException e) {
            System.err.println("Error: Line unavailable");
            e.printStackTrace();
        } catch(IOException e) {
            System.err.println("Error: I/O error");
            e.printStackTrace();
        }
    }
}
