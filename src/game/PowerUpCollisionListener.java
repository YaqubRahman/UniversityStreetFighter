package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * The {@code PowerUpCollisionListener} class handles the collision events between the player characters
 * and the {@link PowerUp} object in the game. This class is responsible for triggering specific actions
 * when a player (either {@link Student} or {@link Student2}) collects a power-up.
 * <p>
 * When a player collides with a {@code PowerUp}, the listener invokes specific methods on the player
 * (such as {@link Student#setRedBand()} or {@link Student2#setRedEars()}) to apply the benefits of the power-up.
 * After the collision, the {@code PowerUp} object is destroyed, and the effects are applied to the player.
 * </p>
 * <p>
 * The class checks for collisions with either player and applies different effects based on which player
 * has collected the power-up. The effects typically involve visual or gameplay changes, such as altering the
 * player's appearance or granting a temporary ability.
 * </p>
 *
 * <p>
 * Key features:
 * <ul>
 *     <li>Handles collision detection between players and the {@link PowerUp} object.</li>
 *     <li>Applies effects to the players, such as changing their appearance or abilities.</li>
 *     <li>Destroys the {@code PowerUp} object after it is collected by a player.</li>
 * </ul>
 * </p>
 */

public class PowerUpCollisionListener implements CollisionListener{
    private Student student;
    private Student2 student2;

    public PowerUpCollisionListener(Student student, Student2 student2) {
        this.student = student;
        this.student2 = student2;
    }

    @Override
    public void collide(CollisionEvent e) {
        // Check if a student collided with a heart
        if (e.getReportingBody() instanceof PowerUp) {
            if (e.getOtherBody() == student) {
                student.setRedBand();
                e.getReportingBody().destroy();
            } else if (e.getOtherBody() == student2) {
                student2.setRedEars();
                e.getReportingBody().destroy();
            }
        }
        // This handles the case where the reporting and other bodies might be swapped
        else if (e.getOtherBody() instanceof PowerUp) {
            if (e.getReportingBody() == student) {
                student.setRedBand();
                e.getOtherBody().destroy();
            } else if (e.getReportingBody() == student2) {
                student2.setRedEars();
                e.getOtherBody().destroy();
            }
        }
    }


}
