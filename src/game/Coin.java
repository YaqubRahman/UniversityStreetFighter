package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represents a collectible coin in the game.
 * <p>
 * The coin is a circular object that can be picked up by players. When collected, a sound is played, and the coin is destroyed.
 * The class integrates with the game's world and uses a sound clip to notify the collection event.
 * </p>
 */

public class Coin extends Walker {
    private static final Shape coinShape = new CircleShape(0.7F);
    private static SoundClip coinPickupSound;
    private static BodyImage image = new BodyImage("data/Coin.gif");

    static{
        try{
            coinPickupSound = new SoundClip("data/CoinPickupSound.wav");
            System.out.println("Loading coin pickup sound");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Coin(World world){
        super(world, coinShape);
        addImage(image);
    }

    @Override
    public void destroy(){
        coinPickupSound.play();
        super.destroy();
    }
}
