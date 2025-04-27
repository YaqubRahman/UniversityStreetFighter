package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The {@code Level2} class represents the second level in the game, extending the {@link GameWorld} class.
 * <p>
 * This level introduces new gameplay elements, including the spawning of {@link LavaRock} obstacles that
 * interact with both players. The players, represented by {@link Student} and {@link Student2}, must avoid
 * these hazards while trying to reduce each other's health to zero.
 * </p>
 * <p>
 * Key features of this level include:
 * <ul>
 *     <li>Music specific to the level, played throughout the level's duration.</li>
 *     <li>Timed spawning of {@link LavaRock} obstacles at random horizontal positions.</li>
 *     <li>Player health management, with both players starting at full health.</li>
 *     <li>Collision listeners to handle interactions between players and LavaRock obstacles.</li>
 * </ul>
 * </p>
 * The level continues until one player's health reaches zero, determining the winner of the level.
 * The game then transitions to the next level based on the result.
 */

public class Level2 extends GameWorld {
    private LavaRock lavaRock;
    private Student student;
    private Student2 student2;
    private static final String level2_music = "data/Level2GameMusic.wav";
    private String winner;



    public Level2(Game game) {
        super(game);
        SoundHandler.playSound(level2_music, true);
        getStudent().setHealth(100);
        getStudent2().setHealth(100);
        getStudent().setPosition(new Vec2(8, -4f));
        getStudent2().setPosition(new Vec2(-8, -4f));
        System.out.println("It works/loaded - level 2");

        new javax.swing.Timer(1000, e -> {
            int randomX = (int)(Math.random() * 33) - 16;

            LavaRock lavaRock = new LavaRock(Level2.this);
            lavaRock.setPosition(new Vec2(randomX, 50));
            lavaRock.addCollisionListener(new LavaRockCollisionListener(getStudent(), getStudent2()));

            System.out.println("LavaRock spawned at x = " + randomX);
        }).start();





    }


    public String getWinner() {
        return winner;
    }


    @Override
    public boolean isComplete(){
        if(getStudent().getHealth() <= 0 || getStudent2().getHealth() <= 0){
            if(getStudent().getHealth() <= 0){
                winner = "Left Player";
            } else if (getStudent2().getHealth() <= 0) {
                winner = "Right Player";
            }
            return true;}
        else return false;
    }

}
