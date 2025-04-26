package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Axe extends Walker {
    private static final Shape axeShape = new CircleShape(2F);
    private static BodyImage image = new BodyImage("data/Axe.gif", 4.0f);
    private static SoundClip explosionSound;

    static{
        try{
            explosionSound = new SoundClip("data/Explosion.wav");
            System.out.println("Loading explosion sound");
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }


    public Axe(World world){
        super(world, axeShape);
        addImage(image);
    }

    @Override
    public void destroy(){
        super.destroy();
    }

}
