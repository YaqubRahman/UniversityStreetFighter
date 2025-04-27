package game;

import city.cs.engine.*;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The {@code Student} class represents a player-controlled character in the game. It extends the
 * {@link Walker} class, allowing it to move and interact with the world. The student character has
 * properties like health, coins, and special abilities, and can perform actions such as punching
 * and activating a red band for enhanced abilities.
 *
 * <p>
 * This class manages the student's appearance through animations, which change based on the
 * character's state (normal or red band). It also tracks the student's health, coins,
 * and interaction with other game elements.
 * </p>
 *
 * <p>
 * Key methods:
 * <ul>
 *     <li>{@link #startImageAnimation()}: Starts an animation cycle for the student's normal appearance.</li>
 *     <li>{@link #getHealth()}: Returns the student's current health.</li>
 *     <li>{@link #setHealth(int)}: Sets the student's health to a specific value.</li>
 *     <li>{@link #getCoin()}: Returns the number of coins the student currently has.</li>
 *     <li>{@link #setCoin(int)}: Sets the number of coins the student has.</li>
 *     <li>{@link #getTotalCoins()}: Returns the total number of coins collected by the student.</li>
 *     <li>{@link #addToTotalCoins(int)}: Adds a specified number of coins to the student's total.</li>
 *     <li>{@link #getPunching()}: Returns whether the student is currently punching.</li>
 *     <li>{@link #setPunching(Boolean)}: Sets whether the student is currently punching.</li>
 *     <li>{@link #setRedBand()}: Activates the student's red band, changing the appearance and abilities.</li>
 *     <li>{@link #getRedBand()}: Returns whether the student has the red band active.</li>
 * </ul>
 * </p>
 */

public class Student extends Walker {
    private static final Shape studentShape1 = new PolygonShape((float) (0.0f*1.7), (float) (1.06f*1.7), (float) (0.46f*1.7), (float) (0.63f*1.7), (float) (0.5f*1.7), (float) (-0.59f*1.7), (float) (0.54f*1.7), (float) (-1.9f*1.7), (float) (-0.56f*1.7), (float) (-1.91f*1.7), (float) (-0.44f*1.7), (float) (0.78f*1.7));

    private static final String[] Student_Images = {"data/Ahmad1.png", "data/Ahmad2.png"};
    private static final String[] Student_Images_Red_Band = {"data/Ahmad1RedBand.png", "data/Ahmad2RedBand.png"};
    private int currentImageIndex = 0;

    private static final BodyImage image1 = new BodyImage("data/Ahmad1.png", 7f);

    private int health;
    private int coin;
    private int totalCoins;
    private boolean isAnimationRunning = false;
    private boolean redBand = false;
    private boolean isPunching;

    private GameWorld level;
    private Game game;


    public Student(World world, GameWorld level, Game game) {
        super(world, studentShape1);
        addImage(image1);
        health = 0;
        coin = 0;
        this.level = level;
        this.game = game;

        startImageAnimation();
    }

    public void startImageAnimation(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (!isAnimationRunning) {
                        if(redBand){
                            removeAllImages();
                            currentImageIndex = (currentImageIndex + 1) % Student_Images_Red_Band.length;
                            addImage(new BodyImage(Student_Images_Red_Band[currentImageIndex], 7f));
                        }
                        else {
                            removeAllImages();
                            currentImageIndex = (currentImageIndex + 1) % Student_Images.length;
                            addImage(new BodyImage(Student_Images[currentImageIndex], 7f));
                        }
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

    public int getCoin() {
        return coin;
    }
    public void setCoin(int coin) {
        this.coin = coin;
    }
    public void addToTotalCoins(int amount) {
        totalCoins += amount;
    }
    public int getTotalCoins(){
        return totalCoins;
    }

    public Boolean getPunching(){
        return isPunching;
    }

    public void setPunching(Boolean isPunching){
        this.isPunching = isPunching;
    }

    public void setRedBand(){
        redBand = true;
    }

    public Boolean getRedBand(){
        return redBand;
    }


}
