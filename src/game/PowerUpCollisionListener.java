package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

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
