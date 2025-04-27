package game;

import org.jbox2d.common.Vec2;

/**
 * The {@code Level1} class is the first level in the game, extending the {@link GameWorld} class.
 * <p>
 * This level involves two players, represented by the {@link Student} and {@link Student2} classes. The objective
 * of the level is for each player to defeat the other by reducing their health to zero.
 * The level includes features such as:
 * <ul>
 *     <li>Initial positions for both players.</li>
 *     <li>Timed power-up spawning at regular intervals.</li>
 *     <li>Collision listeners for interactions between players and power-ups.</li>
 *     <li>Health tracking for both players to determine the winner.</li>
 * </ul>
 * </p>
 * The level continues until one player's health reaches zero, at which point the winner is determined.
 * The game then transitions to the next level based on the result.
 */
public class Level1 extends GameWorld {
    private Game game;
    private Student student;
    private Student2 student2;
    private javax.swing.Timer powerUpTimer;
    private PowerUp powerUp;
    private String winner;

    public Level1(Game game) {
        super(game);
        this.game = game;
        this.student = getStudent();
        this.student2 = getStudent2();

        getStudent().setPosition(new Vec2(8, -4f));
        getStudent2().setPosition(new Vec2(-8, -4f));
        System.out.println("It works/loaded - level 1");



        powerUpTimer = new javax.swing.Timer(15000, e ->{

            powerUp = new PowerUp(Level1.this);
            powerUp.setPosition(new Vec2(8, 1));

            powerUp.addCollisionListener(new PowerUpCollisionListener(student, student2));

        });
        powerUpTimer.setInitialDelay(5000);
        powerUpTimer.start();

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
