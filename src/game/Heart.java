package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

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
