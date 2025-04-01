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
        if (e.getOtherBody() instanceof Student2){
            System.out.println("Collision! with Skyler");
            if(student.getPunching()){
                System.out.println("Ahmad has punched Skyler!");
                student2.setHealth(student2.getHealth()-10);
            }
        } else if (e.getOtherBody() instanceof Student){
            System.out.println("Collision! with Ahmad");
            if(student2.getPunching()) {
                System.out.println("Skyler has punched Ahmad!");
                student.setHealth(student.getHealth() - 10);
            }
        }


    }
}
