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


    public Level2(Game game) {
        super(game);
        getStudent().setHealth(100);
        getStudent2().setHealth(100);
        getStudent().setPosition(new Vec2(8, -4f));
        getStudent2().setPosition(new Vec2(-8, -4f));
        System.out.println("It works/loaded - level 2");

        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {

                int randomX = (int)(Math.random() * 33) - 16; // Random X (-16 to 16)


                lavaRock = new LavaRock(Level2.this);
                lavaRock.setPosition(new Vec2(randomX, 50));

                lavaRock.addCollisionListener(new LavaRockCollisionListener(student, student2));

            }
        }, 2000, 2000);




    }


    @Override
    public boolean isComplete(){
        if(getStudent().getHealth() == 0 || getStudent2().getHealth() == 0)
            return true;
        else return false;
    }

    @Override
    public String getLevelName() {
        return "Level2";
    }
}
