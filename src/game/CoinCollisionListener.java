package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

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