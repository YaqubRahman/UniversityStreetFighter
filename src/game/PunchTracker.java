package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

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
                System.out.println("Ahmad has punched Skyler!");
                gruntSound.play();
                student2.setHealth(student2.getHealth()-10);
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
