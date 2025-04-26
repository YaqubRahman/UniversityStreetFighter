package game;

import city.cs.engine.*;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Student extends Walker {
    private static final Shape studentShape1 = new PolygonShape((float) (0.0f*1.7), (float) (1.06f*1.7), (float) (0.46f*1.7), (float) (0.63f*1.7), (float) (0.5f*1.7), (float) (-0.59f*1.7), (float) (0.54f*1.7), (float) (-1.9f*1.7), (float) (-0.56f*1.7), (float) (-1.91f*1.7), (float) (-0.44f*1.7), (float) (0.78f*1.7));

    private static final String[] Student_Images = {"data/Ahmad1.png", "data/Ahmad2.png"};
    private static final String[] Student_Images_Red_Band = {"data/Ahmad1RedBand.png", "data/Ahmad2RedBand.png"};
    private static final String[] Student_Images_Flip = {"data/Ahmad1Flip.png", "data/Ahmad2Flip.png"};
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

    public void startImageAnimationFlip(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (!isAnimationRunning) {
                        removeAllImages();
                        currentImageIndex = (currentImageIndex + 1) % Student_Images_Flip.length;
                        addImage(new BodyImage(Student_Images_Flip[currentImageIndex], 7f));
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
