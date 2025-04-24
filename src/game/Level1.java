package game;

import org.jbox2d.common.Vec2;

public class Level1 extends GameWorld {
    private Game game;
    public Level1(Game game) {
        super(game);
        this.game = game;
        getStudent().setPosition(new Vec2(8, -4f));
        getStudent2().setPosition(new Vec2(-8, -4f));
        System.out.println("It works/loaded - level 1");


    }
    @Override
    public boolean isComplete(){
        if(getStudent().getHealth() <= 0 || getStudent2().getHealth() <= 0){
            return true;}
        else return false;
    }

    @Override
    public String getLevelName() {
        return "Level1";
    }
}
