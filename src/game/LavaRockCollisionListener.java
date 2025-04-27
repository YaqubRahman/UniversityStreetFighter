package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.*;

/**
 * A collision listener that handles interactions between the {@link LavaRock} object and the {@link Student} and {@link Student2} characters.
 * <p>
 * This class listens for collisions between the {@link LavaRock} and both players (i.e., {@link Student} and {@link Student2}).
 * When a collision is detected, it reduces the health of the character involved in the collision and destroys the {@link LavaRock}.
 * </p>
 * <p>
 * The collision handler ensures that when either student collides with the LavaRock, they lose health, specifically 20 health points.
 * </p>
 *
 * <p>
 * The class handles collisions both when the LavaRock is the reporting body and when it is the other body in the collision.
 * This makes sure the effect is triggered regardless of the collision order in the physics engine.
 * </p>
 */
public class LavaRockCollisionListener implements CollisionListener {
    private Student student;
    private Student2 student2;

    public LavaRockCollisionListener(Student student, Student2 student2) {
        this.student = student;
        this.student2 = student2;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof LavaRock) {
            if (e.getOtherBody() == student) {
                student.setHealth(student.getHealth() - 20);
            } else if (e.getOtherBody() == student2) {
                student2.setHealth(student2.getHealth() - 20);
            }
            e.getReportingBody().destroy();

        }
        // This handles the case where the reporting and other bodies might be swapped
        else if (e.getOtherBody() instanceof LavaRock) {
            if (e.getReportingBody() == student) {
                student.setHealth(student.getHealth() - 20);
            } else if (e.getReportingBody() == student2) {
                student2.setHealth(student2.getHealth() - 20);
            }
            e.getReportingBody().destroy();
        }

    }

}
