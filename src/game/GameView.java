package game;
import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private final int Student2health;
    private final int Student1health;
    private Image background;

    public GameView(GameWorld world, int width, int height) {
        super(world, width, height);
        background = new ImageIcon("data/Background4.gif").getImage();
        
        this.Student2health = world.getStudent2().getHealth();
        this.Student1health = world.getStudent().getHealth();
    }

    @Override
    protected void paintBackground(Graphics2D g){
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
    
    @Override
    protected void paintForeground(Graphics2D g){
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Health: " + this.Student2health, 50, 50);
        g.drawString("Health: " + this.Student1health, 550, 50);
    }
}
