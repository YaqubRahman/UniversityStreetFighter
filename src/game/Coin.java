package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;


public class Coin extends Walker {
    private static final Shape coinShape = new CircleShape(0.7F);
    private static BodyImage image = new BodyImage("data/Coin.gif");

    private boolean isPickedUp;

    public Coin(World world){
        super(world, coinShape);
        addImage(image);
    }
}
