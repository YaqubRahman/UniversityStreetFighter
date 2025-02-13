package game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Image;

public class GameView extends UserView {
    private Image background;

    public GameView(World world, int width, int height) {
        super(world, width, height);
        // Load the background image
        try {
            background = new ImageIcon("data/Background.jpg").getImage();
        } catch (Exception e) {
            System.err.println("Error loading background image: " + e.getMessage());
            background = null;
        }
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        // Draw the background image if it loaded successfully
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        // You can add foreground elements here, like score or health
        g.drawString("Player 1: ARROW KEYS to move, SPACE to punch", 10, 20);
        g.drawString("Player 2: WASD to move, E/Q to punch", 10, 40);
    }
}