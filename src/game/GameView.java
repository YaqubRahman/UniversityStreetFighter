package game;
import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private Image background;

    public GameView(GameWorld world, int width, int height) {
        super(world, width, height);
        background = new ImageIcon("data/Background.jpg").getImage();
    }

    @Override
    protected void paintBackground(Graphics2D g){
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
