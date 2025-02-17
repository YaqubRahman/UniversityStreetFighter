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
    private static final String[] STUDENT_IMAGES3 = {"data/SkylerDefault1.png", "data/SkylerDefault2.png", "data/SkylerDefault4.png"};
    private int currentImageIndex = 0;
    private  int currentImageIndex2 = 0;
    private int currentImageIndex3 = 0;// To track the current image index
    private boolean canJump = true; // Flag to track if the jump is allowed
    private DynamicBody student; // Student body
    private DynamicBody student2;
    private DynamicBody student3;

    private static final String background_music = "data/FightingGameTheme.wav";
    private boolean isAnimationRunning = false;
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

        final float scaleFactor1 = 3.0f;
        float[] originalPoints2 ={0.83f,-1.88f, 0.88f,1.7f, -0.04f,1.78f, -0.36f,1.07f, -0.66f,-0.49f, -0.72f,-1.9f};
        float[] scaledPoints1 = new float[originalPoints2.length];
        for (int i = 0; i < scaledPoints1.length; i++) {
            scaledPoints1[i] = originalPoints2[i] * scaleFactor1;
        }
        //make a character (with an overlaid image)
        Shape studentShape = new PolygonShape(scaledPoints1);


        // Define the constant to scale the points
        final float scaleFactor = 4.0f;
        float[] originalPoints = {0.0f, 1.08f, 0.51f, 0.64f, 0.63f, -1.9f, -0.57f, -1.91f, -0.52f, -0.79f, -0.4f, 0.84f};
        float[] scaledPoints = new float[originalPoints.length];
        for (int i = 0; i < originalPoints.length; i++) {
            scaledPoints[i] = originalPoints[i] * scaleFactor;
        }
        Shape studentShape2 = new PolygonShape(scaledPoints);


        final float scaleFactor3 = 4.0f;
        float[] originalPoints3 = {0.48f,-0.03f, 0.38f,-1.75f, -0.74f,-1.77f, -0.68f,0.46f, -0.06f,0.98f, 0.37f,0.95f};
        float[] scaledPoints3 = new float[originalPoints3.length];
        for (int i = 0; i < originalPoints3.length; i++) {
            scaledPoints3[i] = originalPoints3[i] * scaleFactor3;
        }
        Shape studentShape3 = new PolygonShape(scaledPoints3);

        student = new DynamicBody(world, studentShape);
        student2 = new DynamicBody(world, studentShape2);
        student3 = new DynamicBody(world, studentShape3);
        student.setPosition(new Vec2(-17, -5));
        student2.setPosition(new Vec2(15, -5));
        student3.setPosition(new Vec2(17, -5));

        // Add the initial image to the student
        student.addImage(new BodyImage(STUDENT_IMAGES[currentImageIndex], 7));
        student2.addImage(new BodyImage(STUDENT_IMAGES2[currentImageIndex2], 7));
        student3.addImage(new BodyImage(STUDENT_IMAGES3[currentImageIndex3], 7));

        // Timer to alternate the image every second
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (!isAnimationRunning) {
                    // Remove the current image
                        student.removeAllImages();
                        student2.removeAllImages();
                        student3.removeAllImages();
                    // Switch to the next image
                        currentImageIndex = (currentImageIndex + 1) % STUDENT_IMAGES.length;
                        currentImageIndex2 = (currentImageIndex2 + 1) % STUDENT_IMAGES2.length;
                        currentImageIndex3 = (currentImageIndex3 + 1) % STUDENT_IMAGES3.length;
                    // Add the new image
                        student.addImage(new BodyImage(STUDENT_IMAGES[currentImageIndex], 12));
                        student2.addImage(new BodyImage(STUDENT_IMAGES2[currentImageIndex2], 16));
                        student3.addImage(new BodyImage(STUDENT_IMAGES3[currentImageIndex3], 16));
                    }
                });
            }
        }, 0, 300); // Schedule the task to run every 1000 milliseconds (1 second)

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
                    if (!isAnimationRunning) {
                        isAnimationRunning = true;
                    student3.removeAllImages();
                    student3.addImage(new BodyImage("data/SkylerUpper1.png", 15));
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            SwingUtilities.invokeLater(() -> {
                                student3.removeAllImages();
                                student3.addImage(new BodyImage("data/SkylerUpper2.png", 15));
                            });
                        }
                    }, 100);

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            SwingUtilities.invokeLater(() -> {
                                student3.removeAllImages();
                                student3.addImage(new BodyImage("data/SkylerUpper3.png", 15));
                            });
                        }
                    }, 200);

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            SwingUtilities.invokeLater(() -> {
                                student3.removeAllImages();
                                student3.addImage(new BodyImage("data/SkylerUpper4.png", 15));
                                isAnimationRunning = false;
                            });
                        }
                    }, 300);
                    }
                }

                else if (key == KeyEvent.VK_G) {
                    if (!isAnimationRunning) {
                        isAnimationRunning = true;
                        student3.removeAllImages();
                        student3.addImage(new BodyImage("data/SkylerUppercaut1.png", 15));
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                SwingUtilities.invokeLater(() -> {
                                    student3.removeAllImages();
                                    student3.addImage(new BodyImage("data/SkylerUppercaut2.png", 15));
                                });
                            }
                        }, 100);

                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                SwingUtilities.invokeLater(() -> {
                                    student3.removeAllImages();
                                    student3.addImage(new BodyImage("data/SkylerUppercaut3.png", 15));
                                    isAnimationRunning = false;
                                });
                            }
                        }, 200);
                    }
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
