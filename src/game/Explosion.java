package game;

import city.cs.engine.*;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;
import javax.swing.*;

public class Explosion extends Walker{
    private static final Shape explosionShape = new CircleShape(2F);
    private static BodyImage image = new BodyImage("data/Explosion.gif", 7F);

    public Explosion(World world, float x, float y) {
        super(world, explosionShape);
        addImage(image);
        setPosition(new Vec2(x,y));

        new Timer(1000, e -> this.destroy()).start();
    }


    @Override
    public void destroy() {
        super.destroy();
    }

}
