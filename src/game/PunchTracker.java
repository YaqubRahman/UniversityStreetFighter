package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class PunchTracker implements CollisionListener {

    private Student student;
    private Student2 student2;

    public PunchTracker(Student student, Student2 student2) {
        this.student = student;
        this.student2 = student2;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Student2 && student.getPunching()){
            student2.setHealth(student2.getHealth()-10);

        }

    }
}
