package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import java.awt.*;

/**
 * The abstract base class for game levels, extending {@link World}.
 * <p>
 * This class represents a game world, including the setup of the environment (e.g., ground, walls),
 * player characters, collectibles (e.g., coins, hearts), and level progression logic.
 * It is responsible for managing collisions, spawning coins and hearts at random positions,
 * and detecting when a level is complete. The specific level behavior is determined by subclasses.
 * </p>
 *
 * <p>
 * Subclasses like {@link Level1}, {@link Level2}, and {@link Level3} will define the specifics of
 * each level (e.g., win conditions, unique objects, etc.). The `GameWorld` class provides the common
 * mechanics, including player initialization, collectible management, and level transition.
 * </p>
 */
public abstract class GameWorld extends World {
    private Student student;
    private Student2 student2;
    private Coin coin;
    private Coin coin2;
    private javax.swing.Timer coinTimer;
    private javax.swing.Timer heartTimer;
    private Heart heart;
    private LavaRock lavaRock;
    private GameWorld level;
    private Game game;
    private boolean levelFinished = false;


    public GameWorld(Game game) {
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
        wall1.setFillColor(new Color(0, 0, 0, 0));
        wall1.setLineColor(new Color(0,0,0,0));

        Shape wallleft = new BoxShape(1, 40f);
        StaticBody wall2 = new StaticBody(this, wallleft);
        wall2.setPosition(new Vec2(-18f, -7.5f));
        wall2.setFillColor(new Color(0, 0, 0, 0));
        wall2.setLineColor(new Color(0,0,0,0));



        // Make the character
        //Ahmad (Player1) (Right side)
        student = new Student(this, level, game);
        student.setHealth(student.getHealth() + 100);

        //Skyler (Player2) (Left side)
        student2 = new Student2(this);
        student2.setHealth(student2.getHealth() + 100);

        // Setting up Coin timer so that coins spawn randomly every 3000ms
        coinTimer = new javax.swing.Timer(3000, e -> {
            int randomX = (int)(Math.random() * 33) - 16; // Random X (-16 to 16)
            int randomY = (int)(Math.random() * 15) - 7; // Random Y (-7 to 7)

            coin = new Coin(GameWorld.this);
            coin.setPosition(new Vec2(randomX, randomY));

            coin.addCollisionListener(new CoinCollisionListener(student, student2));

        });
        coinTimer.setInitialDelay(2000);
        coinTimer.start();

        // Setting up Heart timer so that coins spawn randomly every 5000ms
        heartTimer = new javax.swing.Timer(5000, e ->{
            int randomX = (int)(Math.random() * 33) - 16; // Random X (-16 to 16)
            int randomY = (int)(Math.random() * 15) - 7; // Random Y (-7 to 7)

            heart = new Heart(GameWorld.this);
            heart.setPosition(new Vec2(randomX, randomY));

            heart.addCollisionListener(new HeartCollisionListener(student, student2));

        });
        heartTimer.setInitialDelay(10000);
        heartTimer.start();


        coin2 = new Coin(this);
        coin2.setPosition(new Vec2(8, 4f));


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
        student2.addCollisionListener(punchtracker);

        // StepListener to check if level is complete
        this.addStepListener(new StepListener() {
            @Override
            public void preStep(StepEvent e) {
                // No action needed before step
            }

            @Override
            public void postStep(StepEvent e) {
                if (!levelFinished && isComplete()) {
                    levelFinished = true; // Prevents multiple transitions
                    student.addToTotalCoins(student.getCoin());
                    student2.addToTotalCoins(student2.getCoin());
                    game.goToNextLevel();
                }
            }
        });

    }

    public int getStudentTotalCoins(){
        return student.getTotalCoins();
    }
    public int getStudent2TotalCoins(){
        return student2.getTotalCoins();
    }

    public Student getStudent(){
        return student;
    }
    public Student2 getStudent2(){
        return student2;
    }


    public abstract boolean isComplete();





}


