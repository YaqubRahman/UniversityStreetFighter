package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameWorld extends World {
    private Student student;
    private Student2 student2;
    private Coin coin;
    private Coin coin2;
    public GameWorld() {
        super();

        // make the ground
        Shape shape = new BoxShape(17, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -7.5f));
        ground.setFillColor(new Color(0, 0, 0, 0));

        // make the walls
        Shape wallright = new BoxShape(1, 40f);
        StaticBody wall1 = new StaticBody(this, wallright);
        wall1.setPosition(new Vec2(18f, -7.5f));

        Shape wallleft = new BoxShape(1, 40f);
        StaticBody wall2 = new StaticBody(this, wallright);
        wall2.setPosition(new Vec2(-18f, -7.5f));

        // make the character
        //Ahmad (Player1) (Right side)
        student = new Student(this);
        student.setPosition(new Vec2(8, -4f));
        student.setHealth(student.getHealth() + 100);

        //Skyler (Player2) (Left side)
        student2 = new Student2(this);
        student2.setPosition(new Vec2(-8, -4f));
        student2.setHealth(student2.getHealth() + 100);


        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {

                int randomX = (int)(Math.random() * 33) - 16; // Random X (-16 to 16)
                int randomY = (int)(Math.random() * 15) - 7; // Random Y (-7 to 7)

                coin = new Coin(GameWorld.this);
                coin.setPosition(new Vec2(randomX, randomY));

                coin.addCollisionListener(new CoinCollisionListener(student, student2));

            }
        }, 2000, 3000);




        //coin = new Coin(this);
        //coin.setPosition(new Vec2(-9, 4f));

        coin2 = new Coin(this);
        coin2.setPosition(new Vec2(8, 4f));

        //coin.addCollisionListener(new CoinCollisionListener(student, student2));
        //CoinCollisionListener pickup = new CoinCollisionListener(student, student2);
        //student.addCollisionListener(pickup);
        //student2.addCollisionListener(pickup);

        coin2.addCollisionListener(new CoinCollisionListener(student, student2));
        CoinCollisionListener pickup2 = new CoinCollisionListener(student, student2);
        student.addCollisionListener(pickup2);
        student2.addCollisionListener(pickup2);


        //2. populate it with bodies (ex: platforms, collectibles, characters)

        // make a suspended platform
        Shape platformShape = new BoxShape(1F, 0.2F);
        StaticBody platform1 = new StaticBody(this, platformShape);
        platform1.setPosition(new Vec2(-8, 0f));
        platform1.setFillColor(Color.ORANGE);

        Shape platformShape2 = new BoxShape(1F, 0.2F);
        StaticBody platform2 = new StaticBody(this, platformShape2);
        platform2.setPosition(new Vec2(8, 0f));
        platform2.setFillColor(Color.ORANGE);

        this.setGravity(60);

        PunchTracker punchtracker = new PunchTracker(student, student2);
        student.addCollisionListener(punchtracker);

        //java.util.Timer timer = new java.util.Timer();
        //timer.schedule(new java.util.TimerTask() {
        //    @Override
        //    public void run() {
        //        checkFlip(student, student2); // Call your flip logic
        //    }
        //}, 0, 1); // Run every 1ms


    }
    public Student getStudent(){
        return student;
    }
    public Student2 getStudent2(){
        return student2;
    }

    public void checkFlip(Student student, Student2 student2){
        if(student.getPosition().x < student2.getPosition().x){
            student.startImageAnimationFlip();
        } else if(student.getPosition().x > student2.getPosition().x){
            student.startImageAnimation();
        }
    }



}


