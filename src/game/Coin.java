package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;


public class Coin extends Walker{
    private static final Shape coinShape = new PolygonShape(-0.02f,1.98f, 1.28f,1.67f, 1.78f,0.06f, 1.28f,-1.98f, -1.3f,-1.99f, -1.9f,-0.02f, -1.26f,1.74f);
    private static BodyImage image = new BodyImage("data/Coin.gif");

    private boolean isPickedUp;

    public Coin(World world){
        super(world, coinShape);
        addImage(image);
    }
}
