package game;

import org.jbox2d.common.Vec2;
import city.cs.engine.*;

public class Level3 extends GameWorld {
    private Student student;
    private Student2 student2;
    private static final String level3_music = "data/Level3GameMusic.wav";
    private String winner;



    public Level3(Game game) {
        super(game);
        SoundHandler.playSound(level3_music, true);
        getStudent().setHealth(100);
        getStudent2().setHealth(100);
        getStudent().setPosition(new Vec2(8, -4f));
        getStudent2().setPosition(new Vec2(-8, -4f));
        System.out.println("It works/loaded - level 3");

        new javax.swing.Timer(5000, e -> {
            boolean spawnFromLeft = Math.random() < 0.5;
            float xPosition;
            float yPosition = (float)(Math.random() * 10) -5;
            if (spawnFromLeft) {
                xPosition = -10f;
            } else {
                xPosition = 10f;
            }

            Axe axe = new Axe(Level3.this);
            axe.setPosition(new Vec2(xPosition, yPosition));
            axe.addCollisionListener(new AxeCollisionListener(getStudent(), getStudent2()));

            if (spawnFromLeft){
                axe.setLinearVelocity(new Vec2(10, 0));
            } else{
                axe.setLinearVelocity(new Vec2(-10, 0));
            }
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
        return "Level3";
    }



}
