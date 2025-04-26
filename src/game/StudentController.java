package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class StudentController implements KeyListener {
    private Student student;
    private Student2 student2;

    private static final Shape studentShape1Punching = new PolygonShape((-1.32f * 1.7f), (0.66f * 1.7f), (-0.09f * 1.7f), (1.02f * 1.7f), (0.47f * 1.7f), (0.71f * 1.7f), (0.58f * 1.7f), (-1.93f * 1.7f), (-0.65f * 1.7f), (-1.9f * 1.7f), (-1.28f * 1.7f), (0.38f * 1.7f));

    private static SoundClip punchSound;
    private static SoundClip jumpSound;

    // Constructor
    public StudentController(Student student, Student2 student2) {
        this.student = student;
        this.student2 = student2;
    }

    static{
        try{
            punchSound = new SoundClip("data/PunchSoundEffect.wav");
            System.out.println("Loading punch sound");
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }

    static{
        try{
            jumpSound = new SoundClip("data/JumpSoundEffect.wav");
            System.out.println("Loading jump sound");
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }

    public void updateStudent(Student player1, Student2 player2) {
        student = player1;
        student2 = player2;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_LEFT) {
            if(student.getRedBand()){
                student.startWalking(-10);
            } else {
                student.startWalking(-5);
            }
        } else if (code == KeyEvent.VK_RIGHT) {
            if(student.getRedBand()){
                student.startWalking(10);
            } else {
                student.startWalking(5);
            }
        } else if (code == KeyEvent.VK_UP) {
            student.setLinearVelocity(new Vec2(student.getLinearVelocity().x, 30));
            student.removeAllImages();
            student.addImage(new BodyImage("data/AhmadJump.png", 7));
            jumpSound.play();
        } else if (code == KeyEvent.VK_DOWN) {
            student.setLinearVelocity(new Vec2(student.getLinearVelocity().x, - 30));
        } else if (code == KeyEvent.VK_L){
            if(student.getRedBand()){
                student.removeAllImages();
                student.setPunching(true);
                student.addImage(new BodyImage("data/AhmadPunch2RedBand.png", 7));
                punchSound.play();
            } else{
            student.removeAllImages();
            student.setPunching(true);
            student.addImage(new BodyImage("data/AhmadPunch2.png", 7));
            punchSound.play();
            }
        } else if (code == KeyEvent.VK_K){
            if(student.getRedBand()){
                student.removeAllImages();
                student.setPunching(true);
                student.addImage(new BodyImage("data/AhmadPunch1RedBand.png", 7));
                punchSound.play();
            } else {
                student.removeAllImages();
                student.setPunching(true);
                student.addImage(new BodyImage("data/AhmadPunch1.png", 7));
                punchSound.play();
            }
        } else if (code == KeyEvent.VK_A) {
            student2.startWalking(-5);
        } else if (code == KeyEvent.VK_D) {
            student2.startWalking(5);
        } else if (code == KeyEvent.VK_W) {
            student2.setLinearVelocity(new Vec2(student2.getLinearVelocity().x, 30));
            jumpSound.play();
        } else if (code == KeyEvent.VK_S) {
            student2.setLinearVelocity(new Vec2(student2.getLinearVelocity().x, - 30));
        } else if (code == KeyEvent.VK_Q) {
            if (!student2.getisAnimationRunning()) {
                student2.setisAnimationRunning(true);
                student2.setPunching(true);
                student2.removeAllImages();
                student2.addImage(new BodyImage("data/SkylerUpper1.png", 7));
                punchSound.play();

                java.util.Timer timer = new java.util.Timer();
                timer.schedule(new java.util.TimerTask() {
                    @Override
                    public void run() {
                        student2.removeAllImages();
                        student2.addImage(new BodyImage("data/SkylerUpper2.png", 7));
                    }
                }, 100);

                timer.schedule(new java.util.TimerTask() {
                    @Override
                    public void run() {
                        student2.removeAllImages();
                        student2.addImage(new BodyImage("data/SkylerUpper3.png", 7));
                    }
                }, 200);

                timer.schedule(new java.util.TimerTask() {
                    @Override
                    public void run() {
                        student2.removeAllImages();
                        student2.addImage(new BodyImage("data/SkylerUpper4.png", 7));

                        timer.schedule(new java.util.TimerTask() {
                            @Override
                            public void run() {
                                student2.setisAnimationRunning(false);
                                timer.cancel(); // Clean up the timer
                            }
                        }, 200);

                    }
                }, 300);
            }
        } else if (code == KeyEvent.VK_E) {
            if (!student2.getisAnimationRunning()) {
                student2.setisAnimationRunning(true);
                student2.setPunching(true);
                student2.removeAllImages();
                student2.addImage(new BodyImage("data/SkylerUppercaut1.png", 7));
                punchSound.play();

                java.util.Timer timer = new java.util.Timer();
                timer.schedule(new java.util.TimerTask() {
                    @Override
                    public void run() {
                        student2.removeAllImages();
                        student2.addImage(new BodyImage("data/SkylerUppercaut2.png", 7));
                    }
                }, 100);

                timer.schedule(new java.util.TimerTask() {
                    @Override
                    public void run() {
                        student2.removeAllImages();
                        student2.addImage(new BodyImage("data/SkylerUppercaut3.png", 7));

                        timer.schedule(new java.util.TimerTask() {
                            @Override
                            public void run() {
                                student2.setisAnimationRunning(false);
                                timer.cancel(); // Clean up the timer
                            }
                        }, 200);

                    }
                }, 200);

            }
        }

    }

    // Whenever the key is released after press
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
        } else if (key == KeyEvent.VK_L || key == KeyEvent.VK_K){
            student.setPunching(false);
        } else if (key == KeyEvent.VK_Q || key == KeyEvent.VK_E){
            student2.setPunching(false);
        }
}




}
