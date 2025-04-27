package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * The {@code PunchTracker} class listens for collision events between the two player characters
 * ( {@link Student} and {@link Student2} ) when they are punching each other during gameplay.
 * This class tracks when one player punches the other, plays a sound effect for the punch,
 * and applies damage to the player who was punched.
 * <p>
 * The {@code PunchTracker} listens for collisions between the players and handles the logic for applying
 * damage based on whether the punching player has a specific effect, such as the {@link Student} having
 * the red band equipped.
 * </p>
 * <p>
 * Key features:
 * <ul>
 *     <li>Tracks punching collisions between the two players.</li>
 *     <li>Plays a grunt sound effect when a punch is detected.</li>
 *     <li>Applies different amounts of damage based on whether the punching player has a specific effect (e.g., red band).</li>
 *     <li>Updates health for the player who was punched based on the power of the punch.</li>
 * </ul>
 * </p>
 */

public class PunchTracker implements CollisionListener {

    private Student student;
    private Student2 student2;
    private static SoundClip gruntSound;

    public PunchTracker(Student student, Student2 student2) {
        this.student = student;
        this.student2 = student2;
    }


    static{
        try{
            gruntSound = new SoundClip("data/Grunt.wav");
            System.out.println("Loading grunt sound");
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }


    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Student2){
            System.out.println("Collision! with Skyler");
            if(student.getPunching()){
                if(student.getRedBand()){
                    System.out.println("Ahmad has punched Skyler!");
                    gruntSound.play();
                    student2.setHealth(student2.getHealth()-30);
                } else {
                    System.out.println("Ahmad has punched Skyler!");
                    gruntSound.play();
                    student2.setHealth(student2.getHealth() - 10);
                }
            }
        } else if (e.getOtherBody() instanceof Student){
            System.out.println("Collision! with Ahmad");
            if(student2.getPunching()) {
                System.out.println("Skyler has punched Ahmad!");
                gruntSound.play();
                student.setHealth(student.getHealth() - 10);
            }
        }


    }
}
