package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.*;


public class AxeCollisionListener implements CollisionListener {
    private Student student;
    private Student2 student2;

    public AxeCollisionListener(Student student, Student2 student2) {
        this.student = student;
        this.student2 = student2;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getReportingBody() instanceof Axe) {
            if (e.getOtherBody() == student) {
                student.setHealth(student.getHealth() - 20);
                e.getReportingBody().destroy();
            } else if (e.getOtherBody() == student2) {
                student2.setHealth(student2.getHealth() - 20);
                e.getReportingBody().destroy();
            }

        }
        // This handles the case where the reporting and other bodies might be swapped
        else if (e.getOtherBody() instanceof Axe) {
            if (e.getReportingBody() == student) {
                student.setHealth(student.getHealth() - 20);
                e.getReportingBody().destroy();
            } else if (e.getReportingBody() == student2) {
                student2.setHealth(student2.getHealth() - 20);
                e.getReportingBody().destroy();
            }
        }

    }

}