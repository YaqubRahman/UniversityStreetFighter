package game;

import org.jbox2d.common.Vec2;
import city.cs.engine.*;

public class Level3 extends GameWorld {
    private Student student;
    private Student2 student2;
    private static final String level3_music = "data/Level3GameMusic.wav";
    private String winner;


    /**
     * The {@code Level3} class represents the third level in the game, extending the {@link GameWorld} class.
     * <p>
     * This level introduces new challenges with the addition of {@link Axe} obstacles that move across the screen,
     * potentially damaging players. The players, represented by {@link Student} and {@link Student2}, must avoid these
     * moving hazards while trying to reduce each other's health to zero.
     * </p>
     * <p>
     * Key features of this level include:
     * <ul>
     *     <li>Music specific to the level, played throughout the duration of the level.</li>
     *     <li>Timed spawning of {@link Axe} obstacles that spawn from either the left or right side of the screen.</li>
     *     <li>Moving {@link Axe} obstacles with random vertical positions and horizontal movement across the screen.</li>
     *     <li>Collision listeners to handle interactions between players and the axe obstacles.</li>
     * </ul>
     * </p>
     * The level continues until one player's health reaches zero, determining the winner of the level.
     * The game then transitions to the next level based on the result.
     */
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

}
