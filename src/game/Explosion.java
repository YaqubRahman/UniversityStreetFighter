package game;

import city.cs.engine.*;
import javax.swing.Timer;
import org.jbox2d.common.Vec2;
import javax.swing.*;

/**
 * Represents an explosion in the game after the {@link LavaRock} has collided, visually depicted with an animated gif.
 * <p>
 * The explosion has a circular shape and is positioned at a specified location in the game world.
 * It automatically destroys itself after a short duration (400 milliseconds) using a timer.
 * </p>
 */
public class Explosion extends Walker{
    private static final Shape explosionShape = new CircleShape(2F);
    private static BodyImage image = new BodyImage("data/Explosion.gif", 7F);

    public Explosion(World world, float x, float y) {
        super(world, explosionShape);
        addImage(image);
        setPosition(new Vec2(x,y));

        new Timer(400, e -> this.destroy()).start();
    }


    @Override
    public void destroy() {
        super.destroy();
    }

}
