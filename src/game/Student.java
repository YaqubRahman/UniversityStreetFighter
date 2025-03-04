package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Student extends Walker {
    private static final Shape studentShape1 = new PolygonShape((float) (0.0f*1.7), (float) (1.06f*1.7), (float) (0.46f*1.7), (float) (0.63f*1.7), (float) (0.5f*1.7), (float) (-0.59f*1.7), (float) (0.54f*1.7), (float) (-1.9f*1.7), (float) (-0.56f*1.7), (float) (-1.91f*1.7), (float) (-0.44f*1.7), (float) (0.78f*1.7));

    private static final String[] Student_Images = {"data/Ahmad1.png", "data/Ahmad2.png"};
    private int currentImageIndex = 0;

    private static final BodyImage image1 = new BodyImage("data/Ahmad1.png", 7f);

    private int health;
    private boolean isAnimationRunning = false;


    public Student(World world) {
        super(world, studentShape1);
        addImage(image1);
        health = 0;

        startImageAnimation();
    }

    private void startImageAnimation(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (!isAnimationRunning) {
                        removeAllImages();
                        currentImageIndex = (currentImageIndex + 1) % Student_Images.length;
                        addImage(new BodyImage(Student_Images[currentImageIndex], 7f));
                    }
                });
            }
        }, 0, 300);
    }




    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
