package game;

import org.jbox2d.common.Vec2;
import city.cs.engine.*;

public class Level3 extends GameWorld {
    private Student student;
    private Student2 student2;
    private static final String level3_music = "data/Level3GameMusic.wav";


    public Level3(Game game) {
        super(game);
        SoundHandler.playSound(level3_music, true);
        getStudent().setHealth(100);
        getStudent2().setHealth(100);
        getStudent().setPosition(new Vec2(8, -4f));
        getStudent2().setPosition(new Vec2(-8, -4f));
        System.out.println("It works/loaded - level 3");
    }

    @Override
    public boolean isComplete(){
        if(getStudent().getHealth() <= 0 || getStudent2().getHealth() <= 0)
            return true;
        else return false;
    }

    @Override
    public String getLevelName() {
        return "Level3";
    }



}
