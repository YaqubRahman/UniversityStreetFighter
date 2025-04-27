package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * Represents an Axe object in the game used in {@link Level3}, which is a type of {@link Walker}.
 * <p>
 * The Axe has a circular shape and a specific image associated with it.
 * It also preloads an explosion sound effect for when it is used or destroyed.
 * </p>
 */
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
