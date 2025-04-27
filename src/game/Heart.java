package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents a Heart collectible in the game. This object is picked up by players to restore health.
 * <p>
 * The `Heart` class extends {@link Walker}, and is used to create heart objects in the game world.
 * When a player collides with a heart, the player's health is restored, and the heart is destroyed with a sound effect.
 * </p>
 *
 * <p>
 * The heart has an image that represents it visually, and it plays a sound effect when picked up.
 * </p>
 */
public class Heart extends Walker {
    private static final Shape heartShape = new BoxShape(0.5F,0.5F );
    private static BodyImage image = new BodyImage("data/Heart.gif");
    private static SoundClip heartPickupSound;

    static{
        try{
            heartPickupSound = new SoundClip("data/HeartPickupSoundEffect.wav");
            System.out.println("Loading heart pickup sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }




    public Heart(World world){
        super(world, heartShape);
        addImage(image);
    }

    @Override
    public void destroy(){
        heartPickupSound.play();
        super.destroy();
    }





}
