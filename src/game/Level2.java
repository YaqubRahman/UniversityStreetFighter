package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

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

    @Override
    public String getLevelName() {
        return "Level2";
    }
}
