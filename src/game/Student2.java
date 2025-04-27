package game;

import city.cs.engine.*;
import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The {@code Student2} class represents another player-controlled character in the game. It extends
 * the {@link Walker} class, enabling it to interact with the world and move around. The character has
 * properties such as health, coins, and abilities that affect the gameplay. This class also handles the
 * animation of the student character based on their state, such as having red ears or not, and whether
 * the student is punching or not.
 *
 * <p>
 * The {@code Student2} character has a default animation cycle and a special red ears animation that
 * alters the character’s appearance. It also includes functionality to track health, coin collection,
 * and punching actions.
 * </p>
 *
 * <p>
 * Key methods:
 * <ul>
 *     <li>{@link #startImageAnimation()}: Starts an animation cycle for the student's default or red ears appearance.</li>
 *     <li>{@link #getHealth()}: Returns the student's current health.</li>
 *     <li>{@link #setHealth(int)}: Sets the student's health to a specific value.</li>
 *     <li>{@link #getCoin()}: Returns the number of coins the student currently has.</li>
 *     <li>{@link #setCoin(int)}: Sets the number of coins the student has.</li>
 *     <li>{@link #getTotalCoins()}: Returns the total number of coins collected by the student.</li>
 *     <li>{@link #addToTotalCoins(int)}: Adds a specified number of coins to the student's total.</li>
 *     <li>{@link #getPunching()}: Returns whether the student is currently punching.</li>
 *     <li>{@link #setPunching(Boolean)}: Sets whether the student is currently punching.</li>
 *     <li>{@link #setRedEars()}: Activates the student's red ears, changing the appearance and abilities.</li>
 *     <li>{@link #getRedEars()}: Returns whether the student has the red ears active.</li>
 *     <li>{@link #getisAnimationRunning()}: Returns whether the student's animation is currently running.</li>
 *     <li>{@link #setisAnimationRunning(Boolean)}: Sets whether the animation for the student is running.</li>
 * </ul>
 * </p>
 */

public class Student2 extends Walker {
    private static final Shape studentShape2 = new PolygonShape ((float) (0.32f *1.7), (float) (0.94f *1.7), (float) (0.47f *1.7), (float) (0.48f *1.7), (float) (0.32f *1.7), (float) (-1.77f *1.7), (float) (-0.76f *1.7), (float) (-1.78f *1.7), (float) (-0.6f *1.7), (float) (0.42f *1.7), (float) (-0.18f *1.7), (float) (0.93f *1.7));

    private static final String[] Student2_Images = {"data/SkylerDefault1.png", "data/SkylerDefault2.png", "data/SkylerDefault4.png"};
    private static final String[] Student2_ImagesRedEars = {"data/SkylerDefault1RedEars.png", "data/SkylerDefault2RedEars.png", "data/SkylerDefault4RedEars.png"};
    private int currentImageIndex = 0;

    private static final BodyImage image2 = new BodyImage("data/SkylerDefault1.png", 7f);

    private int health;
    private int coin;
    private int totalCoins;
    public boolean isAnimationRunning = false;
    private boolean redEars = false;
    public boolean isPunching = false;

    public Student2(World world) {
        super(world, studentShape2);
        addImage(image2);
        health = 0;
        coin = 0;
        isAnimationRunning = false;

        startImageAnimation();
    }

    private void startImageAnimation(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (!isAnimationRunning) {
                        if(redEars){
                            removeAllImages();
                            currentImageIndex = (currentImageIndex + 1) % Student2_ImagesRedEars.length;
                            addImage(new BodyImage(Student2_ImagesRedEars[currentImageIndex], 7f));

                        } else {
                            removeAllImages();
                            currentImageIndex = (currentImageIndex + 1) % Student2_Images.length;
                            addImage(new BodyImage(Student2_Images[currentImageIndex], 7f));
                        }
                    }
                });
            }
        }, 0, 200);
    }

    public Boolean getisAnimationRunning() {
        return isAnimationRunning;
    }

    public void setisAnimationRunning(Boolean isAnimationRunning) {
        this.isAnimationRunning = isAnimationRunning;
        if (isAnimationRunning) {
            currentImageIndex = 0;
        } else{
            startImageAnimation();
        }
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

    public void setRedEars(){
        redEars = true;
    }

    public Boolean getRedEars(){
        return redEars;
    }


}
