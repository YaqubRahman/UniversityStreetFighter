package game;

import city.cs.engine.UserView;
import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private GameWorld world;
    private Image background;
    private Image background2;
    private Image AhmadVSkylerImage;
    private ClockTimer clockTimer;

    private GameWorld level;


    public GameView(GameWorld world, int width, int height) {
        super(world, width, height);
        this.level = world;
        background = new ImageIcon("data/Background4.gif").getImage();
        background2 = new ImageIcon("data/Background3.gif").getImage();
        AhmadVSkylerImage = new ImageIcon("data/AhmadVSkylerImage.png").getImage();
        clockTimer = new ClockTimer(world);
        this.add(clockTimer.getTimerLabel());

    }



    @Override
    protected void paintBackground(Graphics2D g){
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    protected void paintForeground(Graphics2D g){
        int student1Health = level.getStudent().getHealth();
        int student2Health = level.getStudent2().getHealth();
        int student1Coin = level.getStudent().getCoin();
        int student2Coin = level.getStudent2().getCoin();

        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Health: " + student2Health, 150, 30);
        g.drawString("Health: " + student1Health, 450, 30);
        g.drawString("Coins: " + student2Coin, 30, 30);
        g.drawString("Coins: " + student1Coin, 590, 30);
        g.drawImage(AhmadVSkylerImage, 280, 0, 150, 40, this);
    }
}
