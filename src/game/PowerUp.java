package game;

import city.cs.engine.*;

/**
 * The {@code PowerUp} class represents a collectible power-up object in the game.
 * <p>
 * This class extends {@link Walker}
 * The {@code PowerUp} object is designed to be collected by the players, represented by {@link Student} and {@link Student2}.
 * When a player collides with the {@code PowerUp}, it can provide a specific advantage (such as more damage dealt and increased speed),
 * though the specific effects are typically handled by the collision listeners and the {@link StudentController}.
 * </p>
 * <p>
 * Key features of the {@code PowerUp} include:
 * <ul>
 *     <li>The object uses a {@link BoxShape} for collision detection.</li>
 *     <li>An image representing the power-up, loaded from the file "data/PowerUp.png".</li>
 *     <li>The object is destroyed after being collected, triggering any associated effects.</li>
 *     <li>The object will change the look of the player but this is handled in {@link Student} and {@link Student2} </li>
 * </ul>
 * </p>
 * This class is typically used in conjunction with {@link PowerUpCollisionListener} that manages the interaction between
 * the power-up and the players.
 */

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
