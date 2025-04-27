package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * Handles collisions between players ({@link Student} and {@link Student2}) and {@link Coin}.
 * <p>
 * When a player collides with a coin, the coin count for that player is incremented by 1,
 * and the coin is destroyed. This class listens for coin collection events and updates
 * the player's score accordingly.
 * </p>
 */

public class CoinCollisionListener implements CollisionListener {
    private Student student;
    private Student2 student2;
    public CoinCollisionListener(Student student, Student2 student2) {
        this.student = student;
        this.student2 = student2;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coin) {
            if (e.getReportingBody() == student) {
                student.setCoin(student.getCoin() + 1);
                e.getOtherBody().destroy();
            } else if (e.getReportingBody() == student2) {
                student2.setCoin(student2.getCoin() + 1);
                e.getOtherBody().destroy();
            }
        }
    }
}