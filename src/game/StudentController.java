package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.jbox2d.common.Vec2;

public class StudentController implements KeyListener {
    private Student student;
    private Student2 student2;

    public StudentController(Student student, Student2 student2) {
        this.student = student;
        this.student2 = student2;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_A) {
            student.startWalking(-5);
        } else if (code == KeyEvent.VK_D) {
            student.startWalking(5);
        } else if (code == KeyEvent.VK_W) {
            student.setLinearVelocity(new Vec2(student.getLinearVelocity().x, 30));
        } else if (code == KeyEvent.VK_S) {
            student.setLinearVelocity(new Vec2(student.getLinearVelocity().x, - 30));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
