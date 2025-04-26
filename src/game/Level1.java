package game;

import org.jbox2d.common.Vec2;

public class Level1 extends GameWorld {
    private Game game;
    private Student student;
    private Student2 student2;
    private javax.swing.Timer powerUpTimer;
    private PowerUp powerUp;

    public Level1(Game game) {
        super(game);
        this.game = game;
        this.student = getStudent();
        this.student2 = getStudent2();

        getStudent().setPosition(new Vec2(8, -4f));
        getStudent2().setPosition(new Vec2(-8, -4f));
        System.out.println("It works/loaded - level 1");



        powerUpTimer = new javax.swing.Timer(15000, e ->{

            powerUp = new PowerUp(Level1.this);
            powerUp.setPosition(new Vec2(8, 1));

            powerUp.addCollisionListener(new PowerUpCollisionListener(student, student2));

        });
        powerUpTimer.setInitialDelay(5000);
        powerUpTimer.start();


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
