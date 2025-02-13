package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Your main game entry point
 */
public class Game {

    // Image paths
    private static final String[] STUDENT_IMAGES = {"data/Talip1.png", "data/Talip2.png"};
    private static final String[] STUDENT_IMAGES2 = {"data/Ahmad1.png", "data/Ahmad2.png"};
    private int currentImageIndex = 0;
    private  int currentImageIndex2 = 0;// To track the current image index
    private boolean canJump = true; // Flag to track if the jump is allowed
    private DynamicBody student; // Student body
    private DynamicBody student2;

    private static final String background_music = "data/FightingGameTheme.wav";

    /** Initialise a new Game. */
    public Game() {
        //1. make an empty game world
        World world = new World();
        SoundHandler.playSound(background_music, true);

        //2. populate it with bodies (ex: platforms, collectibles, characters)
        //make a ground platform
        Shape shape = new BoxShape(30, 0.5f);
        StaticBody ground = new StaticBody(world, shape);
        ground.setPosition(new Vec2(0f, -11.5f));

        // make a suspended platform
        Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(world, platformShape);
        platform1.setPosition(new Vec2(1, -4f));

        //make a character (with an overlaid image)
        Shape studentShape = new BoxShape(2, 5);
        Shape studentShape2 = new BoxShape(2, 7);
        student = new DynamicBody(world, studentShape);
        student2 = new DynamicBody(world, studentShape2);
        student.setPosition(new Vec2(-17, -5));
        student2.setPosition(new Vec2(15, -5));

        // Add the initial image to the student
        student.addImage(new BodyImage(STUDENT_IMAGES[currentImageIndex], 7));
        student2.addImage(new BodyImage(STUDENT_IMAGES2[currentImageIndex2], 7));

        // Timer to alternate the image every second
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    // Remove the current image
                    student.removeAllImages();
                    student2.removeAllImages();
                    // Switch to the next image
                    currentImageIndex = (currentImageIndex + 1) % STUDENT_IMAGES.length;
                    currentImageIndex2 = (currentImageIndex2 + 1) % STUDENT_IMAGES2.length;
                    // Add the new image
                    student.addImage(new BodyImage(STUDENT_IMAGES[currentImageIndex], 12));
                    student2.addImage(new BodyImage(STUDENT_IMAGES2[currentImageIndex2], 16));
                });
            }
        }, 0, 500); // Schedule the task to run every 1000 milliseconds (1 second)

        //3. make a view to look into the game world
        GameView view = new GameView(world, 1000, 500);



        //optional: draw a 1-metre grid over the view
        // view.setGridResolution(1);

        //4. create a Java window (frame) and add the game view to it
        final JFrame frame = new JFrame("City Game");
        frame.add(view);

        // Add key listener for character movement
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT) {
                    student.setLinearVelocity(new Vec2(-10, student.getLinearVelocity().y));
                    student.removeAllImages();
                    student.addImage(new BodyImage("data/Talip2.png", 12));
                } else if (key == KeyEvent.VK_RIGHT) {
                    student.setLinearVelocity(new Vec2(10, student.getLinearVelocity().y));
                } else if (key == KeyEvent.VK_UP) {
                    if (canJump) {
                        student.setLinearVelocity(new Vec2(student.getLinearVelocity().x, 90));
                        student.removeAllImages();
                        student.addImage(new BodyImage("data/TalipJump.png", 8));
                        canJump = false;

                        // Reset the flag after a certain time has passed
                        Timer jumpTimer = new Timer();
                        jumpTimer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                canJump = true;
                            }
                        }, 2000); // Allow jump again after 4 second
                    }
                } else if (key == KeyEvent.VK_DOWN) {
                    student.setLinearVelocity(new Vec2(student.getLinearVelocity().x, -20));
                    student.removeAllImages();
                    student.addImage(new BodyImage("data/TalipJump.png", 8));
                } else if (key == KeyEvent.VK_SPACE) {
                    student.removeAllImages();
                    student.addImage(new BodyImage("data/Talip3.png", 12));
                } else if (key == KeyEvent.VK_W){
                    student2.setLinearVelocity(new Vec2(student2.getLinearVelocity().x, 20));
                    student2.removeAllImages();
                    student2.addImage(new BodyImage("data/AhmadJump.png", 14));
                } else if (key == KeyEvent.VK_D) {
                    student2.setLinearVelocity(new Vec2(10, student2.getLinearVelocity().y));
                } else if (key == KeyEvent.VK_A) {
                    student2.setLinearVelocity(new Vec2(-10, student2.getLinearVelocity().y));
                } else if (key == KeyEvent.VK_S) {
                    student2.setLinearVelocity(new Vec2(student.getLinearVelocity().x, -20));
                    student2.removeAllImages();
                    student2.addImage(new BodyImage("data/AhmadJump.png", 14));
                } else if (key == KeyEvent.VK_E) {
                    student2.removeAllImages();
                    student2.addImage(new BodyImage("data/AhmadPunch1.png", 15));
                } else if (key == KeyEvent.VK_Q) {
                    student2.removeAllImages();
                    student2.addImage(new BodyImage("data/AhmadPunch2.png", 15));
                } else if (key == KeyEvent.VK_F) {
                    student2.removeAllImages();
                    student2.addImage(new BodyImage("data/Ahmad3.png", 15));
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            SwingUtilities.invokeLater(() -> {
                                student2.removeAllImages();
                                student2.addImage(new BodyImage("data/Ahmad4.png", 15));
                            });
                        }
                    }, 500);
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
                    student.setLinearVelocity(new Vec2(0, student.getLinearVelocity().y));
                } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
                    student.setLinearVelocity(new Vec2(student.getLinearVelocity().x, 0));
                } else if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
                    student2.setLinearVelocity(new Vec2(student2.getLinearVelocity().x, 0));
                } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_D ) {
                    student2.setLinearVelocity(new Vec2(0, student2.getLinearVelocity().y));
                }
            }
        });

        // enable the frame to quit the application when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        //optional: uncomment this to make a debugging view
        JFrame debugView = new DebugViewer(world, 500, 500);

        // start our game world simulation!
        world.start();
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }
}
