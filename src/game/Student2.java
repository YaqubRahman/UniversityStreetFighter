package game;

import city.cs.engine.*;
import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;

public class Student2 extends Walker {
    private static final Shape studentShape2 = new PolygonShape ((float) (0.32f *1.7), (float) (0.94f *1.7), (float) (0.47f *1.7), (float) (0.48f *1.7), (float) (0.32f *1.7), (float) (-1.77f *1.7), (float) (-0.76f *1.7), (float) (-1.78f *1.7), (float) (-0.6f *1.7), (float) (0.42f *1.7), (float) (-0.18f *1.7), (float) (0.93f *1.7));
    private static final BodyImage image2 = new BodyImage("data/SkylerDefault1.png", 7f);

    private int health;

    public Student2(World world) {
        super(world, studentShape2);
        addImage(image2);
        health = 0;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


}
