package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

/**
 * The {@code SoundHandler} class is responsible for playing and controlling sound effects and music
 * within the game. It provides methods to play sounds, including looping tracks, and to stop currently
 * playing sounds.
 * <p>
 * This class uses {@link javax.sound.sampled.Clip} to load and play audio files. It supports both
 * one-time plays as well as looped sounds, providing flexibility for background music and sound effects.
 * </p>
 * <p>
 * Key methods:
 * <ul>
 *     <li>{@link #playSound(String, boolean)}: Plays a sound from a given file path. If specified, the sound can loop indefinitely.</li>
 *     <li>{@link #stopCurrentMusic()}: Stops and closes the currently playing audio clip if it's running.</li>
 * </ul>
 * </p>
 */

public class SoundHandler {

    private static Clip currentClip;

    public static void playSound(String path, boolean loop){
        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            currentClip = AudioSystem.getClip();
            currentClip.open(inputStream);
            currentClip.loop(0);
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

    public static void stopCurrentMusic(){
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
        }
    }


}
