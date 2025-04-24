package game;

import city.cs.engine.World;
import city.cs.engine.UserView;
import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private GameWorld world;
    private Image background;
    private Image background2;
    private Image background5;
    private Image AhmadVSkylerImage;
    private ClockTimer clockTimer;
    private GameWorld level;

    public GameView(GameWorld world, int width, int height, Game game) {
        super(world, width, height);
        this.level = world;
        background = new ImageIcon("data/Background2.gif").getImage();
        background2 = new ImageIcon("data/Background7.gif").getImage();
        background5 = new ImageIcon("data/Background5.gif").getImage();
        AhmadVSkylerImage = new ImageIcon("data/AhmadVSkylerImage.png").getImage();
        clockTimer = new ClockTimer(world, game);
        this.add(clockTimer.getTimerLabel());
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        if (level instanceof Level1) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
        else if (level instanceof Level2) {
            g.drawImage(background2, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    protected void paintForeground(Graphics2D g){
        int student1Health = level.getStudent().getHealth();
        int student2Health = level.getStudent2().getHealth();
        int student1Coin = level.getStudent().getCoin();
        int student2Coin = level.getStudent2().getCoin();

        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Health: " + student2Health, 200, 50);
        g.drawString("Health: " + student1Health, 420, 50);
        g.drawString("Coins: " + student2Coin, 30, 30);
        g.drawString("Coins: " + student1Coin, 590, 30);
        g.drawImage(AhmadVSkylerImage, 280, 0, 150, 40, this);

        int barWidth = 150;
        int barHeight = 20;
        int filledWidthStudent1 = (int) (((float)student1Health/ 100) * barWidth);
        int filledWidthStudent2 = (int) (((float)student2Health/ 100) * barWidth);

        g.setColor(Color.RED);
        g.fillRect(410, 10, barWidth, barHeight);
        g.setColor(Color.GREEN);
        g.fillRect(410,10, filledWidthStudent1, barHeight);
        g.setColor(Color.WHITE);
        g.drawRect(410, 10, barWidth, barHeight);

        g.setColor(Color.RED);
        g.fillRect(150, 10, barWidth, barHeight);
        g.setColor(Color.GREEN);
        g.fillRect(150 + barWidth - filledWidthStudent2,10, filledWidthStudent2, barHeight);
        g.setColor(Color.WHITE);
        g.drawRect(150, 10, barWidth, barHeight);



    }


    @Override
    public void setWorld(World w){
        super.setWorld(w);
        if (w instanceof GameWorld){
            this.level = (GameWorld) w;
        }
    }
}
