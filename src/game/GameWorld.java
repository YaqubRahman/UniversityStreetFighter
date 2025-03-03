package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class GameWorld extends World {
    private Student student;
    private Student2 student2;
    public GameWorld() {
        super();

        // make the ground
        Shape shape = new BoxShape(17, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -7.5f));

        // make the walls
        Shape wallright = new BoxShape(1, 40f);
        StaticBody wall1 = new StaticBody(this, wallright);
        wall1.setPosition(new Vec2(18f, -7.5f));

        Shape wallleft = new BoxShape(1, 40f);
        StaticBody wall2 = new StaticBody(this, wallright);
        wall2.setPosition(new Vec2(-18f, -7.5f));

        // make the character
        student = new Student(this);
        student.setPosition(new Vec2(7, -2));
        student.setCredits(student.getCredits() + 15);

        student2 = new Student2(this);
        student2.setPosition(new Vec2(1, -2));

        //2. populate it with bodies (ex: platforms, collectibles, characters)

        // make a suspended platform
        Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-8, -4f));

    }
    public Student getStudent(){
        return student;
    }
    public Student2 getStudent2(){
        return student2;
    }
}
