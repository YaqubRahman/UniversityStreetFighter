package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Represents a LavaRock object that explodes when destroyed, causing an explosion effect in the game.
 * <p>
 * The `LavaRock` class extends the `Walker` class and represents a dynamic object that behaves like a rock
 * in the game world. When the LavaRock is destroyed, it triggers an explosion at its current position
 * and plays an explosion sound effect. The explosion being of another another class {@link Explosion}.
 * </p>
 *
 * <p>
 * This object uses a circular shape for its body and has a specific image associated with it. The destruction
 * of the LavaRock results in creating an explosion and playing an associated sound.
 * </p>
 */
public class LavaRock extends Walker {
    private static final Shape lavarockShape = new CircleShape(2F);
    private static BodyImage image = new BodyImage("data/Rockpng.gif", 4.0f);
    private static SoundClip explosionSound;

    static{
        try{
            explosionSound = new SoundClip("data/Explosion.wav");
            System.out.println("Loading explosion sound");
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }


    public LavaRock(World world){
        super(world, lavarockShape);
        addImage(image);
    }

    @Override
    public void destroy(){
        new Explosion(this.getWorld(), this.getPosition().x, this.getPosition().y);
        explosionSound.play();
        super.destroy();
    }

}
