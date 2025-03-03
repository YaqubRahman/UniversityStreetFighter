package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import city.cs.engine.BodyImage;
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
        if (code == KeyEvent.VK_LEFT) {
            student.startWalking(-5);
        } else if (code == KeyEvent.VK_RIGHT) {
            student.startWalking(5);
        } else if (code == KeyEvent.VK_UP) {
            student.setLinearVelocity(new Vec2(student.getLinearVelocity().x, 10));
            //student.removeAllImages();
            //student.addImage(new BodyImage("data/AhmadJump.png", 7));
        } else if (code == KeyEvent.VK_DOWN) {
            student.setLinearVelocity(new Vec2(student.getLinearVelocity().x, - 10));
        } else if (code == KeyEvent.VK_A) {
            student2.startWalking(-5);
        } else if (code == KeyEvent.VK_D) {
            student2.startWalking(5);
        } else if (code == KeyEvent.VK_W) {
            student2.setLinearVelocity(new Vec2(student2.getLinearVelocity().x, 30));
        } else if (code == KeyEvent.VK_S) {
            student2.setLinearVelocity(new Vec2(student2.getLinearVelocity().x, - 30));
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            student.setLinearVelocity(new Vec2(student.getLinearVelocity().x, 0));
        } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT){
            student.stopWalking();
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_W) {
            student2.setLinearVelocity(new Vec2(student2.getLinearVelocity().x, 0));
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_D){
            student2.stopWalking();

    }
}

}
