package game;

import org.jbox2d.common.Vec2;

public class Level2 extends GameWorld {
    public Level2(Game game) {
        super(game);
        getStudent().setHealth(10);
        getStudent2().setHealth(10);
        getStudent().setPosition(new Vec2(8, -4f));
        getStudent2().setPosition(new Vec2(-8, -4f));
        System.out.println("It works/loaded - level 2");

    }


    @Override
    public boolean isComplete(){
        if(getStudent().getHealth() == 0 || getStudent2().getHealth() == 0)
            return true;
        else return false;
    }

    @Override
    public String getLevelName() {
        return "Level2";
    }
}
