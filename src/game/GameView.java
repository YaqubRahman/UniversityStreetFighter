package game;

import city.cs.engine.UserView;
import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private GameWorld world;
    private Image background;
    private Image AhmadVSkylerImage;

    public GameView(GameWorld world, int width, int height) {
        super(world, width, height);
        this.world = world;
        background = new ImageIcon("data/Background4.gif").getImage();
        AhmadVSkylerImage = new ImageIcon("data/AhmadVSkylerImage.png").getImage();
    }

    @Override
    protected void paintBackground(Graphics2D g){
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    protected void paintForeground(Graphics2D g){
        int student1Health = world.getStudent().getHealth();
        int student2Health = world.getStudent2().getHealth();
        int student1Coin = world.getStudent().getCoin();
        int student2Coin = world.getStudent2().getCoin();

        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Health: " + student2Health, 150, 30);
        g.drawString("Health: " + student1Health, 450, 30);
        g.drawString("Coins: " + student2Coin, 30, 30);
        g.drawString("Coins: " + student1Coin, 600, 30);
        g.drawImage(AhmadVSkylerImage, 280, 0, 150, 40, this);
    }
}
