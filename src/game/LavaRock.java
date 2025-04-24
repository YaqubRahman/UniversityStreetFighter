package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class LavaRock extends Walker {
    private static final Shape lavarockShape = new CircleShape(2F);
    private static BodyImage image = new BodyImage("data/Rockpng.gif", 4.0f);

    public LavaRock(World world){
        super(world, lavarockShape);
        addImage(image);
    }

    @Override
    public void destroy(){
        new Explosion(this.getWorld(), this.getPosition().x, this.getPosition().y);
        super.destroy();
    }

}
