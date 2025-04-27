package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;


/**
 * Listens for collisions between players and hearts, and restores health when a collision occurs.
 * <p>
 * The `HeartCollisionListener` is responsible for handling the collision events where a player collides with a heart.
 * If the player's health is less than 100, the collision will increase their health by 10 points and destroy the heart object.
 * </p>
 *
 * <p>
 * This listener works with both players (Student and Student2) and will only increase health if the player's health is not already at the maximum value of 100.
 * </p>
 */

public class HeartCollisionListener implements CollisionListener {
    private Student student;
    private Student2 student2;

    public HeartCollisionListener(Student student, Student2 student2) {
        this.student = student;
        this.student2 = student2;
    }

    @Override
    public void collide(CollisionEvent e) {
        // Check if a student collided with a heart
        if (e.getReportingBody() instanceof Heart) {
            if (e.getOtherBody() == student && student.getHealth() < 100) {
                student.setHealth(student.getHealth() + 10);
                e.getReportingBody().destroy();
            } else if (e.getOtherBody() == student2 && student2.getHealth() < 100) {
                student2.setHealth(student2.getHealth() + 10);
                e.getReportingBody().destroy();
            }
        }
        // This handles the case where the reporting and other bodies might be swapped
        else if (e.getOtherBody() instanceof Heart) {
            if (e.getReportingBody() == student && student.getHealth() < 100) {
                student.setHealth(student.getHealth() + 10);
                e.getOtherBody().destroy();
            } else if (e.getReportingBody() == student2 && student2.getHealth() < 100) {
                student2.setHealth(student2.getHealth() + 10);
                e.getOtherBody().destroy();
            }
        }
    }
}