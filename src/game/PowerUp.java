package game;

import city.cs.engine.*;

public class PowerUp extends Walker{
    private static final Shape powerUpShape = new BoxShape(0.5F,0.5F );
    private static BodyImage image = new BodyImage("data/PowerUp.png");

    public PowerUp(World world) {
        super(world, powerUpShape);
        addImage(image);
    }

    @Override
    public void destroy(){
        super.destroy();
    }



}
