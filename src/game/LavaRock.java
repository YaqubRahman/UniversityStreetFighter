package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

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
