package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

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